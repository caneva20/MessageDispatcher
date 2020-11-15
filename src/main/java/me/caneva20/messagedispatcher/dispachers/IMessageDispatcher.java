package me.caneva20.messagedispatcher.dispachers;

import org.bukkit.command.CommandSender;

public interface IMessageDispatcher {
    void raw(CommandSender to, String message);

    void info(CommandSender to, String message);

    void warn(CommandSender to, String message);

    void success(CommandSender to, String message);

    void error(CommandSender to, String message);

    void debug(CommandSender to, String message);
}
