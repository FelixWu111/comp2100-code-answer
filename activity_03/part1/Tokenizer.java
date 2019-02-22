public class Tokenizer {
    
    private String _buffer;
    
    public Tokenizer() {
        setBuffer("");
    }
    
    public Tokenizer(String buffer) {
        setBuffer(buffer);
    }
    
    public void setBuffer(String buffer) {
        _buffer = buffer;
    }
    
    public Token takeNext() {
        // Add your implementation here.
        return null;
    }
    
    public Token next() {
        // Add your implementation here.
        return null;
    }
    
    public boolean hasNext() {
        // Add your implementation here.
        return false;
    }
}