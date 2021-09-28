import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Symbole{
String nom;
Symbole(String nom){this.nom=nom;}
Symbole(){};

};
interface verif_eval{
    public double verifier_evaluer() throws VariableInexistanteException,ParentheseFermanteException,ParentheseOuvranteException, VariableSyntaxeException;
}
class Variable extends Symbole implements verif_eval {
    private boolean verifier;
    private double valeur;
    Variable(String nom){
        super(nom);
    }
    public double verifier_evaluer() throws VariableInexistanteException,VariableSyntaxeException{
        Pattern p;
        Matcher m;
        p = Pattern.compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");
        m = p.matcher(this.nom);
        if(m.find()){
            switch(this.nom.toLowerCase()){
                case "cos":
                throw new VariableSyntaxeException();
                case "sin":
                throw new VariableSyntaxeException();
                case "log":
                throw new VariableSyntaxeException();
                case "sqrt":
                throw new VariableSyntaxeException();
                case "tan":
                throw new VariableSyntaxeException();
                case "abs":
                throw new VariableSyntaxeException();
                case "print":
                throw new VariableSyntaxeException();
                case "let":
                throw new VariableSyntaxeException();
                case "end":
                throw new VariableSyntaxeException();
                default: return 0;
            }
        }
        else{
            throw new VariableSyntaxeException();
        }


    }
    public String getnom(){
    return nom;
    }
    public double getValeur(){return this.valeur;}
    public void setvaleur(Double valeur){
        this.valeur=valeur;
        };
};
class Fonction_standard extends Symbole {
    public static double evaluer(String fct, Double args) throws ArgumentNegatifException,FonctionStandardInexistanteException{
        switch(fct){
            case "cos": return Math.cos(Math.toRadians(args));
            case "sin": return Math.sin(Math.toRadians(args));
            case "sqrt":if(args<0) throw new ArgumentNegatifException();
             else return Math.sqrt(Math.abs(args));
            case "abs":return Math.abs(args);
            case "tan": return Math.tan(Math.toRadians(args));
            case "log":
                if(args<0) throw new ArgumentNegatifException();
                else return Math.log(Math.abs(args));
            default:
                throw new FonctionStandardInexistanteException();
        }
    }
}
