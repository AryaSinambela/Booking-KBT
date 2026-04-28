public enum JenisKBT {
    REGULER("KBT Reguler", 35_000),
    EXECUTIVE("KBT Executive", 65_000);

    private final String label;
    private final int hargaPerKursi;

    JenisKBT(String label, int hargaPerKursi) {
        this.label = label;
        this.hargaPerKursi = hargaPerKursi;
    }

    public String getLabel() { return label; }
    public int getHargaPerKursi() { return hargaPerKursi; }

    @Override
    public String toString() { return label + " (Rp " + String.format("%,d", hargaPerKursi) + "/kursi)"; }
}
