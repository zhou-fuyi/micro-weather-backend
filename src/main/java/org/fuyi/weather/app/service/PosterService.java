package org.fuyi.weather.app.service;

import org.fuyi.weather.app.dto.qce.WeatherPosterCommand;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/20 下午3:27
 * @since: 1.0
 */
public interface PosterService {
    /**
     * 创建天气海报
     * @param command
     * @return
     */
    String weatherPoster(WeatherPosterCommand command);
}
