package me.caneva20.messagedispatcher;

public interface IMessageParser {
    String parse(String raw, MessageLevel level);
}
