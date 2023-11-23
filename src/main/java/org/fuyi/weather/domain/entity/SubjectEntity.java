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
 * @time: 2022/2/5 下午6:57
 * @since: 1.0
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "subject", schema = "public", catalog = "micro_weather_db_v100")
public class SubjectEntity extends AbstractBaseEntity {

    private static final long serialVersionUID = 1877627759626408812L;

    /**
     * openid
     */
    private String oid;
    /**
     * UnionID
     */
    private String uid;
    /**
     * 昵称
     */
    private String name;
    /**
     * 手机号
     */
    private String phone_num;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 登录授权状态（Silence 静默、Authorized 已授权）
     */
    private String authState;
    /**
     * 记录创建时间
     */
    @CreationTimestamp
    private Timestamp createTime;
    /**
     * 记录的更新时间
     */
    @UpdateTimestamp
    private Timestamp updateTime;
}
