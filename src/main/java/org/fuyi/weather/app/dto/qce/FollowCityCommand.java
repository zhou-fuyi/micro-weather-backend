package org.fuyi.weather.app.dto.qce;

import lombok.*;

import java.util.Objects;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午7:25
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FollowCityCommand {

    private Long id;

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

    public boolean isNew(){
        return Objects.isNull(id);
    }
}
