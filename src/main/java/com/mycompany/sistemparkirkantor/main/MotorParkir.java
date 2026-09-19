package com.mycompany.sistemparkirkantor.main;

public class MotorParkir extends KendaraanParkir {
    private int nomorLokerHelm;
    private boolean isPakaiStiker;

    public MotorParkir(String platNomor, String namaPemilik, String nip, String departemen, int jamMasuk, int nomorLokerHelm, boolean isPakaiStiker) {
        super(platNomor, namaPemilik, nip, departemen, jamMasuk);
        this.nomorLokerHelm = nomorLokerHelm;
        this.isPakaiStiker = isPakaiStiker;
    }

    public int getNomorLokerHelm() { return nomorLokerHelm; }
    public void setNomorLokerHelm(int nomorLokerHelm) { this.nomorLokerHelm = nomorLokerHelm; }

    public boolean isPakaiStiker() { return isPakaiStiker; }
    public void setPakaiStiker(boolean pakaiStiker) { isPakaiStiker = pakaiStiker; }

    @Override
    public String getJenisKendaraan() {
        return "Motor";
    }

    @Override
    public String getDetailKhusus() {
        return "Loker Helm: " + nomorLokerHelm + " | Stiker: " + (isPakaiStiker ? "Resmi (Gratis)" : "Tamu");
    }

    @Override
    public double hitungBiayaParkir(int jamKeluar) {
        if (isPakaiStiker) {
            return 0.0; // Gratis untuk karyawan berstiker resmi
        }
        int durasi = Math.max(1, jamKeluar - getJamMasuk());
        return durasi * 2000.0; // Tarif tamu Rp 2.000/jam
    }
}