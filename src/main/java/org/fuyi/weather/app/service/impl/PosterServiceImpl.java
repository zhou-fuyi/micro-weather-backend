package org.fuyi.weather.app.service.impl;

import org.fuyi.weather.app.dto.qce.WeatherPosterCommand;
import org.fuyi.weather.app.service.PosterService;
import org.fuyi.weather.domain.entity.poster.WeatherPoster;
import org.fuyi.weather.infra.common.exception.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/20 下午3:34
 * @since: 1.0
 */
@Service
public class PosterServiceImpl implements PosterService {

    @Value("${micro-weather.poster.directory}")
    private String posterStoragePath;

    @Value("${micro-weather.poster.key}")
    private String key;

    @Value("${micro-weather.poster.api}")
    private String api;

    @Value("${micro-weather.poster.proxy}")
    private String proxy;

    private final ModelMapper mapper;

    public PosterServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String weatherPoster(WeatherPosterCommand command) {
        WeatherPoster poster = buildPoster(command);
        try {
            poster.draw(posterStoragePath);
        }catch (IOException exception){
            throw new ServiceException(exception.getMessage());
        }
        return poster.getUrl();
    }

    private WeatherPoster buildPoster(WeatherPosterCommand command){
        WeatherPoster poster = new WeatherPoster(api, key, proxy);
        mapper.map(command, poster);
        return poster;
    }
}
