package com.mycompany.sistemparkirkantor.main;

public abstract class KendaraanParkir {
    private String platNomor;
    private String namaPemilik;
    private String nip;
    private String departemen;
    private int jamMasuk;

    public KendaraanParkir(String platNomor, String namaPemilik, String nip, String departemen, int jamMasuk) {
        this.platNomor = platNomor;
        this.namaPemilik = namaPemilik;
        this.nip = nip;
        this.departemen = departemen;
        this.jamMasuk = jamMasuk;
    }

    public String getPlatNomor() { return platNomor; }
    public void setPlatNomor(String platNomor) { this.platNomor = platNomor; }

    public String getNamaPemilik() { return namaPemilik; }
    public void setNamaPemilik(String namaPemilik) { this.namaPemilik = namaPemilik; }

    public String getNip() { return nip; }
    public void setNip(String nip) { this.nip = nip; }

    public String getDepartemen() { return departemen; }
    public void setDepartemen(String departemen) { this.departemen = departemen; }

    public int getJamMasuk() { return jamMasuk; }
    public void setJamMasuk(int jamMasuk) {
        if (jamMasuk >= 0 && jamMasuk <= 23) {
            this.jamMasuk = jamMasuk;
        }
    }

    public abstract String getJenisKendaraan();
    public abstract String getDetailKhusus();
    public abstract double hitungBiayaParkir(int jamKeluar);
}