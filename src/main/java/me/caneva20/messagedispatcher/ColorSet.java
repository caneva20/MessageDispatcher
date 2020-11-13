package me.caneva20.messagedispatcher;

public class ColorSet {
    public final char primary;
    public final char secondary;
    public final char accent;
    public final char detail;

    public ColorSet(char primary, char secondary, char accent, char detail) {
        this.primary = primary;
        this.secondary = secondary;
        this.accent = accent;
        this.detail = detail;
    }

    public ColorSet(char primary, char secondary, char accent) {
        this(primary, secondary, accent, 'f');
    }

    public ColorSet(char primary, char secondary) {
        this(primary, secondary, 'f', 'f');
    }
}
