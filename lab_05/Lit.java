
public class Lit {
    private int _value = 0;
    
    public Lit(int value) {
        _value = value;
    }
    
    public int value() {
        return _value;
    }
    
    public String toString() {
        return Integer.toString(_value);
    }
}
