
public class Factor {
    private Lit _lit = null;
    private Exp _exp = null;
    private boolean _minus = false;
    
    public Factor(Lit lit) {
        _lit = lit;
    }
    
    public Factor(Exp exp, boolean minus) {
        _exp = exp;
        _minus = minus;
    }
    
    public int value() {
        return _lit == null ? (_minus ? -1 * _exp.value() : _exp.value()) : _lit.value();
    }
    
    public String toString() {
        return _lit == null ? (_minus ? "-":"") + "(" + _exp.toString() + ")" : _lit.toString();
    }
}
