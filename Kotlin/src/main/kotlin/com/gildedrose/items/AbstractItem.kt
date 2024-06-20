package com.gildedrose.items

import com.gildedrose.InvalidItemException


abstract class AbstractItem(
    final override val name: String,
    final override var sellIn: Int,
    final override var quality: Int,
    validator: (Int, Int) -> Unit = ::validate
): SmartItem {

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
        protected fun validate(sellIn: Int, quality: Int) {
            require(quality >= 0) { "Quality must be greater than or equal to 0" }
            require(quality <= DEFAULT_QUALITY_LIMIT) { "Quality must be less than or equal to 0" }
        }
        private const val DEFAULT_QUALITY_LIMIT = 50
    }

    override fun toString(): String = "$name: SellIn $sellIn, Quality $quality"

}