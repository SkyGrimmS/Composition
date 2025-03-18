package com.example.composition.utils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner

fun FragmentActivity.handleOnBackPressed(
    lifecycle: LifecycleOwner,
    onBackEnable: Boolean = true,
    onPress: () -> Unit,
) {
    onBackPressedDispatcher.addCallback(
        lifecycle,
        object : OnBackPressedCallback(onBackEnable) {
            override fun handleOnBackPressed() {
                onPress()
            }
        })
}