package com.example.bananos.stringParser;

import com.example.bananos.model.GroceryItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ParseString {

    private List<GroceryItem> groceryItemList;
    private String voiceInput;

    //constructor
    public ParseString(List<GroceryItem> groceryItemList, String voiceInput) {
        this.groceryItemList = groceryItemList;
        this.voiceInput = voiceInput;
    }

    //EFFECTS: returns a list of every word contained in the string of voice input
    private List<String> stringToWords() {

        String[] words = voiceInput.split(" ");

        return new ArrayList<>(Arrays.asList(words));
    }



}
