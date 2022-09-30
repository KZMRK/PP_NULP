package com.kazmiruk.droidbattle.droid;

import com.kazmiruk.droidbattle.interfeces.DroidsConstants;

import java.util.Random;

/**
 * <p>
 * Mavka - a creature of Ukrainian mythology, close to a mermaid,
 * in the form of a beautiful naked girl with long flowing hair.
 * According to folk beliefs, the souls of drowned women and girls
 * who died without baptism turn into mavok.
 * </p>
 * <a href="https://uk.wikipedia.org/wiki/%D0%9C%D0%B0%D0%B2%D0%BA%D0%B0">More about Mavka</a>
 */
public class MavkaDroid extends Droid implements DroidsConstants {
    private static int droidNo = 1;

    public MavkaDroid() {
        super("Mavka-" + droidNo, MAVKA_HEALTH, MAVKA_DAMAGE, MAVKA_ENERGY);
        droidNo++;
    }

    /**
     * A special feature of the droid Mavka is to fall in
     * love with another droid, who will give her his energy
     * and health
     *
     * @param defender Droid opponent
     */
    @Override
    public void useSpecialFeature(Droid defender) {
        Random random = new Random();
        int stolenEnergy = random.nextInt((int)(0.5 *defender.getEnergy()), (int)(0.75 * defender.getEnergy()));
        int stolenHealth = random.nextInt((int)(0.1 * defender.getHealth()), (int)(0.20 * defender.getHealth()));

        this.setEnergy(this.getEnergy() + stolenEnergy);
        this.setHealth(this.getHealth() + stolenHealth);
        defender.setEnergy(defender.getEnergy() - stolenEnergy);
        defender.setHealth(defender.getHealth() - stolenHealth);

        System.out.println(
                defender.getName() + " fell in love with " +
                        this.getName() + " and gave her " +
                        stolenEnergy + " energy and "+
                        stolenHealth + " health"

        );
        specialFeatureUse = true;
    }


}
