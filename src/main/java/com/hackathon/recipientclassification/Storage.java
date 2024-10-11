package com.hackathon.recipientclassification;

import com.hackathon.recipientclassification.jobs.DataReadingJob;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Storage{

    private static final List<ProfileToCategory> storage = new ArrayList<>();
    private static final List<Profile> profilesStorage = new ArrayList<>();

    public List<String> getAllCategories() {
        return storage.stream().map(p -> p.category).distinct().collect(Collectors.toList());
    }

    public void add(DataReadingJob.SendMailingRecord sendMailingRecord, String category) {
        var existingProfile = storage.stream()
                .filter(p -> p.category.equals(category) && p.profile.equals(sendMailingRecord.getEmail()))
                .findFirst();

        if (existingProfile.isPresent()) {
            var profile = existingProfile.get();
            profile.setSentCount(profile.sentCount + 1);
            if (sendMailingRecord.isWasOpened()) {
                profile.setOpenedCount(profile.openedCount + 1);
            }
        } else {
            var profile = new ProfileToCategory();
            profile.setSentCount(1);
            profile.setProfile(sendMailingRecord.getEmail());
            profile.setCategory(category);

            if (sendMailingRecord.isWasOpened()) {
                profile.setOpenedCount(1);
            }

            storage.add(profile);
        }

        var existingOverallStatProfile = profilesStorage.stream()
                .filter(p -> p.recipient.equals(sendMailingRecord.getEmail()))
                .findFirst();

        if (existingOverallStatProfile.isPresent()) {
            var profile = existingOverallStatProfile.get();
            profile.setRecipient(sendMailingRecord.getEmail());
            profile.setSent(profile.sent + 1);

            if (sendMailingRecord.isWasOpened()) {
                profile.setOpened(profile.opened + 1);
            }
        } else {
            var profile = new Profile();
            profile.setRecipient(sendMailingRecord.getEmail());
            profile.setSent(1);

            if (sendMailingRecord.isWasOpened()) {
                profile.setOpened(1);
            }
            profilesStorage.add(profile);
        }
    }

    public Rate getRate(String categoryName, String email) {
        var profileForCategory = storage.stream()
                .filter(p -> p.category.equals(categoryName) && p.profile.equals(email))
                .findFirst()
                .orElse(null);
        var existingOverallStatProfile = profilesStorage.stream()
                .filter(p -> p.recipient.equals(email))
                .findFirst()
                .orElse(null);

        var result = new Rate();
        if (existingOverallStatProfile == null) {
            return null;
        }

        result.setCommonRate((double) existingOverallStatProfile.opened / (double) existingOverallStatProfile.sent);

        if (profileForCategory == null) {
            result.setCategoryRate(0);
        } else {
            result.setCategoryRate((double) ((double)profileForCategory.getOpenedCount()) / (double)profileForCategory.getSentCount());
        }

        return result;
    }

    public FullRate getFullRate(String email) {
        var categories = storage.stream()
                .filter(p -> p.profile.equals(email))
                .map(p -> new CategoryRate(p.category, (double) p.openedCount / (double) p.sentCount))
                .collect(Collectors.toList());
        var existingOverallStatProfile = profilesStorage.stream()
                .filter(p -> p.recipient.equals(email))
                .findFirst()
                .orElse(null);
        if (existingOverallStatProfile == null || categories.isEmpty()) {
            return null;
        }

        var result = new FullRate();
        result.setFullRate((double) existingOverallStatProfile.opened / (double) existingOverallStatProfile.sent);
        result.setRates(categories);
        return result;
    }

    public class Rate {
        private double categoryRate;
        private double commonRate;

        public double getCategoryRate() {
            return categoryRate;
        }

        public void setCategoryRate(double categoryRate) {
            this.categoryRate = categoryRate;
        }

        public double getCommonRate() {
            return commonRate;
        }

        public void setCommonRate(double commonRate) {
            this.commonRate = commonRate;
        }
    }

    public class FullRate {
        private double fullRate;

        private List<CategoryRate> rates;

        public double getFullRate() {
            return fullRate;
        }

        public void setFullRate(double fullRate) {
            this.fullRate = fullRate;
        }

        public List<CategoryRate> getRates() {
            return rates;
        }

        public void setRates(List<CategoryRate> rates) {
            this.rates = rates;
        }
    }

    public class CategoryRate {
        private String category;
        private double rate;

        public CategoryRate(String category, double rate) {
            this.category = category;
            this.rate = rate;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }

    public class ProfileToCategory {
        private String profile;
        private String category;

        private int sentCount;
        private int openedCount;

        public int getSentCount() {
            return sentCount;
        }

        public void setSentCount(int sentCount) {
            this.sentCount = sentCount;
        }

        public int getOpenedCount() {
            return openedCount;
        }

        public void setOpenedCount(int openedCount) {
            this.openedCount = openedCount;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    public class Profile {
        private String recipient;
        private int sent;
        private int opened;

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public int getSent() {
            return sent;
        }

        public void setSent(int sent) {
            this.sent = sent;
        }

        public int getOpened() {
            return opened;
        }

        public void setOpened(int opened) {
            this.opened = opened;
        }
    }
}
