package com.example.composition.utils

fun String?.isFieldValid():Boolean = !this.isNullOrBlank()

fun String.isSumMoreThanSeven(): Boolean {
    val sum = this.toInt()
    return (sum >= MIN_SUM)
}

fun String.isParamMoreThanOne(): Boolean {
    val param = this.toInt()
    return (param >= MIN_SETTINGS_VALUE)
}



const val MIN_SUM = 7
const val MIN_SETTINGS_VALUE= 1