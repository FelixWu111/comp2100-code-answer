    public class Factor {

    Lit _lit = null;
    Exp _exp = null;

    public Factor(Lit lit) {
        _lit = lit;
    }

    public Factor(Exp exp) {
        _exp = exp;
    }

    public String toString() {
        return _lit == null ? "(" + _exp.toString() + ")" : _lit.toString();
    }

    public int value() {
        return _lit == null ? _exp.value() : _lit.value();
    }
}
