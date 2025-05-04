# Analisis Sentimen Pengaduan Masyarakat

Aplikasi analisis sentimen untuk pengaduan masyarakat menggunakan Spring Boot dan BERT (Bidirectional Encoder Representations from Transformers).

## Fitur

- Input pengaduan masyarakat melalui form yang user-friendly
- Analisis sentimen otomatis menggunakan model BERT
- Visualisasi hasil analisis sentimen (positif, negatif, netral)
- Riwayat pengaduan dengan filter dan statistik
- Antarmuka responsif menggunakan Tailwind CSS

## Teknologi

- Spring Boot 2.7.0
- Java 11
- Deep Java Library (DJL) untuk BERT
- Thymeleaf template engine
- Tailwind CSS
- Google Fonts
- Font Awesome icons

## Cara Menjalankan Aplikasi

1. Pastikan Java 11 dan Maven sudah terinstall di sistem Anda

2. Clone repository ini:
   ```bash
   git clone [repository-url]
   cd complaint-analysis
   ```

3. Build project menggunakan Maven:
   ```bash
   mvn clean install
   ```

4. Jalankan aplikasi:
   ```bash
   mvn spring-boot:run
   ```

5. Buka browser dan akses aplikasi di:
   ```
   http://localhost:8000
   ```

## Penggunaan

1. **Submit Pengaduan**
   - Buka halaman utama
   - Isi form pengaduan
   - Klik tombol "Analisis Sentimen"

2. **Lihat Hasil Analisis**
   - Hasil analisis akan ditampilkan dengan visualisasi
   - Termasuk skor kepercayaan (confidence score)

3. **Akses Riwayat**
   - Klik menu "Riwayat"
   - Lihat semua pengaduan beserta hasil analisisnya
   - Filter berdasarkan sentimen

## Struktur Project

```
project-root/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/complaintanalysis/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── ComplaintAnalysisApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   ├── result.html
│   │       │   └── history.html
│   │       └── application.properties
└── pom.xml
```

## Konfigurasi

Konfigurasi aplikasi dapat diubah di `src/main/resources/application.properties`:

- `server.port`: Port server (default: 8000)
- `app.sentiment.confidence-threshold`: Threshold confidence score (default: 0.6)
- `app.sentiment.model.cache-dir`: Direktori cache model BERT

## Kontribusi

Silakan berkontribusi dengan membuat pull request atau melaporkan issues.

## Lisensi

[MIT License](LICENSE)
