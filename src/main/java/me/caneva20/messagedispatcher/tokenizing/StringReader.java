package me.caneva20.messagedispatcher.tokenizing;

import java.util.Collections;
import java.util.List;

public class StringReader {
    private final char[] chars;
    private final int _length;

    private int _currentIndex = 0;
    private char _currentChar;
    private StringBuilder _currentPhrase = new StringBuilder();

    public StringReader(String string) {
        _length = string.length();

        chars = string.toCharArray();
    }

    public boolean hasNext() {
        return _currentIndex < _length;
    }

    public Character getCurrentChar() {
        if (_currentIndex >= _length) {
            return null;
        }

        return chars[_currentIndex];
    }

    public void consumeCurrentChar() {
        _currentPhrase.append(getCurrentChar());
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
            _currentChar = getCurrentChar();

            if (targets.contains(_currentChar)) {
                return getCurrentPhrase();
            }

            _currentPhrase.append(_currentChar);
            _currentIndex++;
        }

        return readCurrentPhrase();
    }

    public String readTo(char target) {
        return readTo(Collections.singletonList(target));
    }
}
