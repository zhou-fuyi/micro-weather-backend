package org.fuyi.weather.app.service;

import org.fuyi.weather.domain.entity.CityWeatherDayByDayForecastEntity;
import org.fuyi.weather.domain.entity.CityWeatherHourByHourForecastEntity;
import org.fuyi.weather.domain.entity.CityWeatherRealTimeEntity;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午5:10
 * @since: 1.0
 */
public interface CityWeatherService {
    /**
     * 获取实时天气信息
     * @param location 区域信息，区域ID或经纬度
     * @return
     */
    CityWeatherRealTimeEntity fetchRealTime(String location);

    CityWeatherHourByHourForecastEntity fetchHourByHourForecast(String location);

    CityWeatherDayByDayForecastEntity fetchDayByDayForecast(String location);
}
