package me.caneva20.messagedispatcher.tokenizing;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringReader {
    private final String raw;
    private final char[] chars;
    private final int _length;

    private int _currentIndex = 0;
    private StringBuilder _currentPhrase = new StringBuilder();

    public StringReader(String rawString) {
        this.raw = rawString;
        _length = rawString.length();

        chars = rawString.toCharArray();
    }

    public boolean hasNext() {
        return _currentIndex < _length;
    }

    public int getCurrentIndex() {
        return _currentIndex;
    }

    public String getRaw() {
        return raw;
    }

    public @Nullable Character getCurrentChar() {
        if (_currentIndex >= _length) {
            return null;
        }

        return chars[_currentIndex];
    }

    public String getCurrentPhrase() {
        return _currentPhrase.toString();
    }

    public String readCurrentPhrase() {
        String currentPhrase = _currentPhrase.toString();
        _currentPhrase = new StringBuilder();

        return currentPhrase;
    }

    public void moveNext() {
        _currentIndex++;
    }

    public String readTo(List<Character> targets) {
        while (hasNext()) {
            Character _currentChar = getCurrentChar();

            if (targets.contains(_currentChar)) {
                return getCurrentPhrase();
            }

            _currentPhrase.append(_currentChar);
            _currentIndex++;
        }

        return readCurrentPhrase();
    }

    public String readTo(char targetA) {
        return readTo(Collections.singletonList(targetA));
    }

    public String readTo(char targetA, char targetB) {
        return readTo(Arrays.asList(targetA, targetB));
    }

    public String readTo(char targetA, char targetB, char targetC) {
        return readTo(Arrays.asList(targetA, targetB, targetC));
    }
}
