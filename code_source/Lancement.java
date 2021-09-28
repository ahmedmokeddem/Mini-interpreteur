public class Lancement {
    public static void main(String[] args) {
        Interpreteur b;
        System.out.println("\n Entrez vos commandes. Tapez end pour terminer votre programme.");
        System.out.println(" Une commande doit être de la forme");
        System.out.println(" let <variable> = <expression>");
        System.out.println(" ou");
        System.out.println(" print <expression>");
        while (true) {
            try {
                b = new Interpreteur();
                b.traitement();
            } catch (CommandeInexistanteException e) {
                e.getMesage();
            } catch(ArrayIndexOutOfBoundsException e){
                String ANSI_RESET = "\u001B[0m";
                String ANSI_RED = "\u001B[31m";
                System.out.println(ANSI_RED+"    Erreur: Synthaxe erronée"+ANSI_RESET);
            }catch(VariableSyntaxeException e){
                e.getMesage();
            }
        }
    }
}
