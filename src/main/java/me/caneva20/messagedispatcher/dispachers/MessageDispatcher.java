package me.caneva20.messagedispatcher.dispachers;

import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.IMessageParser;
import me.caneva20.messagedispatcher.utils.Params;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class MessageDispatcher implements IMessageDispatcher {
    protected final IMessageParser parser;

    protected MessageDispatcher(IMessageParser parser) {
        this.parser = parser;
    }

    protected String format(String rawMessage, MessageLevel level, Params params) {
        String message = parser.parse(rawMessage, level, params.build());

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    protected String format(String rawMessage, MessageLevel level) {
        return format(rawMessage, level, Params.EMPTY_PARAMS);
    }

    @Override
    public void raw(CommandSender to, String message) {
        to.sendMessage(message);
    }
}
