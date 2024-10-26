package com.gildedrose;

import java.util.List;

class GildedRose {
    public Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public GildedRose(List<Item> items) {
        this.items = items.toArray(new Item[items.size()]);
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("foo")) {
                item.name = "fixme";
            }

            if (isLegendary(item)) {
                continue;
            }

            updateSellIn(item);

            if (isExpired(item)) {
                handleExpiredItem(item);
                continue;
            }

            updateItemQuality(item);

            if (item.quality < 0) {
                item.quality = 0;
            }
        }
    }

    private boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private void updateSellIn(Item item) {
        item.sellIn--;
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private void handleExpiredItem(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
            return;
        }
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
            return;
        }
        decreaseQuality(item);
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
            return;
        }

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQuality(item);

            if (item.sellIn < 10) {
                increaseQuality(item);
            }

            if (item.sellIn < 5) {
                increaseQuality(item);
            }
            return;
        }

        decreaseQuality(item);
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}
