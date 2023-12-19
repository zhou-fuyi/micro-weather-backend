package org.fuyi.weather.app.controller.v1;

import org.fuyi.weather.app.dto.GenericWeatherProxyResponse;
import org.fuyi.weather.app.service.CityWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午5:12
 * @since: 1.0
 */
@RestController
@RequestMapping("/v1/city_weather")
public class CityWeatherController {

    private CityWeatherService cityWeatherService;

    public CityWeatherController(CityWeatherService cityWeatherService) {
        this.cityWeatherService = cityWeatherService;
    }

    @GetMapping("/{location}")
    public GenericWeatherProxyResponse realTime(@PathVariable String location){
        return new GenericWeatherProxyResponse(cityWeatherService.fetchRealTime(location).getData());
    }

    @GetMapping("/24h/{location}")
    public GenericWeatherProxyResponse hourByHourForecast(@PathVariable String location){
        return new GenericWeatherProxyResponse(cityWeatherService.fetchHourByHourForecast(location).getData());
    }

    @GetMapping("/7d/{location}")
    public GenericWeatherProxyResponse dayByDayForecast(@PathVariable String location){
        return new GenericWeatherProxyResponse(cityWeatherService.fetchDayByDayForecast(location).getData());
    }

}
