package com.techelevator;

import java.util.HashMap;

public class Slot {
    private String identifier;
    private int quantity = 5;
    private String brandName;
    private String category;
    private int price;
    private int totalSales = 0;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
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
        }

    public String getPhrase(){
        HashMap<String, String> phraseMap = new HashMap<String, String>();
        phraseMap.put("Chip", "Crunch Crunch, Yum!");
        phraseMap.put("Candy", "Munch Munch, Yum!");
        phraseMap.put("Drink", "Glug Glug, Yum!");
        phraseMap.put("Gum", "Chew Chew, Yum!");
        return phraseMap.get(category);
    }
}
