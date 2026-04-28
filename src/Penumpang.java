public class Penumpang {
    private String namaLengkap;
    private String noHp;
    private String namaLoket;
    private String tujuan;
    private String jamKeberangkatan;

    public Penumpang(String namaLengkap, String noHp, String namaLoket,
                     String tujuan, String jamKeberangkatan) {
        this.namaLengkap = namaLengkap;
        this.noHp = noHp;
        this.namaLoket = namaLoket;
        this.tujuan = tujuan;
        this.jamKeberangkatan = jamKeberangkatan;
    }

    public String getNamaLengkap()       { return namaLengkap; }
    public String getNoHp()              { return noHp; }
    public String getNamaLoket()         { return namaLoket; }
    public String getTujuan()            { return tujuan; }
    public String getJamKeberangkatan()  { return jamKeberangkatan; }

    @Override
    public String toString() {
        return "Nama     : " + namaLengkap + "\n" +
               "No. HP   : " + noHp        + "\n" +
               "Loket    : " + namaLoket   + "\n" +
               "Tujuan   : " + tujuan      + "\n" +
               "Jam      : " + jamKeberangkatan;
    }
}
