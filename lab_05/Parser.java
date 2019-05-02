
public class Parser {

    Tokenizer _tokenizer;
    
    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    /*
        <exp>    ::= <term> | <exp> + <term> | <exp> - <term>
        <term>   ::= <factor> | <term> * <factor> | <term> / <factor>
        <factor> ::= <integer literal> | (<exp>)
     */
    public Exp parse() {
        // Parse term
        Exp exp = new Exp(parseTerm());
        // Check if more terms to parse
        while (_tokenizer.next() != null &&  (_tokenizer.next().type() == Token.Type.Add || _tokenizer.next().type() == Token.Type.Minus)) {
            Token.Type op = _tokenizer.takeNext().type();
            // Merge previous term into factor
            exp = new Exp(exp, op == Token.Type.Add ? Operation.Add : Operation.Sub, parseTerm());

        }

        return exp;
    }

    public Term parseTerm() {
        // TODO: Implement this
        return null;
    }

    public Factor parseFactor() {
        // TODO: Implement this
        return null;
    }
}
