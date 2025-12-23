package me.abbatrombone.traz.Panels.ButtonActions;

import java.util.ArrayList;
import java.util.Random;

public class Challenges {

    public static String challenges(){
        Random random = new Random();
        ArrayList<String> challanges = new ArrayList<>();
        challanges.add("Only use your secondary, don't disappoint General Brash in front of the other 10 star generals");
        challanges.add("Complete this deathless helldiver");
        challanges.add("Use this build for the whole operation. The ministry of science demands so!");
        challanges.add("Complete all secondary objectives before doing the primary mission");
        challanges.add("The resupply is banned, due to poor performance on your end helldiver");
        challanges.add("You cannot use your iron sights or scopes");
        challanges.add("This is now a solo dive");
        challanges.add("We are cutting off your stim addition. You cannot use stims.");
        challanges.add("Collect at least the half the total samples");
        challanges.add("Do the mission in half the operation time");
        challanges.add("Can only use blue strategems once");

        int challangesNumber = random.nextInt(challanges.size());

        return challanges.get(challangesNumber);
    }
}
