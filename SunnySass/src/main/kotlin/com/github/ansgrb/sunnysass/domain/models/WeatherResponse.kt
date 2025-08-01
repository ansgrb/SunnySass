package com.github.ansgrb.sunnysass.domain.models

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherResponse(
	@JsonProperty
	val main: Main,
	@JsonProperty
	val weather: List<Weather>
)
