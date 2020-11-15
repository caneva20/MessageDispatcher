package me.caneva20.messagedispatcher.dispachers;

import java.util.Map;

public interface IConsoleMessageDispatcher {
    void raw(String message);

    void info(String message);

    void info(String message, Map<String, String> params);

    void warn(String message);

    void warn(String message, Map<String, String> params);

    void success(String message);

    void success(String message, Map<String, String> params);

    void error(String message);

    void error(String message, Map<String, String> params);

    void debug(String message);

    void debug(String message, Map<String, String> params);
}
