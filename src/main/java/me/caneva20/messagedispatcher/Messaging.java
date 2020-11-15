package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.configurations.DefaultConfiguration;
import me.caneva20.messagedispatcher.configurations.IConfiguration;
import me.caneva20.messagedispatcher.configurations.TokenConfiguration;
import me.caneva20.messagedispatcher.dispachers.*;
import org.bukkit.plugin.java.JavaPlugin;
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

    public static IMessageDispatcher createDispatcher(String tag, boolean useTag) {
        DefaultConfiguration defaultConfiguration = get(DefaultConfiguration.class);

        if (useTag) {
            TokenConfiguration tokenConfiguration = get(TokenConfiguration.class);
            return new PlayerMessageDispatcher(defaultConfiguration.getMessageParser(), tag, tokenConfiguration);
        }

        return new UntaggedPlayerMessageDispatcher(defaultConfiguration.getMessageParser());
    }

    public static IMessageDispatcher createDispatcher(JavaPlugin plugin, boolean useTag) {
        return createDispatcher(plugin.getName(), useTag);
    }

    public static IConsoleMessageDispatcher createConsoleDispatcher(JavaPlugin plugin, String tag, boolean useTag) {
        IMessageDispatcher dispatcher = createDispatcher(tag, useTag);

        return new ConsoleMessageDispatcher(plugin, dispatcher);
    }

    public static IConsoleMessageDispatcher createConsoleDispatcher(JavaPlugin plugin, boolean useTag) {
        return createConsoleDispatcher(plugin, plugin.getName(), useTag);
    }
}
