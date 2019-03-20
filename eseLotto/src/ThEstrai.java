import java.util.Random;

public class ThEstrai extends Thread{
    private final DatiCondivisi ptrDati;


    public ThEstrai(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        Random rn = new Random();
        for (Ruota r : ptrDati.getRuote()) {
            for(int i =0;i<Ruota.NUMERI_PER_RUOTA;i++)
                r.setAt(i,rn.nextInt(DatiCondivisi.MAX_NUMERO));
            ptrDati.getEstrattoUno().release();
            ptrDati.getEstrattoDue().release();
        }
        ptrDati.getFineSemaphore().release();
    }
}
