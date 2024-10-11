import com.hackathon.recipientclassification.categorizator.Categorizator;
import com.hackathon.recipientclassification.categorizator.ChatGptAdapter;
import com.hackathon.recipientclassification.categorizator.ChatGptAnswerHandler;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TestClass {
    @Test
    public void test() {
        ChatGptAnswerHandler chatGptAnswerHandler = new ChatGptAnswerHandler();
        ChatGptAdapter chatGptAdapter = new ChatGptAdapter();
        Categorizator categorizator = new Categorizator(chatGptAdapter, chatGptAnswerHandler);

        ArrayList<String> mailings = new ArrayList<>();
        mailings.add("Time to put your feet up!");
        mailings.add("Time to order something new!");
        mailings.add("Here's a special discount ");
        mailings.add("Tasty Summer Sale at Wolt");
        mailings.add("Purchase receipt");
        mailings.add("You just earned €7.00 in Wolt credits");
        mailings.add("סידרנו לך הטבה סופר שווה | פרסומת");
        mailings.add("קיבלת הטבה מחנויות הפארם | פרסומת");

        Map<String, Set<String>> cs = categorizator.getCategories(mailings, Collections.emptyList());
        List<String> themes = new ArrayList<>(cs.keySet());

        String caregory = categorizator.getCategory("It's time to new order", themes);

        System.out.println(caregory);
    }
}
