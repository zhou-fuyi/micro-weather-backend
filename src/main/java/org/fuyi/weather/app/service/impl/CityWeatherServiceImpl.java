package org.fuyi.weather.app.service.impl;

import org.fuyi.weather.app.service.CityWeatherService;
import org.fuyi.weather.domain.entity.CityWeatherDayByDayForecastEntity;
import org.fuyi.weather.domain.entity.CityWeatherHourByHourForecastEntity;
import org.fuyi.weather.domain.entity.CityWeatherRealTimeEntity;
import org.fuyi.weather.infra.repository.weather.CityWeatherDayByDayForecastRepository;
import org.fuyi.weather.infra.repository.weather.CityWeatherHourByHourForecastRepository;
import org.fuyi.weather.infra.repository.weather.CityWeatherRealTimeRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午5:11
 * @since: 1.0
 */
@Service
public class CityWeatherServiceImpl implements CityWeatherService {

    private CityWeatherRealTimeRepository cityWeatherRealTimeRepository;
    private CityWeatherHourByHourForecastRepository cityWeatherHourByHourForecastRepository;
    private CityWeatherDayByDayForecastRepository cityWeatherDayByDayForecastRepository;

    public CityWeatherServiceImpl(CityWeatherRealTimeRepository cityWeatherRealTimeRepository, CityWeatherHourByHourForecastRepository cityWeatherHourByHourForecastRepository, CityWeatherDayByDayForecastRepository cityWeatherDayByDayForecastRepository) {
        this.cityWeatherRealTimeRepository = cityWeatherRealTimeRepository;
        this.cityWeatherHourByHourForecastRepository = cityWeatherHourByHourForecastRepository;
        this.cityWeatherDayByDayForecastRepository = cityWeatherDayByDayForecastRepository;
    }

    @Override
    public CityWeatherRealTimeEntity fetchRealTime(String location) {
        Map<String, String> params = Map.of("location", location);
        return cityWeatherRealTimeRepository.fetchData(params, new CityWeatherRealTimeEntity());
    }

    @Override
    public CityWeatherHourByHourForecastEntity fetchHourByHourForecast(String location) {
        Map<String, String> params = Map.of("location", location);
        return cityWeatherHourByHourForecastRepository.fetchData(params, new CityWeatherHourByHourForecastEntity());
    }

    @Override
    public CityWeatherDayByDayForecastEntity fetchDayByDayForecast(String location) {
        Map<String, String> params = Map.of("location", location);
        return cityWeatherDayByDayForecastRepository.fetchData(params, new CityWeatherDayByDayForecastEntity());
    }
}
