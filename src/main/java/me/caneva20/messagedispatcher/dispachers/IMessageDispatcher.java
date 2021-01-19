package me.caneva20.messagedispatcher.dispachers;

import org.bukkit.command.CommandSender;

import java.util.Map;

public interface IMessageDispatcher {

  void raw(CommandSender to, String message);

  void raw(CommandSender to, String message, Object... params);

  void info(CommandSender to, String message);

  void info(CommandSender to, String message, Object... params);

  void info(CommandSender to, String message, Map<String, String> params);

  void warn(CommandSender to, String message);

  void warn(CommandSender to, String message, Object... params);

  void warn(CommandSender to, String message, Map<String, String> params);

  void success(CommandSender to, String message);

  void success(CommandSender to, String message, Object... params);

  void success(CommandSender to, String message, Map<String, String> params);

  void error(CommandSender to, String message);

  void error(CommandSender to, String message, Object... params);

  void error(CommandSender to, String message, Map<String, String> params);

  void debug(CommandSender to, String message);

  void debug(CommandSender to, String message, Object... params);

  void debug(CommandSender to, String message, Map<String, String> params);
}
