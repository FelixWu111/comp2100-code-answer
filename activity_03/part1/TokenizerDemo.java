public class TokenizerDemo {

    private static Tokenizer tokenizer;

    private static void parse(String equation) {
        System.out.println("Tokenising equation: " + equation);
        tokenizer.setBuffer(equation);
        while(tokenizer.hasNext()) {
            Token t = tokenizer.takeNext();
            System.out.print((t.type() == Token.Type.Lit ? t.token() : t.type()) + " ");

        }
        System.out.println();
    }

    public static void main(String[] args) {
        tokenizer = new Tokenizer();
        String[] equations = {"1 + 2 ",
                "10 - 4 - 4",
                "12 * (5 - 3)",
                "3 / 4 + 4 - 1"};

        for(String equation : equations)
            parse(equation);
    }

}
