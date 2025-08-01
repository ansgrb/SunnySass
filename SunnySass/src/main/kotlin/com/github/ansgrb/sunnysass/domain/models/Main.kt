package com.github.ansgrb.sunnysass.domain.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Main(
	@JsonProperty("temp")
	val temp: Double
)