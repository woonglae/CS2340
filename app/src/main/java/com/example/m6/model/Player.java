package com.example.m6.model;

import java.util.Arrays;
import java.util.List;

public class Player {

    public static List<String> validDifficulty = Arrays.asList("Beginner", "Easy", "Normal", "Hard", "Impossible");

    private String name, difficulty, spaceship;
    private int pilot, fighter, trader, engineer, credit;


    public static int findIndexofDifficulty(String difficulty) {
        int i = 0;
        while(i<4) {
            if(difficulty.equals(validDifficulty.get(i))) {return i;}
            i++;
        }
        return 0;
    }
    public Player(String name, int pilot, int fighter, int trader, int engineer, String difficulty) {
        this.name = name;
        this.pilot = pilot;
        this.fighter = fighter;
        this.trader = trader;
        this.engineer = engineer;
        this.difficulty = difficulty;
        credit = 1000;
        spaceship = "gnat";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPilot() {
        return pilot;
    }

    public void setPilot(int pilot) {
        this.pilot = pilot;
    }

    public int getFighter() {
        return fighter;
    }

    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    public int getTrader() {
        return trader;
    }

    public void setTrader(int trader) {
        this.trader = trader;
    }

    public int getEngineer() {
        return engineer;
    }

    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(String spaceship) {
        this.spaceship = spaceship;
    }

    public String toString(){
        return String.format("Player %s has pilot point %d, Engineer point %d, Fighter point %d, Trader point %d with %s spaceship, $%d credit and difficulty : %s",
                getName(), getPilot(), getEngineer(), getFighter(), getTrader(), getSpaceship(), getCredit(), getDifficulty());
    }

}
