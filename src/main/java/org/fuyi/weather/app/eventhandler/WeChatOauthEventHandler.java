package org.fuyi.weather.app.eventhandler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.fuyi.weather.app.dto.OAuthCheckResponseDto;
import org.fuyi.weather.app.dto.OAuthResponseDto;
import org.fuyi.weather.app.dto.UserInfoDto;
import org.fuyi.weather.app.dto.qce.UserInfoCommand;
import org.fuyi.weather.app.dto.qce.WeChatOauthEvent;
import org.fuyi.weather.app.service.UserInfoService;
import org.fuyi.weather.infra.common.api.ResultCode;
import org.fuyi.weather.infra.common.context.AuthContext;
import org.fuyi.weather.infra.common.exception.PermissionDeniedException;
import org.fuyi.weather.infra.common.exception.ServiceException;
import org.fuyi.weather.infra.util.JwtHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 上午11:06
 * @since: 1.0
 */
@Slf4j
@Component
public class WeChatOauthEventHandler {

    private static String WE_CHAT_OAUTH_API = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Value("${micro-weather.wechat.app-id}")
    private String appId;

    @Value("${micro-weather.wechat.secret}")
    private String secret;

    /**
     * 默认为七天
     */
    private static final Long DEFAULT_DURATION = 7 * 24 * 60 * 60 * 1000L;

    private RestTemplate restTemplate;

    private UserInfoService userInfoService;

    public WeChatOauthEventHandler(RestTemplate restTemplate, UserInfoService userInfoService) {
        this.restTemplate = restTemplate;
        this.userInfoService = userInfoService;
    }

    public OAuthResponseDto forSession(WeChatOauthEvent event) {
        String finalUrl = String.format(WE_CHAT_OAUTH_API, appId, secret, event.getCode());
        String result = restTemplate.getForObject(finalUrl, String.class);
        log.info("oauth result: {}", result);
        InternalResponseEntity responseEntity = new InternalResponseEntity(result);
        UserInfoDto userInfo = userInfoService.findByOid(responseEntity.getOpenId());
        if (Objects.isNull(userInfo)) {
            userInfo = userInfoService.register(UserInfoCommand
                    .builder()
                    .oid(responseEntity.getOpenId())
                    .uid(responseEntity.getUnionId())
                    .build());
        }
        if (Objects.isNull(userInfo)){
            throw new ServiceException("UserInfo can not be null. Maybe there is a problem with the registration function.");
        }
        String auth_token = JwtHelper.generateToken(userInfo.getId(), DEFAULT_DURATION);
        return OAuthResponseDto.builder().auth_token(auth_token).build();
    }

    public OAuthCheckResponseDto forCheck(){
        String authToken = AuthContext.getAuth();
        log.info("current auth_token is: " + authToken);
        if (!StringUtils.hasText(authToken)){
            throw new PermissionDeniedException(ResultCode.INVALID_TOKEN, "Can not find token from request header.");
        }
        if (Objects.isNull(JwtHelper.verifyToken(authToken))){
            throw new PermissionDeniedException(ResultCode.INVALID_TOKEN);
        }
        log.info("Token verification successful.");
        return new OAuthCheckResponseDto(authToken, Boolean.FALSE, Boolean.FALSE);
    }

    static class InternalResponseEntity {

        private String sessionKey;
        private String openId;
        private String unionId;

        public InternalResponseEntity(String response) {
            JSONObject result = JSONObject.parseObject(response);
            if (Objects.nonNull(result.get("errcode"))) {
                throw new ServiceException(String.format("auth.code2Session 调用失败: %s, %s", result.get("errcode"), result.get("errmsg")));
            }
            this.sessionKey = (String) result.get("session_key");
            this.openId = (String) result.get("openid");
            this.unionId = (String) result.get("unionid");
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public String getOpenId() {
            return openId;
        }

        public String getUnionId() {
            return unionId;
        }

        @Override
        public String toString() {
            return "InternalResponseEntity{" +
                    "sessionKey='" + sessionKey + '\'' +
                    ", openId='" + openId + '\'' +
                    ", unionId='" + unionId + '\'' +
                    '}';
        }
    }
}
