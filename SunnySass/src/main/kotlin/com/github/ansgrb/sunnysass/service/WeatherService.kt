package com.github.ansgrb.sunnysass.service

import com.github.ansgrb.sunnysass.domain.models.WeatherResponse
import com.github.ansgrb.sunnysass.domain.models.WeatherTipResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.random.Random

@Service
class WeatherService(
	private val restTemplate: RestTemplate
) {
	@Value("\${openweather.api.key}")
	private lateinit var apiKey: String

	@Value("\${openweather.api.url}")
	private lateinit var apiUrl: String

	@Value("\${openweather.api.units}")
	private lateinit var units: String

	private val tipsByWeather = mapOf(
		"Clear" to listOf(
			"It's sunny out there, ice cream time!",
			"Grab your sunglasses, it's a bright day!",
			"Perfect day for a picnic, don't you think?"
		),
		"Clouds" to listOf(
			"Cloudy vibes? Time for a cozy book!",
			"Gray skies call for a movie marathon!",
			"Clouds are just nature's blanket, snuggle up!"
		),
		"Rain" to listOf(
			"Rainy day? Dance in the puddles!",
			"Grab an umbrella, or just get wet!",
			"Rain's here, time for a tea party indoors!"
		),
		"Snow" to listOf(
			"Snow's falling, build a snowman army!",
			"Hot cocoa and snowball fights, let's go!",
			"Snowy day? Sled now, shovel later!"
		)
	)

	fun getWeatherAndTip(city: String): WeatherTipResponse {
		val url = "$apiUrl?q=$city&appid=$apiKey&units=$units"
		val response = restTemplate.getForObject(url, WeatherResponse::class.java)
			?: throw IllegalStateException("Failed to fetch weather data for $city")

		val temperature = response.main.temp
		val weatherCondition = response.weather.firstOrNull()?.main ?: "Clear"
		val tips = tipsByWeather[weatherCondition] ?: tipsByWeather["Clear"]!!
		val tip = tips[Random.nextInt(tips.size)]

		return WeatherTipResponse(temperature, tip)
	}

}