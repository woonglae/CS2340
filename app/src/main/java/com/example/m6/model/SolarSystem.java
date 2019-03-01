package com.example.m6.model;

public class SolarSystem {
    private String name;
    private int coordinate_x;
    private int coordinate_y;
    private int techLevel;
    private int resource;
    private TechLevel techLevelValue;
    private Resource resourceValue;
    private Planet planet;
    
    //솔라 시스템의 IE, CR, ER.
    public boolean DROUGHT;
    public boolean LOTSOFWATER;
    public boolean DESERT;
    public boolean COLD;
    public boolean RICHFAUNA;
    public boolean LIFELESS;
    public boolean CROPFAIL;
    public boolean RICHSOIL;
    public boolean POORSOIL;
    public boolean WAR;
    public boolean MINERALRICH;
    public boolean MINERALPOOR;
    public boolean BOREDOM;
    public boolean ARTISTIC;
    public boolean WARLIKE;
    public boolean PLAGUE;
    public boolean LOTSOFHERBS;
    public boolean LACKOFWORKERS;
    public boolean BOREDOM;
    public boolean WEIRDMUSHROOMS;
    
    SolarSystem(String name, int x, int y, int techLevel, int resource) {
        Planet planet = new Planet(name, x, y);
        this.name = name;
        coordinate_x = x;
        coordinate_y = y;
        this.techLevel = techLevel;
        this.resource = resource;
        resourceValue = Resource.values()[resource];
        techLevelValue = TechLevel.values()[techLevel];
        
        //솔라 시스템에 IE, CR, ER 랜덤하게 생성
        DROUGHT = (Math.random() < 0.5);
        LOTSOFWATER = (Math.random() < 0.5);
        DESERT = (Math.random() < 0.5);
        COLD = (Math.random() < 0.5);
        RICHFAUNA = (Math.random() < 0.5);
        LIFELESS = (Math.random() < 0.5);
        CROPFAIL = (Math.random() < 0.5);
        RICHSOIL = (Math.random() < 0.5);
        POORSOIL = (Math.random() < 0.5);
        WAR = (Math.random() < 0.5);
        MINERALRICH = (Math.random() < 0.5);
        MINERALPOOR = (Math.random() < 0.5);
        BOREDOM = (Math.random() < 0.5);
        ARTISTIC = (Math.random() < 0.5);
        WARLIKE = (Math.random() < 0.5);
        PLAGUE = (Math.random() < 0.5);
        LOTSOFHERBS = (Math.random() < 0.5);
        LACKOFWORKERS = (Math.random() < 0.5);
        WEIRDMUSHROOMS = (Math.random() < 0.5);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCoordinate_x() {
        return coordinate_x;
    }
    public void setCoordinate_x(int coordinate_x) {
        this.coordinate_x = coordinate_x;
    }
    public int getCoordinate_y() {
        return coordinate_y;
    }
    public void setCoordinate_y(int coordinate_y) {
        this.coordinate_y = coordinate_y;
    }
    public int getTechLevel() {
        return techLevel;
    }
    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }
    public TechLevel getTechLevelValue() {
        return techLevelValue;
    }
    public int getResource() {
        return resource;
    }
    public void setResource(int resource) {
        this.resource = resource;
    }
    public Resource getResourceValue() {
        return resourceValue;
    }

    public String toString(){
        return name+", ("+coordinate_x+", "+coordinate_y+"), techLevel " +techLevel + " " + techLevelValue +", resource : "+resource + " " + resourceValue;
    }
}
