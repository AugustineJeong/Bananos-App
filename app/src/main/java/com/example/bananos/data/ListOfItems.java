package com.example.bananos.data;

import com.example.bananos.model.GroceryItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListOfItems implements Serializable {
    private List<GroceryItem> groceryItemList = new ArrayList<>();

    public List<GroceryItem> getGroceryItemList() {
        return groceryItemList;
    }
}
