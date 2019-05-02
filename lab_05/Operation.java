public enum Operation {
    None (""),
    Add ("+"),
    Sub ("-"),
    Mult ("*"),
    Div ("/");

    String op;

    Operation(String s) {
        op = s;
    }

    @Override
    public String toString() {
        return op;
    }
}
