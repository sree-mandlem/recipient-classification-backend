package com.hackathon.recipientclassification.jobs;

import com.hackathon.recipientclassification.Storage;
import com.hackathon.recipientclassification.categorizator.ICategorizator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class DataReadingJob {

    private static final Logger LOG = Logger.getLogger(DataReadingJob.class.getName());

    private final Storage storage;
    private final ICategorizator categorizator;
    private boolean readed = false;

    public DataReadingJob(Storage storage, ICategorizator categorizator) {

        this.storage = storage;
        this.categorizator = categorizator;
    }

    private SendMailingRecord parseRecord(String str) {
        var parts = str.split(";");
        return new SendMailingRecord(parts[1], parts[2], Long.parseLong(parts[0]), parts[3].equals("opened"));
    }


    @Scheduled(fixedDelay = 30000)
    public void run() {
        if (readed) {
            return;
        }

        LOG.info("Starting data reading");
        String filePath = "sendMailings";

        try {
            Path path = Paths.get(filePath);
            List<SendMailingRecord> lines = Files.readAllLines(path).stream().map(this::parseRecord).toList();
            List<String> mailingThemes = lines.stream().map(t -> t.mailingName).toList();
            var categories = categorizator.getCategories(mailingThemes, storage.getAllCategories());
            for (var line: lines) {
                String category = categories.entrySet().stream().filter(c -> c.getValue().contains(line.mailingName))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

                if (category == null) {
                    continue;
                }

                storage.add(line, category);
            }
            LOG.info("Data reading finished, suggested categories: " + storage.getAllCategories());
            readed = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class SendMailingRecord {
        private String email;
        private String mailingName;
        private long id;
        private boolean wasOpened;

        public SendMailingRecord(String email, String mailingName, long id, boolean wasOpened) {
            this.email = email;
            this.mailingName = mailingName;
            this.id = id;
            this.wasOpened = wasOpened;
        }

        public boolean isWasOpened() {
            return wasOpened;
        }

        public void setWasOpened(boolean wasOpened) {
            this.wasOpened = wasOpened;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getMailingName() {
            return mailingName;
        }

        public void setMailingName(String mailingName) {
            this.mailingName = mailingName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
