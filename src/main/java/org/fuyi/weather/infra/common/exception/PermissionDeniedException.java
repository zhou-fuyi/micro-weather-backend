package org.fuyi.weather.infra.common.exception;

import lombok.Getter;
import org.fuyi.weather.infra.common.api.ResultCode;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午4:30
 * @since: 1.0
 */
public class PermissionDeniedException extends RuntimeException{

    @Getter
    private final ResultCode resultCode;

    public PermissionDeniedException(String message) {
        super(message);
        this.resultCode = ResultCode.UN_AUTHORIZED;
    }

    public PermissionDeniedException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public PermissionDeniedException(ResultCode resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
    }

    public PermissionDeniedException(ResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

}
