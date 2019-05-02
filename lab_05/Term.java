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
        return null;
    public int value() {
        return 0;
    }


}
