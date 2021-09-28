class CommandeInexistanteException extends Exception{
    
    String getMesage(){
        String message="    Erreur: La commande saisie non reconnue";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED+message+ANSI_RESET);
        return message;
    }
}
class VariableSyntaxeException extends Exception{
    String getMesage(){
        String message="    Erreur: Syntaxe variable erronée";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED+message+ANSI_RESET);
        return message;
    }
};
class ParentheseManquanteException extends Exception{};
class ParentheseOuvranteException extends ParentheseManquanteException{
    String getMesage(){
        String message="    Erreur: Parenthèse ouvrante manquante";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED+message+ANSI_RESET);
        return message;
    }
};
class ParentheseFermanteException extends ParentheseManquanteException{
    String getMesage(){
        String message="    Erreur: Parenthèse fermante manquante";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED+message+ANSI_RESET);
        return message;
    }
};
class VariableInexistanteException extends Exception{
    String nom;
    public VariableInexistanteException(String nom){
    super();
    this.nom = nom;
    }
    String getMesage(){
        String message="    Erreur: Variable " +this.nom+" non declarée";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED+message+ANSI_RESET);
        return message;
    }
};
class FonctionStandardInexistanteException extends Exception{
    String getMesage(){
        String message="    Erreur: Fonction standard non reconnue ";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED+message+ANSI_RESET);
        return message;
    }
};
class ExpressionErronneeException extends Exception {
    String getMesage(){
        String message="    Erreur: Expression erronée";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED+message+ANSI_RESET);
        return message;
    }
};
class ArgumentNegatifException extends Exception{
    String getMesage(){
        String message="    Erreur: Argument Négatif ";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED+message+ANSI_RESET);
        return message;
    }
}



