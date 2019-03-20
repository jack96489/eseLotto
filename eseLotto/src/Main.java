import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Quante route vuoi usare?");
        int numRuote = scan.nextInt();
        while (numRuote <= 0) {
            System.err.println("Inserisci un numero maggiore di 0");
            numRuote = scan.nextInt();
        }

        System.out.println("Inserisci l'ambo su cui punti: ");
        int primo, secondo;
        do {
            System.out.print("Primo numero[1-99]:");
            primo = scan.nextInt();
        } while (primo <= 0 || primo >= 100);
        do {
            System.out.print("Secondo numero[1-99]:");
            secondo = scan.nextInt();
        } while (secondo <= 0 || secondo >= 100);

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
    }
}
