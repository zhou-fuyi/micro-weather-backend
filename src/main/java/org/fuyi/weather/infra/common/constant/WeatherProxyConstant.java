package org.fuyi.weather.infra.common.constant;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午3:58
 * @since: 1.0
 */
public interface WeatherProxyConstant {

    /**
     * 前分隔符
     */
    String FRONT_SEPARATOR = "{";

    /**
     * 后分隔符
     */
    String BACK_SEPARATOR = "}";

    /**
     * 分隔符
     * 用于将URL打断为一组Block
     * location={location}&key={key}
     */
    String SPLIT_SEPARATOR = "&";

    /**
     * 用于将Block打断
     * location={location}
     */
    String INTERRUPT_SEPARATOR = "=";

    String INDEX_SEPARATOR = "?";

    interface DataColumn {

        String NOW = "now";

        String DAILY = "daily";

        String HOURLY = "hourly";

        String WARNING = "warning";

        String WARNING_LOC_LIST = "warningLocList";
    }

    interface Api {
        interface CityWeather {
            // 实时天气
            String REAL_TIME = "https://devapi.qweather.com/v7/weather/now?location={location}&publicid={publicid}&t={t}&sign={sign}";

            // 逐天天气预报
            String DAY_BY_DAY = "https://devapi.qweather.com/v7/weather/7d?location={location}&publicid={publicid}&t={t}&sign={sign}";

            // 逐小时天气预报
            String HOUR_BY_HOUR = "https://devapi.qweather.com/v7/weather/24h?location={location}&publicid={publicid}&t={t}&sign={sign}";
        }

        interface Air {
            // 实时空气质量
            String REAL_TIME = "https://devapi.qweather.com/v7/air/now?location={location}&publicid={publicid}&t={t}&sign={sign}";
        }
    }
}
