package com.gildedrose.itemhandlers

import com.gildedrose.InvalidItemException
import com.gildedrose.Item
import com.gildedrose.TestUtils
import com.gildedrose.items.SulfurasItem
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class SulfurasTest {

    @ParameterizedTest
    @MethodSource("singleDayTestSource")
    fun defaultHandlerSingleDayTest(item: Item, sellIn: Int, quality: Int) {
        val sulfuras = SulfurasItem(item)
        sulfuras.update()
        TestUtils.assertSmartItem(sulfuras, item.name, sellIn, quality)
    }
    @ParameterizedTest
    @MethodSource("invalidItemSource")
    fun invalidItemTest(item: Item) {
        assertThrows<InvalidItemException> { SulfurasItem(item) }
    }

    companion object {
        @JvmStatic
        fun singleDayTestSource(): List<Arguments> =
            listOf(
                Arguments.of(Item("Sulfuras, Hand of Ragnaros", 10, 80), 10, 80),
                Arguments.of(Item("Sulfuras, Hand of Ragnaros", -3, 80), -3, 80),
            )

        @JvmStatic
        fun invalidItemSource(): List<Arguments> =
            listOf(
                Arguments.of(Item("Sulfuras, Hand of Ragnaros", 10, 70)),
                Arguments.of(Item("Sulfuras, Hand of Ragnaros", -3, -30)),
            )

    }

}