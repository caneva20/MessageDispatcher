package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.configurations.DefaultConfiguration;
import me.caneva20.messagedispatcher.configurations.IConfiguration;
import me.caneva20.messagedispatcher.configurations.TokenConfiguration;
import me.caneva20.messagedispatcher.dispachers.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class Messaging {
    private static final Map<Class<? extends IConfiguration>, IConfiguration> configMap = new HashMap<>();

    /**
     * Returns an instance of a configuration file that can either be used
     * to fetch a configuration for some feature or to set parameters that
     * a feature might use
     *
     * @param klass The class to configure
     * @return An instance of {@code kclass}
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T extends IConfiguration> T get(Class<T> klass) {
        var config = configMap.getOrDefault(klass, null);

        if (config == null) {
            try {
                config = klass.newInstance();

                configMap.put(klass, config);
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

    public static <T extends IConfiguration> void configure(Class<T> klass) {
        get(klass);
    }

    public static IMessageDispatcher createDispatcher(String tag, boolean useTag) {
        var defaultConfiguration = get(DefaultConfiguration.class);

        if (useTag) {
            var tokenConfiguration = get(TokenConfiguration.class);
            return new PlayerMessageDispatcher(defaultConfiguration.getMessageParser(), tag, tokenConfiguration);
        }

        return new UntaggedPlayerMessageDispatcher(defaultConfiguration.getMessageParser());
    }

    public static IMessageDispatcher createDispatcher(JavaPlugin plugin, boolean useTag) {
        return createDispatcher(plugin.getName(), useTag);
    }

    public static IConsoleMessageDispatcher createConsoleDispatcher(JavaPlugin plugin, String tag, boolean useTag) {
        var dispatcher = createDispatcher(tag, useTag);

        return new ConsoleMessageDispatcher(plugin, dispatcher);
    }

    public static IConsoleMessageDispatcher createConsoleDispatcher(JavaPlugin plugin, boolean useTag) {
        return createConsoleDispatcher(plugin, plugin.getName(), useTag);
    }
}
