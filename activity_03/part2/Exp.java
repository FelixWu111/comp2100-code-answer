
public class Exp {
    private Term _term = null;
    private Exp _exp = null;
    private boolean _isAdd = false;
    
    public Exp(Term term) {
        _term = term;
    }
    
    public Exp(Term term, boolean isAdd, Exp exp) {
        _term = term;
        _isAdd = isAdd;
        _exp = exp;
    }
    
    public int value() {
        return (_exp==null) ? _term.value() : (_isAdd ? _term.value() + _exp.value() : _term.value() - _exp.value());
    }
    
    public String toString() {
        return (_exp==null) ? _term.toString() : (_isAdd ? _term.toString() + "+" + _exp.toString() : _term.toString() + "-" + _exp.toString());
    }
}
