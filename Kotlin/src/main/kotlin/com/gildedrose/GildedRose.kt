package com.gildedrose

import com.gildedrose.items.SmartItem

class GildedRose(val items: List<Item>) {

    private val smartItems = SmartItemFactory().let {
        factory -> items.map(factory::createItem)
    }

    fun updateQuality() {
        smartItems.forEach(SmartItem::update)
    }

}

