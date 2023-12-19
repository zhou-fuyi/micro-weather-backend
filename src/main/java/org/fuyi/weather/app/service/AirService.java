package org.fuyi.weather.app.service;

import org.fuyi.weather.domain.entity.AirRealTimeEntity;

/**
 * 空气质量服务
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午11:28
 * @since: 1.0
 */
public interface AirService {
    /**
     * 获取实时空气质量
     * @param location 区域信息，区域ID或经纬度
     * @return
     */
    AirRealTimeEntity fetchRealTime(String location);
}
