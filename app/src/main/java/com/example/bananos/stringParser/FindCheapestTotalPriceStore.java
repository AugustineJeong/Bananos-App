package com.example.bananos.stringParser;

import com.example.bananos.model.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class FindCheapestTotalPriceStore {

    private List<GroceryItem> groceryItemList;
    private List<String> itemNameList;

    //constructor
    public FindCheapestTotalPriceStore(List<GroceryItem> groceryItemList) {
        this.groceryItemList = groceryItemList;
    }

    //given a list of all matching items from all stores, method returns the name of the store where the total price of
    //all required items is the cheapest
    public String getCheapestStore() {
        List<String> storesList = new ArrayList<>();

        itemNameList = new ArrayList<>();


        for (GroceryItem groceryItem : groceryItemList) {
            if (!storesList.contains(groceryItem.getStore())) {
                storesList.add(groceryItem.getStore());
            }
            if (!itemNameList.contains(groceryItem.getName())) {
                itemNameList.add(groceryItem.getName());
            }
        }

        List<String> goodStoreList = new ArrayList<>();

        for (String store : storesList) {
            if (hasAllMatchingItems(store)) {
                goodStoreList.add(store);
            }
        }

        storesList = goodStoreList;

        if (storesList.isEmpty()) {
            return "No store that sells all items";
        }

        Double minPrice = 500000.0;
        String store = "error";
        Double price;

        for (String storeName : storesList) {
            price = 0.0;

            List<GroceryItem> temporaryList = new ArrayList<>();


            for (GroceryItem groceryItem : this.groceryItemList) {
                if (groceryItem.getStore().equals(storeName)) {
                    temporaryList.add(groceryItem);
                }
            }
            for (GroceryItem groceryItem : temporaryList) {
                price += (groceryItem.getPrice() * groceryItem.getQuantity());
            }
            if (price < minPrice) {
                minPrice = price;
                store = storeName;
            }
        }
        return store;
    }

    private boolean hasAllMatchingItems(String store) {
        int i = 0;
        for (String itemName : itemNameList) {
            for (GroceryItem groceryItem : groceryItemList) {
                if (groceryItem.getName().equals(itemName)) {
                    if (groceryItem.getStore().equals(store)) {
                        i += 1;
                    }
                }
            }
        }
            return (i == itemNameList.size());
        }
    }
