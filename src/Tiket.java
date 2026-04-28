import java.util.List;

// ─── Abstract base ────────────────────────────────────────────────────────────
public abstract class Tiket {

    private static final double DISKON_DUA_TIKET = 0.10;

    protected Penumpang penumpang;
    protected JenisKBT jenisKBT;
    protected List<String> kursiDipilih;
    protected int jumlahTiket;
    protected UkuranPaket paket;          // null jika tidak ada paket
    protected String kodeBooking;

    public Tiket(Penumpang penumpang, JenisKBT jenisKBT,
                 List<String> kursiDipilih, int jumlahTiket, UkuranPaket paket) {

        if (kursiDipilih.size() != jumlahTiket)
            throw new IllegalArgumentException(
                "Jumlah kursi yang dipilih harus sama dengan jumlah tiket.");

        this.penumpang     = penumpang;
        this.jenisKBT      = jenisKBT;
        this.kursiDipilih  = kursiDipilih;
        this.jumlahTiket   = jumlahTiket;
        this.paket         = paket;
        this.kodeBooking   = generateKode();
    }

    // ── Kalkulasi harga ──────────────────────────────────────────────────────

    public int hitungSubtotalTiket() {
        return jenisKBT.getHargaPerKursi() * jumlahTiket;
    }

    public int hitungDiskon() {
        if (jumlahTiket == 2)
            return (int) (hitungSubtotalTiket() * DISKON_DUA_TIKET);
        return 0;
    }

    public int hitungBiayaPaket() {
        return paket != null ? paket.getHarga() : 0;
    }

    public int hitungTotal() {
        return hitungSubtotalTiket() - hitungDiskon() + hitungBiayaPaket();
    }

    // ── Template method ───────────────────────────────────────────────────────
    public final String cetakTiket() {
        String garis  = "=".repeat(50);
        String strip  = "-".repeat(50);

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(garis).append("\n");
        sb.append(headerTiket()).append("\n");      // hook untuk subclass
        sb.append(garis).append("\n");
        sb.append("Kode Booking : ").append(kodeBooking).append("\n");
        sb.append(strip).append("\n");
        sb.append("DATA PENUMPANG\n");
        sb.append(penumpang).append("\n");
        sb.append(strip).append("\n");
        sb.append("DETAIL PERJALANAN\n");
        sb.append("Jenis KBT    : ").append(jenisKBT.getLabel()).append("\n");
        sb.append("Kursi        : ").append(String.join(", ", kursiDipilih)).append("\n");
        sb.append("Jumlah Tiket : ").append(jumlahTiket).append("\n");

        if (paket != null)
            sb.append("Paket Barang : ").append(paket.getKeterangan()).append("\n");

        sb.append(strip).append("\n");
        sb.append("RINCIAN HARGA\n");
        sb.append(String.format("  Tiket  : Rp %,d x %d = Rp %,d%n",
                jenisKBT.getHargaPerKursi(), jumlahTiket, hitungSubtotalTiket()));

        if (hitungDiskon() > 0)
            sb.append(String.format("  Diskon : -Rp %,d (10%% tiket ganda)%n", hitungDiskon()));

        if (paket != null)
            sb.append(String.format("  Paket  : +Rp %,d%n", hitungBiayaPaket()));

        sb.append(strip).append("\n");
        sb.append(String.format("  TOTAL  : Rp %,d%n", hitungTotal()));
        sb.append(garis).append("\n");
        return sb.toString();
    }

    // ── Abstract hook ─────────────────────────────────────────────────────────
    protected abstract String headerTiket();

    // ── Helper ────────────────────────────────────────────────────────────────
    private static int urutan = 1;
    private static String generateKode() {
        return String.format("KBT-%04d", urutan++);
    }

    // ── Getter ────────────────────────────────────────────────────────────────
    public String getKodeBooking()       { return kodeBooking; }
    public Penumpang getPenumpang()      { return penumpang; }
    public JenisKBT getJenisKBT()       { return jenisKBT; }
    public List<String> getKursi()       { return kursiDipilih; }
    public int getJumlahTiket()          { return jumlahTiket; }
    public UkuranPaket getPaket()        { return paket; }
}
