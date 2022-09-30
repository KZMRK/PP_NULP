package com.kazmiruk.droidbattle.droid;

import com.kazmiruk.droidbattle.interfeces.DroidsConstants;

import java.util.Random;

/**
 * <p>
 * Viy is a character of Ukrainian demonology, who has long and thick
 * eyelashes (eyelids), through which he cannot see anything. When the evil
 * force raises its eyelashes, its gaze is murderous and destructive. Most fully
 * described and popularized by Mykola Gogol in the novel of the same name.
 * </p>
 * <p>
 * Viy droid can destroy his opponent with laser gaze.
 * </p>
 * <a href="https://uk.wikipedia.org/wiki/%D0%92%D1%96%D0%B9">More about Viy</a>
 */
public class ViyDroid extends Droid implements DroidsConstants {
    private static int droidNo = 1;

    public ViyDroid() {
        super("Viy-" + droidNo, VIY_HEALTH, VIY_DAMAGE, VIY_ENERGY);
        droidNo++;
    }

    /**
     * Droid Vii's special feature is his killer laser gaze.
     * The droid can take from 40% to 75% of the opponent's
     * health
     *
     * @param defender Opponent droid
     */
    @Override
    public void useSpecialFeature(Droid defender) {
        int specialDamage = new Random().nextInt((int)(0.4 * defender.getHealth()), (int)(0.75 * defender.getHealth()));
        defender.setHealth(defender.getHealth() - specialDamage);
        this.setEnergy(this.getEnergy() / 2);

        System.out.println(this.getName() + " inflicted damage " + specialDamage + " to "
                + defender.getName() + " with his SUPER GAZE");
        specialFeatureUse = true;
    }
}
