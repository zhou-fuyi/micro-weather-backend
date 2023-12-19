package org.fuyi.weather.infra.repository.weather;

import org.fuyi.weather.domain.entity.AirRealTimeEntity;
import org.fuyi.weather.infra.common.constant.WeatherProxyConstant;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午4:29
 * @since: 1.0
 */
@Repository
public class AirRealTimeRepository extends AbstractBaseWeatherProxyRepository<AirRealTimeEntity> {

    public AirRealTimeRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    protected String obtainApi() {
        return WeatherProxyConstant.Api.Air.REAL_TIME;
    }
}
