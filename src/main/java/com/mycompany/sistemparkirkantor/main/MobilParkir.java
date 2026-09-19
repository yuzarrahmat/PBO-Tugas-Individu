package com.mycompany.sistemparkirkantor.main;

public class MobilParkir extends KendaraanParkir {
    private String slotParkir;
    private boolean isStikerResmi;

    public MobilParkir(String platNomor, String namaPemilik, String nip, String departemen, int jamMasuk, String slotParkir, boolean isStikerResmi) {
        super(platNomor, namaPemilik, nip, departemen, jamMasuk);
        this.slotParkir = slotParkir;
        this.isStikerResmi = isStikerResmi;
    }

    public String getSlotParkir() { return slotParkir; }
    public void setSlotParkir(String slotParkir) { this.slotParkir = slotParkir; }

    public boolean isStikerResmi() { return isStikerResmi; }
    public void setStikerResmi(boolean stikerResmi) { isStikerResmi = stikerResmi; }

    @Override
    public String getJenisKendaraan() {
        return "Mobil";
    }

    @Override
    public String getDetailKhusus() {
        return "Slot: " + slotParkir + " | Stiker: " + (isStikerResmi ? "Resmi (Gratis)" : "Tamu");
    }

    @Override
    public double hitungBiayaParkir(int jamKeluar) {
        if (isStikerResmi) {
            return 0.0; // Gratis untuk karyawan berstiker resmi
        }
        int durasi = Math.max(1, jamKeluar - getJamMasuk());
        return durasi * 5000.0; // Tarif tamu Rp 5.000/jam
    }
}