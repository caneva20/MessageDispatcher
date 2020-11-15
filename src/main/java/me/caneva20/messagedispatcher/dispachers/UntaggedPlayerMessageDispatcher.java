package me.caneva20.messagedispatcher.dispachers;

import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.IMessageParser;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.Map;

public class UntaggedPlayerMessageDispatcher extends MessageDispatcher {
    public UntaggedPlayerMessageDispatcher(IMessageParser parser) {
        super(parser);
    }

    private void send(CommandSender to, String message, MessageLevel level, Map<String, String> params) {
        to.sendMessage(format(message, level, params));
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
        send(to, message, MessageLevel.DEBUG, params);
    }

    @Override
    public void debug(CommandSender to, String message) {
        debug(to, message, Collections.emptyMap());
    }
}
