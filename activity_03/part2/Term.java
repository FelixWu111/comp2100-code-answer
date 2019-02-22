
public class Term {
    private Factor _factor;
    
    public Term(Factor factor) {
        _factor = factor;
    }
    
    public int value() {
        return _factor.value();
    }
    
    public String toString() {
        return _factor.toString();
    }
    
}
