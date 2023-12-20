package org.fuyi.weather.app.dto;

import lombok.*;
import org.fuyi.weather.infra.common.dto.AbstractBaseDto;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午7:22
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FollowCityDto extends AbstractBaseDto {
    private static final long serialVersionUID = 1545804399795703819L;
    /**
     * 用户ID
     */
    private Long subjectId;

    /**
     * 城市行政区划ID
     */
    private Long divisionId;

    /**
     * 城市名称
     */
    private String divisionName;

    /**
     * 城市行政区划编码
     */
    private String divisionCode;

    /**
     * 排序，自然数，从1
     */
    private Integer orderNum;

    /**
     * Free（游离状态）
     * Focus（关注状态）
     */
    private String state;
}
