package org.fuyi.weather.infra.common.api;

import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 接口返回状态码枚举
 *
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/18 下午10:10
 * @since: 1.0
 */
@AllArgsConstructor
public enum ResultCode implements Serializable {

    // 操作成功
    SUCCESS(HttpServletResponse.SC_OK, "Operation is Successful"),

    // 204 表示请求成功，但无资源返回
    NO_CONTENT(HttpServletResponse.SC_NO_CONTENT, "No content, please try again."),

    // 业务异常
    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "Biz Exception"),

    // 请求未经授权(未认证)
    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Request Unauthorized"),

    INVALID_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token."),

    // 资源不存在
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 Not Found"),

    // 请求传递参数无法读取
    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "Message Can't be Read"),

    // 不支持的方法
    METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Supported"),

    // 不支持的媒体类型
    MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported"),

    // 请求被拒绝，禁止访问(未授权)
    REQ_FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "Request Rejected"),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),

    // 缺少必须的参数
    PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "Missing Required Parameter"),

    // 参数类型不匹配
    PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Type Mismatch"),

    // 参数绑定错误
    PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Binding Error"),

    // 参数校验错误
    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error");

    final int code;

    final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResultCode getInstanceByCode(int code){
        for (ResultCode value : ResultCode.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        return ResultCode.SUCCESS;
    }
}
