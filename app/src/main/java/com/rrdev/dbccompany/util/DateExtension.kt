package com.rrdev.dbccompany.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatOnPattern(pattern: String = "dd/MM/yyyy"): String {
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(this)
}