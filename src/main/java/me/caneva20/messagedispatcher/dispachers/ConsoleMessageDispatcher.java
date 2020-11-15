package me.caneva20.messagedispatcher.dispachers;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class ConsoleMessageDispatcher implements IConsoleMessageDispatcher {
    private final IMessageDispatcher dispatcher;
    private final ConsoleCommandSender console;

    public ConsoleMessageDispatcher(JavaPlugin plugin, IMessageDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        console = plugin.getServer().getConsoleSender();
    }

    @Override
    public void raw(String message) {
        dispatcher.raw(console, message);
    }

    @Override
    public void info(String message, Map<String, String> params) {
        dispatcher.info(console, message, params);
    }

    @Override
    public void info(String message) {
        dispatcher.info(console, message);
    }

    @Override
    public void warn(String message, Map<String, String> params) {
        dispatcher.warn(console, message, params);
    }

    @Override
    public void warn(String message) {
        dispatcher.warn(console, message);
    }

    @Override
    public void success(String message, Map<String, String> params) {
        dispatcher.success(console, message, params);
    }

    @Override
    public void success(String message) {
        dispatcher.success(console, message);
    }

    @Override
    public void error(String message, Map<String, String> params) {
        dispatcher.error(console, message, params);
    }

    @Override
    public void error(String message) {
        dispatcher.error(console, message);
    }

    @Override
    public void debug(String message, Map<String, String> params) {
        dispatcher.debug(console, message, params);
    }

    @Override
    public void debug(String message) {
        dispatcher.debug(console, message);
    }
}
