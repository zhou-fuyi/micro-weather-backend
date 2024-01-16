package org.fuyi.weather.app.dto;

import lombok.*;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2024/1/10 15:43
 * @since: 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class OAuthCheckResponseDto {

    private String auth_token;
    private boolean expired;
    private boolean refreshed;
}
