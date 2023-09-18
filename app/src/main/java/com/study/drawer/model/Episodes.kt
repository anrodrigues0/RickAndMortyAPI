package com.study.drawer.model

data class Episodes(
    val info: Info,
    val results: List<EpisodeResult>,
)

data class Info(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any?,
)

data class EpisodeResult(
    val id: Long,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
)