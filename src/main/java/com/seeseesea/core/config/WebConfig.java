package com.seeseesea.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.seeseesea.core.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebConfig
 */
@Configuration
public class WebConfig {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    /**
     * 配置Jackson
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(dateFormat);
        //对象中为null的字段就不序列化了
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);
        //json转对象时，json中的未知字段就不反序列化
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new ToStringSerializer());
        module.addSerializer(Long.class, new ToStringSerializer());
        module.addSerializer(Long.TYPE, new ToStringSerializer());

        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        objectMapper.registerModule(module);
        JsonUtils.setObjectMapper(objectMapper);
        log.info("{}ObjectMapper初始化完成{}", "=".repeat(20), "=".repeat(20));
        return objectMapper;
    }

    /**
     * 跨域过滤器
     */
    @Bean(name = "corsFilterRegistrationBean")
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        /*
         * 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
         */
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(source);
        //要提高跨域浏览器的优先级
        FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>(corsFilter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }


    /**
     * jdk21后 使用虚拟线程来执行异步函数
     */
    @Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
    public AsyncTaskExecutor asyncTaskExecutor(@Qualifier("executorService") ExecutorService executorService) {
        return new TaskExecutorAdapter(executorService);
    }

    /**
     * jdk21 后tomcat使用虚拟线程来处理协议
     */
    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler ->
                protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());

    }
}
