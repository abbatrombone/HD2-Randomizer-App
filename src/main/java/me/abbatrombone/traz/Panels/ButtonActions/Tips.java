package me.abbatrombone.traz.Panels.ButtonActions;

import java.util.ArrayList;
import java.util.Random;

public class Tips {
    public static String tips(){
        Random random = new Random();
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("When things seem bleak let Democracy guide you!");
        phrases.add("Liberty isn’t a gift; it’s the reward of courage. So don't waste it Helldiver");
        phrases.add("Freedom isn't free, now pay the toll for democracy!");
        phrases.add("Every mission is a chance to make history. Now go make this one into the books");
        phrases.add("True liberty means standing together, no matter the cost");
        phrases.add("Friendly fire isn't");
        phrases.add("When Liberty resummons you learn from your death");
        phrases.add("Examine Liberties tools, and use the ones it gave you correctly");
        //phrases.add("Death does not defeat Liberty, only autocracy does");

        int phraseNumber = random.nextInt(phrases.size());

        return phrases.get(phraseNumber);
    }
}
