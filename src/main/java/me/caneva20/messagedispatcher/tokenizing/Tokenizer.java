package me.caneva20.messagedispatcher.tokenizing;

import me.caneva20.messagedispatcher.tokenizing.tokens.InvalidToken;
import me.caneva20.messagedispatcher.tokenizing.tokens.LiteralStringToken;
import me.caneva20.messagedispatcher.tokenizing.tokens.TagToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer implements ITokenizer {
    private static final char BEGINNING = '$';
    private static final char OPENING = '<';
    private static final char CLOSING = '>';

    @Override
    public List<IToken> tokenize(String raw) {
        StringReader reader = new StringReader(raw);
        List<IToken> tokens = new ArrayList<>();

        while (reader.hasNext()) {
            tokens.addAll(tokenize(reader));
        }

        return tokens;
    }

    private void printMalformedTokenMissingClosing(String tokenName) {
        System.out.printf("Malformed input (001): %s does not contain a closing tag\n", tokenName);
    }

    private void printMalformedTokenMissingOpening(String tokenName) {
        System.out.printf("Malformed input (002): %s does not contain an opening tag\n", tokenName);
    }

    private void printMalformedTokenInvalidStart(String tokenName) {
        System.out.printf("Malformed input (003): Invalid $ found, %s does not contain an opening tag\n", tokenName);
    }

    public List<IToken> tokenize(StringReader reader) {
        List<IToken> doneTokens = new ArrayList<>();

        List<IToken> children = new ArrayList<>();

        String literal = reader.readTo(BEGINNING);
        reader.moveNext();
        reader.readCurrentPhrase();

        if (!literal.isEmpty()) {
            doneTokens.add(new LiteralStringToken(literal));
        }

        if (!reader.hasNext()) {
            return doneTokens;
        }

        String tokenName = reader.readTo(Arrays.asList(BEGINNING, OPENING, CLOSING));
        reader.readCurrentPhrase();

        Character currentChar = reader.getCurrentChar();

        reader.moveNext();

        if (currentChar == BEGINNING || currentChar == CLOSING) {
            if (currentChar == BEGINNING) {
                printMalformedTokenInvalidStart(tokenName);
            } else {
                printMalformedTokenMissingOpening(tokenName);
            }

            doneTokens.add(new InvalidToken(tokenName, new LiteralStringToken("")));
            return doneTokens;
        }

        do {
            String partialContent = reader.readTo(Arrays.asList(BEGINNING, CLOSING));
            currentChar = reader.getCurrentChar();

            if (currentChar == null) {
                doneTokens.add(new InvalidToken(tokenName, new LiteralStringToken(partialContent)));

                printMalformedTokenMissingClosing(tokenName);
                return doneTokens;
            }

            if (currentChar == BEGINNING) {
                reader.readCurrentPhrase();

                children.addAll(tokenize(reader));

                //Clear current phrase
                reader.readCurrentPhrase();
                currentChar = reader.getCurrentChar();

                if (currentChar == null) {
                    doneTokens.add(new InvalidToken(tokenName, children));
                    printMalformedTokenMissingClosing(tokenName);

                    return doneTokens;
                }
            }
        } while (currentChar != CLOSING);

        String content = reader.readCurrentPhrase();
        reader.moveNext();

        if (!content.isEmpty()) {
            children.add(new LiteralStringToken(content));
        }

        doneTokens.add(new TagToken(tokenName, children));

        return doneTokens;
    }
}
