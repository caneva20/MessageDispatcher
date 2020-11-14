package me.caneva20.messagedispatcher;

public class ColorSwatch {
    public final char primary;
    public final char secondary;
    public final char accent;
    public final char detail;

    public ColorSwatch(char primary, char secondary, char accent, char detail) {
        this.primary = primary;
        this.secondary = secondary;
        this.accent = accent;
        this.detail = detail;
    }

    public ColorSwatch(char primary, char secondary, char accent) {
        this(primary, secondary, accent, 'f');
    }

    public ColorSwatch(char primary, char secondary) {
        this(primary, secondary, 'f', 'f');
    }
}
