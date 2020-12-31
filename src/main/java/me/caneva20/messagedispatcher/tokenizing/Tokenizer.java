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
    private static final char ESCAPING = '\\';

    @Override
    public List<IToken> tokenize(String raw) {
        var reader = new StringReader(raw, ESCAPING);
        List<IToken> tokens = new ArrayList<>();

        while (reader.hasNext()) {
            var result = tokenize(reader);

            if (result == null) {
                //error
                break;
            }

            tokens.addAll(result);
        }

        return tokens;
    }

    private void printUnexpectedTokenError(StringReader reader, char token, char expected) {
        System.out.printf("Error: token '%s' is not allowed at %s. Expected was %s\n",
                token,
                reader.getCurrentIndex(),
                expected
        );

        var builder = new StringBuilder();

        builder.append('\t');

        for (var i = 0; i < reader.getCurrentIndex() - 1; i++) {
            builder.append(" ");
        }

        builder.append("↑ Token not allowed here");

        System.out.println("\t" + reader.getRaw());
        System.out.println(builder.toString());
    }

    private void printUnexpectedEndError() {
        System.out.print("Error: unexpected string end reached\n");
    }

    public @Nullable List<IToken> tokenize(StringReader reader) {
        var tokens = new ArrayList<IToken>();

        while (true) {
            var literal = reader.readTo(BEGINNING, OPENING, CLOSING);

            if (!literal.isEmpty()) {
                tokens.add(new LiteralStringToken(literal));
            }

            if (!reader.hasNext()) {
                return tokens;
            }

            var currentChar = reader.getCurrentChar();
            reader.moveNext();
            reader.readCurrentPhrase();

            if (currentChar == null) {
                printUnexpectedEndError();
                return null;
            } else if (currentChar == OPENING) {
                printUnexpectedTokenError(reader, OPENING, BEGINNING);
                return null;
            } else if (currentChar == BEGINNING) {
                var token = readToken(reader);

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
        var name = reader.readTo(BEGINNING, OPENING, CLOSING);
        var currentChar = reader.getCurrentChar();
        reader.moveNext();
        reader.readCurrentPhrase();

        if (currentChar == null) {
            //error
            printUnexpectedEndError();
        } else if (currentChar == OPENING) {
            if (name.isEmpty()) {
                var parameterName = readParameterName(reader);
                
                if (parameterName != null) {
                    return new ParameterToken(parameterName);
                }
            } else {
                return new TagToken(name, tokenize(reader));
            }
        } else if (currentChar == BEGINNING) {
            //error
            printUnexpectedTokenError(reader, BEGINNING, OPENING);
        } else if (currentChar == CLOSING) {
            //error
            printUnexpectedTokenError(reader, CLOSING, OPENING);
        }

        return null;
    }

    @Nullable
    private String readParameterName(StringReader reader) {
        return readTo(reader, CLOSING);
    }

    @Nullable
    private String readTo(StringReader reader, char expected) {
        var read = reader.readTo(BEGINNING, OPENING, CLOSING);
        var currentChar = reader.getCurrentChar();
        reader.moveNext();
        reader.readCurrentPhrase();

        if (currentChar == null) {
            //error
            printUnexpectedEndError();
        } else if (currentChar != expected) {
            //error
            printUnexpectedTokenError(reader, currentChar, expected);
        } else {
            return read;
        }

        return null;
    }
}
