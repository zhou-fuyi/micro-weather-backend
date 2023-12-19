package org.fuyi.weather.infra.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fuyi.weather.infra.common.entity.AbstractWeatherProxyWrappedEntity;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午4:46
 * @since: 1.0
 */
public class WeatherProxyHelper {

    private static String UPDATE_TIME_COLUMN = "updateTime";

    /**
     * 解析网络请求后返回数据，基于此进行实体填充.
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T extends AbstractWeatherProxyWrappedEntity> T filling(String source, AbstractWeatherProxyWrappedEntity target) {
        String dataColumn = target.getDataColumn();
        JSONObject content = JSONObject.parseObject(source);
        target = JSONObject.toJavaObject(content, target.getClass());
        if (Objects.nonNull(target) && StringUtils.hasText(dataColumn)) {
            target.setData((JSON) JSONObject.toJSON(content.get(target.getDataColumn())));
            if (Objects.nonNull(content.get(UPDATE_TIME_COLUMN))){
                if (target.getData() instanceof JSONObject){
                    ((JSONObject) target.getData()).put(UPDATE_TIME_COLUMN, content.get(UPDATE_TIME_COLUMN));
                }else {
                    ((JSONArray) target.getData()).stream().map(item -> {
                        if (item instanceof JSONObject) {
                            ((JSONObject)item).put(UPDATE_TIME_COLUMN, content.get(UPDATE_TIME_COLUMN));
                        }
                        return item;
                    }).collect(Collectors.toList());
                }
            }
        }
        return (T) target;
    }

}
