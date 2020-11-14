package me.caneva20.messagedispatcher.tokenizing;

import me.caneva20.messagedispatcher.tokenizing.tokens.LiteralStringToken;
import me.caneva20.messagedispatcher.tokenizing.tokens.ParameterToken;
import me.caneva20.messagedispatcher.tokenizing.tokens.TagToken;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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
            List<IToken> result = tokenize(reader);

            if (result == null) {
                //error
                break;
            }

            tokens.addAll(result);
        }

        return tokens;
    }

    private void printUnexpectedTokenError(String raw, char token, char expected, int index) {
        System.out.printf("Error: token '%s' is not allowed at %s. Expected was %s\n",
                token,
                index,
                expected
        );

        StringBuilder builder = new StringBuilder();

        builder.append('\t');

        for (int i = 0; i < index - 1; i++) {
            builder.append(" ");
        }

        builder.append("↑ Token not allowed here");

        System.out.println("\t" + raw);
        System.out.println(builder.toString());
    }

    private void printUnexpectedEndError() {
        System.out.print("Error: unexpected string end reached\n");
    }

    public @Nullable List<IToken> tokenize(StringReader reader) {
        List<IToken> tokens = new ArrayList<>();

        while (true) {
            String literal = reader.readTo(BEGINNING, OPENING, CLOSING);

            if (!literal.isEmpty()) {
                tokens.add(new LiteralStringToken(literal));
            }

            if (!reader.hasNext()) {
                return tokens;
            }

            Character currentChar = reader.getCurrentChar();
            reader.moveNext();
            reader.readCurrentPhrase();

            if (currentChar == null) {
                printUnexpectedEndError();
                return null;
            } else if (currentChar == OPENING) {
                printUnexpectedTokenError(reader.getRaw(), OPENING, BEGINNING, reader.getCurrentIndex());
                return null;
            } else if (currentChar == BEGINNING) {
                IToken token = readToken(reader);

                if (token == null) {
                    //error
                    return null;
                }

                tokens.add(token);
            } else if (currentChar == CLOSING) {
                return tokens;
            }
        }
    }

    private @Nullable IToken readToken(StringReader reader) {
        String name = reader.readTo(BEGINNING, OPENING, CLOSING);
        Character currentChar = reader.getCurrentChar();
        reader.moveNext();
        reader.readCurrentPhrase();

        if (currentChar == null) {
            //error
            printUnexpectedEndError();
        } else if (currentChar == OPENING) {
            if (name.isEmpty()) {
                String parameterName = readParameterName(reader);
                
                if (parameterName != null) {
                    return new ParameterToken(name);
                }
            } else {
                return new TagToken(name, tokenize(reader));
            }
        } else if (currentChar == BEGINNING) {
            //error
            printUnexpectedTokenError(reader.getRaw(), BEGINNING, OPENING, reader.getCurrentIndex());
        } else if (currentChar == CLOSING) {
            //error
            printUnexpectedTokenError(reader.getRaw(), CLOSING, OPENING, reader.getCurrentIndex());
        }

        return null;
    }

    @Nullable
    private String readParameterName(StringReader reader) {
        return readTo(reader, CLOSING);
    }

    @Nullable
    private String readTo(StringReader reader, char expected) {
        String read = reader.readTo(BEGINNING, OPENING, CLOSING);
        Character currentChar = reader.getCurrentChar();
        reader.moveNext();
        reader.readCurrentPhrase();

        if (currentChar == null) {
            //error
            printUnexpectedEndError();
        } else if (currentChar != expected) {
            //error
            printUnexpectedTokenError(reader.getRaw(), currentChar, expected, reader.getCurrentIndex());
        } else {
            return read;
        }

        return null;
    }
}
