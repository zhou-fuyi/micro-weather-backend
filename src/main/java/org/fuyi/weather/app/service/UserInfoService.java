package org.fuyi.weather.app.service;

import org.fuyi.weather.app.dto.UserInfoDto;
import org.fuyi.weather.app.dto.qce.UserInfoCommand;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午10:30
 * @since: 1.0
 */
public interface UserInfoService {

    boolean existsByOid(String oid);

    UserInfoDto findByOid(String oid);

    UserInfoDto register(UserInfoCommand userInfoCommand);

    UserInfoDto update(UserInfoCommand userInfoCommand);

}
