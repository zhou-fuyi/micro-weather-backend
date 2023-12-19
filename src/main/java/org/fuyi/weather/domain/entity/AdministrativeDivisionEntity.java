package org.fuyi.weather.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.fuyi.weather.infra.common.entity.AbstractBaseEntity;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import java.sql.Timestamp;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2023/12/17 22:57
 * @since: 1.0
 **/
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "administrative_division", schema = "public", catalog = "micro_weather_db_v100")
public class AdministrativeDivisionEntity extends AbstractBaseEntity {

    /**
     * 行政区划名称
     */
    private String name;

    private String enName;

    private String varName;

    /**
     * 行政区代码
     */
    private String code;
    /**
     * 行政区中心点
     */
    private Point centerPoint;
    /**
     * 行政区边界
     */
    private Geometry bounds;

    /**
     * 行政区划等级（省级行政区：1， 地级行政区：2， 县级行政区：3）
     */
    private Integer grade;

    private String type;

    private String remark;

    private Integer timeline;

    private Timestamp createTime;

    private Timestamp updateTime;
}
