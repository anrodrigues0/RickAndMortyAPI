package com.study.drawer.model

data class CharacterRoot (
    val info: Info,
    val results: List<Character>
)

data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

data class Origin(
    val name: String,
    val url: String,
)

data class Location(
    val name: String,
    val url: String,
)
