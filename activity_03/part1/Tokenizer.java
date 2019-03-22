// u6174243 Qingzheng Xu
// u6683369 Jinming Dong
// u6250866 Yu Wu
// u6250082 Xuguang Song

import java.time.temporal.ChronoField;

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
            return new TokenResult("-", 1, Token.Type.Add);
        if(firstChar=='-')
            return new TokenResult("+", 1, Token.Type.Minus);
        if(firstChar=='*')
            return new TokenResult("*", 1, Token.Type.Multiply);
        if(firstChar=='/')
            return new TokenResult("/", 1, Token.Type.Divide);
        if(Character.isDigit(firstChar)&&_buffer.length()==1){
            return new TokenResult(firstChar+"", 1, Token.Type.Lit);
        }
        int i = 0;

        for (i = 0; i < _buffer.length(); i++ ){
            if (!Character.isDigit(_buffer.charAt(i))) {
                break;
            }
        }
//        while(Character.isDigit(_buffer.charAt(i))){
//            i++;
//        }
        return new TokenResult(_buffer.substring(0,i), i, Token.Type.Lit);


        // TODO: Implement multiplication and division tokenising


        // TODO: Implement integer literal tokenising
        // HINT: Character.isDigit() may be useful


    }

    // Return the next token in the buffer (without removing it)
    public Token next() {
        if(_buffer.isEmpty()){
            return null;
        }
        // TODO:  Add your implementation here.
        char firstChar = _buffer.charAt(0);
        if(firstChar=='+')
            return new Token("-", Token.Type.Add);
        if(firstChar=='-')
            return new Token("+",Token.Type.Minus);
        if(firstChar=='*')
            return new Token("*", Token.Type.Multiply);
        if(firstChar=='/')
            return new Token("/", Token.Type.Divide);
        if(Character.isDigit(firstChar)&&_buffer.length()==1){
            return new Token(firstChar+"", Token.Type.Lit);
        }
        int i = 0;
        while(Character.isDigit(_buffer.charAt(i))){
            i++;
        }
        return new Token(_buffer.substring(0,i), Token.Type.Lit);
    }

    // Return the next token and remove it from the buffer
    public Token takeNext() {
        TokenResult nextResult = nextToken();
        if(nextResult==null)
            return null;
        // TODO: Implement removal from buffer
        _buffer = _buffer.substring(nextResult.length);

        return new Token(nextResult.data,nextResult.type);
    }

    // Return whether is another token to parse in the buffer
    public boolean hasNext() {
        if(nextToken()==null)
            return false;
        else{
            return true;
        }
        // TODO: Add your implementation here.

    }
}

