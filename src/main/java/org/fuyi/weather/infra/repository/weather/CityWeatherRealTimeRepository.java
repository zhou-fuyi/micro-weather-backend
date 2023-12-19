package org.fuyi.weather.infra.repository.weather;

import lombok.extern.slf4j.Slf4j;
import org.fuyi.weather.domain.entity.CityWeatherRealTimeEntity;
import org.fuyi.weather.infra.common.constant.WeatherProxyConstant;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午5:07
 * @since: 1.0
 */
@Slf4j
@Repository
public class CityWeatherRealTimeRepository extends AbstractBaseWeatherProxyRepository<CityWeatherRealTimeEntity>{

    public CityWeatherRealTimeRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    protected String obtainApi() {
        return WeatherProxyConstant.Api.CityWeather.REAL_TIME;
    }
}
