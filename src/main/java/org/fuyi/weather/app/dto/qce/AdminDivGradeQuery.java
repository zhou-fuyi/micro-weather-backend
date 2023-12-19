package org.fuyi.weather.app.dto.qce;

import lombok.*;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午10:17
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AdminDivGradeQuery {
    /**
     * 行政区等级
     */
    private Integer grade;
    /**
     * 是否同步查询关联空间数据，默认不进行查询
     */
    @Builder.Default
    private Boolean spatial = false;
}
