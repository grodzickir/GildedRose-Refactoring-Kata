package com.gildedrose.items

import com.gildedrose.InvalidItemException
import com.gildedrose.Item

const val DEFAULT_QUALITY_LIMIT = 50

abstract class AbstractItem(
    final override val item: Item,
    validator: (Int, Int) -> Unit = ::defaultValidation
): SmartItem {
    final override val name: String
        get() = item.name
    final override var sellIn get() = item.sellIn
        protected set(value) {
            item.sellIn = value
        }
    final override var quality get() = item.quality
        protected set(value) {
            item.quality = value
        }

    protected open val maxQualityLimit = DEFAULT_QUALITY_LIMIT

    protected abstract fun updateInternal()

    init {
        try {
            validator(sellIn, quality)
        } catch (e: IllegalArgumentException) {
            throw InvalidItemException(this, e.message ?: "")
        }
    }

    final override fun update() {
        updateInternal()
        ensureQualityWithinLimits()
        ensureQualityWithinLimits()
    }

    private fun ensureQualityWithinLimits() {
        if (quality < 0) quality = 0
        if (quality > maxQualityLimit) quality = maxQualityLimit
    }

    companion object {
        protected fun defaultValidation(sellIn: Int, quality: Int) {
            require(quality >= 0) { "Quality must be greater than or equal to 0" }
            require(quality <= DEFAULT_QUALITY_LIMIT) { "Quality must be less than or equal to 0" }
        }
    }

    override fun toString(): String = "${item.name}: SellIn $sellIn, Quality $quality"

}