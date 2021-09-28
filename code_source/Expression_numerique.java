import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Expression_numerique{
    String content;
    Expression_numerique(String content){
        this.content = content;
    }
    public double verifier_evaluer() throws ParentheseFermanteException , ParentheseOuvranteException , ArgumentNegatifException , FonctionStandardInexistanteException,VariableInexistanteException, ExpressionErronneeException, ArgumentNegatifException {
        Pattern p;
        Matcher m;
        int i=0;
        int compteur=0;
        String[] arrOfStr = this.content.split("((?<=\\+|-|\\*|/|sqrt|cos|sin|tan|abs|log|\\(|\\))|(?=\\+|-|\\*|/|sqrt|cos|sin|tan|abs|log|\\(|\\)))");
        for (String string : arrOfStr) {
            p = Pattern.compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");
            m = p.matcher(string);
            if(string.equals("(")){
                compteur++;
            }else if(string.equals(")")){
                if(compteur==0){throw new ParentheseOuvranteException();}
                compteur--;
            }
            if(m.find()&&(arrOfStr.length-1==i || !(arrOfStr[i+1].equals("(")))) {
            Double val=((Variable)Interpreteur.recherche_var(string)).getValeur();
            arrOfStr[i] = String.valueOf(val);
            this.content = String.join("",arrOfStr);
            }
            i++;
        }
        if(compteur>0){throw new ParentheseFermanteException();}
        String str=this.content;
        return new Object() {
            int pos = -1, ch;
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean op(int charToop) {
                while (ch == ' ') nextChar();
                if (ch == charToop) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() throws ExpressionErronneeException, FonctionStandardInexistanteException, ArgumentNegatifException{
                nextChar();
                double x = parseExpression();
                return x;
            }
            double parseExpression() throws ExpressionErronneeException, FonctionStandardInexistanteException, ArgumentNegatifException {
                double x = parseTerm();
                for (;;) {
                    if      (op('+')) x += parseTerm();
                    else if (op('-')) x -= parseTerm();
                    else return x;
                }
            }
            double parseTerm() throws ExpressionErronneeException, FonctionStandardInexistanteException, ArgumentNegatifException {
                double x = parseFactor();
                for (;;) {
                    if      (op('*')) x *= parseFactor();
                    else if (op('/')) x /= parseFactor();
                    else return x;
                }
            }
            double parseFactor() throws ExpressionErronneeException, FonctionStandardInexistanteException, ArgumentNegatifException {
                if (op('+')) return parseFactor();
                if (op('-')) return -parseFactor();
                double x;
                int startPos = this.pos;
                if (op('(')) { 
                    x = parseExpression();
                    op(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { 
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { 
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) {
                        if (x<0) {throw new ArgumentNegatifException();}
                        else {x = Fonction_standard.evaluer("sqrt", x);}
                    }
                    else if (func.equals("sin")) x = Fonction_standard.evaluer("sin", x);
                    else if (func.equals("cos")) x = Fonction_standard.evaluer("cos", x);
                    else if (func.equals("tan")) x = Fonction_standard.evaluer("tan", x);
                    else if (func.equals("log")) {if (x<0) {throw new ArgumentNegatifException();} 
                    else {x = Fonction_standard.evaluer("log", x);;}}
                    else if (func.equals("abs")) x = Fonction_standard.evaluer("abs", x);
                    else throw new FonctionStandardInexistanteException();
                } else {
                    throw new ExpressionErronneeException();
                }
                if (op('^')) x = Math.pow(x, parseFactor());
                return x;
            }
        }.parse();
    }}