package com.gildedrose.items


interface SmartItem {
    fun update()
    val name: String
    val sellIn: Int
    val quality: Int
}