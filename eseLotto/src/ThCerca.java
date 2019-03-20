import java.util.Random;

public class ThCerca extends Thread {
    private final DatiCondivisi ptrDati;
    private final int numThread;

    public ThCerca(DatiCondivisi ptrDati, int numThread) {
        this.ptrDati = ptrDati;
        this.numThread = numThread;
    }

    @Override
    public void run() {
        Random rn = new Random();
        final int daCercare;
        if (numThread == 1)
            daCercare = ptrDati.getPunatatoUno();
        else
            daCercare = ptrDati.getPuntatoDue();
        try {
            for (Ruota r : ptrDati.getRuote()) {
                if (numThread == 1)
                    ptrDati.getEstrattoUno().acquire();
                else
                    ptrDati.getEstrattoDue().acquire();
                for (int i = 0; i < Ruota.NUMERI_PER_RUOTA; i++)
                    if (daCercare == r.getAt(i))
                        if (numThread == 1)
                            r.setVintoUno(true);
                        else
                            r.setVintoDue(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ptrDati.getFineSemaphore().release();
    }
}
