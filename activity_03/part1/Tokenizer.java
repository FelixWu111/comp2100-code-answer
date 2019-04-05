import java.util.Objects;

public class Tokenizer {

    private String _buffer;

    // Inner class to encapsulate token and buffer information
    class TokenResult {
        String data;
        int length;
        Token.Type type;

        public TokenResult(String d, int l, Token.Type t) {
            data = d;
            length = l;
            type = t;
        }
    }

    public Tokenizer() {
        setBuffer("");
    }

    public Tokenizer(String buffer) {
        setBuffer(buffer);
    }

    public void setBuffer(String buffer) {
        _buffer = buffer;
    }

    // Method to extract next token
    private TokenResult nextToken() {
        _buffer = _buffer.trim(); // Remove whitespace
        if(_buffer.isEmpty())
            return null;
        char firstChar = _buffer.charAt(0);
        if(firstChar=='+')
            return new TokenResult("+", 1, Token.Type.Add);
        if(firstChar=='-')
            return new TokenResult("-", 1, Token.Type.Minus);

        // TODO: Implement multiplication and division tokenising
        if(firstChar=='*')
            return new TokenResult("*", 1, Token.Type.Multiply);
        if(firstChar=='/')
            return new TokenResult("/", 1, Token.Type.Divide);

        if(firstChar=='(')
            return new TokenResult("(", 1, Token.Type.LeftBracket);
        if(firstChar==')')
            return new TokenResult(")", 1, Token.Type.RightBracket);
        // TODO: Implement integer literal tokenising
        // HINT: Character.isDigit() may be useful
        if(Character.isDigit(firstChar)) {
            int start = 0;
            int pos = 0;
            while (pos < _buffer.length() && Character.isDigit(_buffer.charAt(pos)))
                pos++;
            return new TokenResult(_buffer.substring(start, pos), pos - start, Token.Type.Lit);
        }

        return new TokenResult(""+firstChar,1, Token.Type.Unknown);
    }

    // Return the next token in the buffer (without removing it)
    public Token next() {
        // TODO:  Add your implementation here.
        TokenResult next = nextToken();
        return next == null ? null : new Token(next.data,next.type);

    }

    // Return the next token and remove it from the buffer
    public Token takeNext() {
        TokenResult nextResult = nextToken();
        if (nextResult == null){
            return null;
        }
        _buffer = _buffer.substring(nextResult.length);
        return new Token(nextResult.data, nextResult.type);
    }

    // Return whether is another token to parse in the buffer
    public boolean hasNext() {
        return nextToken() != null;
        // TODO: Add your implementation here.

    }
}