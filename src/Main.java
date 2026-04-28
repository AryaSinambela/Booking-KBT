import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * ╔══════════════════════════════════════════╗
 *   SISTEM BOOKING MOBIL KBT – Main Program
 *   OOP: Encapsulation, Inheritance,
 *        Polymorphism, Abstraction
 * ╚══════════════════════════════════════════╝
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final BookingService service = new BookingService();

    // ── Warna ANSI ────────────────────────────────────────────────────────────
    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String CYAN   = "\u001B[36m";
    static final String GREEN  = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String RED    = "\u001B[31m";
    static final String BLUE   = "\u001B[34m";

    public static void main(String[] args) {
        banner();

        boolean jalan = true;
        while (jalan) {
            menuUtama();
            int pilihan = inputInt("Pilihan: ", 1, 4);
            switch (pilihan) {
                case 1 -> prosesBooking();
                case 2 -> tampilkanRiwayat();
                case 3 -> service.getKabin().tampilkanDenah();
                case 4 -> { System.out.println(GREEN + "\nTerima kasih! Sampai jumpa. 🚗" + RESET); jalan = false; }
            }
        }
        sc.close();
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  PROSES BOOKING (orkestrasi semua langkah)
    // ═══════════════════════════════════════════════════════════════════════
    private static void prosesBooking() {

        System.out.println(CYAN + "\n── LANGKAH 1: DATA PENUMPANG ──" + RESET);
        Penumpang penumpang = inputDataPenumpang();

        System.out.println(CYAN + "\n── LANGKAH 2: JUMLAH TIKET ──" + RESET);
        System.out.println("  (Pesan 2 tiket sekaligus = hemat 10%!)");
        int jumlah = inputInt("Jumlah tiket [1-4]: ", 1, 4);

        System.out.println(CYAN + "\n── LANGKAH 3: JENIS KBT ──" + RESET);
        JenisKBT jenis = pilihJenisKBT();

        System.out.println(CYAN + "\n── LANGKAH 4: PILIH KURSI ──" + RESET);
        service.getKabin().tampilkanDenah();
        List<String> kursi = pilihKursi(jumlah);

        System.out.println(CYAN + "\n── LANGKAH 5: PAKET BARANG (opsional) ──" + RESET);
        UkuranPaket paket = pilihPaket();

        // Buat tiket via service (Factory Method)
        try {
            Tiket tiket = service.buatTiket(penumpang, jenis, kursi, jumlah, paket);
            System.out.println(GREEN + BOLD + "\n✔  Booking berhasil!" + RESET);
            System.out.println(tiket.cetakTiket());
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "\n✘  Gagal: " + e.getMessage() + RESET);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  INPUT HELPERS
    // ═══════════════════════════════════════════════════════════════════════
    private static Penumpang inputDataPenumpang() {
        System.out.print("  Nama lengkap        : "); String nama = sc.nextLine().trim();
        System.out.print("  No. HP              : "); String hp   = sc.nextLine().trim();
        System.out.print("  Nama loket          : "); String loket= sc.nextLine().trim();

        System.out.println("  Tujuan:");
        String[] kota = {"Medan","Binjai","Kabanjahe","Berastagi",
                         "Pematangsiantar","Tebing Tinggi","Kisaran"};
        for (int i = 0; i < kota.length; i++)
            System.out.printf("    [%d] %s%n", i+1, kota[i]);
        int t = inputInt("  Pilih tujuan [1-7]: ", 1, 7);
        String tujuan = kota[t - 1];

        System.out.print("  Jam keberangkatan (HH:MM) : "); String jam = sc.nextLine().trim();

        return new Penumpang(nama, hp, loket, tujuan, jam);
    }

    private static JenisKBT pilihJenisKBT() {
        JenisKBT[] values = JenisKBT.values();
        for (int i = 0; i < values.length; i++)
            System.out.printf("  [%d] %s%n", i+1, values[i]);
        int p = inputInt("  Pilih jenis [1-2]: ", 1, 2);
        return values[p - 1];
    }

    private static List<String> pilihKursi(int jumlah) {
        List<String> dipilih = new ArrayList<>();
        System.out.println("  Pilih " + jumlah + " kursi (contoh: A1, B2, ...)");
        while (dipilih.size() < jumlah) {
            System.out.printf("  Kursi ke-%d : ", dipilih.size() + 1);
            String kode = sc.nextLine().trim().toUpperCase();
            if (!service.getKabin().kursiAda(kode)) {
                System.out.println(RED + "  Kursi tidak dikenal. Coba lagi." + RESET);
            } else if (!service.getKabin().kursiTersedia(kode)) {
                System.out.println(RED + "  Kursi " + kode + " sudah terisi. Pilih lain." + RESET);
            } else if (dipilih.contains(kode)) {
                System.out.println(YELLOW + "  Kursi sudah dipilih sebelumnya." + RESET);
            } else {
                dipilih.add(kode);
                System.out.println(GREEN + "  ✔ Kursi " + kode + " ditambahkan." + RESET);
            }
        }
        return dipilih;
    }

    private static UkuranPaket pilihPaket() {
        System.out.print("  Ada paket/barang yang dikirim? [y/n]: ");
        String jawab = sc.nextLine().trim().toLowerCase();
        if (!jawab.equals("y")) return null;

        UkuranPaket[] uk = UkuranPaket.values();
        for (UkuranPaket u : uk) System.out.println("  " + u);
        int p = inputInt("  Pilih ukuran [1-4]: ", 1, 4);
        return uk[p - 1];
    }

    private static void tampilkanRiwayat() {
        List<Tiket> list = service.getRiwayat();
        if (list.isEmpty()) {
            System.out.println(YELLOW + "\n  Belum ada transaksi." + RESET);
            return;
        }
        System.out.println(CYAN + "\n── RIWAYAT BOOKING ──" + RESET);
        for (Tiket t : list) {
            System.out.printf("  %s | %s | %-20s | %-10s | Rp %,d%n",
                t.getKodeBooking(),
                t.getJenisKBT().getLabel(),
                t.getPenumpang().getNamaLengkap(),
                t.getPenumpang().getTujuan(),
                t.hitungTotal());
        }
    }

    // ── Utilitas ──────────────────────────────────────────────────────────────
    private static int inputInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n >= min && n <= max) return n;
                System.out.println(RED + "  Masukkan angka antara " + min + " dan " + max + "." + RESET);
            } catch (NumberFormatException e) {
                System.out.println(RED + "  Input tidak valid." + RESET);
            }
        }
    }

    private static void menuUtama() {
        System.out.println(BLUE + "\n╔══════════════════════════════╗");
        System.out.println("║    MENU BOOKING MOBIL KBT    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  [1] Pesan Tiket             ║");
        System.out.println("║  [2] Riwayat Booking         ║");
        System.out.println("║  [3] Lihat Denah Kursi       ║");
        System.out.println("║  [4] Keluar                  ║");
        System.out.println("╚══════════════════════════════╝" + RESET);
    }

    private static void banner() {
        System.out.println(CYAN + BOLD);
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║        SISTEM BOOKING MOBIL KBT              ║");
        System.out.println("  ║  Reguler  |  Executive  |  Antar Paket       ║");
        System.out.println("  ╚══════════════════════════════════════════════╝");
        System.out.println(RESET);
    }
}
