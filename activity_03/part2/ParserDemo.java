public class ParserDemo {

    private static Tokenizer tokenizer;
    private static Parser parser;
    
    private static void parse(String equation) {
        System.out.println("Parsing equation: " + equation);
        tokenizer.setBuffer(equation);
        Exp exp = parser.parse();
        if(exp==null) {
            System.out.println("Get an null expression.");
        } else {
            System.out.println(exp.toString() + "=" + Integer.toString(exp.value()));
        }
    }
    
    public static void main(String[] args) {
        tokenizer = new Tokenizer();
        parser = new Parser(tokenizer);
        
        String[] equations = {"1 + 2 ",                      // 3
                              "10 - 4 - 4",                  // 2
                              "12 * 5 - 3",                  // 57
                              "(10 - 2) * (10 / 2) + 1",     // 41
                              "100 / 5 - 3 * (4 + 1 - 2)"};  // 11
        for(String equation : equations)
            parse(equation);
    }

}
