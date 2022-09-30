package com.kazmiruk.droidbattle.droid;

import com.kazmiruk.droidbattle.interfeces.DroidsConstants;

/**
 * <p>
 * Chort is a character of Slavic mythology, an evil spirit or
 * the personification of a generalized evil force.
 * Chort knew how to transform into other creatures,
 * so the droid's special function is to exchange health with its opponent
 * </p>
 * <a href="https://uk.wikipedia.org/wiki/%D0%A7%D0%BE%D1%80%D1%82">More about Chort</a>
 */
public class ChortDroid extends Droid implements DroidsConstants {
    private static int droidNo = 1;

    public ChortDroid() {
        super("Chort-" + droidNo, CHORT_HEALTH, CHORT_DAMAGE, CHORT_ENERGY);
        droidNo++;
    }

    /**
     * If the value of the opponent's health is greater than the
     * chort's health, then he exchanges health with the opponent
     *
     * @param defender Opponent droid
     */
    @Override
    public void useSpecialFeature(Droid defender) {
        System.out.println(this.getName() + " turned into a " + defender.getName());
        if (defender.getHealth() > this.getHealth()) {
            int tmp = this.getHealth();
            this.setHealth(defender.getHealth());
            defender.setHealth(tmp);
            System.out.println(this.getName() + " new health is " + this.getHealth());
            System.out.println(defender.getName() + " new health is " + defender.getHealth());
        }
        specialFeatureUse = true;
    }
}
