package com.techelevator;

//import java.util.HashMap;
//import java.util.Map;
import java.util.*;

public class Snack {
    private String brandName;
    private String category;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public Snack(String brandName, String category, int price) {
        this.brandName = brandName;
        this.category = category;
        this.price = price;
    }

    public Map getPhraseMap(){
        Map<String, String> phraseMap = new HashMap<String, String>();
        phraseMap.put("chip", "Crunch Crunch, Yum!");
        phraseMap.put("candy", "Munch Munch, Yum!");
        phraseMap.put("drink", "Glug Glug, Yum!");
        phraseMap.put("gum", "Chew Chew, Yum!");
        return phraseMap;
    }

    public String getPhrase(){
        System.out.println(getPhraseMap().get(category));
    }

}
