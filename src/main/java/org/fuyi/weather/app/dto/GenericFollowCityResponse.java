package org.fuyi.weather.app.dto;

import lombok.*;
import org.fuyi.weather.infra.common.api.BaseResponse;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/6 上午11:18
 * @since: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GenericFollowCityResponse extends BaseResponse {
    private FollowCityDto data;
}
