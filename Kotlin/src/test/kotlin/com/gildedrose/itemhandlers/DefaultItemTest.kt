package com.gildedrose.itemhandlers

import com.gildedrose.InvalidItemException
import com.gildedrose.Item
import com.gildedrose.TestUtils
import com.gildedrose.items.DefaultItem
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DefaultItemTest {

    @ParameterizedTest
    @MethodSource("singleDayTestSource")
    fun defaultHandlerSingleDayTest(item: Item, sellIn: Int, quality: Int) {
        val defaultItem = DefaultItem(item)
        defaultItem.update()
        TestUtils.assertSmartItem(defaultItem, item.name, sellIn, quality)
    }

    @ParameterizedTest
    @MethodSource("invalidItemSource")
    fun invalidItemTest(item: Item) {
        assertThrows<InvalidItemException> { DefaultItem(item) }
    }

    companion object {
        @JvmStatic
        fun singleDayTestSource(): List<Arguments> =
            listOf(
                Arguments.of(Item("foo", 1, 1), 0, 0),
                Arguments.of(Item("foo", 0, 1), -1, 0),
                Arguments.of(Item("foo", -5, 20), -6, 18),
                Arguments.of(Item("foo", 15, 5), 14, 4),
                Arguments.of(Item("foo", 15, 0), 14, 0),
            )

        @JvmStatic
        fun invalidItemSource(): List<Arguments> =
            listOf(
                Arguments.of(Item("foo", 10, 70)),
                Arguments.of(Item("foo", -3, -30)),
            )

    }

}