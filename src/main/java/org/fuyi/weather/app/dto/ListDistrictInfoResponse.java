package org.fuyi.weather.app.dto;

import lombok.*;
import org.fuyi.weather.infra.common.api.BaseResponse;

import java.util.List;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午9:26
 * @since: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ListDistrictInfoResponse extends BaseResponse {
    private List<AdministrativeDivisionDto> data;
}
