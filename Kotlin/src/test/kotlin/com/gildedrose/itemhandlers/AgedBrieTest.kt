package com.gildedrose.itemhandlers

import com.gildedrose.InvalidItemException
import com.gildedrose.Item
import com.gildedrose.TestUtils
import com.gildedrose.items.AgedBrieItem
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class AgedBrieTest {

    @ParameterizedTest
    @MethodSource("singleDayTestSource")
    fun defaultHandlerSingleDayTest(item: Item, sellIn: Int, quality: Int) {
        val agedBrie = AgedBrieItem(item)
        agedBrie.update()
        TestUtils.assertSmartItem(agedBrie, item.name, sellIn, quality)
    }


    @ParameterizedTest
    @MethodSource("invalidItemSource")
    fun invalidItemTest(item: Item) {
        assertThrows<InvalidItemException> { AgedBrieItem(item) }
    }


    companion object {
        @JvmStatic
        fun singleDayTestSource(): List<Arguments> =
            listOf(
                Arguments.of(Item("Aged Brie", 1, 1), 0, 2),
                Arguments.of(Item("Aged Brie", 0, 1), -1, 3),
                Arguments.of(Item("Aged Brie", -5, 20), -6, 22),
                Arguments.of(Item("Aged Brie", 15, 5), 14, 6),
                Arguments.of(Item("Aged Brie", 15, 0), 14, 1),
                Arguments.of(Item("Aged Brie", -2, 49), -3, 50),
            )


        @JvmStatic
        fun invalidItemSource(): List<Arguments> =
            listOf(
                Arguments.of(Item("Aged Brie", 10, 70)),
                Arguments.of(Item("Aged Brie", -3, -30)),
            )

    }

}