package com.dahgin.dashginapp

data class Task(
    val title: String,
    val description: String,
    val done: Boolean = false,
    val year: Int,
    val mon: Int,
    val day: Int,
    val hour: Int,
    val min: Int,
)
