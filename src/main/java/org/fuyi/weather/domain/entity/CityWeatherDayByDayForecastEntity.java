package org.fuyi.weather.domain.entity;

import lombok.*;
import org.fuyi.weather.infra.common.constant.WeatherProxyConstant;
import org.fuyi.weather.infra.common.entity.AbstractWeatherProxyWrappedEntity;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/8 下午10:01
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CityWeatherDayByDayForecastEntity extends AbstractWeatherProxyWrappedEntity {
    @Override
    public String getDataColumn() {
        return WeatherProxyConstant.DataColumn.DAILY;
    }
}