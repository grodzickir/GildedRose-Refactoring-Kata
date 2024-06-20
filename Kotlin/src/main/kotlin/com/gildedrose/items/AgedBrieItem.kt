package com.gildedrose.items

import com.gildedrose.Item

class AgedBrieItem(item: Item):
    AbstractItem(item.name, item.sellIn, item.quality) {

    override fun updateInternal() {
        sellIn--
        if (sellIn >= 0)
            quality++
        else quality += 2
    }


}