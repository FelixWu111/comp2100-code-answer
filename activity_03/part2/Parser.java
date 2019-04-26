
public class Parser {
    /*
        <exp>    ::= <term> | <exp> + <term> | <exp> - <term>
        <term>   ::= <factor> | <term> * <factor> | <term> / <factor>
        <factor> ::= <integer literal> | (<exp>)
     */

    Tokenizer _tokenizer;
    
    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    public Exp parse() {
        Exp exp = new Exp(parseTerm());

        while (_tokenizer.next() != null &&
              (_tokenizer.next().type()==Token.Type.Add||
               _tokenizer.next().type()==Token.Type.Minus))

        {
            Token.Type tt = _tokenizer.takeNext().type(); // Merge term into factor

            if(tt==Token.Type.Add){exp = new Exp(exp, Operation.Add, parseTerm());}
            else{exp = new Exp(exp, Operation.Sub, parseTerm());}
        }

        return exp;
    }

    public Term parseTerm() {
        // TODO: Implement this
        Term term = new Term(parseFactor());

        while (_tokenizer.next() != null &&
              (_tokenizer.next().type() == Token.Type.Multiply||
              _tokenizer.next().type() == Token.Type.Divide))

        {   Token.Type tt = _tokenizer.takeNext().type();

            if(tt==Token.Type.Multiply){term = new Term(term,Operation.Mult,parseFactor());}
            else{term = new Term(term,Operation.Div,parseFactor());}
        }
        return term;
    }

    public Factor parseFactor() {
        // TODO: Implement this
        if (_tokenizer.next() != null &&
            _tokenizer.next().type() == Token.Type.LeftBracket)

        {   _tokenizer.takeNext();
            Factor factor = new Factor(parse());
            _tokenizer.takeNext();
            return factor;
        }else {
            return new Factor(new Lit(Integer.parseInt(_tokenizer.takeNext().token())));
        }
    }
}


