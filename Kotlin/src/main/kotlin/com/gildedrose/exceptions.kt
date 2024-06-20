package com.gildedrose

import com.gildedrose.items.SmartItem

class InvalidItemException(item: SmartItem, reason: String):
    Exception("Invalid ${item::class.simpleName} $item. Reason: $reason.")