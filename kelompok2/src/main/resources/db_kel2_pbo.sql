-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Jun 2024 pada 10.44
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kel2_pbo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `anak`
--

CREATE TABLE `anak` (
  `idAnak` int(11) NOT NULL,
  `namaAnak` varchar(50) NOT NULL,
  `umurAnak` int(2) NOT NULL,
  `jenisKelamin` varchar(15) NOT NULL,
  `statusAnak` varchar(20) NOT NULL,
  `kodePantiAsuhan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `anak`
--

INSERT INTO `anak` (`idAnak`, `namaAnak`, `umurAnak`, `jenisKelamin`, `statusAnak`, `kodePantiAsuhan`) VALUES
(4, 'Coco', 11, 'Perempuan', 'Normal', 2),
(6, 'Maria', 12, 'Perempuan', 'Normal', 1),
(7, 'Luffy', 12, 'Laki-laki', 'Normal', 1),
(8, 'Zoro', 10, 'Laki-laki', 'Normal', 1),
(9, 'Nami', 12, 'Perempuan', 'Normal', 1),
(10, 'Chopper', 9, 'Laki-laki', 'Berkebutuhan Khusus', 1),
(11, 'Usopp', 12, 'Laki-laki', 'Normal', 1),
(12, 'Sanji', 15, 'Laki-laki', 'Normal', 1),
(13, 'Franky', 16, 'Laki-laki', 'Berkebutuhan Khusus', 1),
(14, 'Brook', 17, 'Laki-laki', 'Berkebutuhan Khusus', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `bantuan`
--

CREATE TABLE `bantuan` (
  `idBantuan` int(11) NOT NULL,
  `idDonatur` int(11) NOT NULL,
  `date` date NOT NULL,
  `jenisBarang` varchar(50) NOT NULL,
  `kuantitas` int(11) NOT NULL,
  `jumlahUang` int(11) NOT NULL,
  `jenisBantuan` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `bantuan`
--

INSERT INTO `bantuan` (`idBantuan`, `idDonatur`, `date`, `jenisBarang`, `kuantitas`, `jumlahUang`, `jenisBantuan`) VALUES
(1, 2, '2024-05-31', 'Makanan', 12, 0, 'B'),
(2, 2, '2024-05-31', '-', 0, 21, 'U'),
(3, 4, '2024-05-31', 'Pakaian', 200, 0, 'B'),
(4, 4, '2024-05-31', '-', 0, 777000000, 'U'),
(5, 4, '2024-05-31', '-', 0, 777000000, 'U'),
(6, 4, '2024-05-31', '-', 0, 12345000, 'U'),
(7, 4, '2024-05-31', 'Pakaian', 1000, 0, 'B'),
(8, 4, '2024-05-31', 'Pakaian', 1, 0, 'B'),
(9, 4, '2024-05-31', 'Makanan', 1000, 0, 'B'),
(10, 4, '2024-05-31', '-', 0, 3000, 'U'),
(11, 4, '2024-05-31', '-', 0, 12222222, 'U'),
(12, 4, '2024-05-31', '-', 0, 12222, 'U'),
(13, 4, '2024-06-01', '11111111112', 19, 0, 'B'),
(14, 4, '2024-06-01', '-', 0, 1111111, 'U'),
(15, 2, '2024-06-01', 'Tempat Tidur', 12, 0, 'B'),
(16, 2, '2024-06-01', '-', 0, 20000, 'U'),
(17, 6, '2024-06-01', 'Buku', 12, 0, 'B'),
(18, 6, '2024-06-01', 'Mainan Batu', 10, 0, 'B'),
(19, 6, '2024-06-01', '-', 0, 33333333, 'U');

-- --------------------------------------------------------

--
-- Struktur dari tabel `donatur`
--

CREATE TABLE `donatur` (
  `idDonatur` int(11) NOT NULL,
  `namaDonatur` varchar(100) NOT NULL,
  `umurDonatur` int(2) NOT NULL,
  `pekerjaanDonatur` varchar(50) NOT NULL,
  `alamatDonatur` varchar(150) NOT NULL,
  `nomorDonatur` varchar(15) NOT NULL,
  `emailDonatur` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `role` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `donatur`
--

INSERT INTO `donatur` (`idDonatur`, `namaDonatur`, `umurDonatur`, `pekerjaanDonatur`, `alamatDonatur`, `nomorDonatur`, `emailDonatur`, `password`, `role`) VALUES
(1, 'Admin', 20, 'Mahasiswa', 'Universitas Mulawarman', '082148188767', 'kelompok2@gmail.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'A'),
(2, 'Dinda Ayu Aprilia', 20, 'Mahasiswa', 'Jl. perjuangan', '08122222222', 'dinda@gmail.com', '7384ccd839149ed98a47f7c61f5167f8ddb7167ef58003bd8f984ac0407c2e63', 'U'),
(3, 'Vista Atsfi', 20, 'Dokter', 'Jl. A no.7 kota Samarinda', '777777777777', 'vista@gmail.com', '6fb80b36f70e5ec31fd295a194ed82bb9b65bb77fb1f66df5671c26c195ffbc7', 'U'),
(4, 'Jane Ports', 19, 'Mahasiswa', 'Di mana-mana hatiku senang', '+6287532668976', 'jane7@gmail.com', '27545b395a8e5915b48557d0e26ef3e05e368d0f65ae786a806df38f9f4e3bc5', 'U'),
(6, 'M. Sahit', 20, 'Karyawan', 'Jl. AAAAAAAAAAAA', '0822222222', 'sahit@gmail.com', 'e310abe880fc8e2b5d97a3eff325f541587b1a2ebdaf6c750422bcb3952a75b7', 'U');

-- --------------------------------------------------------

--
-- Struktur dari tabel `historianak`
--

CREATE TABLE `historianak` (
  `idAnak` int(11) NOT NULL,
  `namaAnak` varchar(50) NOT NULL,
  `umurAnak` int(2) NOT NULL,
  `jenisKelamin` varchar(20) NOT NULL,
  `statusAnak` varchar(30) NOT NULL,
  `statusKeluar` varchar(10) NOT NULL,
  `namaPantiAsuhan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `historianak`
--

INSERT INTO `historianak` (`idAnak`, `namaAnak`, `umurAnak`, `jenisKelamin`, `statusAnak`, `statusKeluar`, `namaPantiAsuhan`) VALUES
(1, 'Dinda', 1, 'Perempuan', 'Normal', 'Diadopsi', 'SunShine'),
(2, 'Vista', 7, 'Perempuan', 'Normal', 'Diadopsi', 'SunShine'),
(5, 'Gracia', 11, 'Laki-laki', 'Berkebutuhan Khusus', 'Lulus', 'SunShine'),
(16, 'Robin', 10, 'Perempuan', 'Normal', 'Lulus', 'SunShine'),
(17, 'Aza', 12, 'Perempuan', 'Berkebutuhan Khusus', 'Diadopsi', 'SunShine');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pantiasuhan`
--

CREATE TABLE `pantiasuhan` (
  `kodePantiAsuhan` int(11) NOT NULL,
  `namaPantiAsuhan` varchar(100) NOT NULL,
  `kapasitasPantiAsuhan` int(3) NOT NULL,
  `lokasiPantiAsuhan` varchar(100) NOT NULL,
  `statusPantiAsuhan` varchar(20) NOT NULL,
  `statusBangunan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pantiasuhan`
--

INSERT INTO `pantiasuhan` (`kodePantiAsuhan`, `namaPantiAsuhan`, `kapasitasPantiAsuhan`, `lokasiPantiAsuhan`, `statusPantiAsuhan`, `statusBangunan`) VALUES
(1, 'SunShine', 10, 'Jl. Mawar', 'Tidak Penuh', 'Baik'),
(2, 'ABC Domba', 110, 'Jl. Mawar No.13 ', 'Tidak Penuh', 'Baik'),
(3, 'Mawar', 50, 'J. Kehidupan1', 'Tidak Penuh', 'Baik'),
(4, 'Azaea', 11, 'Jl. Perjuangan', 'Tidak Penuh', 'Baik');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `anak`
--
ALTER TABLE `anak`
  ADD PRIMARY KEY (`idAnak`),
  ADD KEY `kodePantiAsuhan` (`kodePantiAsuhan`);

--
-- Indeks untuk tabel `bantuan`
--
ALTER TABLE `bantuan`
  ADD PRIMARY KEY (`idBantuan`),
  ADD KEY `idDonatur` (`idDonatur`);

--
-- Indeks untuk tabel `donatur`
--
ALTER TABLE `donatur`
  ADD PRIMARY KEY (`idDonatur`);

--
-- Indeks untuk tabel `historianak`
--
ALTER TABLE `historianak`
  ADD PRIMARY KEY (`idAnak`);

--
-- Indeks untuk tabel `pantiasuhan`
--
ALTER TABLE `pantiasuhan`
  ADD PRIMARY KEY (`kodePantiAsuhan`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `anak`
--
ALTER TABLE `anak`
  MODIFY `idAnak` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT untuk tabel `bantuan`
--
ALTER TABLE `bantuan`
  MODIFY `idBantuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `donatur`
--
ALTER TABLE `donatur`
  MODIFY `idDonatur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `historianak`
--
ALTER TABLE `historianak`
  MODIFY `idAnak` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT untuk tabel `pantiasuhan`
--
ALTER TABLE `pantiasuhan`
  MODIFY `kodePantiAsuhan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `anak`
--
ALTER TABLE `anak`
  ADD CONSTRAINT `anak_ibfk_1` FOREIGN KEY (`kodePantiAsuhan`) REFERENCES `pantiasuhan` (`kodePantiAsuhan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `bantuan`
--
ALTER TABLE `bantuan`
  ADD CONSTRAINT `bantuan_ibfk_1` FOREIGN KEY (`idDonatur`) REFERENCES `donatur` (`idDonatur`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
