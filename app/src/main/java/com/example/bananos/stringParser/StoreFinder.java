package com.example.bananos.stringParser;


import com.example.bananos.model.GroceryItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreFinder {

    private List<GroceryItem> database;
    private String voiceInput;
    private int quantity;
    private List<String> words;
    private List<String> wordsSingular;
    private List<String> wordsSingular2;
    private List<GroceryItem> matchingGroceryItems = new ArrayList<>();

    //constructor
    public StoreFinder(List<GroceryItem> database, String voiceInput) {
        this.database = database;
        this.voiceInput = voiceInput;
    }

    //EFFECTS: returns a list of every word contained in the string of voice input
    private void stringToWords() {

        String[] words = voiceInput.split(" ");

        this.words =  new ArrayList<>(Arrays.asList(words));

        this.wordsSingular = new ArrayList<>();

        this.wordsSingular2 = new ArrayList<>();

        for (String word : this.words) {
            this.wordsSingular.add(word.substring(0, word.length() - 1));
        }

        for (String word : this.words) {
            if (word.length() > 2) {
                this.wordsSingular2.add(word.substring(0, word.length() - 2));
            }
        }
    }

    //EFFECTS: finds the number of quantity
    public void findQuantity() {
        this.quantity = 1;
        for (String word : this.words) {
            if (word.equalsIgnoreCase("zero") || word.equalsIgnoreCase("0")) {
                this.quantity = 0;
                break;
            }
            if (word.equalsIgnoreCase("one") || word.equalsIgnoreCase("1")) {
                this.quantity = 1;
                break;
            }
            if (word.equalsIgnoreCase("two") || word.equalsIgnoreCase("2")) {
                this.quantity = 2;
                break;
            }
            if (word.equalsIgnoreCase("three") || word.equalsIgnoreCase("3")) {
                this.quantity = 3;
                break;
            }
            if (word.equalsIgnoreCase("four") || word.equalsIgnoreCase("4")) {
                this.quantity = 4;
                break;
            }
            if (word.equalsIgnoreCase("five") || word.equalsIgnoreCase("5")) {
                this.quantity = 5;
                break;
            }
            if (word.equalsIgnoreCase("six") || word.equalsIgnoreCase("6")) {
                this.quantity = 6;
                break;
            }
            if (word.equalsIgnoreCase("seven") || word.equalsIgnoreCase("7")) {
                this.quantity = 7;
                break;
            }
            if (word.equalsIgnoreCase("eight") || word.equalsIgnoreCase("8")) {
                this.quantity = 8;
                break;
            }
            if (word.equalsIgnoreCase("nine") || word.equalsIgnoreCase("9")) {
                this.quantity = 9;
                break;
            }
            if (word.equalsIgnoreCase("ten") || word.equalsIgnoreCase("10")) {
                this.quantity = 10;
                break;
            }
        }
    }

    //EFFECTS: makes list of items with matching name (ex. all apple items)
    private void matchingItems() {

        for (String word : words) {
            for (GroceryItem groceryItem : this.database) {
                if (groceryItem.getName().equals(word)) {
                    groceryItem.setQuantity(quantity);
                    this.matchingGroceryItems.add(groceryItem);
                }
            }
        }

        for (String word : wordsSingular) {
            for (GroceryItem groceryItem : this.database) {
                if (groceryItem.getName().equals(word)) {
                    groceryItem.setQuantity(quantity);
                    this.matchingGroceryItems.add(groceryItem);
                }
            }
        }

        for (String word : wordsSingular2) {
            for (GroceryItem groceryItem : this.database) {
                if (groceryItem.getName().equals(word)) {
                    groceryItem.setQuantity(quantity);
                    this.matchingGroceryItems.add(groceryItem);
                }
            }
        }
    }

    //EFFECTS: returns cheapest matching GroceryItem object, or returns null if there is no match
    //also checks for singular variant of user input. i.e. if user says "apples", will check for
    //item name 'apple' and 'apples'.
    public GroceryItem findCheapest() {
        stringToWords();
        findQuantity();

        System.out.println(this.quantity);

        matchingItems();

        if (this.matchingGroceryItems.isEmpty()) {
            return null;
        }

        GroceryItem cheapestItem = this.matchingGroceryItems.get(0);

        for (GroceryItem groceryItem : this.matchingGroceryItems) {
            if (groceryItem.getPrice() < cheapestItem.getPrice()) {
                cheapestItem = groceryItem;
                cheapestItem.setQuantity(this.quantity);
            }
        }
        return cheapestItem;
    }

    //EFFECTS: returns all GroceryItems objects with matching names
    //also checks for singular variant of user input. i.e. if user says "apples", will check for
    //item name 'apple' and 'apples'.
    public List<GroceryItem> findAllMatching() {
        stringToWords();
        findQuantity();
        matchingItems();

        return this.matchingGroceryItems;
    }
}


