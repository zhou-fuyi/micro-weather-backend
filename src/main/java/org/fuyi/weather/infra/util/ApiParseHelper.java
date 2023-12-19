package org.fuyi.weather.infra.util;

import org.apache.commons.lang3.StringUtils;
import org.fuyi.weather.infra.common.constant.WeatherProxyConstant;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午5:31
 * @since: 1.0
 */
public class ApiParseHelper {

    /**
     * 路径参数解析与替换
     * <p>
     * 名称严格匹配优先，其次按顺序替换
     * <p>
     * 注：由于HashMap是无序的，所以无法保证数据是按照插入排序的，TreeMap默认升序排序，如果不确定键值是否与api的键值匹配，
     * 则可以使用顺序特性进行替换，但传入的Map实例必须是能够保证按插入排序的，如：LinkedHashMap
     *
     * @param api
     * @param params
     * @return
     */
    public static String parse(String api, Map<String, String> params) {
        // api 拆解
        int firstLocation = api.indexOf(WeatherProxyConstant.INDEX_SEPARATOR);
        if (firstLocation == -1) {
            return api;
        }
        String frontSegment = api.substring(0, firstLocation);
        String backSegment = api.substring(firstLocation);

        String processedBackSegment = "";
        String[] backSegmentBlock = backSegment.split(WeatherProxyConstant.SPLIT_SEPARATOR);
        Iterator<String> iterator = params.values().iterator();
        for (String block : backSegmentBlock) {
            String[] block_segments = block.split(WeatherProxyConstant.INTERRUPT_SEPARATOR);
            for (String block_segment : block_segments) {
                if (block_segment.startsWith(WeatherProxyConstant.FRONT_SEPARATOR) && block_segment.endsWith(WeatherProxyConstant.BACK_SEPARATOR)) {
                    block_segment = block_segment.substring(1, block_segment.length() - 1);
                    boolean next = true;
                    if (!StringUtils.isEmpty(params.get(block_segment))) {
                        block_segment = params.get(block_segment);
                    } else {
                        if (iterator.hasNext()) {
                            block_segment = iterator.next();
                            next = false;
                        } else {
                            throw new RuntimeException("传入参数个数错误，无法进行api替换");
                        }
                    }
                    if (next) {
                        if (iterator.hasNext()) {
                            iterator.next();
                        }
                    }
                } else {
                    block_segment += WeatherProxyConstant.INTERRUPT_SEPARATOR;
                }

                processedBackSegment += block_segment;
            }
            processedBackSegment += WeatherProxyConstant.SPLIT_SEPARATOR;
        }
        if (processedBackSegment.endsWith(WeatherProxyConstant.SPLIT_SEPARATOR)) {
            processedBackSegment = processedBackSegment.substring(0, processedBackSegment.length() - 1);
        }
        return frontSegment + processedBackSegment;
    }
}
