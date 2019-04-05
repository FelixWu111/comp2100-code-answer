
public class Token {
    public enum Type {Unknown, Lit, Add, Minus, Multiply, Divide, LeftBracket, RightBracket};
    private String _token = "";
    private Type _type = Type.Unknown;

    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }

    public String token() {
        return _token;
    }

    public Type type() {
        return _type;
    }
}
