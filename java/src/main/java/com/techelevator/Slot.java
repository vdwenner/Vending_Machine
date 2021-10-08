package com.techelevator;

import java.util.HashMap;

import static java.lang.String.valueOf;

public class Slot {
    private String identifier;
    private int quantity = 5;
    private String brandName;
    private String category;
    private int price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //constructors


    public Slot(String identifier, String brandName, String category, int price) {
        this.identifier = identifier;
        this.brandName = brandName;
        this.category = category;
        this.price = price;
    }

    public Slot(String line) {
        if ((line != null) && (!line.isEmpty())) {	// Skip null or empty lines
            String[] parts = line.split("\\|");
            this.identifier = parts[0];
            this.brandName = parts[1];
            this.category = parts[3];
            String priceAsString = parts[2].replace(".", "");
            Integer priceAsInt = Integer.parseInt(priceAsString);
            this.price = priceAsInt;

            }
//if this doesn't work, check the parsing for price becoming an int
        }



    //insert display slot method later

    public String getPhrase(){
        HashMap<String, String> phraseMap = new HashMap<String, String>();
        phraseMap.put("Chip", "Crunch Crunch, Yum!");
        phraseMap.put("Candy", "Munch Munch, Yum!");
        phraseMap.put("Drink", "Glug Glug, Yum!");
        phraseMap.put("Gum", "Chew Chew, Yum!");
        return phraseMap.get(category);
    }
}
