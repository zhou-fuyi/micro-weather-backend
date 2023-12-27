package org.fuyi.weather.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.fuyi.weather.infra.common.entity.AbstractBaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午7:12
 * @since: 1.0
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "follow_city", schema = "public", catalog = "micro_weather_db_v100")
public class FollowCityEntity extends AbstractBaseEntity {

    private static final long serialVersionUID = -3177826873536331815L;
    public static final String FREE_STATE = "Free";
    public static final String FOCUS_STATE = "Focus";

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
     * Free（游离状态）
     * Focus（关注状态）
     */
    private String state;

    /**
     * 排序，自然数，从1
     */
    private Integer orderNum;

    /**
     * 记录的创建时间
     */
    @CreationTimestamp
    private Timestamp createTime;

    @UpdateTimestamp
    private Timestamp updateTime;
}
