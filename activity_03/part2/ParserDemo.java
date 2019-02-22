
public class ParserDemo {

    private static Tokenizer tokenizer;
    private static Parser parser;
    
    public static void parse(String equation) {
        tokenizer.setBuffer(equation);
        System.out.println("Parsing equation " + equation);
        Exp resultExp=parser.parse();
        if(resultExp==null)
            System.out.println("Your implementation returns an null Exp.");
        else {
            System.out.println(resultExp.toString() + "=" + resultExp.value());
        }
    }
    
    public static void main(String[] args) {
        tokenizer = new Tokenizer();
        parser = new Parser(tokenizer);
        
        String[] equations = {"3 + 5",
                              "12\t+(6-27)",
                              "-5 + 7 - (3    + 22)"};
        for(int i=0; i<equations.length; ++i)
            parse(equations[i]);
    }

}
