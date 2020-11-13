package me.caneva20.messagedispatcher.exceptions;

public class NoTokenRegistryDefined extends RuntimeException {
    public NoTokenRegistryDefined() {
        super("There's no TokenRegistry defined");
    }
}
