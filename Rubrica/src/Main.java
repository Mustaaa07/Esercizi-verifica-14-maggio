import java.util.Scanner;
import static Tools.Utility.*;

public class Main{
    private static final int Max_Contatti = 100; //numero massimo contatti contenuti in rubrica
    private static Contatto[] rubrica = new Contatto[Max_Contatti];
    private static int numContatti = 0;
    private static String passwordNascosti;

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

        boolean vero = true;            //scelta opzioni
        while (vero) {
            int scelta = menu(opzioni, sc);
            switch (scelta) {
                case 1:
                    inserisciContatto(sc);
                    break;
                case 2:
                    visualizzaContatti(false, sc);
                    break;
                case 3:
                    visualizzaContatti(true, sc);
                    break;
                case 4:
                    cambiaStatoContatto(sc);
                    break;
                case 5:
                    effettuaChiamata(sc);
                    break;
                case 6:
                    ricercaContattoChiamato(sc);
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
    private static void inserisciContatto(Scanner sc) {
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
    private static void visualizzaContatti(boolean nascosti, Scanner sc) {
        if (nascosti) {
            System.out.print("Inserisci la password per visualizzare i contatti nascosti: ");
            String password = sc.nextLine();
            if (!password.equals(passwordNascosti)) {
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
    private static void cambiaStatoContatto(Scanner sc) {
        System.out.print("Inserisci il nome del contatto da modificare: ");
        String nome = sc.nextLine();
        System.out.print("Inserisci il cognome del contatto da modificare: ");
        String cognome = sc.nextLine();

        for (int i = 0; i < numContatti; i++) {
            if (rubrica[i].nome.equalsIgnoreCase(nome) && rubrica[i].cognome.equalsIgnoreCase(cognome)) {
                rubrica[i].nascosto = !rubrica[i].nascosto;
                System.out.println("Stato del contatto cambiato correttamente.");
                return;
            }
        }
        System.out.println("Contatto inesistente.");
    }

    //inserire il nome e cognome del contatto che mi ha chiamato presente nella mia rubrica in modo tale di ricercarlo con il prossimo metodo e se lo trovo ma è nascosto, apparirà contatto nascosto
    private static void effettuaChiamata(Scanner sc) {
        System.out.print("Inserisci il nome del contatto che mi ha chiamato ");
        String nome = sc.nextLine();
        System.out.print("Inserisci il cognome del contatto che mi ha chiamato ");
        String cognome = sc.nextLine();

        for (int i = 0; i < numContatti; i++) {
            if (rubrica[i].nome.equalsIgnoreCase(nome) && rubrica[i].cognome.equalsIgnoreCase(cognome)) {
                System.out.println("Chiamata effettuata da: " + rubrica[i]);
                return;
            }
        }
        System.out.println("Contatto inesistente.");
    }

    //ricerca del contatto che mi ha chiamato (non sono riuscito a fare in modo che il contatto nascosto che ha chiamato non si visualizzi)
    private static void ricercaContattoChiamato(Scanner sc) {
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

