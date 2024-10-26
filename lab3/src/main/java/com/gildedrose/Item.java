package com.gildedrose;

public class Item {
    public ItemName name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = new ItemName(name);
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name.getName() + ", " + this.sellIn + ", " + this.quality;
    }
}
