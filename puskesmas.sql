-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2017 at 06:45 PM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `puskesmas`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `ID` char(6) NOT NULL,
  `Nama_Admin` varchar(20) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ID`, `Nama_Admin`, `Username`, `Password`) VALUES
('000000', 'Admin', 'admin', '1');

-- --------------------------------------------------------

--
-- Table structure for table `antrian`
--

CREATE TABLE IF NOT EXISTS `antrian` (
  `no_antrian` int(11) NOT NULL,
  `no_registrasi` char(15) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `bagian` enum('Umum','Gigi','Bidan') NOT NULL,
  `tanggal` int(11) NOT NULL,
  `flag_active` char(1) NOT NULL,
  `flag_skip` char(1) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `antrian`
--

INSERT INTO `antrian` (`no_antrian`, `no_registrasi`, `nama_pasien`, `bagian`, `tanggal`, `flag_active`, `flag_skip`, `tanggal_entri`, `nama_pengentri`) VALUES
(2, 'P00411707030001', 'Melani', 'Bidan', 8, 'N', 'N', '2017-08-08 23:58:57', 'Inne Aprianna Kabeakan'),
(1, '040199140517001', 'Adnan Firdaus', 'Umum', 9, 'N', 'N', '2017-08-09 00:15:33', 'Inne Aprianna Kabeakan'),
(1, 'P10161707060001', 'Bramantyo', 'Bidan', 9, 'N', 'N', '2017-08-09 00:35:50', 'Inne Aprianna Kabeakan'),
(1, '210795140517001', 'Fahri Ansyah', 'Gigi', 9, 'N', 'N', '2017-08-09 09:16:20', 'Inne Aprianna Kabeakan'),
(2, 'P12451708080001', 'Pramudya Airlangga', 'Gigi', 9, 'N', 'N', '2017-08-09 09:49:55', 'Inne Aprianna Kabeakan'),
(3, '040199140517001', 'Adnan Firdaus', 'Gigi', 9, 'N', 'N', '2017-08-09 10:05:40', 'Inne Aprianna Kabeakan'),
(4, '131192060617001', 'Rahmat Solihun', 'Gigi', 9, 'N', 'N', '2017-08-09 10:13:32', 'Inne Aprianna Kabeakan'),
(5, 'P00411707030001', 'Melani', 'Gigi', 9, 'N', 'N', '2017-08-09 10:18:30', 'Inne Aprianna Kabeakan'),
(6, 'P01391707030002', 'Siti Rokayah', 'Gigi', 9, 'N', 'N', '2017-08-09 10:24:36', 'Inne Aprianna Kabeakan');

-- --------------------------------------------------------

--
-- Table structure for table `antrian_bayar`
--

CREATE TABLE IF NOT EXISTS `antrian_bayar` (
  `no_antrian` int(11) NOT NULL,
  `nomor_rekam_medis` char(14) NOT NULL,
  `no_registrasi` char(15) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `tanggal` varchar(2) NOT NULL,
  `flag_active` char(1) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `antrian_bayar`
--

INSERT INTO `antrian_bayar` (`no_antrian`, `nomor_rekam_medis`, `no_registrasi`, `nama_pasien`, `tanggal`, `flag_active`, `tanggal_entri`, `nama_pengentri`) VALUES
(1, 'RM080820170001', 'P00411707030001', 'Melani', '9', 'N', '2017-08-09 00:01:31', 'Umi Rahmawati, Am.Keb'),
(2, 'RM090820170002', '040199140517001', 'Adnan Firdaus', '9', 'N', '2017-08-09 00:18:32', 'dr. Heri Muhardi'),
(3, 'RM090820170003', 'P10161707060001', 'Bramantyo', '9', 'N', '2017-08-09 00:37:20', 'Umi Rahmawati, Am.Keb'),
(4, 'RM090820170004', '210795140517001', 'Fahri Ansyah', '9', 'N', '2017-08-09 09:18:41', 'drg. Afrida Aryani'),
(5, 'RM090820170005', 'P12451708080001', 'Pramudya Airlangga', '9', 'N', '2017-08-09 09:54:48', 'drg. Afrida Aryani'),
(6, 'RM090820170006', '040199140517001', 'Adnan Firdaus', '9', 'N', '2017-08-09 10:06:59', 'drg. Afrida Aryani'),
(7, 'RM090820170007', '131192060617001', 'Rahmat Solihun', '9', 'N', '2017-08-09 10:16:00', 'drg. Afrida Aryani'),
(8, 'RM090820170008', 'P00411707030001', 'Melani', '9', 'N', '2017-08-09 10:21:29', 'drg. Afrida Aryani'),
(9, 'RM090820170009', 'P01391707030002', 'Siti Rokayah', '9', 'N', '2017-08-09 10:25:37', 'drg. Afrida Aryani');

-- --------------------------------------------------------

--
-- Table structure for table `bidan`
--

CREATE TABLE IF NOT EXISTS `bidan` (
  `kode_bidan` char(6) NOT NULL,
  `nama_bidan` varchar(200) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `password` varchar(20) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_resep`
--

CREATE TABLE IF NOT EXISTS `detail_resep` (
  `kode_detail` int(11) NOT NULL,
  `kode_resep` char(12) NOT NULL,
  `kode_obat` char(6) NOT NULL,
  `nama_obat` varchar(200) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga_satuan` int(11) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_resep`
--

INSERT INTO `detail_resep` (`kode_detail`, `kode_resep`, `kode_obat`, `nama_obat`, `jumlah`, `harga_satuan`, `tanggal_entri`, `nama_pengentri`) VALUES
(66, 'RSP170808001', 'TAB006', 'B.COM-C', 1, 25000, '2017-08-09 00:00:11', 'Umi Rahmawati, Am.Keb'),
(67, 'RSP170808001', 'VIT008', 'Prenatal', 1, 10000, '2017-08-09 00:00:22', 'Umi Rahmawati, Am.Keb'),
(68, 'RSP170809002', 'TAB063', 'Simvastatin 10 Mg', 1, 10000, '2017-08-09 00:17:06', 'dr. Heri Muhardi'),
(69, 'RSP170809002', 'VIT012', 'Sangobion', 2, 8000, '2017-08-09 00:17:46', 'dr. Heri Muhardi'),
(70, 'RSP170809003', 'VIT007', 'Novabion', 3, 10000, '2017-08-09 00:37:09', 'Umi Rahmawati, Am.Keb'),
(71, 'RSP170809004', 'TAB004', 'Ambroxol', 1, 10000, '2017-08-09 09:18:27', 'drg. Afrida Aryani'),
(72, 'RSP170809005', 'VIT013', 'Fondazen', 2, 10000, '2017-08-09 09:54:27', 'drg. Afrida Aryani'),
(73, 'RSP170809006', 'VIT009', 'Rovital', 1, 10000, '2017-08-09 10:06:48', 'drg. Afrida Aryani'),
(74, 'RSP170809007', 'VIT014', 'Enervon-C', 1, 25000, '2017-08-09 10:15:43', 'drg. Afrida Aryani'),
(75, 'RSP170809008', 'TAB038', 'Bion-3', 1, 25000, '2017-08-09 10:21:16', 'drg. Afrida Aryani'),
(76, 'RSP170809009', 'TAB039', 'Neuralgin', 3, 20000, '2017-08-09 10:25:26', 'drg. Afrida Aryani');

-- --------------------------------------------------------

--
-- Table structure for table `detail_tindakan`
--

CREATE TABLE IF NOT EXISTS `detail_tindakan` (
  `kode_detail` bigint(20) NOT NULL,
  `nomor_rekam_medis` char(14) NOT NULL,
  `kode_tindakan` char(6) NOT NULL,
  `nama_tindakan` varchar(200) NOT NULL,
  `harga` int(11) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_tindakan`
--

INSERT INTO `detail_tindakan` (`kode_detail`, `nomor_rekam_medis`, `kode_tindakan`, `nama_tindakan`, `harga`, `tanggal_entri`, `nama_pengentri`) VALUES
(71, 'RM080820170001', 'JLA001', 'Pemeriksaan Bidan', 15000, '2017-08-08 23:59:48', 'Umi Rahmawati, Am.Keb'),
(72, 'RM080820170001', 'INJ004', 'Injection Dexa', 20000, '2017-08-08 23:59:53', 'Umi Rahmawati, Am.Keb'),
(73, 'RM090820170002', 'JLA002', 'Pemeriksaan Dokter Umum', 20000, '2017-08-09 00:16:24', 'dr. Heri Muhardi'),
(74, 'RM090820170002', 'IMN004', 'Imunisasi Campak / Polio', 35000, '2017-08-09 00:16:28', 'dr. Heri Muhardi'),
(75, 'RM090820170002', 'HEM002', 'Hemogoblin', 20000, '2017-08-09 00:16:37', 'dr. Heri Muhardi'),
(76, 'RM090820170003', 'JLA001', 'Pemeriksaan Bidan', 15000, '2017-08-09 00:36:42', 'Umi Rahmawati, Am.Keb'),
(77, 'RM090820170003', 'HEM010', 'Urin Rutin', 30000, '2017-08-09 00:36:50', 'Umi Rahmawati, Am.Keb'),
(78, 'RM090820170004', 'GIG015', 'Pemasangan Gigi Palsu Acrilic 1 Gigi Pertama', 200000, '2017-08-09 09:17:42', 'drg. Afrida Aryani'),
(79, 'RM090820170004', 'GIG017', 'Pemasangan Gigi Palsu Flexi Denture Gigi Pertama Rahang', 500000, '2017-08-09 09:17:52', 'drg. Afrida Aryani'),
(80, 'RM090820170005', 'GIG015', 'Pemasangan Gigi Palsu Acrilic 1 Gigi Pertama', 200000, '2017-08-09 09:50:19', 'drg. Afrida Aryani'),
(81, 'RM090820170005', 'GIG016', 'Pemasangan Gigi Palsu Acrilic Gigi Berikutnya Per 3 ', 300000, '2017-08-09 09:50:41', 'drg. Afrida Aryani'),
(82, 'RM090820170006', 'GIG015', 'Pemasangan Gigi Palsu Acrilic 1 Gigi Pertama', 200000, '2017-08-09 10:06:18', 'drg. Afrida Aryani'),
(83, 'RM090820170006', 'GIG016', 'Pemasangan Gigi Palsu Acrilic Gigi Berikutnya Per 2 ', 200000, '2017-08-09 10:06:27', 'drg. Afrida Aryani'),
(84, 'RM090820170007', 'GIG001', 'Konsultasi', 50000, '2017-08-09 10:14:05', 'drg. Afrida Aryani'),
(85, 'RM090820170007', 'GIG015', 'Pemasangan Gigi Palsu Acrilic 1 Gigi Pertama', 200000, '2017-08-09 10:14:10', 'drg. Afrida Aryani'),
(86, 'RM090820170007', 'GIG016', 'Pemasangan Gigi Palsu Acrilic Gigi Berikutnya Per 2 Gigi', 200000, '2017-08-09 10:14:22', 'drg. Afrida Aryani'),
(87, 'RM090820170007', 'GIG017', 'Pemasangan Gigi Palsu Flexi Denture Gigi Pertama Rahang', 500000, '2017-08-09 10:14:25', 'drg. Afrida Aryani'),
(88, 'RM090820170008', 'GIG001', 'Konsultasi', 50000, '2017-08-09 10:18:51', 'drg. Afrida Aryani'),
(89, 'RM090820170008', 'GIG015', 'Pemasangan Gigi Palsu Acrilic 1 Gigi Pertama', 200000, '2017-08-09 10:18:53', 'drg. Afrida Aryani'),
(90, 'RM090820170008', 'GIG016', 'Pemasangan Gigi Palsu Acrilic Gigi Berikutnya Per 3 Gigi', 300000, '2017-08-09 10:19:00', 'drg. Afrida Aryani'),
(91, 'RM090820170008', 'GIG017', 'Pemasangan Gigi Palsu Flexi Denture Gigi Pertama Rahang', 500000, '2017-08-09 10:19:01', 'drg. Afrida Aryani'),
(92, 'RM090820170008', '', 'Pemasangan Gigi Palsu Flexi Denture Gigi Berikutnya per 2 Gigi', 300000, '2017-08-09 10:19:09', 'drg. Afrida Aryani'),
(93, 'RM090820170009', 'GIG001', 'Konsultasi', 50000, '2017-08-09 10:24:51', 'drg. Afrida Aryani'),
(94, 'RM090820170009', 'GIG015', 'Pemasangan Gigi Palsu Acrilic 1 Gigi Pertama', 200000, '2017-08-09 10:24:53', 'drg. Afrida Aryani'),
(95, 'RM090820170009', 'GIG016', 'Pemasangan Gigi Palsu Acrilic Gigi Berikutnya Per 2 Gigi', 200000, '2017-08-09 10:25:02', 'drg. Afrida Aryani'),
(96, 'RM090820170009', 'GIG017', 'Pemasangan Gigi Palsu Flexi Denture Gigi Pertama Rahang', 500000, '2017-08-09 10:25:03', 'drg. Afrida Aryani'),
(97, 'RM090820170009', 'GIG018', 'Pemasangan Gigi Palsu Flexi Denture Gigi Berikutnya per 4 Gigi', 600000, '2017-08-09 10:25:10', 'drg. Afrida Aryani');

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE IF NOT EXISTS `dokter` (
  `kode_dokter` char(6) NOT NULL,
  `nama_dokter` varchar(200) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `tipe_dokter` enum('Umum','Gigi') NOT NULL,
  `password` varchar(20) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `header_resep`
--

CREATE TABLE IF NOT EXISTS `header_resep` (
  `kode_resep` char(12) NOT NULL,
  `no_registrasi` char(15) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `total_harga_resep` int(11) NOT NULL,
  `tgl_resep` date NOT NULL,
  `aturan_minum` longtext,
  `nama_dokter` varchar(200) NOT NULL,
  `tanggal_entri` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `header_resep`
--

INSERT INTO `header_resep` (`kode_resep`, `no_registrasi`, `nama_pasien`, `total_harga_resep`, `tgl_resep`, `aturan_minum`, `nama_dokter`, `tanggal_entri`) VALUES
('RSP170808001', 'P00411707030001', 'Melani', 35000, '2017-08-09', 'mohon dihabiskan', 'Umi Rahmawati, Am.Keb', '2017-08-09 00:00:05'),
('RSP170809002', '040199140517001', 'Adnan Firdaus', 26000, '2017-08-09', 'habiskaaaan', 'dr. Heri Muhardi', '2017-08-09 00:16:44'),
('RSP170809003', 'P10161707060001', 'Bramantyo', 30000, '2017-08-09', 'habiskan ya', 'Umi Rahmawati, Am.Keb', '2017-08-09 00:36:59'),
('RSP170809004', '210795140517001', 'Fahri Ansyah', 10000, '2017-08-09', 'abisin', 'drg. Afrida Aryani', '2017-08-09 09:18:19'),
('RSP170809005', 'P12451708080001', 'Pramudya Airlangga', 20000, '2017-08-09', 'cukup diminum', 'drg. Afrida Aryani', '2017-08-09 09:54:16'),
('RSP170809006', '040199140517001', 'Adnan Firdaus', 10000, '2017-08-09', 'habisin', 'drg. Afrida Aryani', '2017-08-09 10:06:39'),
('RSP170809007', '131192060617001', 'Rahmat Solihun', 25000, '2017-08-09', 'habiskan!', 'drg. Afrida Aryani', '2017-08-09 10:15:38'),
('RSP170809008', 'P00411707030001', 'Melani', 25000, '2017-08-09', 'qqqqq', 'drg. Afrida Aryani', '2017-08-09 10:21:09'),
('RSP170809009', 'P01391707030002', 'Siti Rokayah', 60000, '2017-08-09', 'qwwreasas', 'drg. Afrida Aryani', '2017-08-09 10:25:19');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE IF NOT EXISTS `karyawan` (
  `kode_karyawan` varchar(6) NOT NULL,
  `nama_karyawan` varchar(200) NOT NULL,
  `jk_karyawan` enum('L','P') NOT NULL,
  `jabatan` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`kode_karyawan`, `nama_karyawan`, `jk_karyawan`, `jabatan`, `password`, `tanggal_entri`, `nama_pengentri`) VALUES
('000000', 'Admin', 'L', '', '1', '2016-08-01 00:00:00', ''),
('AD0001', 'Inne Aprianna Kabeakan', 'P', 'Administrasi', '1', '2016-08-30 11:50:14', 'Admin'),
('AD0002', 'Saras Utami', 'P', 'Administrasi', '1', '2016-08-30 11:50:43', 'Admin'),
('AD0003', 'Ely Epy Juita Simbolon', 'P', 'Administrasi', '1', '2016-08-30 11:50:59', 'Admin'),
('AN0001', 'Semiwati', 'P', 'Analis', '1', '2016-08-30 11:48:03', 'Admin'),
('AP0001', 'Adwita Sari, S.Si., APT', 'P', 'Apoteker', '1', '2016-08-30 11:48:54', 'Admin'),
('DG0001', 'drg. Afrida Aryani', 'P', 'Dokter Gigi', '1', '2016-08-30 14:42:20', 'Admin'),
('DU0001', 'dr. Heri Muhardi', 'L', 'Dokter Umum', '1', '2016-08-30 14:40:59', 'Admin'),
('DU0002', 'dr. Tubagus Aria Santika', 'L', 'Dokter Umum', '1', '2016-08-30 14:41:28', 'Admin'),
('DU0003', 'dr. Juwitasari Setyaningtyas', 'P', 'Dokter Umum', '1', '2016-08-30 14:41:43', 'Admin'),
('KI0001', 'Umi Rahmawati, Am.Keb', 'P', 'KIA', '1', '2016-08-30 14:43:01', 'Admin'),
('KI0002', 'Wina Novita, Am.Keb', 'P', 'KIA', '1', '2016-08-30 14:43:24', 'Admin'),
('KI0003', 'Fairuza Wakhida, Am.Keb', 'P', 'KIA', '1', '2016-08-30 14:43:43', 'Admin'),
('KI0004', 'Yulia Syarif, AM.Keb', 'P', 'KIA', '1', '2016-08-30 14:44:48', 'Admin'),
('KI0005', 'Novi Eka Andriyanti, AM.Keb', 'P', 'KIA', '1', '2016-08-30 15:57:48', 'Admin'),
('KI0006', 'Novi Enitasari, Am.Keb', 'P', 'KIA', '1', '2016-08-30 15:58:22', 'Admin'),
('KI0007', 'Astriana, Am.Keb', 'P', 'KIA', '1', '2016-08-30 15:58:42', 'Admin'),
('KI0008', 'Mega Sintauli Simanjuntak, Am.Keb', 'P', 'KIA', '1', '2016-08-30 15:59:05', 'Admin'),
('KI0009', 'Wellita Apriamala E.C, Am.Keb', 'P', 'KIA', '1', '2016-08-30 15:59:38', 'Admin'),
('KI0010', 'Nuraini, Am.Keb', 'P', 'KIA', '1', '2016-08-30 16:02:27', 'Admin'),
('KI0011', 'Pramulia Yuni Syara, Am.Keb', 'P', 'KIA', '1', '2016-08-30 16:04:19', 'Admin'),
('KK0001', 'Kepala Klinik', 'P', 'Kepala Klinik', '1', '0000-00-00 00:00:00', ''),
('PW0001', 'Dessy Anggraini, Am.Kep', 'P', 'Perawat', '1', '2016-08-30 10:19:02', 'Admin'),
('TG0001', 'Lisnawati, AMG', 'P', 'Tenaga Gizi', '1', '2016-08-30 11:49:41', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `laboratorium`
--

CREATE TABLE IF NOT EXISTS `laboratorium` (
  `kode_tindakanlab` char(6) NOT NULL,
  `nama_tindakanlab` varchar(200) NOT NULL,
  `jenis_tindakanlab` enum('Hematologi','Kimia Darah') NOT NULL,
  `harga_tindakanlab` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `laboratorium`
--

INSERT INTO `laboratorium` (`kode_tindakanlab`, `nama_tindakanlab`, `jenis_tindakanlab`, `harga_tindakanlab`) VALUES
('HEM001', 'H2TL', 'Hematologi', 40000),
('HEM002', 'Hemogoblin', 'Hematologi', 20000),
('HEM003', 'Leukosit', 'Hematologi', 20000),
('HEM004', 'Trombosit', 'Hematologi', 20000),
('HEM005', 'Hematokrit', 'Hematologi', 20000),
('HEM006', 'H2TL + WIDAL', 'Hematologi', 80000),
('HEM007', 'WIDAL', 'Hematologi', 40000),
('HEM008', 'H2TL + WIDAL + LED', 'Hematologi', 90000),
('HEM009', 'Urin Lengkap', 'Hematologi', 40000),
('HEM010', 'Urin Rutin', 'Hematologi', 30000),
('HEM011', 'Feses Rutin', 'Hematologi', 35000),
('HEM012', 'Waktu Pendarahan', 'Hematologi', 25000),
('HEM013', 'Waktu Pembekuan', 'Hematologi', 25000),
('HEM014', 'Golongan Darah', 'Hematologi', 20000),
('HEM015', 'Mantoux Test', 'Hematologi', 95000),
('HEM016', 'Protein Urine / Reduksi', 'Hematologi', 20000),
('KDA001', 'SGOT', 'Kimia Darah', 40000),
('KDA002', 'SGPT', 'Kimia Darah', 40000),
('KDA003', 'Ureum', 'Kimia Darah', 40000),
('KDA004', 'Creatinin', 'Kimia Darah', 40000),
('KDA005', 'HbsAg', 'Kimia Darah', 80000),
('KDA006', 'Anti HbsAg', 'Kimia Darah', 90000),
('KDA007', 'Cholesterol Total', 'Kimia Darah', 30000),
('KDA008', 'Trigliserida', 'Kimia Darah', 30000),
('KDA009', 'HDL', 'Kimia Darah', 30000),
('KDA010', 'LDL', 'Kimia Darah', 30000),
('KDA011', 'Asam Urat', 'Kimia Darah', 25000),
('KDA012', 'Gula Darah Sewaktu', 'Kimia Darah', 20000),
('KDA013', 'Gula Darah Puasa & 2 PP', 'Kimia Darah', 40000),
('KDA014', 'Billirubin Total', 'Kimia Darah', 40000),
('KDA015', 'Pap Smear', 'Kimia Darah', 100000),
('KDA016', 'Sperma Test', 'Kimia Darah', 60000),
('KDA017', 'Swab Vagina / Uretra', 'Kimia Darah', 60000);

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE IF NOT EXISTS `obat` (
  `kode_obat` char(6) NOT NULL,
  `nama_obat` varchar(200) NOT NULL,
  `jenis_obat` enum('Tablet','Vitamin','Pil','KB','Sirup','Salep','Lain-lain') NOT NULL,
  `harga_satuan` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`kode_obat`, `nama_obat`, `jenis_obat`, `harga_satuan`, `stok`, `tanggal_entri`, `nama_pengentri`) VALUES
('TAB001', 'Acyclovir 200 Mg', 'Tablet', 10000, 9, '2017-04-04 21:11:12', 'Adwita Sari, S.Si., APT'),
('TAB002', 'Acyclovir 400 Mg', 'Tablet', 15000, 6, '2017-04-04 21:13:58', 'Adwita Sari, S.Si., APT'),
('TAB004', 'Ambroxol', 'Tablet', 10000, 4, '2017-04-09 22:11:05', 'Adwita Sari, S.Si., APT'),
('TAB005', 'Asam Mefenamat', 'Tablet', 10000, 8, '2017-04-09 22:15:50', 'Adwita Sari, S.Si., APT'),
('TAB006', 'B.COM-C', 'Tablet', 25000, 8, '2017-04-09 22:16:17', 'Adwita Sari, S.Si., APT'),
('TAB007', 'Bromhexyn', 'Tablet', 10000, 7, '2017-04-09 22:29:08', 'Adwita Sari, S.Si., APT'),
('TAB008', 'Capivlex', 'Tablet', 10000, 5, '2017-04-09 22:30:11', 'Adwita Sari, S.Si., APT'),
('TAB009', 'Cefadroxil', 'Tablet', 15000, 10, '2017-04-09 22:30:41', 'Adwita Sari, S.Si., APT'),
('TAB010', 'Cevixim 100 Mg', 'Tablet', 20000, 10, '2017-04-09 22:33:10', 'Adwita Sari, S.Si., APT'),
('TAB011', 'Cetrizine', 'Tablet', 15000, 10, '2017-04-09 22:33:55', 'Adwita Sari, S.Si., APT'),
('TAB012', 'Clorampenicol 250 Mg', 'Tablet', 15000, 10, '2017-04-09 22:35:04', 'Adwita Sari, S.Si., APT'),
('TAB013', 'Cimetidin', 'Tablet', 10000, 10, '2017-04-09 22:35:20', 'Adwita Sari, S.Si., APT'),
('TAB014', 'Ciproflaxacin', 'Tablet', 15000, 10, '2017-04-10 17:13:17', 'Adwita Sari, S.Si., APT'),
('TAB015', 'Alupurinol', 'Tablet', 10000, 10, '2017-04-10 17:13:38', 'Adwita Sari, S.Si., APT'),
('TAB016', 'Cotri Biasa', 'Tablet', 10000, 10, '2017-04-10 17:13:52', 'Adwita Sari, S.Si., APT'),
('TAB017', 'Cotri Forte', 'Tablet', 15000, 10, '2017-04-10 17:14:09', 'Adwita Sari, S.Si., APT'),
('TAB018', 'Dexamestasone 0,5 Mg', 'Tablet', 5000, 10, '2017-04-10 17:14:41', 'Adwita Sari, S.Si., APT'),
('TAB019', 'Dexamestasone 0,75 Mg', 'Tablet', 5000, 10, '2017-04-10 17:14:50', 'Adwita Sari, S.Si., APT'),
('TAB020', 'Dexteem Plus', 'Tablet', 15000, 10, '2017-04-10 17:15:04', 'Adwita Sari, S.Si., APT'),
('TAB021', 'Dextral F', 'Tablet', 15000, 10, '2017-04-10 17:15:32', 'Adwita Sari, S.Si., APT'),
('TAB022', 'Diafrom', 'Tablet', 10000, 10, '2017-04-10 17:40:43', 'Adwita Sari, S.Si., APT'),
('TAB023', 'Domperidon', 'Tablet', 10000, 10, '2017-04-10 17:41:04', 'Adwita Sari, S.Si., APT'),
('TAB024', 'Farmoten 2,5 Mg', 'Tablet', 10000, 10, '2017-04-10 17:42:51', 'Adwita Sari, S.Si., APT'),
('TAB025', 'Farmoten 5 Mg', 'Tablet', 10000, 10, '2017-04-10 17:43:06', 'Adwita Sari, S.Si., APT'),
('TAB026', 'Parsipen', 'Tablet', 15000, 10, '2017-04-10 17:47:34', 'Adwita Sari, S.Si., APT'),
('TAB027', 'FG-Troches', 'Tablet', 15000, 10, '2017-04-10 17:48:33', 'Adwita Sari, S.Si., APT'),
('TAB028', 'Flucadex', 'Tablet', 15000, 10, '2017-04-10 17:48:48', 'Adwita Sari, S.Si., APT'),
('TAB029', 'Flutamol', 'Tablet', 10000, 10, '2017-04-10 17:49:19', 'Adwita Sari, S.Si., APT'),
('TAB030', 'Glibenclamin', 'Tablet', 10000, 10, '2017-04-10 17:49:46', 'Adwita Sari, S.Si., APT'),
('TAB031', 'Griseufulvin', 'Tablet', 10000, 10, '2017-04-10 17:50:33', 'Adwita Sari, S.Si., APT'),
('TAB032', 'Histigo', 'Tablet', 10000, 10, '2017-04-10 17:50:48', 'Adwita Sari, S.Si., APT'),
('TAB033', 'Intunal', 'Tablet', 15000, 10, '2017-04-10 17:51:01', 'Adwita Sari, S.Si., APT'),
('TAB034', 'Curcumex', 'Tablet', 15000, 10, '2017-04-10 17:52:06', 'Adwita Sari, S.Si., APT'),
('TAB035', 'Lambusid', 'Tablet', 15000, 10, '2017-04-10 17:52:29', 'Adwita Sari, S.Si., APT'),
('TAB036', 'Lanamol', 'Tablet', 10000, 10, '2017-04-10 17:52:41', 'Adwita Sari, S.Si., APT'),
('TAB037', 'Lexapram', 'Tablet', 10000, 10, '2017-04-10 17:52:50', 'Adwita Sari, S.Si., APT'),
('TAB038', 'Bion-3', 'Tablet', 25000, 9, '2017-04-10 17:53:01', 'Adwita Sari, S.Si., APT'),
('TAB039', 'Neuralgin', 'Tablet', 20000, 7, '2017-04-10 17:53:13', 'Adwita Sari, S.Si., APT'),
('TAB040', 'Alinamin', 'Tablet', 20000, 10, '2017-04-10 17:53:30', 'Adwita Sari, S.Si., APT'),
('TAB041', 'Paratusin', 'Tablet', 15000, 10, '2017-04-10 18:01:51', 'Adwita Sari, S.Si., APT'),
('TAB042', 'Limbrozin', 'Tablet', 15000, 10, '2017-04-10 18:02:06', 'Adwita Sari, S.Si., APT'),
('TAB043', 'Loperamid 2 Mg', 'Tablet', 10000, 10, '2017-04-10 18:02:43', 'Adwita Sari, S.Si., APT'),
('TAB044', 'Mediamer', 'Tablet', 35000, 10, '2017-04-10 18:02:57', 'Adwita Sari, S.Si., APT'),
('TAB045', 'Metilergo', 'Tablet', 15000, 10, '2017-05-02 08:53:11', 'Adwita Sari, S.Si., APT'),
('TAB046', 'Methiyl Prednisolone', 'Tablet', 10000, 10, '2017-05-02 08:53:57', 'Adwita Sari, S.Si., APT'),
('TAB047', 'Metronidazole', 'Tablet', 10000, 10, '2017-05-02 08:54:13', 'Adwita Sari, S.Si., APT'),
('TAB048', 'Myloxan', 'Tablet', 15000, 10, '2017-05-02 08:54:24', 'Adwita Sari, S.Si., APT'),
('TAB049', 'Molagit', 'Tablet', 10000, 10, '2017-05-02 08:54:33', 'Adwita Sari, S.Si., APT'),
('TAB050', 'Neurodex', 'Tablet', 10000, 10, '2017-05-02 08:54:43', 'Adwita Sari, S.Si., APT'),
('TAB051', 'Neuromex', 'Tablet', 10000, 10, '2017-05-02 08:55:14', 'Adwita Sari, S.Si., APT'),
('TAB052', 'Nipedipin', 'Tablet', 10000, 10, '2017-05-02 08:55:20', 'Adwita Sari, S.Si., APT'),
('TAB053', 'Omeprazole', 'Tablet', 12000, 9, '2017-05-02 08:55:36', 'Adwita Sari, S.Si., APT'),
('TAB054', 'Piroxicam', 'Tablet', 10000, 10, '2017-05-02 08:55:46', 'Adwita Sari, S.Si., APT'),
('TAB055', 'Erithromycin', 'Tablet', 10000, 10, '2017-05-02 08:56:16', 'Adwita Sari, S.Si., APT'),
('TAB056', 'Doxycycline', 'Tablet', 10000, 10, '2017-05-02 08:56:49', 'Adwita Sari, S.Si., APT'),
('TAB057', 'Ranitidine', 'Tablet', 10000, 10, '2017-05-02 08:56:59', 'Adwita Sari, S.Si., APT'),
('TAB058', 'Tetracicline', 'Tablet', 10000, 10, '2017-05-02 08:57:10', 'Adwita Sari, S.Si., APT'),
('TAB059', 'Loratadine', 'Tablet', 10000, 10, '2017-05-02 08:57:19', 'Adwita Sari, S.Si., APT'),
('TAB060', 'Pronicy', 'Tablet', 15000, 10, '2017-05-02 08:57:34', 'Adwita Sari, S.Si., APT'),
('TAB061', 'Salbutamol 2 Mg', 'Tablet', 5000, 10, '2017-05-02 08:57:51', 'Adwita Sari, S.Si., APT'),
('TAB062', 'Salbutamol 4 Mg', 'Tablet', 5000, 10, '2017-05-02 09:02:08', 'Adwita Sari, S.Si., APT'),
('TAB063', 'Simvastatin 10 Mg', 'Tablet', 10000, 9, '2017-05-02 09:02:26', 'Adwita Sari, S.Si., APT'),
('TAB064', 'Spasminal', 'Tablet', 15000, 10, '2017-05-02 09:02:47', 'Adwita Sari, S.Si., APT'),
('TAB065', 'Thiamphenicol 500 Mg', 'Tablet', 15000, 10, '2017-05-02 09:03:09', 'Adwita Sari, S.Si., APT'),
('TAB066', 'Vitazym', 'Tablet', 15000, 10, '2017-05-02 09:03:19', 'Adwita Sari, S.Si., APT'),
('TAB067', 'Voltadex', 'Tablet', 10000, 10, '2017-05-02 09:03:40', 'Adwita Sari, S.Si., APT'),
('TAB068', 'Zink 20 Mg', 'Tablet', 12000, 10, '2017-05-02 09:03:58', 'Adwita Sari, S.Si., APT'),
('TAB069', 'Nystatin Supp', 'Tablet', 15000, 10, '2017-05-02 09:04:10', 'Adwita Sari, S.Si., APT'),
('TAB070', 'Dulcolax Tablet', 'Tablet', 6000, 10, '2017-05-02 09:04:42', 'Adwita Sari, S.Si., APT'),
('TAB071', 'Buscopan Tablet', 'Tablet', 35000, 10, '2017-05-02 09:04:57', 'Adwita Sari, S.Si., APT'),
('TAB072', 'Bio ATP', 'Tablet', 35000, 10, '2017-05-02 09:07:48', 'Adwita Sari, S.Si., APT'),
('TAB073', 'Propertil', 'Tablet', 150000, 10, '2017-05-02 09:08:02', 'Adwita Sari, S.Si., APT'),
('TAB074', 'Premaston', 'Tablet', 35000, 10, '2017-05-02 09:08:16', 'Adwita Sari, S.Si., APT'),
('TAB075', 'VIT E', 'Tablet', 20000, 10, '2017-05-02 09:08:27', 'Adwita Sari, S.Si., APT'),
('TAB076', 'Dufadilan', 'Tablet', 70000, 10, '2017-05-02 09:08:37', 'Adwita Sari, S.Si., APT'),
('TAB077', 'Katusi', 'Tablet', 45000, 10, '2017-05-02 09:08:46', 'Adwita Sari, S.Si., APT'),
('TAB078', 'Oralit', 'Tablet', 1000, 10, '2017-05-02 09:08:56', 'Adwita Sari, S.Si., APT'),
('TAB080', 'Transamin Acit', 'Tablet', 45000, 45, '2017-05-02 09:09:07', 'Adwita Sari, S.Si., APT'),
('TAB082', 'Yusimox', 'Tablet', 10000, 16, '2017-04-04 21:19:37', 'Adwita Sari, S.Si., APT'),
('VIT001', 'Adfer', 'Vitamin', 15000, 6, '2017-05-02 09:09:26', 'Adwita Sari, S.Si., APT'),
('VIT002', 'Calcivar', 'Vitamin', 10000, 9, '2017-05-02 09:09:39', 'Adwita Sari, S.Si., APT'),
('VIT003', 'Ferobion', 'Vitamin', 15000, 7, '2017-05-02 09:09:49', 'Adwita Sari, S.Si., APT'),
('VIT004', 'Feromex', 'Vitamin', 10000, 9, '2017-05-02 09:09:58', 'Adwita Sari, S.Si., APT'),
('VIT005', 'Folarin', 'Vitamin', 7000, 8, '2017-05-02 09:29:25', 'Adwita Sari, S.Si., APT'),
('VIT007', 'Novabion', 'Vitamin', 10000, 3, '2017-05-02 09:30:18', 'Adwita Sari, S.Si., APT'),
('VIT008', 'Prenatal', 'Vitamin', 10000, 6, '2017-05-02 09:30:31', 'Adwita Sari, S.Si., APT'),
('VIT009', 'Rovital', 'Vitamin', 10000, 9, '2017-05-02 09:30:37', 'Adwita Sari, S.Si., APT'),
('VIT012', 'Sangobion', 'Vitamin', 8000, 21, '2017-07-06 10:21:31', 'Adwita Sari, S.Si., APT'),
('VIT013', 'Fondazen', 'Vitamin', 10000, 8, '2017-05-02 09:29:35', 'Adwita Sari, S.Si., APT'),
('VIT014', 'Enervon-C', 'Vitamin', 25000, 44, '2017-08-09 01:45:33', 'Adwita Sari, S.Si., APT');

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE IF NOT EXISTS `pasien` (
  `no_registrasi` char(15) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `tempat_lahir` varchar(15) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jk_pasien` enum('L','P') NOT NULL,
  `gol_darah` enum('A','B','O','AB','Other') NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `orang_terdekat` varchar(200) NOT NULL,
  `hubungan` varchar(200) NOT NULL,
  `no_bpjs` char(13) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`no_registrasi`, `nama_pasien`, `tempat_lahir`, `tanggal_lahir`, `jk_pasien`, `gol_darah`, `alamat`, `no_telp`, `orang_terdekat`, `hubungan`, `no_bpjs`, `tanggal_entri`, `nama_pengentri`) VALUES
('040199140517001', 'Adnan Firdaus', 'Wetan', '1999-01-04', 'L', 'B', 'jl sukarejo', '088999112223', 'Maisaroh', 'Ibu', '0003312121244', '2017-05-14 14:37:39', 'Inne Aprianna Kabeakan'),
('131192060617001', 'Rahmat Solihun', 'Bekasi', '1992-11-13', 'L', 'A', 'Jl. Pacitan III', '087754468911', 'Samiun', 'Istri', '0001235587874', '2017-06-06 14:41:51', 'Inne Aprianna Kabeakan'),
('210795140517001', 'Fahri Ansyah', 'Bantul', '1995-07-21', 'L', 'Other', 'jl wetan', '082213122212', 'Maisah', 'Ibu', '', '2017-05-14 14:35:15', 'Inne Aprianna Kabeakan'),
('P00411707030001', 'Melani', 'Bandung', '1997-07-02', 'P', 'AB', 'Jl. Sawangan', '088777266612', 'Fauzi', 'Ayah', '0088271721729', '2017-07-03 00:41:38', 'Inne Aprianna Kabeakan'),
('P01391707030002', 'Siti Rokayah', 'Sragen', '1984-02-28', 'P', 'Other', 'Jl. Kalimalang X', '089987721212', 'Waluyo', 'Suami', '0877212121213', '2017-07-03 01:39:20', 'Inne Aprianna Kabeakan'),
('P10161707060001', 'Bramantyo', 'Jakarta', '1988-08-24', 'L', 'A', 'Jl. Inspeksi Saluran Kalimalang', '082311128777', 'Siska', 'Istri', '0882776615552', '2017-07-06 10:16:20', 'Inne Aprianna Kabeakan'),
('P12451708080001', 'Pramudya Airlangga', 'Jakarta', '1995-08-01', 'L', 'A', 'jl. Sawo Kecik', '087762511988', 'Lili', 'Adik', '6612514421899', '2017-08-09 01:52:44', 'Inne Aprianna Kabeakan'),
('P13191707060002', 'Ahmad', 'Jakarta', '1990-07-12', 'L', 'O', 'Jl. ABCD', '082211133333', 'Cika', 'Istri', '0887721111111', '2017-07-06 13:19:38', 'Inne Aprianna Kabeakan');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap`
--

CREATE TABLE IF NOT EXISTS `rawat_inap` (
  `kode_rawat_inap` char(6) NOT NULL,
  `nomor_kamar` varchar(3) NOT NULL,
  `no_registrasi` char(10) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `kode_dokter` char(6) NOT NULL,
  `nama_depan_dokter` varchar(15) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `tanggal_keluar` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_inap`
--

INSERT INTO `rawat_inap` (`kode_rawat_inap`, `nomor_kamar`, `no_registrasi`, `nama_pasien`, `kode_dokter`, `nama_depan_dokter`, `tanggal_masuk`, `tanggal_keluar`) VALUES
('130116', '204', '2013100677', 'Danny', 'D00001', 'ahmad', '2016-01-13', '2016-01-15'),
('130216', '201', '2013100001', 'Debby', 'D00001', 'Ahmad', '2016-01-14', '2016-01-16'),
('150116', '301', '2016011501', 'Abi', 'D00002', 'Abraham', '2016-01-08', '2016-01-10');

-- --------------------------------------------------------

--
-- Table structure for table `rekam_medis`
--

CREATE TABLE IF NOT EXISTS `rekam_medis` (
  `nomor_rekam_medis` char(14) NOT NULL,
  `tgl_rekam_medis` date NOT NULL,
  `no_registrasi` char(15) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `kode_resep` char(12) NOT NULL,
  `keluhan` text NOT NULL,
  `total_harga_tindakan` int(11) NOT NULL,
  `bagian` varchar(15) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_dokter` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rekam_medis`
--

INSERT INTO `rekam_medis` (`nomor_rekam_medis`, `tgl_rekam_medis`, `no_registrasi`, `nama_pasien`, `kode_resep`, `keluhan`, `total_harga_tindakan`, `bagian`, `tanggal_entri`, `nama_dokter`) VALUES
('RM080820170001', '2017-08-09', 'P00411707030001', 'Melani', 'RSP170808001', 'perksa kandungan 8 bulan', 35000, 'Bidan', '2017-08-09 00:01:29', 'Umi Rahmawati, Am.Keb'),
('RM090820170002', '2017-08-09', '040199140517001', 'Adnan Firdaus', 'RSP170809002', 'Demam, sakit kepala', 75000, 'Umum', '2017-08-09 00:18:30', 'dr. Heri Muhardi'),
('RM090820170003', '2017-08-09', 'P10161707060001', 'Bramantyo', 'RSP170809003', 'periksa kandungan istri', 45000, 'Bidan', '2017-08-09 00:37:19', 'Umi Rahmawati, Am.Keb'),
('RM090820170004', '2017-08-09', '210795140517001', 'Fahri Ansyah', 'RSP170809004', 'sakit gigi, ganti gusi', 900000, 'Gigi', '2017-08-09 09:18:40', 'drg. Afrida Aryani'),
('RM090820170005', '2017-08-09', 'P12451708080001', 'Pramudya Airlangga', 'RSP170809005', 'ganti gigi acrilic', 200000, 'Gigi', '2017-08-09 09:54:47', 'drg. Afrida Aryani'),
('RM090820170006', '2017-08-09', '040199140517001', 'Adnan Firdaus', 'RSP170809006', 'ganti ke acrilic', 400000, 'Gigi', '2017-08-09 10:06:58', 'drg. Afrida Aryani'),
('RM090820170007', '2017-08-09', '131192060617001', 'Rahmat Solihun', 'RSP170809007', 'ganti gigi', 950000, 'Gigi', '2017-08-09 10:15:59', 'drg. Afrida Aryani'),
('RM090820170008', '2017-08-09', 'P00411707030001', 'Melani', 'RSP170809008', 'gigi palsu', 1350000, 'Gigi', '2017-08-09 10:21:27', 'drg. Afrida Aryani'),
('RM090820170009', '2017-08-09', 'P01391707030002', 'Siti Rokayah', 'RSP170809009', '', 1550000, 'Gigi', '2017-08-09 10:25:36', 'drg. Afrida Aryani');

-- --------------------------------------------------------

--
-- Table structure for table `tindakan`
--

CREATE TABLE IF NOT EXISTS `tindakan` (
  `kode_tindakan` char(6) NOT NULL,
  `nama_tindakan` varchar(200) NOT NULL,
  `jenis_tindakan` enum('imunisasi bayi','injection','kb','jasa dan lain lain') NOT NULL,
  `harga` int(11) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tindakan`
--

INSERT INTO `tindakan` (`kode_tindakan`, `nama_tindakan`, `jenis_tindakan`, `harga`, `tanggal_entri`, `nama_pengentri`) VALUES
('IMN001', 'Imunisasi Hepatitis', 'imunisasi bayi', 30000, '0000-00-00 00:00:00', ''),
('IMN002', 'Imunisasi BCG / Polio', 'imunisasi bayi', 35000, '0000-00-00 00:00:00', ''),
('IMN003', 'Imunisasi DPT combo / Polio', 'imunisasi bayi', 40000, '0000-00-00 00:00:00', ''),
('IMN004', 'Imunisasi Campak / Polio', 'imunisasi bayi', 35000, '0000-00-00 00:00:00', ''),
('IMN005', 'Imunisasi Campak', 'imunisasi bayi', 30000, '0000-00-00 00:00:00', ''),
('IMN006', 'Imunisasi DPT Infantrik / Polio', 'imunisasi bayi', 285000, '0000-00-00 00:00:00', ''),
('IMN007', 'Imunisasi HIB', 'imunisasi bayi', 230000, '0000-00-00 00:00:00', ''),
('IMN008', 'Imunisasi MMR', 'imunisasi bayi', 130000, '0000-00-00 00:00:00', ''),
('IMN009', 'Imunisasi DPT, HIB, Polio (PEDIACEL)', 'imunisasi bayi', 450000, '0000-00-00 00:00:00', ''),
('IMN010', 'Tindik + Sunat', 'imunisasi bayi', 40000, '0000-00-00 00:00:00', ''),
('INJ001', 'Injection Aminopilin', 'injection', 30000, '0000-00-00 00:00:00', ''),
('INJ002', 'Injection Cefotaxim', 'injection', 25000, '0000-00-00 00:00:00', ''),
('INJ003', 'Injection Chloramex', 'injection', 25000, '0000-00-00 00:00:00', ''),
('INJ004', 'Injection Dexa', 'injection', 20000, '0000-00-00 00:00:00', ''),
('INJ005', 'Injection Dexa 3 amp', 'injection', 30000, '0000-00-00 00:00:00', ''),
('INJ006', 'Injection Duvadilan', 'injection', 35000, '0000-00-00 00:00:00', ''),
('INJ007', 'Injection Dondril', 'injection', 15000, '0000-00-00 00:00:00', ''),
('INJ008', 'Injection Epidosin', 'injection', 25000, '0000-00-00 00:00:00', ''),
('INJ009', 'Injection Neurobion', 'injection', 25000, '0000-00-00 00:00:00', ''),
('INJ010', 'Injection Papaverin', 'injection', 15000, '0000-00-00 00:00:00', ''),
('INJ011', 'Injection Primperan', 'injection', 20000, '0000-00-00 00:00:00', ''),
('INJ012', 'Injection Ranitidin', 'injection', 25000, '0000-00-00 00:00:00', ''),
('INJ013', 'Injection Transamin', 'injection', 35000, '0000-00-00 00:00:00', ''),
('INJ014', 'Injection TT', 'injection', 20000, '0000-00-00 00:00:00', ''),
('JLA001', 'Pemeriksaan Bidan', 'jasa dan lain lain', 15000, '0000-00-00 00:00:00', ''),
('JLA002', 'Pemeriksaan Dokter Umum', 'jasa dan lain lain', 20000, '0000-00-00 00:00:00', ''),
('JLA003', 'Pemeriksaan Dokter Spesialis Kandungan', 'jasa dan lain lain', 90000, '0000-00-00 00:00:00', ''),
('JLA004', 'Tarif Curretase', 'jasa dan lain lain', 1700000, '0000-00-00 00:00:00', ''),
('JLA005', 'Tarif Vacum Ekstrasi (Bidan)', 'jasa dan lain lain', 1000000, '0000-00-00 00:00:00', ''),
('JLA006', 'Tarif Vacum Ekstrasi (Dokter Spesialis Kandungan)', 'jasa dan lain lain', 1500000, '0000-00-00 00:00:00', ''),
('JLA007', 'Partus Sungsang', 'jasa dan lain lain', 500000, '0000-00-00 00:00:00', ''),
('JLA008', 'Pasang Balon', 'jasa dan lain lain', 200000, '0000-00-00 00:00:00', ''),
('JLA009', 'Sunat Laser', 'jasa dan lain lain', 450000, '0000-00-00 00:00:00', ''),
('JLA010', 'Nebulizer Anak', 'jasa dan lain lain', 40000, '0000-00-00 00:00:00', ''),
('JLA011', 'Nebulizer Dewasa', 'jasa dan lain lain', 60000, '0000-00-00 00:00:00', ''),
('KB0001', 'Suntik Cyclo', 'kb', 20000, '0000-00-00 00:00:00', ''),
('KB0002', 'Suntik Depo Geston', 'kb', 18000, '0000-00-00 00:00:00', ''),
('KB0003', 'Suntik Depo Neo', 'kb', 25000, '0000-00-00 00:00:00', ''),
('KB0004', 'Pasang IUD Copper T', 'kb', 150000, '0000-00-00 00:00:00', ''),
('KB0005', 'Pasang IUD Copper Nova T', 'kb', 400000, '0000-00-00 00:00:00', ''),
('KB0006', 'Aff IUD', 'kb', 40000, '0000-00-00 00:00:00', ''),
('KB0007', 'Pasang Implan', 'kb', 100000, '0000-00-00 00:00:00', ''),
('KB0008', 'Aff + Pasang Implan', 'kb', 175000, '0000-00-00 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `tindakan_gigi`
--

CREATE TABLE IF NOT EXISTS `tindakan_gigi` (
  `kode_tindakan` char(6) NOT NULL,
  `nama_tindakan` varchar(200) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tindakan_gigi`
--

INSERT INTO `tindakan_gigi` (`kode_tindakan`, `nama_tindakan`, `harga`) VALUES
('GIG001', 'Konsultasi', 50000),
('GIG002', 'Pencabutan Gigi (Anak) Dengan Topical Anastesi Gel / Chlor Etyl', 60000),
('GIG003', 'Pencabutan Gigi (Anak) Dengan Suntikan', 80000),
('GIG004', 'Pencabutan Gigi (Dewasa) Biasa', 150000),
('GIG005', 'Pencabutan Gigi (Dewasa) Dengan Komplikasi', 200000),
('GIG006', 'Tambal GIC Kecil', 100000),
('GIG007', 'Tambal GIC Besar', 150000),
('GIG008', 'Tambal Sementara Operculectomy', 100000),
('GIG009', 'Tambal Resin Komplit / LC kecil', 100000),
('GIG010', 'Tambal Resin Komplit / LC Sedang', 150000),
('GIG011', 'Tambal Resin Komplit / LC Besar', 175000),
('GIG012', 'Odentectomy (Impected)', 1000000),
('GIG013', 'Pembersihan Karang Gigi (Scalling) 1 Rahang', 100000),
('GIG014', 'Pembersihan Karang Gigi (Scalling) Rahang Atas dan Bawah', 200000),
('GIG015', 'Pemasangan Gigi Palsu Acrilic 1 Gigi Pertama', 200000),
('GIG016', 'Pemasangan Gigi Palsu Acrilic Gigi Berikutnya Per', 100000),
('GIG017', 'Pemasangan Gigi Palsu Flexi Denture Gigi Pertama Rahang', 500000),
('GIG018', 'Pemasangan Gigi Palsu Flexi Denture Gigi Berikutnya', 150000),
('GIG019', 'Pemasangan Gigi Palsu Full Denture Acrilic Rahang Atas dan Rahang Bawah', 2500000),
('GIG020', 'Pemasangan Gigi Palsu Reparasi GTS Basis', 300000),
('GIG021', 'Rebasing Full Denture Per Rahang', 300000),
('GIG022', 'Pin Core', 600000),
('GIG023', 'Incisi Abses, Kuretase', 250000),
('GIG024', 'Fixed Orthodontic Rahang Atas + Rahang Bawah', 5000000),
('GIG025', 'Aktifer Fixed Orthodontie Ganti Karet', 200000),
('GIG026', 'Aktifer Fixed Orthodontie Ganti Karet + Kawat', 300000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `id_transaksi` char(17) NOT NULL,
  `nomor_rekam_medis` char(14) NOT NULL,
  `no_registrasi` char(15) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `kode_resep` char(12) NOT NULL,
  `total_biaya` int(11) NOT NULL,
  `total_bayar` int(11) NOT NULL,
  `jenis_pembayaran` varchar(50) NOT NULL,
  `tanggal_transaksi` datetime NOT NULL,
  `nama_kasir` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `nomor_rekam_medis`, `no_registrasi`, `nama_pasien`, `kode_resep`, `total_biaya`, `total_bayar`, `jenis_pembayaran`, `tanggal_transaksi`, `nama_kasir`) VALUES
('TR-06/07/2017-001', 'RM060720170001', 'P01391707030002', 'Siti Rokayah', 'RSP170706001', 980000, 1000000, 'Tunai', '2017-07-06 08:49:07', 'Adwita Sari, S.Si., APT'),
('TR-06/07/2017-002', 'RM060720170002', '210795140517001', 'Fahri Ansyah', 'RSP1707062', 200000, 200000, 'Tunai', '2017-07-06 08:50:49', 'Adwita Sari, S.Si., APT'),
('TR-06/07/2017-003', 'RM060720170003', 'P10161707060002', 'Bramantyo', 'RSP1707063', 105000, 110000, 'Tunai', '2017-07-06 10:23:05', 'Adwita Sari, S.Si., APT'),
('TR-06/07/2017-004', 'RM060720170004', 'P13191707060003', 'Ahmad', 'RSP170706004', 30000, 50000, 'Tunai', '2017-07-06 13:21:49', 'Adwita Sari, S.Si., APT'),
('TR-08/08/2017-001', 'RM080820170001', 'P12451708080001', 'Pramudya Airlangga', 'RSP170808001', 170000, 175000, 'BPJS', '2017-08-08 13:07:37', 'Adwita Sari, S.Si., APT'),
('TR-08/08/2017-002', 'RM080820170002', 'P01391707030002', 'Siti Rokayah', 'RSP170808005', 270000, 300000, 'Tunai', '2017-08-08 14:49:43', 'Adwita Sari, S.Si., APT'),
('TR-08/08/2017-003', 'RM080820170002', 'P12451708080001', 'Pramudya Airlangga', 'RSP170808002', 103000, 105000, 'Tunai', '2017-08-08 23:54:02', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-001', 'RM080820170001', 'P00411707030001', 'Melani', 'RSP170808001', 70000, 100000, 'BPJS', '2017-08-09 00:06:30', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-002', 'RM090820170002', '040199140517001', 'Adnan Firdaus', 'RSP170809002', 101000, 110000, 'Tunai', '2017-08-09 00:19:10', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-003', 'RM090820170003', 'P10161707060001', 'Bramantyo', 'RSP170809003', 75000, 100000, 'Debit', '2017-08-09 00:37:46', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-004', 'RM090820170004', '210795140517001', 'Fahri Ansyah', 'RSP170809004', 910000, 1000000, 'Tunai', '2017-08-09 09:30:45', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-005', 'RM090820170005', 'P12451708080001', 'Pramudya Airlangga', 'RSP170809005', 220000, 250000, 'Tunai', '2017-08-09 10:07:15', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-006', 'RM090820170006', '040199140517001', 'Adnan Firdaus', 'RSP170809006', 410000, 420000, 'Tunai', '2017-08-09 10:07:28', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-007', 'RM090820170007', '131192060617001', 'Rahmat Solihun', 'RSP170809007', 975000, 1000000, 'Debit', '2017-08-09 10:16:57', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-008', 'RM090820170008', 'P00411707030001', 'Melani', 'RSP170809008', 1375000, 1400000, 'Tunai', '2017-08-09 10:24:06', 'Adwita Sari, S.Si., APT'),
('TR-09/08/2017-009', 'RM090820170009', 'P01391707030002', 'Siti Rokayah', 'RSP170809009', 1610000, 1620000, 'BPJS', '2017-08-09 10:26:19', 'Adwita Sari, S.Si., APT');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `bidan`
--
ALTER TABLE `bidan`
  ADD PRIMARY KEY (`kode_bidan`);

--
-- Indexes for table `detail_resep`
--
ALTER TABLE `detail_resep`
  ADD PRIMARY KEY (`kode_detail`);

--
-- Indexes for table `detail_tindakan`
--
ALTER TABLE `detail_tindakan`
  ADD PRIMARY KEY (`kode_detail`);

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`kode_dokter`);

--
-- Indexes for table `header_resep`
--
ALTER TABLE `header_resep`
  ADD PRIMARY KEY (`kode_resep`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`kode_karyawan`);

--
-- Indexes for table `laboratorium`
--
ALTER TABLE `laboratorium`
  ADD PRIMARY KEY (`kode_tindakanlab`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`kode_obat`);

--
-- Indexes for table `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`no_registrasi`);

--
-- Indexes for table `rawat_inap`
--
ALTER TABLE `rawat_inap`
  ADD PRIMARY KEY (`kode_rawat_inap`);

--
-- Indexes for table `rekam_medis`
--
ALTER TABLE `rekam_medis`
  ADD PRIMARY KEY (`nomor_rekam_medis`);

--
-- Indexes for table `tindakan`
--
ALTER TABLE `tindakan`
  ADD PRIMARY KEY (`kode_tindakan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_resep`
--
ALTER TABLE `detail_resep`
  MODIFY `kode_detail` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=77;
--
-- AUTO_INCREMENT for table `detail_tindakan`
--
ALTER TABLE `detail_tindakan`
  MODIFY `kode_detail` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=98;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
