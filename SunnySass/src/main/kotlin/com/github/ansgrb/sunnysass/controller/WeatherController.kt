package com.github.ansgrb.sunnysass.controller

import com.github.ansgrb.sunnysass.domain.models.WeatherTipResponse
import com.github.ansgrb.sunnysass.service.WeatherService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity


data class CityRequest(val city: String)

@RestController
@RequestMapping("api/weather")
class WeatherController(
	private val weatherService: WeatherService
) {
	@PostMapping("/forecast")
	@Operation(
		summary = "Get weather forecast and a fun tip for a given city",
		description = "Returns the current temperature and a random fun tip based on the weather condition for the specified city.",
		responses = [
			ApiResponse(
				responseCode = "200",
				description = "Successful response with weather data and tip",
				content = [Content(schema = Schema(implementation = WeatherTipResponse::class))]
			),
			ApiResponse(responseCode = "400", description = "Invalid city name"),
			ApiResponse(responseCode = "500", description = "Server error")
		]
	)

	fun getWeatherAndTip(@RequestBody request: CityRequest): ResponseEntity<WeatherTipResponse> {
		return try {
			val response = weatherService.getWeatherAndTip(request.city)
			ResponseEntity.ok(response)
		} catch (_: IllegalStateException) {
			ResponseEntity.badRequest().build()
		}
	}
}