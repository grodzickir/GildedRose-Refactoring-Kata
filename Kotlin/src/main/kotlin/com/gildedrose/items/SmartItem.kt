package com.gildedrose.items

import com.gildedrose.Item


interface SmartItem {
    fun update()
    val item: Item
    val name: String
    val sellIn: Int
    val quality: Int
}