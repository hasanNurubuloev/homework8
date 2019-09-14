package com.company.players;

import java.util.Random;

public class Medic extends Hero {
    private int curePoints;

    public int getCurePoints() {
        return curePoints;
    }

    public void setCurePoints() {
        this.curePoints = new Random().nextInt(20)+10;
    }

    public Medic(int health, int damage) {
        super(health, damage, Ability.HEAL);
    }

    @Override
    public void useAbility(Hero[] heroes, Boss boss) {
        for (int i = 0; i <5 ; i++) {
            if (heroes[i].getHealth()>0 ) {
                setCurePoints();
                heroes[i].setHealth(heroes[i].getHealth() + getCurePoints());
                System.out.println("Medic cured    " + heroes[i].getClass().getSimpleName() + " " + getCurePoints());
            }
        }
    }
}
