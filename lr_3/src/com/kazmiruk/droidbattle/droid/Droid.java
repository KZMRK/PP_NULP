package com.kazmiruk.droidbattle.droid;

import com.kazmiruk.droidbattle.interfeces.Colors;

import java.util.Random;

public abstract class Droid implements Colors {
    private final String name;
    private int health;
    private final int damage;
    private int energy;
    /** Whether a special feature was used */
    protected boolean specialFeatureUse = false;

    public Droid(String name, int health, int damage, int energy) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.energy = energy;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getDamage() { return damage; }

    public int getEnergy() { return energy; }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-5d %-5d %-5d",
                this.getName(),
                this.getHealth(),
                this.getEnergy(),
                this.getDamage()
        );
    }

    public abstract void useSpecialFeature(Droid defender);

    /**
     * Takes an attack from an opposing droid
     * @param damage Maximum damage
     *
     * @return The damage that was made
     */
    public int getHit(int damage) {
        if (energy <= 0) {
            return 0;
        }

        Random random = new Random();
        int actualDamage = random.nextInt((int)(damage * 0.75), damage);

        this.health -= actualDamage;
        if (health < 0) {
            health = 0;
        }
        energy--;
        return actualDamage;
    }

    /**
     * Determines if the droid is alive
     * @return Is droid alive
     */
    public boolean isAlive() { return health > 0; }

    /**
     * Determines if the droid has used
     * a special feature
     *
     * @return whether a special feature was used
     */
    public boolean hasUseSpecialFeature() {
        return specialFeatureUse;
    }

}
