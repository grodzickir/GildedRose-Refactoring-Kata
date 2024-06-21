package com.gildedrose.items

import com.gildedrose.Item

class DefaultItem(item: Item):
    AbstractItem(item) {

    override fun updateInternal() {
        sellIn--
        if (sellIn >= 0)
            quality--
        else quality -= 2
    }


}