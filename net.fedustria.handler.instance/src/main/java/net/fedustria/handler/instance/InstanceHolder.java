package net.fedustria.handler.instance;

import java.util.HashMap;
import java.util.Map;

public class InstanceHolder {
    private static final Map<Class<?>, Object> instances = new HashMap<>();

    public static <T> void setInstance(Class<?> clazz, Object instance) {
        instances.put(clazz, instance);
    }

    public static <T> T getInstance(Class<T> clazz) {
        return clazz.cast(instances.get(clazz));
    }
}

