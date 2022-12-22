package com.dev.zann.appfragment

import java.io.Serializable

data class UserInfo(
    val id: String,
    val name: String,
    val age: Int,
    val work: String
): Serializable {

    fun userTextInfo(): String = """
        O ID desse usuário é $id
        Seu nome é $name
        Sua idade é $age
        Ele trabalha como $work
    """.trimIndent()

}
