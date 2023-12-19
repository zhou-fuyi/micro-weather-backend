package org.fuyi.weather.app.dto.qce;

import lombok.*;

/**
 * 为了全文检索准备
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/4 上午11:49
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AdminDivFullTextQuery {
    /**
     * 行政区名称
     */
    private String name;
    /**
     * 行政区等级
     */
    @Builder.Default
    private Integer grade = 4;
    /**
     * 是否同步查询关联空间数据，默认不进行查询
     */
    @Builder.Default
    private Boolean spatialCapable = false;

    /**
     * 等级反转, 默认开启
     *
     * 即控制查询where条件中grade使用'=' 还是 '!='
     */
    @Builder.Default
    private Boolean gradeInversion = true;
}
