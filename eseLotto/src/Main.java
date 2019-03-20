import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Quante route vuoi usare? ");
        int numRuote = scan.nextInt();
        while (numRuote <= 0) {
            System.err.println("Inserisci un numero maggiore di 0");
            numRuote = scan.nextInt();
        }

        System.out.println("Inserisci l'ambo su cui punti: ");
        int primo, secondo;
        do {
            System.out.print("Primo numero[1-" + DatiCondivisi.MAX_NUMERO + "]: ");
            primo = scan.nextInt();
        } while (primo <= 0 || primo >= DatiCondivisi.MAX_NUMERO);
        do {
            System.out.print("Secondo numero[1-" + DatiCondivisi.MAX_NUMERO + "]: ");
            secondo = scan.nextInt();
        } while (secondo <= 0 || secondo >= DatiCondivisi.MAX_NUMERO);

        final DatiCondivisi dc = new DatiCondivisi(numRuote, primo, secondo);

        final ThEstrai thEstrai = new ThEstrai(dc);
        final ThCerca thCerca1 = new ThCerca(dc, 1), thCerca2 = new ThCerca(dc, 2);

        thEstrai.start();
        thCerca1.start();
        thCerca2.start();

        try {
            dc.getFineSemaphore().acquire(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int ruoteVincenti = 0;
        for (Ruota r : dc.getRuote())
            if (r.hasWon()) {
                ruoteVincenti++;
                System.out.println("Ruota numero " + r.getNumRuota());
                System.out.println("Numeri estratti: " + Arrays.toString(r.getEstratti()));
            }
        System.out.println("Hai vinto in " + ruoteVincenti + " ruote.");
    }
}
