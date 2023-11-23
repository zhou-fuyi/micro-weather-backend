package org.fuyi.weather.infra.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/1 下午4:09
 * @since: 1.0
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 超时时间
     *
     * 单位：毫秒
     */
    private static int TIME_OUT = 3 * 60 * 1000;

    @Bean
    public RestTemplate restTemplate(){
        CloseableHttpClient build = HttpClientBuilder.create()
                .build();
        var factory = new HttpComponentsClientHttpRequestFactory(build);
        factory.setConnectTimeout(TIME_OUT);
        return new RestTemplate(factory);
    }
}
