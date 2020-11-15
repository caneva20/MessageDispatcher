package me.caneva20.messagedispatcher.dispachers;

import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.IMessageParser;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.Map;

public class PlayerMessageDispatcher extends MessageDispatcher {
    public static class TokenConfiguration {
        private final String tag;
        private final String debugTag;
        private final String pluginNameParam;

        public TokenConfiguration(String tag, String debugTag, String pluginNameParam) {
            this.tag = tag;
            this.debugTag = debugTag;
            this.pluginNameParam = pluginNameParam;
        }
    }

    private final String tagName;
    private final TokenConfiguration configuration;

    public PlayerMessageDispatcher(IMessageParser parser, String tagName, TokenConfiguration configuration) {
        super(parser);

        this.tagName = tagName;
        this.configuration = configuration;
    }

    public PlayerMessageDispatcher(IMessageParser parser, JavaPlugin plugin, TokenConfiguration configuration) {
        this(parser, plugin.getName(), configuration);
    }

    private void send(CommandSender to, String message, MessageLevel level, Map<String, String> params) {
        params.put(configuration.pluginNameParam, tagName);

        message = format(String.format("$%s<%s>%s", configuration.tag, tagName, message),
                level,
                params
        );

        to.sendMessage(message);
    }

    private void send(CommandSender to, String message, MessageLevel level) {
        send(to, message, level, Collections.emptyMap());
    }

    @Override
    public void info(CommandSender to, String message, Map<String, String> params) {
        send(to, message, MessageLevel.INFO, params);
    }

    @Override
    public void info(CommandSender to, String message) {
        send(to, message, MessageLevel.INFO);
    }

    @Override
    public void warn(CommandSender to, String message, Map<String, String> params) {
        send(to, message, MessageLevel.WARNING, params);
    }

    @Override
    public void warn(CommandSender to, String message) {
        send(to, message, MessageLevel.WARNING);
    }

    @Override
    public void success(CommandSender to, String message, Map<String, String> params) {
        send(to, message, MessageLevel.SUCCESS, params);
    }

    @Override
    public void success(CommandSender to, String message) {
        send(to, message, MessageLevel.SUCCESS);
    }

    @Override
    public void error(CommandSender to, String message, Map<String, String> params) {
        send(to, message, MessageLevel.ERROR, params);
    }

    @Override
    public void error(CommandSender to, String message) {
        send(to, message, MessageLevel.ERROR);
    }

    @Override
    public void debug(CommandSender to, String message, Map<String, String> params) {
        params.put(configuration.pluginNameParam, tagName);

        message = format(String.format("$%s<%s>%s", configuration.debugTag, tagName, message),
                MessageLevel.DEBUG,
                params
        );

        to.sendMessage(message);
    }

    @Override
    public void debug(CommandSender to, String message) {
        debug(to, message, Collections.emptyMap());
    }
}
