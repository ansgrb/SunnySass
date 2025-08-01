package com.github.ansgrb.sunnysass.domain.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Weather(
	@JsonProperty("main")
	val main: String
)