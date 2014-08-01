package com.antenadigital.bbqnow.app;

import android.graphics.drawable.Drawable;

/**
 * Created by mvera on 5/25/14.
 */

public class BBQItem {

    Drawable mImage = null;
    String name = null;
    boolean selected = false;

    public BBQItem(Drawable image, String name, boolean selected) {
        super();
        this.mImage = image;
        this.name = name;
        this.selected = selected;
    }

    public Drawable getImage() {
        return mImage;
    }
    public void setImage(Drawable image) {
        this.mImage = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
