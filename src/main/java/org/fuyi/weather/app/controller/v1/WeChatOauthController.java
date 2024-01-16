package org.fuyi.weather.app.controller.v1;

import org.fuyi.weather.app.dto.WeChatOauthCheckResponse;
import org.fuyi.weather.app.dto.WeChatOauthResponse;
import org.fuyi.weather.app.dto.qce.WeChatOauthEvent;
import org.fuyi.weather.app.eventhandler.WeChatOauthEventHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 上午11:13
 * @since: 1.0
 */
@RestController
@RequestMapping("/v1/oauth")
public class WeChatOauthController {

    private WeChatOauthEventHandler eventHandler;

    public WeChatOauthController(WeChatOauthEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @GetMapping("/we_chat")
    public WeChatOauthResponse weChatOauth(WeChatOauthEvent event){
        return new WeChatOauthResponse(eventHandler.forSession(event));
    }

    @GetMapping("/check")
    public WeChatOauthCheckResponse weChatOauthCheck(){
        return new WeChatOauthCheckResponse(eventHandler.forCheck());
    }
}
