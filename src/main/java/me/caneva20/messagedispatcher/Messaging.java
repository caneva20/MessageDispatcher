package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.configurations.IConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class Messaging {
    private static final Map<Class<? extends IConfiguration>, IConfiguration> configMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T extends IConfiguration> T get(Class<T> klass) {
        IConfiguration config = configMap.getOrDefault(klass, null);

        if (config == null) {
            try {
                config = klass.newInstance();
            } catch (Exception e) {
                System.out.println("Could not configure type " + klass.getName() + ". Does it contain a parameterless constructor?");

                throw new RuntimeException(e);
            }
        }

        return (T) config;
    }

    public static <T extends IConfiguration> void configure(Class<T> klass, Consumer<T> fn) {
        fn.accept(get(klass));
    }
}
