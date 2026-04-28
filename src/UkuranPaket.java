public enum UkuranPaket {
    KECIL("Kecil (maks 2 kg)", 20_000),
    SEDANG("Sedang (2–5 kg)", 35_000),
    BESAR("Besar (5–15 kg)", 55_000),
    SANGAT_BESAR("Sangat Besar (>15 kg)", 80_000);

    private final String keterangan;
    private final int harga;

    UkuranPaket(String keterangan, int harga) {
        this.keterangan = keterangan;
        this.harga = harga;
    }

    public String getKeterangan() { return keterangan; }
    public int getHarga() { return harga; }

    @Override
    public String toString() {
        return "[" + (ordinal() + 1) + "] " + keterangan + " - Rp " + String.format("%,d", harga);
    }
}
