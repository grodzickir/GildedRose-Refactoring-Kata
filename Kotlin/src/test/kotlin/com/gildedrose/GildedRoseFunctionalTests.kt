package com.gildedrose

import com.gildedrose.TestUtils.Companion.assertItem
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class GildedRoseFunctionalTests {

    @ParameterizedTest
    @MethodSource("itemsForThreeDaysTest")
    fun threeDaysTest(item: Item, expectedSellIn: Int, expectedQuality: Int) {
        val app = GildedRose(listOf(item))
        repeat(3) { app.updateQuality() }
        assertItem(app.items[0], app.items[0].name, expectedSellIn, expectedQuality)
    }

    @Test
    fun multiItemTest() {
        val items = listOf(
            Item("foo", 12, 3),
            Item("Aged Brie", 3, 5),
            Item("Sulfuras, Hand of Ragnaros", 10, 80),
            Item("Conjured Mana Cake", 1, 23),
        )

        val app = GildedRose(items)
        app.updateQuality()
        assertAll("Day 1",
            { assertItem(app.items[0], "foo", 11, 2) },
            { assertItem(app.items[1], "Aged Brie", 2, 6) },
            { assertItem(app.items[2], "Sulfuras, Hand of Ragnaros", 10, 80) },
            { assertItem(app.items[3], "Conjured Mana Cake", 0, 21) },
        )
        app.updateQuality()
        assertAll("Day 2",
            { assertItem(app.items[0], "foo", 10, 1) },
            { assertItem(app.items[1], "Aged Brie", 1, 7) },
            { assertItem(app.items[2], "Sulfuras, Hand of Ragnaros", 10, 80) },
            { assertItem(app.items[3], "Conjured Mana Cake", -1, 17) },
        )
        app.updateQuality()
        assertAll("Day 3",
            { assertItem(app.items[0], "foo", 9, 0) },
            { assertItem(app.items[1], "Aged Brie", 0, 8) },
            { assertItem(app.items[2], "Sulfuras, Hand of Ragnaros", 10, 80) },
            { assertItem(app.items[3], "Conjured Mana Cake", -2, 13) },
        )
        app.updateQuality()
        assertAll("Day 4",
            { assertItem(app.items[0], "foo", 8, 0) },
            { assertItem(app.items[1], "Aged Brie", -1, 10) },
            { assertItem(app.items[2], "Sulfuras, Hand of Ragnaros", 10, 80) },
            { assertItem(app.items[3], "Conjured Mana Cake", -3, 9) },
        )
    }

    companion object {

        @JvmStatic
        private fun itemsForThreeDaysTest(): List<Arguments> = listOf(
            Arguments.of(Item("foo", 15, 45), 12, 42),
            Arguments.of(Item("foo", 1, 15), -2, 10),
            Arguments.of(Item("foo", -1, 15), -4, 9),
            Arguments.of(Item("Sulfuras, Hand of Ragnaros", 10, 80), 10, 80),
            Arguments.of(Item("Aged Brie", 4, 30), 1, 33),
            Arguments.of(Item("Aged Brie", 2, 20), -1, 24),
            Arguments.of(Item("Aged Brie", -5, 20), -8, 26),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 10, 14), 7, 20),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 22, 1), 19, 4),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 6, 32), 3, 40),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 5, 32), 2, 41),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 1, 18), -2, 0),
            Arguments.of(Item("Conjured foo", 5, 12), 2, 6),
            Arguments.of(Item("Conjured foo", 1, 12), -2, 2),
            Arguments.of(Item("Conjured foo", -2, 12), -5, 0),
        )
    }

}


