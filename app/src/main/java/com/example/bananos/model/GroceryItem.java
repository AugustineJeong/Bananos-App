package com.example.bananos.model;

public class GroceryItem {
    private String name;
    private double price;
    private String units = "whatever";
    private String store;
    private int quantity;

    public GroceryItem(String name, double price, String units, String store) {
        this.name = name;
        this.price = price;
        this.units = units;
        this.store = store;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String about() {
        return quantity + " " + name + "(s) at " + "$" + price + "/" + units + " at " + store;
    }
}

