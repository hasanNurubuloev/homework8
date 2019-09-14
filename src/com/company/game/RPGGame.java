package com.company.game;
//Добавить в проект каждому классу героя свою уникальную способность
//Tank должен получать от босса урон, и потом наносить ему свой урон + полученный от босса
//Buffer должен увеличивать атаку каждого героя после каждого раунда на n-ное количество
//Warrior каждый раз при атаке критует. Т.е. каждая атака умножается на случайное число от 2 до 4.
//Нельзя вносить изменения в класс логики
//
//Задание на сообразительность
//Добавить еще игрока, Thor, имеет шанс оглушить босса на раунд, вследствие чего босс пропускает раунд и не бьёт
//Добавить еще игрока, Golem, имеет увеличенную жизнь но слабый удар. Может принимать часть удара босса на других игроков себе

import com.company.players.*;

public class RPGGame {
    public static void startGame() {
        Boss boss = new Boss(900, 30);
        Hero[] heroes = getHeroess();
        printStatistics(heroes, boss);
        while (!isFinished(heroes, boss)) {
            round(heroes, boss);
        }
    }

    private static void round(Hero[] heroes, Boss boss) {
        bossHit(heroes, boss);
        heroesHit(heroes, boss);
        printStatistics(heroes, boss);
        applyAbbility(heroes, boss);
        printStatistics(heroes, boss);
    }

    private static void applyAbbility(Hero[] heroes, Boss boss) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                heroes[i].useAbility(heroes, boss);
            }
        }
    }

    private static void bossHit(Hero[] heroes, Boss boss) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() <= boss.getDamage()) {
                heroes[i].setHealth(0);
            } else {
                heroes[i].setHealth(heroes[i].getHealth() - boss.getDamage());

            }
        }
    }

    private static void heroesHit(Hero[] heroes, Boss boss) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getAbility() != Ability.HEAL && heroes[i].getAbility() != Ability.SAVE_DAMAGE_AND_REVERT) {


                boss.setHealth(boss.getHealth() - heroes[i].getDamage());
            }
        }


    }


    private static boolean isFinished(Hero[] heroes, Boss boss) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                allHeroesDead = false;
                break;
            }

        }
        if (allHeroesDead) {
            System.out.println("Boss won!");
        }
        return allHeroesDead;
    }

    private static Hero[] getHeroess() {
        Warrior warrior = new Warrior(100, 10);
        Buffer buffer = new Buffer(100, 10);
        Tank tank = new Tank(100, 10);
        Thor thor = new Thor(100, 10);
        Golem golem = new Golem(200, 5);
        Medic medic = new Medic(50, 10);
        medic.setCurePoints();
        Hero[] heroes = {warrior, buffer, tank, thor, golem, medic};
        return heroes;
    }

    private static void printStatistics(Hero[] heroes, Boss boss) {
        System.out.println("_______________________");
        System.out.println("Boss health " + boss.getHealth());
        for (int i = 0; i < heroes.length; i++) {
            System.out.println("Hero health  " + heroes[i].getClass().getSimpleName() + "=" + heroes[i].getHealth());

        }
        System.out.println("_______________________");
    }
}
