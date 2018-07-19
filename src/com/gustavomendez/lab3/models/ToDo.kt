package com.gustavomendez.lab3.models

class ToDo(
        val name: String,
        var state: Boolean
) {
    fun changeState() {
        state = !state
    }

    override fun toString(): String {
        return """
            $name (${if(state) "Completa" else "Sin completar"})
        """.trimIndent()
    }

}