package me.caneva20.messagedispatcher.dispachers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.IMessageParser;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class MessageDispatcher implements IMessageDispatcher {

  protected final IMessageParser parser;

  protected MessageDispatcher(IMessageParser parser) {
    this.parser = parser;
  }

  protected String format(String rawMessage, MessageLevel level, Map<String, String> params) {
    var message = parser.parse(rawMessage, level, params);

    return ChatColor.translateAlternateColorCodes('&', message);
  }

  protected String format(String rawMessage, MessageLevel level) {
    return format(rawMessage, level, Collections.emptyMap());
  }

  protected String format(String rawMessage, MessageLevel level, String... params) {
    final var collect = Arrays.stream(params).map(x -> "$par<" + x + ">")
        .collect(Collectors.toList());

    return format(String.format(rawMessage, collect), level);
  }

  protected String bundle(String message, String... params) {
    final var collect = Arrays.stream(params).map(x -> "$par<" + x + ">")
        .collect(Collectors.toList());

    return String.format(message, collect);
  }

  @Override
  public void raw(CommandSender to, String message) {
    to.sendMessage(message);
  }

  @Override
  public void raw(CommandSender to, String message, String... params) {
    raw(to, bundle(message, params));
  }

  @Override
  public void info(CommandSender to, String message, String... params) {
    info(to, bundle(message, params));
  }

  @Override
  public void warn(CommandSender to, String message, String... params) {
    warn(to, bundle(message, params));
  }

  @Override
  public void success(CommandSender to, String message, String... params) {
    success(to, bundle(message, params));
  }

  @Override
  public void error(CommandSender to, String message, String... params) {
    error(to, bundle(message, params));
  }

  @Override
  public void debug(CommandSender to, String message, String... params) {
    debug(to, bundle(message, params));
  }
}
