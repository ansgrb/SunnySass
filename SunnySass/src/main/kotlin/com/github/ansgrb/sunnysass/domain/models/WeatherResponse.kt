package com.github.ansgrb.sunnysass.domain.models

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherResponse(
	@JsonProperty("main")
	val main: Main,
	@JsonProperty("weather")
	val weather: List<Weather>
)
