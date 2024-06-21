package com.gildedrose

import com.gildedrose.items.AgedBrieItem
import com.gildedrose.items.BackstagePassItem
import com.gildedrose.items.DefaultItem
import com.gildedrose.items.SmartItem
import com.gildedrose.items.SulfurasItem

class SmartItemFactory {

    fun createItem(item: Item): SmartItem =
         when {
            item.name == "Aged Brie" -> AgedBrieItem(item)
            item.name == "Sulfuras, Hand of Ragnaros" -> SulfurasItem(item)
            item.name == "Backstage passes to a TAFKAL80ETC concert" -> BackstagePassItem(item)
            item.name.startsWith("Conjured") -> TODO("Conjured item not yet implemented")
            else -> DefaultItem(item)
        }

}