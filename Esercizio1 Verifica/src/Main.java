import java.util.Scanner;
import static Tools.utility.*;

public class Main{
    private static final int Max_Contatti = 100; //numero massimo contatti contenuti in rubrica
    private static Contatto[] rubrica = new Contatto[Max_Contatti]; //dichiarazione della rubrica
    private static int numContatti = 0;
    private static String passwordNascosti; //dichiarazione della stringa per la password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        passwordNascosti = impostaPassword(sc);

        String[] opzioni = {         //menu opzioni
                "Menu principale",
                "1- Inserisci contatto",
                "2- Visualizza contatti pubblici",
                "3- Visualizza contatti nascosti",
                "4- Cambia stato contatto (Pubblico/Nascosto)",
                "5- Effettua chiamata da contatto",
                "6- Ricerca contatto che ha chiamato",
                "7- Esci"
        };

        boolean vero = true;            //scelta opzioni con invocazione dei metodi
        while (vero) {
            int scelta = menu(opzioni, sc);
            switch (scelta) {
                case 1:
                    InserisciContatto(sc);
                    break;
                case 2:
                    VisualizzaContatti(false, sc);
                    break;
                case 3:
                    VisualizzaContatti(true, sc);
                    break;
                case 4:
                    CambiaStatoContatto(sc);
                    break;
                case 5:
                    EffettuaChiamata(sc);
                    break;
                case 6:
                    RicercaContattoChiamato(sc);
                    break;
                case 7:
                    vero = false;
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }
    }

    //METODI

    //inserimento password utilizzato per i contatti nascosti
    private static String impostaPassword(Scanner sc) {
        System.out.print("Imposta la password per i contatti nascosti: ");
        return sc.nextLine();
    }

    //inserimento contatti con la scelta di metterli nascosti o pubblici
    private static void InserisciContatto(Scanner sc) {
        if (numContatti < Max_Contatti) {
            System.out.print("Inserisci nome: ");
            String nome = sc.nextLine();
            System.out.print("Inserisci cognome: ");
            String cognome = sc.nextLine();
            System.out.print("Il contatto è nascosto? (s/n): ");
            boolean nascosto = sc.nextLine().equalsIgnoreCase("s");

            rubrica[numContatti++] = new Contatto(nome, cognome, nascosto);

            System.out.println("Contatto inserito correttamente.");
        } else {
            System.out.println("La rubrica è piena, impossibile inserire nuovi contatti.");
        }
    }

    //metodo per la visualizzazione dei contatti presenti e nello switch si può scegliere se vedere solamente i pubblici con 2- oppure i nascosti con 3- con inserimento password
    private static void VisualizzaContatti(boolean nascosti, Scanner sc) {
        if (nascosti) {
            System.out.print("Inserisci la password per visualizzare i contatti nascosti: ");
            String password = sc.nextLine();
            if (!password.equals(passwordNascosti)) { // controllo con !password.equals(passwordNascosti) se la password è diversa o no da quella inserita precedentemente, in caso lo sia, stamperà password errata.
                System.out.println("Password errata!");
                return;
            }
            System.out.println("Contatti nascosti:");
            for (int i = 0; i < numContatti; i++) {
                if (rubrica[i].nascosto) {
                    System.out.println(rubrica[i]);
                }
            }
        } else {
            System.out.println("Contatti pubblici:");
            for (int i = 0; i < numContatti; i++) {
                if (!rubrica[i].nascosto) {
                    System.out.println(rubrica[i]);
                }
            }
        }
    }

    // metodo per il cambiamento del contatto da pubblico a nascosto e viceversa
    private static void CambiaStatoContatto(Scanner sc) {
        System.out.print("Inserisci il nome del contatto da modificare: ");
        String nome = sc.nextLine();
        System.out.print("Inserisci il cognome del contatto da modificare: ");
        String cognome = sc.nextLine();

        for (int i = 0; i < numContatti; i++) {
            if (rubrica[i].nome.equalsIgnoreCase(nome) && rubrica[i].cognome.equalsIgnoreCase(cognome)) {

                /*rubrica[i].nome.equalsIgnoreCase(nome) && rubrica[i].cognome.equalsIgnoreCase(cognome) controlla se il nome e il cognome di una persona nella rubrica corrispondono esattamente ai valori che
                stiamo cercando e non fa differenza tra lettere maiuscole e minuscole. Se entrambi i nomi e i cognomi coincidono, allora il risultato sarà vero, altrimenti sarà falso.*/

                rubrica[i].nascosto = !rubrica[i].nascosto;
                System.out.println("Stato del contatto cambiato correttamente.");
                return;
            }
        }
        System.out.println("Contatto inesistente.");
    }

    //inserire il nome e cognome del contatto che mi ha chiamato presente nella mia rubrica in modo tale di ricercarlo con il prossimo metodo e se lo trovo ma è nascosto, apparirà contatto nascosto
    private static void EffettuaChiamata(Scanner sc) {
        System.out.print("Inserisci il nome del contatto che mi ha chiamato ");
        String nome = sc.nextLine();
        System.out.print("Inserisci il cognome del contatto che mi ha chiamato ");
        String cognome = sc.nextLine();

        for (int i = 0; i < numContatti; i++) {
            if (rubrica[i].nome.equalsIgnoreCase(nome) && rubrica[i].cognome.equalsIgnoreCase(cognome)) {  //confronto nomi ignorando lettera maiuscola e minuscola per verificare se il contatto è presente in rubrica
                System.out.println("Chiamata effettuata da: " + rubrica[i]);
                return;
            }
        }
        System.out.println("Contatto inesistente.");
    }

    //ricerca del contatto che mi ha chiamato (non sono riuscito a fare in modo che il contatto nascosto che ha chiamato non si visualizzi)
    private static void RicercaContattoChiamato(Scanner sc) {
        System.out.print("Inserisci nome del contatto che ti ha chiamato: ");
        String nome = sc.nextLine();
        System.out.print("Inserisci cognome del contatto che ti ha chiamato: ");
        String cognome = sc.nextLine();

        for (int i = 0; i < numContatti; i++) {
            if (rubrica[i].nome.equalsIgnoreCase(nome) && rubrica[i].cognome.equalsIgnoreCase(cognome)) {
                System.out.println("Contatto trovato: " + rubrica[i]);
                return;
            }
        }
        System.out.println("Contatto inesistente.");
    }
}

