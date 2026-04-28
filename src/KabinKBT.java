import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Merepresentasikan kabin mobil KBT dengan layout kursi.
 * Kursi di-pre-inisialisasi; status TERISI = tidak bisa dipilih.
 */
public class KabinKBT {

    public enum StatusKursi { TERSEDIA, TERISI, DIPILIH }

    private final Map<String, StatusKursi> denah = new LinkedHashMap<>();

    // Kursi yang sudah terisi sebelum booking dibuka
    private static final String[] SUDAH_TERISI = {"A2", "B3", "D4"};

    public KabinKBT() {
        // Baris 1: A (samping supir) – 3 kursi
        for (int i = 1; i <= 3; i++) denah.put("A" + i, StatusKursi.TERSEDIA);
        // Baris 2: B – 4 kursi
        for (int i = 1; i <= 4; i++) denah.put("B" + i, StatusKursi.TERSEDIA);
        // Baris 3: C – 4 kursi
        for (int i = 1; i <= 4; i++) denah.put("C" + i, StatusKursi.TERSEDIA);
        // Baris belakang: D – 5 kursi
        for (int i = 1; i <= 5; i++) denah.put("D" + i, StatusKursi.TERSEDIA);

        for (String k : SUDAH_TERISI) denah.put(k, StatusKursi.TERISI);
    }

    // ── Tampilkan denah ───────────────────────────────────────────────────────
    public void tampilkanDenah() {
        System.out.println("\n  ┌─────────── DENAH KABIN KBT ───────────┐");
        System.out.println("  │            [  SUPIR  ]                │");
        System.out.println("  │              ( ⊙ )                    │");
        System.out.println("  ├────────────────────────────────────────┤");
        tampilkanBaris("Baris 1 (samping supir)", "A1","A2","A3");
        tampilkanBaris("Baris 2               ", "B1","B2","B3","B4");
        tampilkanBaris("Baris 3               ", "C1","C2","C3","C4");
        tampilkanBaris("Baris belakang        ", "D1","D2","D3","D4","D5");
        System.out.println("  └────────────────────────────────────────┘");
        System.out.println("  Keterangan: [Kode] = Tersedia | [####] = Terisi | [>>>>] = Dipilih\n");
    }

    private void tampilkanBaris(String label, String... kodes) {
        StringBuilder sb = new StringBuilder("  │ " + label + ": ");
        for (String k : kodes) {
            StatusKursi s = denah.getOrDefault(k, StatusKursi.TERISI);
            if (s == StatusKursi.TERSEDIA)  sb.append(String.format("[%-3s]", k));
            else if (s == StatusKursi.TERISI)  sb.append("[###]");
            else sb.append("[>>>]");
            sb.append(" ");
        }
        // Pad baris agar rata
        while (sb.length() < 52) sb.append(" ");
        sb.append("│");
        System.out.println(sb);
    }

    // ── Validasi & update ─────────────────────────────────────────────────────
    public boolean kursiTersedia(String kode) {
        return denah.getOrDefault(kode.toUpperCase(), StatusKursi.TERISI) == StatusKursi.TERSEDIA;
    }

    public boolean kursiAda(String kode) {
        return denah.containsKey(kode.toUpperCase());
    }

    public void pesanKursi(String kode) {
        denah.put(kode.toUpperCase(), StatusKursi.TERISI);
    }

    public void tandaiDipilih(String kode) {
        denah.put(kode.toUpperCase(), StatusKursi.DIPILIH);
    }

    public void batalkanPilihan(String kode) {
        denah.put(kode.toUpperCase(), StatusKursi.TERSEDIA);
    }
}
