package org.fuyi.weather.app.service.impl;

import org.fuyi.weather.app.service.AirService;
import org.fuyi.weather.domain.entity.AirRealTimeEntity;
import org.fuyi.weather.infra.repository.weather.AirRealTimeRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午11:30
 * @since: 1.0
 */
@Service
public class AirServiceImpl implements AirService {

    private AirRealTimeRepository realTimeRepository;

    public AirServiceImpl(AirRealTimeRepository realTimeRepository) {
        this.realTimeRepository = realTimeRepository;
    }

    @Override
    public AirRealTimeEntity fetchRealTime(String location) {
        Map<String, String> params = Map.of("location", location);
        return realTimeRepository.fetchData(params, new AirRealTimeEntity());
    }
}
