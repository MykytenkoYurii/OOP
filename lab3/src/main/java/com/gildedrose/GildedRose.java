package com.gildedrose;

public class GildedRose {
    public Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (item.name.equals("Aged Brie")) {
            updateAgedBrie(item);
            return;
        }

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateBackstagePass(item);
            return;
        }

        updateNormalItem(item);

        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            handleExpired(item);
        }
    }

    private void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void updateBackstagePass(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }

        if (item.sellIn < 11 && item.quality < 50) {
            item.quality++;
        }

        if (item.sellIn < 6 && item.quality < 50) {
            item.quality++;
        }
    }

    private void updateNormalItem(Item item) {
        if (item.quality > 0) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.quality--;
            }
        }
    }

    private void handleExpired(Item item) {
        if (item.name.equals("Aged Brie")) {
            updateAgedBrie(item);
            return;
        }

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
            return;
        }

        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality--;
        }
    }
}
