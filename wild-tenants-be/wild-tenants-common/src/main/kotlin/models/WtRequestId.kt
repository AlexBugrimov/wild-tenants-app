package ru.bugrimov.wt.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class WtRequestId(private val id: String) {

    fun asString() = id

    companion object {
        val NONE = WtRequestId("")
    }
}
