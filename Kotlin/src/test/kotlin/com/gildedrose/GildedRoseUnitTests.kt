package com.gildedrose

import com.gildedrose.TestUtils.Companion.assertItem
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class GildedRoseUnitTests {

    @ParameterizedTest
    @MethodSource("itemsForSingleDayTest")
    fun singleDayTest(item: Item, expectedSellIn: Int, expectedQuality: Int) {
        val app = GildedRose(listOf(item))
        app.updateQuality()
        assertItem(app.items[0], app.items[0].name, expectedSellIn, expectedQuality)
    }

    @ParameterizedTest
    @MethodSource("invalidItems")
    fun invalidItemsTest(item: Item) {
        assertThrows<InvalidItemException> { GildedRose(listOf(item)) }
    }

    companion object {
        @JvmStatic
        private fun itemsForSingleDayTest(): List<Arguments> = listOf(
            Arguments.of(Item("foo", 0, 0), -1, 0), // no negative price
            Arguments.of(Item("foo", 0, 2), -1, 0), // twice as fast aging after SellIn date
            Arguments.of(Item("foo", -2, 14), -3, 12),
            Arguments.of(Item("foo", 3, 20), 2, 19), // normal aging before SellIn date
            Arguments.of(Item("Aged Brie", 3, 20), 2, 21), // Aged Brie increases in Quality
            Arguments.of(Item("Aged Brie", 0, 10), -1, 12),
            Arguments.of(Item("Aged Brie", 4, 50), 3, 50), // Quality doesn't go over 50
            Arguments.of(Item("Sulfuras, Hand of Ragnaros", 0, 80), 0, 80), // Sulfuras doesn't age and keeps Quality
            Arguments.of(Item("Sulfuras, Hand of Ragnaros", 10, 80), 10, 80),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 20, 10), 19, 11), // tickets increase in Quality
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 11, 15), 10, 16),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 10, 15), 9, 17), // by 2 if <= 10 days until concert
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 8, 7), 7, 9),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 5, 22), 4, 25), // by 3 if <= 5 days until concert
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 1, 42), 0, 45),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 0, 42), -1, 0), // quality drops to 0 after concert
            Arguments.of(Item("Conjured Mana Cake", 5, 15), 4, 13), // Conjured items degrade twice as fast
            Arguments.of(Item("Conjured Mana Cake", -2, 10), -3, 6),
        )

        @JvmStatic
        private fun invalidItems(): List<Arguments> = listOf(
            Arguments.of(Item("foo", 15, 60)), // Quality can't be over 50
            Arguments.of(Item("foo", 15, 51)),
            Arguments.of(Item("foo", 15, -5)), // Quality can't be negative
            Arguments.of(Item("Sulfuras, Hand of Ragnaros", 10, 35)), // Sulfuras has always Quality of 80
            Arguments.of(Item("Sulfuras, Hand of Ragnaros", 0, 60)),
        )

    }

}


