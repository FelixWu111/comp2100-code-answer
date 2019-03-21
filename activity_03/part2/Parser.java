// u6174243 Qingzheng Xu
// u6683369 Jinming Dong
// u6250866 Yu Wu
// u6250082 Xuguang Song

import java.util.ArrayList;

public class Parser {

    Tokenizer _tokenizer;
    
    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }
    
    public Exp parse() {
        //!TODO: Add your implementation here.
        ArrayList<Token> tokenArrayList = new ArrayList<Token>();
        while(_tokenizer.hasNext()){
            tokenArrayList.add(_tokenizer.takeNext());
        }
        int v = nextStep(tokenArrayList);
        Lit li = new Lit(v);
        Factor fa = new Factor(li);
        Term ter = new Term(fa);
        return new Exp(ter,true,null);
    }

    private int nextStep(ArrayList<Token> l){
        int v = 0;
        int last = 0;
        if(l.get(l.size()-1).type() == Token.Type.RightBracket){
            last = l.size();
        }else {
            last = l.size()-1;
        }
        if(l.size()>1) {
            for(int i = 0; i < last; i++){
                if(l.get(i).type() == Token.Type.Minus) {
                    return v = v - nextStep(delHead(l,1));
                }
                if(l.get(i).type() == Token.Type.Add) {
                    return v = v + nextStep(delHead(l,1));
                }
                if(l.get(i).type() == Token.Type.LeftBracket) {
                    ArrayList<Token> nl = new ArrayList<>();
                    int bn = 1;
                    int digit = 1;
                    while (bn > 0) {
                        for(int j = i+1;j<l.size();j++){
                            if(l.get(i).type() == Token.Type.LeftBracket) {
                                bn++;
                                digit++;
                                l.get(j).type();
                            }
                            if(l.get(i).type() != Token.Type.RightBracket) {
                                nl.add(l.get(j));
                                digit++;
                            }
                            if(l.get(i).type() == Token.Type.RightBracket & j != l.size() - 1) {
                                bn--;
                                digit++;
                            }
                            if(l.get(i).type() == Token.Type.RightBracket & j == l.size() - 1) {
                                return v + nextStep(nl);
                            }
                        }
                    }
                    return v = v + nextStep(nl) + nextStep(delHead(l,digit));
                }
                if(l.get(i).type() == Token.Type.Lit){
                    return v = v + Integer.valueOf(l.get(i).token()) + nextStep(delHead(l,1));
                }
            }
        }
        v = v+Integer.valueOf(l.get(0).token());
        return v;
    }

    private ArrayList<Token> delHead(ArrayList<Token> l,int i){
        ArrayList<Token> tokenArrayList = new ArrayList<>();
        if(i<l.size()){
            for(int j = 1;j<l.size();j++){
                tokenArrayList.add(l.get(j));
            }
            return tokenArrayList;
        }
        return l;
    }
}
