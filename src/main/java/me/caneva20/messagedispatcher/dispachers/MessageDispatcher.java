package me.caneva20.messagedispatcher.dispachers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
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

  protected String format(String rawMessage, MessageLevel level, Object... params) {
    return format(bundle(rawMessage, params), level);
  }

  protected String bundle(String message, Object... params) {
    final var collect = Arrays.stream(params).map(x -> "$par<" + x + ">").toArray();

    return String.format(message, collect);
  }

  @Override
  public void raw(CommandSender to, String message) {
    to.sendMessage(message);
  }

  @Override
  public void raw(CommandSender to, String message, Object... params) {
    raw(to, bundle(message, params));
  }

  @Override
  public void info(CommandSender to, String message, Object... params) {
    info(to, bundle(message, params));
  }

  @Override
  public void warn(CommandSender to, String message, Object... params) {
    warn(to, bundle(message, params));
  }

  @Override
  public void success(CommandSender to, String message, Object... params) {
    success(to, bundle(message, params));
  }

  @Override
  public void error(CommandSender to, String message, Object... params) {
    error(to, bundle(message, params));
  }

  @Override
  public void debug(CommandSender to, String message, Object... params) {
    debug(to, bundle(message, params));
  }
}
