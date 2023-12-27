package org.fuyi.weather.app.controller.v1;


import jakarta.validation.Valid;
import org.fuyi.weather.app.dto.GenericPosterResponse;
import org.fuyi.weather.app.dto.qce.WeatherPosterCommand;
import org.fuyi.weather.app.service.PosterService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/20 下午3:39
 * @since: 1.0
 */
@RestController
@RequestMapping("/v1/posters")
public class PosterController {

    private PosterService posterService;

    public PosterController(PosterService posterService) {
        this.posterService = posterService;
    }

    @PostMapping("/weather")
    public GenericPosterResponse weatherPoster(@RequestBody @Valid WeatherPosterCommand command){
        if (command.getAqiColor().length == 0){
            command.setAqiColor(WeatherPosterCommand.DEFAULT_AQI_COLOR);
        }
        if (!StringUtils.hasText(command.getCenterColor())){
            command.setCenterColor(WeatherPosterCommand.DEFAULT_MARKER_COLOR);
        }
        return new GenericPosterResponse(posterService.weatherPoster(command));
    }
}
