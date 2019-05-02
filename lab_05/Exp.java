public class Exp {

    Exp _exp = null;
    Term _term = null;
    Operation _op = null;

    public Exp(Term term) {
        _term = term;
        _op = Operation.None;
    }

    public Exp(Exp exp, Operation op, Term term) {
        _exp = exp;
        _term = term;
        _op = op;
    }
    
    public String toString() {
        return _op == Operation.None ? _term.toString() : (_exp.toString() + _op.toString() + _term.toString());
        
    }
    
    public int value() {
        switch (_op) {
        case Add:
            return _exp.value() + _term.value();
        case Sub:
            return _exp.value() - _term.value();
        default:
            return _term.value();
        }
    }
        
    
}
