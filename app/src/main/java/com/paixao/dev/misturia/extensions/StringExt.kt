package com.paixao.dev.misturia.extensions

fun String.jsonClean() = this.replace("\n", "").replace("json", "").replace("```", "")