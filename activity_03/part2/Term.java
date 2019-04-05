public class Term {

    Term _term = null;
    Factor _factor = null;
    Operation _op = null;

    public Term(Factor factor) {
        _factor = factor;
        _op = Operation.None;
    }

    public Term(Term term, Operation op, Factor factor) {
        _term = term;
        _op = op;
        _factor = factor;
    }

    public String toString() {
        return _op == Operation.None ? _factor.toString() : (_term.toString() + _op.toString() + _factor.toString());

    }

    public int value() {
        switch (_op) {
            case Mult:
                return _term.value() * _factor.value();
            case Div:
                return _term.value() / _factor.value();
            default:
                return _factor.value();
        }
    }


}
