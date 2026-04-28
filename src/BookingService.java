import java.util.ArrayList;
import java.util.List;

/**
 * BookingService mengorkestrasi proses pemesanan tiket KBT.
 * Menggunakan Factory Method untuk membuat Tiket yang sesuai.
 */
public class BookingService {

    private final KabinKBT kabin;
    private final List<Tiket> riwayat = new ArrayList<>();

    public BookingService() {
        this.kabin = new KabinKBT();
    }

    // ── Factory Method ────────────────────────────────────────────────────────
    public Tiket buatTiket(Penumpang penumpang, JenisKBT jenis,
                           List<String> kursiDipilih, int jumlahTiket,
                           UkuranPaket paket) {

        // Validasi kursi
        for (String k : kursiDipilih) {
            if (!kabin.kursiAda(k))
                throw new IllegalArgumentException("Kursi " + k + " tidak ada dalam kabin.");
            if (!kabin.kursiTersedia(k))
                throw new IllegalArgumentException("Kursi " + k + " sudah terisi.");
        }

        // Buat tiket sesuai jenis
        Tiket tiket;
        if (jenis == JenisKBT.REGULER)
            tiket = new TiketReguler(penumpang, kursiDipilih, jumlahTiket, paket);
        else
            tiket = new TiketExecutive(penumpang, kursiDipilih, jumlahTiket, paket);

        // Tandai kursi sebagai terisi
        for (String k : kursiDipilih) kabin.pesanKursi(k);

        riwayat.add(tiket);
        return tiket;
    }

    public KabinKBT getKabin()         { return kabin; }
    public List<Tiket> getRiwayat()    { return riwayat; }
}
