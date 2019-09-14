package com.company.players;

import java.util.Random;

//Добавить еще игрока, Thor, имеет шанс оглушить босса на раунд, вследствие чего босс пропускает раунд и не бьёт
public class Thor extends Hero {
    public Thor(int health, int damage, Ability ability) {
        super(health, damage, Ability.STUNNED );
    }

    public Thor(int health, int damage) {
        super(health, damage, Ability.STUNNED);
    }


    @Override
    public void useAbility(Hero[] heroes, Boss boss) {
        Random r = new Random (1) ;
        int a = r.nextInt(2) ;
        if (a == 1) {
            System.out.println("Thor stunned the boss");
            boss.setDamage(0);

        }

    }
}
