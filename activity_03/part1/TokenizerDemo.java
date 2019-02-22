
public class TokenizerDemo {

    private static Tokenizer tokenizer;
    
    private static void parse(String equation) {
        System.out.println("Parsing equation: " + equation);
        tokenizer.setBuffer(equation);
        while(tokenizer.hasNext())
            System.out.print(tokenizer.takeNext().token() + " ");
        System.out.println();
    }
    
    public static void main(String[] args) {
        tokenizer = new Tokenizer();
        String[] equations = {"  73  +  65 ", 
                              " (2\t + 21) + 6\t)",
                              "  ) - 5 + \t3\t 7 568"};
        for(int i=0; i<equations.length; ++i)
            parse(equations[i]);
    }

}
