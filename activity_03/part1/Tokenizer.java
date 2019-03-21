// u6174243 Qingzheng Xu
// u6683369 Jinming Dong
// u6250866 Yu Wu
// u6250082 Xuguang Song

import static java.lang.Character.isDigit;

public class Tokenizer {

    private String _buffer;
    int pos;
    char[] whitespace;
    char[] symbol;

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
        if(firstChar=='*')
            return new TokenResult("*",1,Token.Type.Multiply);
        if(firstChar=='/')
            return new TokenResult("/",1,Token.Type.Divide);

        if(Character.isDigit(firstChar)) {
            int counter = 0;
            while(++counter<(_buffer.length()))
                if (!(Character.isDigit(_buffer.charAt(counter))))
            return new TokenResult(_buffer.substring(0,counter),counter,Token.Type.Lit);
        }

        // TODO: Implement multiplication and division tokenising


        // TODO: Implement integer literal tokenising
        // HINT: Character.isDigit() may be useful

        return null;

    }

    // Return the next token in the buffer (without removing it)
    public Token next() {
        // TODO:  Add your implementation here.
        consumewhite();
        if(pos == _buffer.length()) {
            return new Token("",Token.Type.Unknown);
        } else if (isin(_buffer.charAt(pos), symbol)) {
            if(_buffer.charAt(pos) == '(') {
                pos++;
                return new Token("(",Token.Type.LeftBracket);
            }
            if(_buffer.charAt(pos) == ')') {
                pos++;
                return new Token(")",Token.Type.RightBracket);
            }
            if(_buffer.charAt(pos) == '+') {
                pos++;
                return new Token("+",Token.Type.Add);
            }
            if(_buffer.charAt(pos) == '-') {
                pos++;
                return new Token("-",Token.Type.Minus);
            }
            if(_buffer.charAt(pos) == '*') {
                pos++;
                return new Token("*",Token.Type.Multiply);
            }
            if(_buffer.charAt(pos) == '/') {
                pos++;
                return new Token("/",Token.Type.Divide);
            }
        } else{
            int start = pos;
            while (pos < _buffer.length() && Character.isDigit(_buffer.charAt(pos)))
                pos++;
            return new Token(_buffer.substring(start,pos),Token.Type.Lit);
        }
        return new Token("",Token.Type.Unknown);

    }

    // Return the next token and remove it from the buffer
    public Token takeNext() {
        // TODO: Implement removal from buffer
        return next();
    }

    private void consumewhite() {
        while (pos < _buffer.length() && isin(_buffer.charAt(pos), whitespace))
            pos++;
    }

    private boolean isin(char c, char charlist[]) {
        for (char w : charlist) {
            if (w == c)
                return true;
        }
        return false;
    }

    // Return whether is another token to parse in the buffer
    public boolean hasNext() {
        return pos<_buffer.length();
        // TODO: Add your implementation here.

    }
}
