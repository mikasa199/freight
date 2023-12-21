package com.yang.freight.domain.config;

import com.yang.freight.common.JacksonObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/frontend/**").addResourceLocations("classpath:/frontend/");
        //registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     * 扩展mvc框架的消息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        //设置对象转换器，底层使用Jackson将Java对象转换为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());

        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0,messageConverter);
    }

//    /**
//     * 字体资源相关
//     * @param configurer
//     */
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//
//        // 为woff2文件类型设置MIME类型
//        MediaType mediaTypeWoff2 = MediaType.valueOf("application/font-woff2");
//        configurer.mediaType("woff2", mediaTypeWoff2);
//
//        // 为woff文件类型设置MIME类型
//        MediaType mediaTypeWoff = MediaType.valueOf("application/font-woff");
//        configurer.mediaType("woff", mediaTypeWoff);
//
//        // 为ttf文件类型设置MIME类型
//        MediaType mediaTypeTtf = MediaType.valueOf("application/octet-stream");
//        configurer.mediaType("ttf", mediaTypeTtf);
//    }
}