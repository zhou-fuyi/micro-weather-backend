package org.fuyi.weather.app.dto;

import lombok.*;
import org.fuyi.weather.infra.common.dto.AbstractBaseDto;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午10:39
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserInfoDto extends AbstractBaseDto {
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

}
