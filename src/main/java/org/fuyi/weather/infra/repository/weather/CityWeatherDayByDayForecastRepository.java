package org.fuyi.weather.infra.repository.weather;

import lombok.extern.slf4j.Slf4j;
import org.fuyi.weather.domain.entity.CityWeatherDayByDayForecastEntity;
import org.fuyi.weather.infra.common.constant.WeatherProxyConstant;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/8 下午10:02
 * @since: 1.0
 */
@Slf4j
@Repository
public class CityWeatherDayByDayForecastRepository extends AbstractBaseWeatherProxyRepository<CityWeatherDayByDayForecastEntity> {

    public CityWeatherDayByDayForecastRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    protected String obtainApi() {
        return WeatherProxyConstant.Api.CityWeather.DAY_BY_DAY;
    }
}
