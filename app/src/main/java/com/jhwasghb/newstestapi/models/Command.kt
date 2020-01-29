package com.jhwasghb.newstestapi.models

data class Command (
    val command: Int,
    var data: Any? = null,
    var isCompleted: Boolean = false
)