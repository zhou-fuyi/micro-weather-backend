package org.fuyi.weather.infra.common.context;

import org.fuyi.weather.infra.common.constant.AuthConstant;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/6 上午12:17
 * @since: 1.0
 */
public class AuthContext {
    private static String getRequestHeader(String headerName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes)requestAttributes).getRequest();
            String value = request.getHeader(headerName);
            return value;
        }
        return null;
    }

    public static String getSubjectId() {
        return getRequestHeader(AuthConstant.CURRENT_SUBJECT_HEADER);
    }

    public static String getAuth() {
        return getRequestHeader(AuthConstant.AUTHORIZATION_HEADER);
    }
}
