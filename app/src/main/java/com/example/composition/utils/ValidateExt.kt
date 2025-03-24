package com.example.composition.utils

fun Int.isSumMoreThanSeven(): Boolean {
    val sum = this.toInt()
    return (sum >= MIN_SUM)
}

fun Int.isParamMoreThanOne(): Boolean {
    val param = this.toInt()
    return (param >= MIN_SETTINGS_VALUE)
}



const val MIN_SUM = 7
const val MIN_SETTINGS_VALUE= 1