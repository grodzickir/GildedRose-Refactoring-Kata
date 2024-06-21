package com.gildedrose.items

import com.gildedrose.Item

class ConjuredItem(item: Item):
    AbstractItem(item) {

    override fun updateInternal() {
        sellIn--
        if (sellIn >= 0)
            quality -= 2
        else quality -= 4
    }


}