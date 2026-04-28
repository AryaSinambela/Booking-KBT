# pemrograman-berorientasi-objek-data-dataTypes-PBO-2526-Ge
## Aturan
🚗 Sistem Booking Mobil KBT (Java Console App)
Proyek ini adalah aplikasi berbasis konsol untuk manajemen pemesanan tiket bus/travel KBT. Program ini mengimplementasikan prinsip-prinsip OOP untuk menangani berbagai jenis layanan, pemilihan kursi secara visual, dan perhitungan biaya otomatis termasuk diskon serta layanan paket barang.

🛠️ Arsitektur OOP yang Digunakan
Program ini dibangun dengan struktur kelas sebagai berikut:

Main.java: Sebagai entry point dan pengatur alur antarmuka (UI) pengguna.

BookingService.java: Menggunakan Factory Method Pattern untuk memvalidasi ketersediaan kursi dan menginstansiasi objek Tiket yang sesuai.

Tiket.java (Abstract Class): Kelas induk yang mendefinisikan logika umum seperti perhitungan harga, diskon tiket ganda (10%), dan Template Method untuk mencetak tiket.

TiketReguler.java & TiketExecutive.java: Sub-class yang mengimplementasikan detail khusus (seperti header tiket) untuk masing-masing kategori.

KabinKBT.java: Mengelola state denah kursi menggunakan LinkedHashMap untuk menjaga urutan posisi kursi dari samping supir hingga baris belakang.

JenisKBT & UkuranPaket (Enums): Menyimpan konstanta harga dan label agar data lebih aman dan terpusat.

📋 Fitur Utama
Input Data Penumpang: Nama, No. HP, Loket, dan pilihan 7 kota tujuan populer.

Pemilihan Jenis Armada:

Reguler: Rp 35.000 / kursi.

Executive: Rp 65.000 / kursi.

Visualisasi Denah Kursi: Menampilkan layout kabin (Baris A s/d D) lengkap dengan status (Tersedia, Terisi, atau Sedang Dipilih).

Sistem Diskon: Diskon otomatis 10% jika memesan tepat 2 tiket.

Layanan Paket: Penambahan biaya kirim barang berdasarkan 4 kategori ukuran (Mulai Rp 20.000).

Auto-Generated Ticket: Pencetakan struk rapi dengan kode booking unik (format KBT-XXXX).

🚀 Cara Menjalankan
Menggunakan Script (Linux/macOS)
Pastikan kamu memberikan izin eksekusi pada file .sh yang sudah kamu buat:

Bash
chmod +x compile_and_run.sh
./compile_and_run.sh
Manual (Semua OS)
Buka terminal/command prompt di dalam folder BookingKBT/src.

Kompilasi semua file:

Bash
javac *.java
Jalankan program:

Bash
java Main
📸 Contoh Tampilan Denah

Plaintext
  ┌─────────── DENAH KABIN KBT ───────────┐
  │            [  SUPIR  ]                │
  ├────────────────────────────────────────┤
  │ Baris 1 (samping supir): [A1 ] [###] [A3 ]
  │ Baris 2                : [B1 ] [B2 ] [###] [B4 ]
  ...
Keterangan: [###] berarti kursi sudah dipesan sebelumnya.
