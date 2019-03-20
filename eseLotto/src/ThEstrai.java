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
            for(int i =0;i<Ruota.getNumeriPerRuota();i++)
                r.setAt(i,rn.nextInt(100));
            ptrDati.getEstrattoUno().release();
            ptrDati.getEstrattoDue().release();
        }
    }
}
