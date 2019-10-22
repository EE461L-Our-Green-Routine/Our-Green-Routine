package com.example.greenroutine;

public class Card {
    private int pic;
    private String item;
    private String description;
    private String category;

    public Card(int pic, String name, String description) {
        this.pic = pic;
        this.item = name;
        this.description = description;
    }

    public int getPic() {
        return pic;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }
}
