package com.risesun.orderservice.sourceLoader;

import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileInputStream;
import java.util.Map;

/**
 * Spring Boot提供的一个属性文件扩展接口
 * @author admin
 */
public class JsonPropertySourceLocator implements PropertySourceLocator {
    private final static String DEFAULT_LOCATION = "classpath:application.json";
    private final ResourceLoader resourceLoader = new DefaultResourceLoader(getClass().getClassLoader());
    @Override
    public PropertySource<?> locate(Environment environment) {
        return new CustomJsonPropertySourceLoader("CustomJsonPropertySourceLoader", mapPropertySource());
    }
    private Map<String,Object> mapPropertySource(){
        Resource resource = this.resourceLoader.getResource(DEFAULT_LOCATION);
        if(resource == null){
            return null;
        }
        String fileStrContent = getFileStrContent(resource);
        return jsonContent2Map(fileStrContent);
    }
    private String getFileStrContent(Resource resource){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(resource.getFilename());
            byte[] bytes = new byte[(int) resource.getFile().length()];
            fileInputStream.read(bytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Map<String ,Object> jsonContent2Map(String content){
        return JsonParserFactory.getJsonParser().parseMap(content);
    }
}

