package com.example.tbc_shemajamebeli_8.data.model

data class DPO(
    val content: List<Content>
) {
    data class Content(
    val title: String,
    val price: String,
    val cover: String,
    val liked: Boolean
    )

}