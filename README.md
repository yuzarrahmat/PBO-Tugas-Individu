# PBO-Tugas-Individu
# Sistem Manajemen Parkir Kantor

**Nama:** Yuzar Rahmat Rafi Alhaq  
**NIM:** 2509116025  
**Kelas:** Sistem Informasi A '2025  

---

## 1. Penjelasan Studi Kasus

Sistem Manajemen Parkir Kantor adalah aplikasi berbasis *Command Line Interface* (CLI) yang dikembangkan menggunakan bahasa pemrograman Java dengan menerapkan konsep Pemrograman Berorientasi Objek (OOP). 

Aplikasi ini dirancang untuk mencatat dan mengelola operasional kendaraan yang parkir di area kantor. Sistem menyediakan fungsi CRUD (*Create, Read, Update, Delete*) lengkap yang meliputi:
* **Create:** Mencatat kendaraan masuk (mobil/motor) beserta identitas pemilik, NIP, departemen, jam masuk, serta atribut khusus.
  <img width="1920" height="1020" alt="Screenshot 2026-09-20 002249" src="https://github.com/user-attachments/assets/0b1c9229-9cd2-4cb1-9c06-1cb03931b40e" />

* **Read:** Menampilkan seluruh daftar kendaraan yang sedang terparkir secara rapi di konsol.
  <img width="1920" height="1020" alt="Screenshot 2026-09-20 002418" src="https://github.com/user-attachments/assets/7ef7be20-f85b-4a6d-835b-37347e50af51" />

* **Update:** Memperbarui data pemilik dan departemen kendaraan berdasarkan plat nomor.
  <img width="1920" height="1020" alt="Screenshot 2026-09-20 002454" src="https://github.com/user-attachments/assets/058d61ec-27ef-45b5-ad42-ed81dd98915a" />

* **Delete:** Memproses kendaraan keluar, menghitung total biaya parkir otomatis berdasarkan durasi dan status stiker resmi, lalu menghapus data kendaraan dari memori.
  <img width="1920" height="1020" alt="Screenshot 2026-09-20 002713" src="https://github.com/user-attachments/assets/d3b8fa03-e323-4801-8e98-3d49efdcbde9" />


---

## 2. Diagram Kelas & Penjelasan Hierarki Class

### Diagram Kelas Sederhana
```
          [KendaraanParkir] (Abstract Superclass)
                 ▲
                 │
       ┌─────────┴─────────┐
       │                   │
[MobilParkir]        [MotorParkir]
(Subclass 1)         (Subclass 2)
-------------------------------------------------------
Penjelasan Hierarki Class:
KendaraanParkir: Bertindak sebagai Abstract Superclass yang menampung atribut umum milik seluruh kendaraan kantor (platNomor, namaPemilik, nip, departemen, jamMasuk) serta mendeklarasikan method abstrak untuk diimplementasikan oleh subclass.

MobilParkir: Bertindak sebagai Concrete Subclass khusus kendaraan roda empat yang mewarisi KendaraanParkir dengan tambahan atribut khusus slotParkir dan status isStikerResmi.

MotorParkir: Bertindak sebagai Concrete Subclass khusus kendaraan roda dua yang mewarisi KendaraanParkir dengan tambahan atribut khusus nomorLokerHelm dan status isPakaiStiker.
```
### 3. Penjelasan Bagian Kode yang Menerapkan Inheritance
Penerapan Inheritance (Pewarisan) dilakukan dengan menggunakan kata kunci extends pada class MobilParkir dan MotorParkir terhadap class KendaraanParkir.
```
Java
package com.mycompany.sistemparkirkantor.main;

// MobilParkir mewarisi seluruh atribut dan method dari KendaraanParkir
public class MobilParkir extends KendaraanParkir {
    private String slotParkir;
    private boolean isStikerResmi;

    public MobilParkir(String platNomor, String namaPemilik, String nip, String departemen, int jamMasuk, String slotParkir, boolean isStikerResmi) {
        // Memanggil constructor dari Superclass
        super(platNomor, namaPemilik, nip, departemen, jamMasuk);
        this.slotParkir = slotParkir;
        this.isStikerResmi = isStikerResmi;
    }

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
            return 0.0; // Karyawan berstiker resmi gratis
        }
        int durasi = Math.max(1, jamKeluar - getJamMasuk());
        return durasi * 5000.0; // Tamu dikenakan tarif Rp 5.000/jam
    }
}
```
Poin Utama Penerapan Inheritance:
Reusability Kode: Subclass cukup mendefinisikan atribut spesifiknya saja, sedangkan atribut umum seperti platNomor, namaPemilik, nip, departemen, dan jamMasuk diwarisi langsung dari KendaraanParkir.

Method Overriding: Method abstrak getJenisKendaraan(), getDetailKhusus(), dan hitungBiayaParkir() di-override oleh masing-masing subclass untuk menyesuaikan logika kalkulasi dan tampilan spesifik kendaraan.
