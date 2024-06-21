package com.gildedrose.items

import com.gildedrose.Item

class SulfurasItem(item: Item) :
    AbstractItem(item, ::sulfurasValidator) {

    override val maxQualityLimit = SULFURAS_QUALITY

    override fun updateInternal() {
        // do nothing
    }

    companion object {
        private const val SULFURAS_QUALITY = 80
        private fun sulfurasValidator (sellIn: Int, quality: Int) {
            require(quality == SULFURAS_QUALITY) { "Sulfuras can only have quality of 80" }
        }
    }
}