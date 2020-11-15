package me.caneva20.messagedispatcher.utils;

import java.util.HashMap;
import java.util.Map;

public class Params {
    public static final Params EMPTY_PARAMS = new Params();

    private final Map<String, String> params = new HashMap<>();

    public Params() {
    }

    public Params(String name, String value) {
        params.put(name, value);
    }

    public Params(String name, char value) {
        this(name, String.valueOf(value));
    }

    public Params(String name, int value) {
        this(name, String.valueOf(value));
    }

    public Params(String name, long value) {
        this(name, String.valueOf(value));
    }

    public Params(String name, boolean value) {
        this(name, String.valueOf(value));
    }

    public Params(String name, float value) {
        this(name, String.valueOf(value));
    }

    public Params(String name, double value) {
        this(name, String.valueOf(value));
    }

    public Params add(String name, String value) {
        params.put(name, value);

        return this;
    }

    public Params add(String name, char value) {
        return add(name, String.valueOf(value));
    }

    public Params add(String name, int value) {
        return add(name, String.valueOf(value));
    }

    public Params add(String name, long value) {
        return add(name, String.valueOf(value));
    }

    public Params add(String name, boolean value) {
        return add(name, String.valueOf(value));
    }

    public Params add(String name, float value) {
        return add(name, String.valueOf(value));
    }

    public Params add(String name, double value) {
        return add(name, String.valueOf(value));
    }

    public Map<String, String> build() {
        return params;
    }
}
