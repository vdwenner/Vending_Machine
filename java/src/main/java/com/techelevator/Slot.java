package com.techelevator;

public class Slot {
    private String identifier;
    private int quantity;
    private Snack snackType;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Snack getSnackType() {
        return snackType;
    }

    public void setSnackType(Snack snackType) {
        this.snackType = snackType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Slot(String identifier, Snack snackType) {
        this.identifier = identifier;
        this.snackType = snackType;
    }

    //insert display slot method later


}
