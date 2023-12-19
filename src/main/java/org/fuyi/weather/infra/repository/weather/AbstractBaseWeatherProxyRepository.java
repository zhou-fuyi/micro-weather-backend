package org.fuyi.weather.infra.repository.weather;

import org.fuyi.weather.infra.common.entity.AbstractWeatherProxyWrappedEntity;
import org.fuyi.weather.infra.util.ApiParseHelper;
import org.fuyi.weather.infra.util.WeatherProxyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午4:15
 * @since: 1.0
 */
public abstract class AbstractBaseWeatherProxyRepository<T extends AbstractWeatherProxyWrappedEntity> implements BaseWeatherProxyRepository<T> {

    @Value("${micro-weather.data.service.public-key}")
    private String publicKey;

    @Value("${micro-weather.data.service.secret-key}")
    private String secretKey;

    private RestTemplate restTemplate;

    private static Logger logger = LoggerFactory.getLogger(AbstractBaseWeatherProxyRepository.class.getName());

    public AbstractBaseWeatherProxyRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public T fetchData(Map<String, String> params, T t) {
        Map<String, String> completeParams = new HashMap<>();
        completeParams.putAll(params);
        completeParams.put("publicid", publicKey);
        completeParams.put("t", String.valueOf(System.currentTimeMillis()));
        String signature = "";
        try {
            signature = getSignature(completeParams, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.hasText(signature)) {
            throw new RuntimeException("无法通过参数生成签名.");
        }
        completeParams.put("sign", signature);
        String fullUrl = ApiParseHelper.parse(obtainApi(), completeParams);
        logger.info(String.format("The full url is [%s]", fullUrl));
        return doFetchData(fullUrl, t);
    }

    /**
     * 设置访问的API
     *
     * @return
     */
    protected abstract String obtainApi();

    protected T doFetchData(String url, T t) {
        return WeatherProxyHelper.filling(restTemplate.getForObject(url, String.class), t);
    }

    /**
     * 和风天气签名生成算法-JAVA版本
     *
     * @param params params 请求参数集，所有参数必须已转换为字符串类型
     * @param secret secret 签名密钥（用户的认证key）
     * @return 签名
     * @throws Exception
     */
    public static String getSignature(Map<String, String> params, String secret) throws Exception {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            //sign参数 和 空值参数 不加入算法
            if (param.getValue() != null && !"".equals(param.getKey().trim()) && !"sign".equals(param.getKey().trim()) && !"key".equals(param.getKey().trim()) && !"".equals(param.getValue().trim())) {
                baseString.append(param.getKey().trim()).append("=").append(param.getValue().trim()).append("&");
            }
        }
        if (baseString.length() > 0) {
            baseString.deleteCharAt(baseString.length() - 1).append(secret);
        }
        // 使用MD5对待签名串求签
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(baseString.toString().getBytes("UTF-8"));
            return new String(encodeHex(bytes));
        } catch (GeneralSecurityException ex) {
            throw ex;
        }
    }

    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;
        char[] toDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int var5 = 0; i < l; ++i) {
            out[var5++] = toDigits[(240 & data[i]) >>> 4];
            out[var5++] = toDigits[15 & data[i]];
        }
        return out;
    }
}
