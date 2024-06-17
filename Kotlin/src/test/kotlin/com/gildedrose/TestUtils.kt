package com.gildedrose

import org.junit.jupiter.api.Assertions

class TestUtils {
    companion object {
        fun assertItem(item: Item, name: String, sellIn: Int, quality: Int) {
            Assertions.assertEquals(name, item.name)
            Assertions.assertEquals(sellIn, item.sellIn)
            Assertions.assertEquals(quality, item.quality)
        }
    }
}