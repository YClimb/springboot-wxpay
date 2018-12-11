package com.demo.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置RestTemplate
 */
@Configuration
public class RestTemplateConfig {

    private Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

    @Bean
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory) {

        RestTemplate restTemplate = new LogRestTemplate(httpComponentsClientHttpRequestFactory);

        //覆盖RestTemplate内置StringHttpMessageConverter，使其支持中文
        List<HttpMessageConverter<?>> clist = restTemplate.getMessageConverters();

        List<HttpMessageConverter<?>> replaceHMC = new ArrayList<>();
        for (HttpMessageConverter<?> item : clist) {
            if (item.getClass() == StringHttpMessageConverter.class || item.getClass() == MappingJackson2HttpMessageConverter.class) {
                replaceHMC.add(item);
            }
        }
        clist.removeAll(replaceHMC);
        StringHttpMessageConverter httpMsgCvt = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        clist.add(httpMsgCvt);
        //clist.add(fastJsonHttpMessageConverter);
        return restTemplate;
    }

    /**
     * 超时配置
     *
     * @param poolingHttpClientConnectionManager
     * @return
     */
    @Bean
    @ConfigurationProperties("xxx.rest-template.timeout")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setHttpClient(HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager).build());
        return httpRequestFactory;
    }

    /**
     * 连接池配置
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("xxx.rest-template.pool")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager();
        return phccm;
    }

    /**
     * 装饰RestTemplate,使其打印请求前后日志
     */
    class LogRestTemplate extends RestTemplate {

        public LogRestTemplate(ClientHttpRequestFactory requestFactory) {
            super(requestFactory);
        }

        protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
                                  ResponseExtractor<T> responseExtractor) throws RestClientException {
            Instant startInst = Instant.now();
            T result = super.doExecute(url, method, requestCallback, responseExtractor);
            Instant endInst = Instant.now();
            logger.info("接口(" + url + ")执行时间为 >>>>>>> : " + Duration.between(startInst, endInst).toMillis());
            return result;
        }
    }

}
