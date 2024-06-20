package com.gildedrose.itemhandlers

import com.gildedrose.InvalidItemException
import com.gildedrose.Item
import com.gildedrose.TestUtils
import com.gildedrose.items.BackstagePassItem
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BackstagePassTest {

    @ParameterizedTest
    @MethodSource("singleDayTestSource")
    fun defaultHandlerSingleDayTest(item: Item, sellIn: Int, quality: Int) {
        val backstagePass = BackstagePassItem(item)
        backstagePass.update()
        TestUtils.assertSmartItem(backstagePass, item.name, sellIn, quality)
    }

    @ParameterizedTest
    @MethodSource("invalidItemSource")
    fun invalidItemTest(item: Item) {
        assertThrows<InvalidItemException> { BackstagePassItem(item) }
    }

    companion object {
        @JvmStatic
        fun singleDayTestSource(): List<Arguments> =
            listOf(
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 0, 1), -1, 0),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", -5, 20), -6, 0),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 15, 5), 14, 6),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 11, 0), 10, 1),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 10, 0), 9, 2),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 6, 0), 5, 2),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 5, 0), 4, 3),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 1, 1), 0, 4),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", -2, 49), -3, 0),
            )

        @JvmStatic
        fun invalidItemSource(): List<Arguments> =
            listOf(
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 10, 70)),
                Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", -3, -30)),
            )

    }

}