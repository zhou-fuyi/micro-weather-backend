package org.fuyi.weather.infra.common.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 基础返回体
 *
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/1/18 下午10:10
 * @since: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"message", "code", "success"})
public class BaseResponse implements Serializable {

    /**
     * ResultCode中存放了固定的msg信息，是对code的描述
     * 这里提到的message是指额外的描述信息，可以是更细致的异常描述，也可以是业务异常的描述
     */
    private String message;

    @Builder.Default
    private ResultCode code = ResultCode.SUCCESS;

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS;
    }

    public void setCode(int code) {
        this.code = ResultCode.getInstanceByCode(code);
    }

    public int getCode() {
        return code.getCode();
    }

    public String getMessage() {
        if (!StringUtils.hasText(this.message)){
            return this.code.getMsg();
        }
        return this.message;
    }
}
