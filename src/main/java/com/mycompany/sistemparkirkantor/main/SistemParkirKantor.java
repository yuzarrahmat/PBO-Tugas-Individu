package com.mycompany.sistemparkirkantor.main;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemParkirKantor {
    private static ArrayList<KendaraanParkir> list = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        // Data Awal
        list.add(new MobilParkir("KT 1234 AB", "Ryu", "199801", "IT", 8, "B1", true));
        list.add(new MotorParkir("KT 5678 CD", "Budi", "199802", "HR", 9, 102, true));

        while (true) {
            System.out.println("\n=== PARKIR KANTOR ===\n1. Tampil Data\n2. Tambah Kendaraan\n3. Ubah Data\n4. Hapus / Keluar Parkir\n5. Keluar");
            System.out.print("Pilih [1-5]: ");
            int pilih = bacaInt();

            if (pilih == 1) tampilData();
            else if (pilih == 2) tambahData();
            else if (pilih == 3) ubahData();
            else if (pilih == 4) hapusData();
            else if (pilih == 5) break;
            else System.out.println("Pilihan tidak valid!");
        }
    }

    private static void tampilData() {
        if (list.isEmpty()) { System.out.println("Parkir kosong."); return; }
        System.out.println("\n-------------------------------------------------------------------------------------");
        for (KendaraanParkir k : list) {
            System.out.printf("%s | %s | %s (%s) | Jam Masuk: %d | %s\n",
                    k.getPlatNomor(), k.getJenisKendaraan(), k.getNamaPemilik(), k.getDepartemen(), k.getJamMasuk(), k.getDetailKhusus());
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private static void tambahData() {
        System.out.print("Jenis (1. Mobil / 2. Motor): ");
        int j = bacaInt();
        System.out.print("Plat Nomor: "); String plat = in.nextLine();
        System.out.print("Nama Pemilik: "); String nama = in.nextLine();
        System.out.print("NIP: "); String nip = in.nextLine();
        System.out.print("Departemen: "); String dept = in.nextLine();
        System.out.print("Jam Masuk (0-23): "); int jam = bacaInt();

        if (j == 1) {
            System.out.print("Slot Parkir: "); String slot = in.nextLine();
            System.out.print("Stiker Resmi (y/n)? "); boolean s = in.nextLine().equalsIgnoreCase("y");
            list.add(new MobilParkir(plat, nama, nip, dept, jam, slot, s));
        } else if (j == 2) {
            System.out.print("No Loker Helm: "); int loker = bacaInt();
            System.out.print("Stiker Resmi (y/n)? "); boolean s = in.nextLine().equalsIgnoreCase("y");
            list.add(new MotorParkir(plat, nama, nip, dept, jam, loker, s));
        }
        System.out.println("Berhasil ditambahkan!");
    }

    private static void ubahData() {
        System.out.print("Masukkan Plat Nomor: ");
        KendaraanParkir k = cari(in.nextLine());
        if (k != null) {
            System.out.print("Nama Pemilik Baru: "); k.setNamaPemilik(in.nextLine());
            System.out.print("Departemen Baru: "); k.setDepartemen(in.nextLine());
            System.out.println("Data berhasil diperbarui!");
        } else System.out.println("Data tidak ditemukan!");
    }

    private static void hapusData() {
        System.out.print("Masukkan Plat Nomor Keluar: ");
        KendaraanParkir k = cari(in.nextLine());
        if (k != null) {
            System.out.print("Jam Keluar (0-23): ");
            int jamKeluar = bacaInt();
            System.out.println("Total Biaya Parkir: Rp " + k.hitungBiayaParkir(jamKeluar));
            list.remove(k);
            System.out.println("Kendaraan telah keluar!");
        } else System.out.println("Data tidak ditemukan!");
    }

    private static KendaraanParkir cari(String plat) {
        for (KendaraanParkir k : list) {
            if (k.getPlatNomor().equalsIgnoreCase(plat)) return k;
        }
        return null;
    }

    private static int bacaInt() {
        while (true) {
            try { return Integer.parseInt(in.nextLine().trim()); }
            catch (Exception e) { System.out.print("Input angka! Masukkan lagi: "); }
        }
    }
}