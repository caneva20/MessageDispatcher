package me.caneva20.messagedispatcher.dispachers;

import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.IMessageParser;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.Map;

public abstract class MessageDispatcher implements IMessageDispatcher {
    protected final IMessageParser parser;

    protected MessageDispatcher(IMessageParser parser) {
        this.parser = parser;
    }

    protected String format(String rawMessage, MessageLevel level, Map<String, String> params) {
        String message = parser.parse(rawMessage, level, params);

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    protected String format(String rawMessage, MessageLevel level) {
        return format(rawMessage, level, Collections.emptyMap());
    }

    @Override
    public void raw(CommandSender to, String message) {
        to.sendMessage(message);
    }
}
