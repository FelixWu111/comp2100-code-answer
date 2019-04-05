
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
        Term term = new Term(parseFactor());
        while (_tokenizer.next() != null && (_tokenizer.next().type() == Token.Type.Multiply|| _tokenizer.next().type() == Token.Type.Divide)){
            Token.Type op = _tokenizer.takeNext().type();
            term = new Term(term,op == Token.Type.Multiply ? Operation.Mult : Operation.Div,parseFactor());
        }
        return term;
    }

    public Factor parseFactor() {
        // TODO: Implement this
        if (_tokenizer.next() != null && _tokenizer.next().type() == Token.Type.LeftBracket){
            _tokenizer.takeNext();
            Factor factor = new Factor(parse());
            _tokenizer.takeNext();
            return factor;
        }else {
            return new Factor(new Lit(Integer.parseInt(_tokenizer.takeNext().token())));
        }
    }
}
