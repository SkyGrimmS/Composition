package com.example.composition.utils

import com.google.android.material.textfield.TextInputLayout

fun String?.isFieldValid(): Boolean = !this.isNullOrBlank()

fun String.isSumMoreThanSeven(): Boolean {
    val sum = this.toInt()
    return (sum >= MIN_SUM)
}

fun String.isParamMoreThanOne(): Boolean {
    val param = this.toInt()
    return (param >= MIN_SETTINGS_VALUE)
}

fun TextInputLayout.setError(textError: String? = null) {
    this.isErrorEnabled = textError == null
    textError?.let {
        this.error = it
    }
}

const val MIN_SUM = 7
const val MIN_SETTINGS_VALUE = 1