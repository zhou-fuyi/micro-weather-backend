package org.fuyi.weather.app.dto.qce;

import lombok.*;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/19 下午10:31
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AdminDivGradeAndCodeLikeQuery {
    private String code;
    private Integer grade;
    @Builder.Default
    private Boolean spatial = false;
}
