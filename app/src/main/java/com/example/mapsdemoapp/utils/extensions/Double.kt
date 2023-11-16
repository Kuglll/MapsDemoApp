package com.example.mapsdemoapp.utils.extensions

fun Double.limitDecimals(
    maxDecimalsCount: Int = 6
): String = if (this.toInt().toDouble() == this) {
    this.toInt().toString()
} else {
    "%.${maxDecimalsCount}f".format(this)
        .trimEnd('0')
}
