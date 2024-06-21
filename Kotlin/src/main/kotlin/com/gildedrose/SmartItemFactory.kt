package com.gildedrose

import com.gildedrose.items.*

class SmartItemFactory {

    fun createItem(item: Item): SmartItem =
         when {
            item.name == "Aged Brie" -> AgedBrieItem(item)
            item.name == "Sulfuras, Hand of Ragnaros" -> SulfurasItem(item)
            item.name == "Backstage passes to a TAFKAL80ETC concert" -> BackstagePassItem(item)
            item.name.startsWith("Conjured") -> ConjuredItem(item)
            else -> DefaultItem(item)
        }

}