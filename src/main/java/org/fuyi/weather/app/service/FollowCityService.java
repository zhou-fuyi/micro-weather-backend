package org.fuyi.weather.app.service;

import org.fuyi.weather.app.dto.FollowCityDto;
import org.fuyi.weather.app.dto.qce.FollowCityCommand;

import java.util.List;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午7:22
 * @since: 1.0
 */
public interface FollowCityService {

    FollowCityDto follow(FollowCityCommand cityCommand);

    void unFollow(Long id);

    FollowCityDto update(FollowCityCommand cityCommand);

    List<FollowCityDto> listAllFollowCitiesByUserId(Long userId);

}
