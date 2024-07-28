package com.woutervandervelde.e_cook.domain.ext

fun String.capitalizeWords(): String =
    this.split(" ").joinToString(" ") { it.lowercase().replaceFirstChar { it.titlecase() } }