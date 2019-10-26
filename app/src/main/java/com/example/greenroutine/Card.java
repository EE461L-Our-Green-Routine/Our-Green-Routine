package com.example.greenroutine;

import android.graphics.drawable.Drawable;

public class Card {
    private Drawable pic;
    private String item;
    private String description;
    private String category;

    public Card(Drawable pic, String name, String description) {
        this.pic = pic;
        this.item = name;
        this.description = description;
    }

    public Drawable getPic() {
        return pic;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }
}
