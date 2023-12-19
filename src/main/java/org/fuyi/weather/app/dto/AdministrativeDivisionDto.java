package org.fuyi.weather.app.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.*;
import org.fuyi.weather.infra.common.dto.AbstractBaseDto;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午8:19
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AdministrativeDivisionDto extends AbstractBaseDto {

    /**
     * 行政区划名称
     */
    private String name;

    private String enName;

    private String varName;

    /**
     * 行政区划等级（省级行政区：1， 地级行政区：2， 县级行政区：3，乡级行政区：4）
     */
    private Integer grade;
    /**
     * 行政区代码
     */
    private String code;
    /**
     * 行政区中心点
     */
    private JSONObject centerPoint;
    /**
     * 行政区边界
     */
    private JSONObject bounds;

    private String type;

    private String remark;

    private Integer timeline;
}
