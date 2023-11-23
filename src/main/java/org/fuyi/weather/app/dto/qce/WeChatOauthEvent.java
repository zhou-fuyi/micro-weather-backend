package org.fuyi.weather.app.dto.qce;

import lombok.*;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 上午11:02
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class WeChatOauthEvent {
    private String code;
}
