package org.fuyi.weather.app.controller.v1;

import org.fuyi.weather.app.dto.GenericFollowCityResponse;
import org.fuyi.weather.app.dto.ListFollowCityResponse;
import org.fuyi.weather.app.dto.qce.FollowCityCommand;
import org.fuyi.weather.app.service.FollowCityService;
import org.fuyi.weather.infra.common.api.BaseResponse;
import org.fuyi.weather.infra.common.context.AuthContext;
import org.fuyi.weather.infra.util.JwtHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/6 上午12:49
 * @since: 1.0
 */
@RestController
@RequestMapping("/v1/follow_cities")
public class FollowCityController {

    private FollowCityService followCityService;

    public FollowCityController(FollowCityService followCityService) {
        this.followCityService = followCityService;
    }

    @PostMapping("/follow")
    public GenericFollowCityResponse follow(@RequestBody FollowCityCommand cityCommand){
        if (Objects.isNull(cityCommand.getSubjectId())){
            cityCommand.setSubjectId(JwtHelper.decodeForSubjectId(AuthContext.getAuth()));
        }
        return new GenericFollowCityResponse(followCityService.follow(cityCommand));
    }
    @GetMapping
    public ListFollowCityResponse listFollowCitiesByUserId(){
        Long userId = JwtHelper.decodeForSubjectId(AuthContext.getAuth());
        return new ListFollowCityResponse(followCityService.listAllFollowCitiesByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public BaseResponse unFollow(@PathVariable Long id){
        followCityService.unFollow(id);
        return BaseResponse.builder().build();
    }
}
