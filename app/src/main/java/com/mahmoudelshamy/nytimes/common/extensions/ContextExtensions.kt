package com.mahmoudelshamy.nytimes.common.extensions

import android.content.Context
import android.view.LayoutInflater

val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)