package org.fuyi.weather.infra.common.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 天气代理实体基础类
 *
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午3:22
 * @since: 1.0
 */
public abstract class AbstractWeatherProxyWrappedEntity implements Serializable {

    private static final long serialVersionUID = 5559333755209092109L;
    /**
     * API状态码，具体含义请参考状态码
     * 参考 <a>https://dev.qweather.com/docs/resource/status-code/</a>
     */
    private String code;
    /**
     * 当前API的最近更新时间
     * 参考 <a>https://dev.qweather.com/docs/resource/glossary/#update-time</a>
     */
    private String updateTime;
    /**
     * 当前数据的响应式页面，便于嵌入网站或应用
     */
    private String fxLink;

    private JSONObject refer;

    private JSON data;

    public AbstractWeatherProxyWrappedEntity() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public JSONObject getRefer() {
        return refer;
    }

    public void setRefer(JSONObject refer) {
        this.refer = refer;
    }

    public JSON getData() {
        return data;
    }

    public void setData(JSON data) {
        this.data = data;
    }

    public abstract String getDataColumn();

    @Override
    public String toString() {
        return "WeatherProxyWrappedEntity{" +
                "code='" + code + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", fxLink='" + fxLink + '\'' +
                ", refer=" + refer.toJSONString() +
                ", data=" + data.toJSONString() +
                '}';
    }
}
