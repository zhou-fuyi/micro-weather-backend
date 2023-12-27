package org.fuyi.weather.app.dto.qce;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/20 下午3:28
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class WeatherPosterCommand {

    public static int[] DEFAULT_AQI_COLOR = new int[]{155, 167, 127};

    public static String DEFAULT_MARKER_COLOR = "blue";

    @NotBlank(message = "location can not be blank.")
    private String location;

    @NotBlank(message = "bounds can not be blank.")
    private String bounds;

    @NotBlank(message = "temperature can not be blank.")
    private String temperature;

    @NotBlank(message = "text can not be blank.")
    private String text;

    @NotBlank(message = "wind can not be blank.")
    private String wind;

    @NotBlank(message = "aqi can not be blank.")
    private String aqi;

    private int[] aqiColor;

    @NotBlank(message = "center can not be blank.")
    private String center;

    private String centerColor;
}
