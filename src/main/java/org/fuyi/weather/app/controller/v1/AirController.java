package org.fuyi.weather.app.controller.v1;

import org.fuyi.weather.app.dto.GenericWeatherProxyResponse;
import org.fuyi.weather.app.service.AirService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午11:32
 * @since: 1.0
 */
@RestController
@RequestMapping("/v1/air")
public class AirController {

    private AirService airService;

    public AirController(AirService airService) {
        this.airService = airService;
    }

    @GetMapping("/{location}")
    public GenericWeatherProxyResponse realTime(@PathVariable String location){
        return new GenericWeatherProxyResponse(airService.fetchRealTime(location).getData());
    }
}
