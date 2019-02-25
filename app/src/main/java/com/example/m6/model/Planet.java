package com.example.m6.model;
public class Planet {
    private String name;
    private double goodsPrice;
    private int coordinateX, coordinateY;
    public Planet(String name, int coordinateX, int coordinateY) {
        this(name, 0, coordinateX, coordinateY);
    }
    public Planet(String name, double goodsPrice, int coordinateX, int coordinateY) {
        this.name = name;
        this.goodsPrice = goodsPrice;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getGoodsPrice() {
        return goodsPrice;
    }
    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public int getCoordinateX() {
        return coordinateX;
    }
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }
    public int getCoordinateY() {
        return coordinateY;
    }
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
}
