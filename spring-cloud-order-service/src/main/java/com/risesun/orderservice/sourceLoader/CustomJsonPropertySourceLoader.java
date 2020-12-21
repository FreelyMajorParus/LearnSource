package com.risesun.orderservice.sourceLoader;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.util.StringUtils;

import java.util.Map;

public class CustomJsonPropertySourceLoader extends EnumerablePropertySource<Map<String,Object>> {

    public CustomJsonPropertySourceLoader(String name, Map<String, Object> source) {
        super(name, source);
    }

    protected CustomJsonPropertySourceLoader(String name) {
        super(name);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(this.source.keySet());
    }

    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }
}
