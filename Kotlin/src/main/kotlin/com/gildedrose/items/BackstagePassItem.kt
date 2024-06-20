package com.gildedrose.items

import com.gildedrose.Item

class BackstagePassItem(item: Item):
    AbstractItem(item.name, item.sellIn, item.quality) {

    override fun updateInternal() {
        sellIn--
        when {
            sellIn >= 10 -> quality += 1
            sellIn in 5..9 -> quality += 2
            sellIn in 0..4 -> quality += 3
            else -> quality = 0
        }

    }


}