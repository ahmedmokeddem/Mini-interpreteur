
import java.util.*;

class Interpreteur {
    Commande commande;
    String ligne_commande;
    public static Map<String, Symbole> Table_de_Symbole = new HashMap<String, Symbole>();

    public Interpreteur() throws CommandeInexistanteException, ArrayIndexOutOfBoundsException,VariableSyntaxeException {
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_RESET = "\u001B[0m";
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print(ANSI_CYAN+" >  "+ANSI_RESET);
        ligne_commande = sc.nextLine();
        String[] mots = ligne_commande.split(" ");
        String[] result=new String[mots.length-1];
        String cmd= mots[0];
        System.arraycopy(mots,1,result,0,mots.length-1);
        String str=String.join("",result);
            switch (cmd.toLowerCase()) {
            case "let":
            List<String> list = Arrays.asList(result);
            if(list.contains("cos")||list.contains("sin")||list.contains("tan")||list.contains("log")||list.contains("sqrt")||list.contains("abs")||list.contains("let")||list.contains("print")){
                throw new VariableSyntaxeException();
            }
            mots=str.split("=");
                Variable var = new Variable(mots[0]);
                Expression_numerique express1 = new Expression_numerique(mots[1]);
                commande = new Let(express1, var);
                break;
            case "print":
                Expression_numerique express2 = new Expression_numerique(str);
                commande = new Print(express2);
                break;
            case "end":
            String ANSI_GREEN = "\u001B[32m";
            System.out.println(ANSI_GREEN+"    Fin du programme"+ANSI_RESET);
                System.exit(0);
            default:
                throw new CommandeInexistanteException();
        }
    }

    public static Symbole recherche_var(String variable) throws VariableInexistanteException {

        if (!Table_de_Symbole.isEmpty()) {
            if (Table_de_Symbole.containsKey(variable)) {
                return Table_de_Symbole.get(variable);
            } else throw new VariableInexistanteException(variable);
        } else {
            throw new VariableInexistanteException(variable);
        }

    }

    public static void insertion_var(String variable, Symbole valeur) {

        Table_de_Symbole.put(variable, valeur);

    }

    public void traitement() {
        try {
            commande.compiler();
            commande.executer();
        } catch (ArgumentNegatifException e) {
            e.getMesage();
        } catch(VariableInexistanteException e){
            e.getMesage();
        }
        catch (FonctionStandardInexistanteException e) {
            e.getMesage();
        } catch (ParentheseFermanteException e) {
            e.getMesage();
        } catch (ParentheseOuvranteException e) {
            e.getMesage();
        }
        catch(ExpressionErronneeException e){
            e.getMesage();
        }catch(VariableSyntaxeException e){
            e.getMesage();
        }
    }
}