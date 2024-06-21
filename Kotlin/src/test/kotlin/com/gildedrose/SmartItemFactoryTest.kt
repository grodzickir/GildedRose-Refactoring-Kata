package com.gildedrose

import com.gildedrose.items.AgedBrieItem
import com.gildedrose.items.BackstagePassItem
import com.gildedrose.items.DefaultItem
import com.gildedrose.items.SmartItem
import com.gildedrose.items.SulfurasItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

class SmartItemFactoryTest {

    private val subject = SmartItemFactory()

    @ParameterizedTest
    @MethodSource("factoryTestSource")
    fun smartItemFactoryTest(item: Item, klass: KClass<out SmartItem>){
        Assertions.assertInstanceOf(klass.java, subject.createItem(item))
    }

    companion object {
        @JvmStatic
        fun factoryTestSource(): List<Arguments> = listOf(
            Arguments.of(Item("Aged Brie", 10, 2), AgedBrieItem::class),
            Arguments.of(Item("Aged Brie", 0, 2), AgedBrieItem::class),
            Arguments.of(Item("foo", 0, 2), DefaultItem::class),
            Arguments.of(Item("any_name", 15, 2), DefaultItem::class),
            Arguments.of(Item("Sulfuras, Hand of Ragnaros", 0, 80), SulfurasItem::class),
            Arguments.of(Item("Backstage passes to a TAFKAL80ETC concert", 10, 2), BackstagePassItem::class),
        )
    }

}