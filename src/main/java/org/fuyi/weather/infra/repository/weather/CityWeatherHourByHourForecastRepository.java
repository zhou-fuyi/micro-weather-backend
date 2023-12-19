package org.fuyi.weather.infra.repository.weather;

import lombok.extern.slf4j.Slf4j;
import org.fuyi.weather.domain.entity.CityWeatherHourByHourForecastEntity;
import org.fuyi.weather.infra.common.constant.WeatherProxyConstant;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/7 下午10:58
 * @since: 1.0
 */
@Slf4j
@Repository
public class CityWeatherHourByHourForecastRepository extends AbstractBaseWeatherProxyRepository<CityWeatherHourByHourForecastEntity> {

    public CityWeatherHourByHourForecastRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    protected String obtainApi() {
        return WeatherProxyConstant.Api.CityWeather.HOUR_BY_HOUR;
    }
}
