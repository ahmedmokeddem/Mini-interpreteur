
interface Compiler_Executer{
    void compiler()throws ParentheseFermanteException,ParentheseOuvranteException,VariableInexistanteException,ArgumentNegatifException,FonctionStandardInexistanteException, ExpressionErronneeException, VariableSyntaxeException;
    void executer() throws ParentheseFermanteException,ParentheseOuvranteException,ArgumentNegatifException,FonctionStandardInexistanteException,VariableInexistanteException, ExpressionErronneeException;
   }

abstract class Commande implements Compiler_Executer  {

}
class Let extends Commande {
   Expression_numerique expression;
   Variable variable;
   public Let(Expression_numerique expression,Variable variable){
       this.expression=expression;
       this.variable=variable;
   }
   public void compiler()throws ParentheseFermanteException,ParentheseOuvranteException,ArgumentNegatifException,FonctionStandardInexistanteException,VariableInexistanteException, ExpressionErronneeException,VariableSyntaxeException{
       this.expression.verifier_evaluer();
       this.variable.verifier_evaluer();

   };
   public void executer()throws ParentheseFermanteException,ParentheseOuvranteException,ArgumentNegatifException,FonctionStandardInexistanteException,VariableInexistanteException, ExpressionErronneeException{
           this.variable.setvaleur(this.expression.verifier_evaluer());
           Interpreteur.insertion_var(this.variable.getnom(),this.variable);
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";
           System.out.println(ANSI_GREEN+"    OK"+ANSI_RESET);
   };
}
class Print extends Commande{
   Expression_numerique expression;
   Print(Expression_numerique expression){
       this.expression=expression;
   }
   public void compiler()throws ParentheseFermanteException,ParentheseOuvranteException,ArgumentNegatifException,FonctionStandardInexistanteException,VariableInexistanteException, ExpressionErronneeException{
       expression.verifier_evaluer();
   };
   public void executer()throws ParentheseFermanteException,ParentheseOuvranteException,ArgumentNegatifException,FonctionStandardInexistanteException,VariableInexistanteException, ExpressionErronneeException{
    String ANSI_RESET = "\u001B[0m";
    String ANSI_GREEN = "\u001B[32m";
    System.out.println(ANSI_GREEN+"    La valeur est : "+ this.expression.verifier_evaluer()+ANSI_RESET);
   };
}
class End extends Commande{
   public void compiler(){};
   public void executer(){};
}
