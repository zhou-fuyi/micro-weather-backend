package org.fuyi.weather.app.dto.qce;

import lombok.*;
import org.fuyi.weather.infra.common.constant.OAuthStateConstant;

import java.util.Objects;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午10:41
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserInfoCommand {

    private Long id;

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
    @Builder.Default
    private String authState = OAuthStateConstant.SILENCE_STATE;

    public boolean isNew(){
        return Objects.isNull(id);
    }
}
