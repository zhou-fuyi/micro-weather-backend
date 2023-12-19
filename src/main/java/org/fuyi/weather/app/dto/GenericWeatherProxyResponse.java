package org.fuyi.weather.app.dto;

import com.alibaba.fastjson.JSON;
import lombok.*;
import org.fuyi.weather.infra.common.api.BaseResponse;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午5:14
 * @since: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GenericWeatherProxyResponse extends BaseResponse {
    private JSON data;
}
