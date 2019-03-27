// u6174243 Qingzheng Xu
// u6683369 Jinming Dong
// u6250866 Yu Wu
// u6250082 Xuguang Song

public class Parser {

    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    /*
        <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
        <term>   ::= <integer literal>
     */
    public Exp parse() {
        // TODO: Implement this

        while(_tokenizer.hasNext()) {
            Token t = _tokenizer.takeNext();
            if (t.type() != Token.Type.Lit)
                return null;
            if (_tokenizer.hasNext()) {
                Token t2 = _tokenizer.takeNext();
                if (t2.type() == Token.Type.Add) {
                    return new Exp(new Term(new Factor(new Lit(Integer.parseInt(t.token())))), true, this.parse());
                } else if (t2.type() == Token.Type.Minus) {
                    return new Exp(new Term(new Factor(new Lit(Integer.parseInt(t.token())))), false, this.parse());
                }
            } else {
                return new Exp(new Term(new Factor(new Lit(Integer.parseInt(t.token())))));
            }




        }

        return null;
    }

}

