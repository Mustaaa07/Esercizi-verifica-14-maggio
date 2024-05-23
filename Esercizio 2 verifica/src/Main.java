import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int dim;
        int num;
        Scanner sc = new Scanner(System.in);

        // Inserimento dimensione array compresa fra 8 e 10
        do {
            System.out.println("Inserisci la dimensione dell'array compresa fra 8 e 10:");
            dim = sc.nextInt();
        } while (dim < 8 || dim > 10);

        int[] Array = new int[dim];

        //inserimento valori all'interno dell'array compresi fra 1 e 30 senza duplicati
        for (int i = 0; i < Array.length; i++) {
            boolean Duplicate;
            do {
                Duplicate = false;    /*controllo inserimento di un numero duplicato in modo di effettuare un reinserimento in caso di duplicato
                tramite un boolean e tramite degli if in caso che se sia vero che il numero inserito è un duplicato, chiede il rinserimento del numero poichè non lo accetta.
                tutto all'interno di un do-while*/
                System.out.println("Inserisci i valori dell'array compresi fra 1 e 30:");
                num = sc.nextInt();

                if (num < 1 || num > 30) {
                    System.out.println("Numero fuori intervallo. Riprova.");
                    Duplicate = true;
                } else {
                    for (int j = 0; j < i; j++) {
                        if (Array[j] == num) {
                            System.out.println("Numero duplicato. Riprova.");
                            Duplicate = true;
                            break;
                        }
                    }
                }
            } while (Duplicate);

            Array[i] = num;
        }

        Selection(Array);
        Visualizza(Array);
    }

    public static void Selection(int[] Array) {   //Selection sort per ordinare solo i numeri pari tramite l'utilizzo di un if con all'interno la modulazione, lasciando i dispari nella loro posizione d'origine
        int n = Array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            if (Array[i] % 2 == 0) {
                for (int j = i + 1; j < n; j++) {
                    if (Array[j] % 2 == 0) {
                        if (Array[j] < Array[minIndex]) {
                            minIndex = j;
                        }
                    }
                }
                if (minIndex != i) {
                    int temp = Array[i];
                    Array[i] = Array[minIndex];
                    Array[minIndex] = temp;
                }
            }
        }
    }
//metodo di visualizzazione dall'array ordinato
    public static void Visualizza(int[] Array) {
        for (int i = 0; i < Array.length; i++) {
            System.out.println(Array[i]);
        }
    }
}
