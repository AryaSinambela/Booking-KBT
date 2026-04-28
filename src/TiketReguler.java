import java.util.List;

public class TiketReguler extends Tiket {

    public TiketReguler(Penumpang penumpang, List<String> kursiDipilih,
                        int jumlahTiket, UkuranPaket paket) {
        super(penumpang, JenisKBT.REGULER, kursiDipilih, jumlahTiket, paket);
    }

    @Override
    protected String headerTiket() {
        return "          *** TIKET KBT REGULER ***";
    }
}
