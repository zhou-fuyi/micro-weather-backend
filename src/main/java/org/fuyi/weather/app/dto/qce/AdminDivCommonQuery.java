package org.fuyi.weather.app.dto.qce;

import lombok.*;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/22 下午5:45
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AdminDivCommonQuery {
    /**
     * 行政区划编码
     */
    private String code;
    /**
     * 行政区等级
     */
    private Integer grade;
    /**
     * 是否同步查询关联空间数据，默认不进行查询
     */
    @Builder.Default
    private Boolean spatialCapable = false;

    public boolean isEmpty (){
        return !StringUtils.hasText(code) && Objects.isNull(grade);
    }
}
