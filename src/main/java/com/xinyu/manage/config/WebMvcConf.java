package com.xinyu.manage.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author huangxk
 * @date 2020/5/26
 * @Description:
 */
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        MappingJackson2HttpMessageConverter jacksonConverter=new MappingJackson2HttpMessageConverter(objectMapper);
////        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
////        FastJsonConfig fastJsonConfig = new FastJsonConfig();
////        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
////        fastConverter.setFeatures();
//        converters.add(jacksonConverter);
//    }
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.PrettyFormat
//        );
//
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastConverter);
//    }
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        // 1、定义一个convert转换消息的对象
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        // 2、添加fastjson的配置信息
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        // 3、在convert中添加配置信息
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        // 4、将convert添加到converters中
//        converters.add(fastConverter);
//        // 5、追加默认转换器
//        addDefaultHttpMessageConverters(converters);
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        //自定义配置...
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures(SerializerFeature.QuoteFieldNames,
//                SerializerFeature.WriteEnumUsingToString,
//                /*SerializerFeature.WriteMapNullValue,*/
//                SerializerFeature.WriteDateUseDateFormat,
//                SerializerFeature.DisableCircularReferenceDetect);
//        fastJsonHttpMessageConverter.setFastJsonConfig(config);
//        converters.add(fastJsonHttpMessageConverter);
//    }

    /**
     * 使用fastJson的方式一     需要  extends WebMvcConfigurationSupport
     * 1.需要先定义一个convert转换消息的对象
     * 2.添加fastJson的配置信息，比如：是否要格式化返回的json数据
     * 3.在convert中添加配置信息
     * 4.将convert添加到converts当中
     * @param converters
     *  这种方式才能解决乱码问题，下面的方式二我试了一下，不得行
     *  原文链接：https://blog.csdn.net/qq_33371766/article/details/82220605
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        for (HttpMessageConverter<?> converter : converters) {
//            if (converter instanceof MappingJackson2HttpMessageConverter){
//                converters.remove(converter);
//            }
//        }
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while(iterator.hasNext()){
            HttpMessageConverter<?> converter = iterator.next();
            if(converter instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }
        }
        super.configureMessageConverters(converters);
        //1.需要先定义一个convert转换消息对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据
        FastJsonConfig fastConfig=new FastJsonConfig();
        fastConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //处理中文乱码问题(不然出现中文乱码)
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastConfig);
        //4.将convert添加到converts当中
        converters.add(fastConverter);
    }

    /**
     * 这种方式不需要 extends WebMvcConfigurationSupport
     * 在这里使用@Bean注入 使用fastJson方式二
     */
   /*@Bean
   public HttpMessageConverters fastJsonConverters() {
      //1.需要先定义一个convert转换消息对象
      FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
      //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据
      FastJsonConfig fastConfig=new FastJsonConfig();
      fastConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
      //处理中文乱码问题(不然出现中文乱码)
      List<MediaType> fastMediaTypes = new ArrayList<>();
      fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
      fastConverter.setSupportedMediaTypes(fastMediaTypes);
      //3.在convert中添加配置信息
      fastConverter.setFastJsonConfig(fastConfig);
      //4.将convert添加到converts当中
      HttpMessageConverter<?> converter=fastConverter;
      return new HttpMessageConverters(converter);
   }*/



}