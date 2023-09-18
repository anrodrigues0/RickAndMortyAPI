package com.study.drawer.model

data class LocationRoot(
    val info: Info,
    val results: List<LocationResult>,
)

data class LocationResult(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
)
