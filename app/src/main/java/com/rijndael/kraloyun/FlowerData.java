package com.rijndael.kraloyun;

import android.graphics.drawable.Drawable;

public class FlowerData {

    private String flowerName;
    private String flowerDescription;
    private Drawable flowerImage;

    public FlowerData(String flowerName, String flowerDescription, Drawable flowerImage) {
        this.flowerName = flowerName;
        this.flowerDescription = flowerDescription;
        this.flowerImage = flowerImage;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public String getFlowerDescription() {
        return flowerDescription;
    }

    public Drawable getFlowerImage() {
        return flowerImage;
    }
}
