package org.fuyi.weather.infra.repository.weather;

import org.fuyi.weather.infra.common.entity.AbstractWeatherProxyWrappedEntity;

import java.util.Map;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午4:12
 * @since: 1.0
 */
public interface BaseWeatherProxyRepository<T extends AbstractWeatherProxyWrappedEntity> {
    T fetchData(Map<String, String> params, T t);
}
