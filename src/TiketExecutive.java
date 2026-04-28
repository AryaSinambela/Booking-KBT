import java.util.List;

public class TiketExecutive extends Tiket {

    public TiketExecutive(Penumpang penumpang, List<String> kursiDipilih,
                          int jumlahTiket, UkuranPaket paket) {
        super(penumpang, JenisKBT.EXECUTIVE, kursiDipilih, jumlahTiket, paket);
    }

    @Override
    protected String headerTiket() {
        return "        *** TIKET KBT EXECUTIVE ★★★ ***";
    }
}
