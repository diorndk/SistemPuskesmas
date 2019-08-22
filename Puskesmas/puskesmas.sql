-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2017 at 03:21 AM
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
  `no_registrasi` char(10) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `bagian` enum('Umum','Gigi') NOT NULL,
  `tanggal` int(11) NOT NULL,
  `flag_active` char(1) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `antrian`
--

INSERT INTO `antrian` (`no_antrian`, `no_registrasi`, `nama_pasien`, `bagian`, `tanggal`, `flag_active`, `tanggal_entri`, `nama_pengentri`) VALUES
(1, '2016080001', 'Matthew Hutama Wong', 'Umum', 25, 'N', '2016-10-25 15:38:01', 'Inne Aprianna Kabeakan'),
(2, '2016080004', 'Faris Hakim', 'Umum', 25, 'N', '2016-10-25 15:38:10', 'Inne Aprianna Kabeakan'),
(3, '2016090001', 'Nurul Azis', 'Umum', 25, 'N', '2016-10-25 15:38:22', 'Inne Aprianna Kabeakan'),
(4, '2016102501', 'Akrom', 'Umum', 25, 'n', '2016-10-25 15:38:38', 'Inne Aprianna Kabeakan'),
(5, '2016090002', 'Gibran Senja', 'Umum', 25, 'N', '2016-10-25 15:38:45', 'Inne Aprianna Kabeakan'),
(6, '2016080001', 'Matthew Hutama Wong', 'Umum', 25, 'N', '2016-10-25 15:38:48', 'Inne Aprianna Kabeakan'),
(1, '2013100001', 'Debby', 'Umum', 26, 'N', '2016-10-26 09:10:46', 'Inne Aprianna Kabeakan'),
(2, '2016090002', 'Gibran Senja', 'Umum', 26, 'N', '2016-10-26 09:14:43', 'Inne Aprianna Kabeakan'),
(3, '2016102501', 'Akrom', 'Umum', 26, 'n', '2016-10-26 09:16:30', 'Inne Aprianna Kabeakan'),
(4, '2016080001', 'Matthew Hutama Wong', 'Umum', 26, 'N', '2016-10-26 09:17:01', 'Inne Aprianna Kabeakan'),
(5, '2016080001', 'Matthew Hutama Wong', 'Umum', 26, 'N', '2016-10-26 09:17:04', 'Inne Aprianna Kabeakan'),
(6, '2016080004', 'Faris Hakim', 'Umum', 26, 'N', '2016-10-26 09:17:06', 'Inne Aprianna Kabeakan'),
(7, '2016080002', 'Habib Wong', 'Umum', 26, 'N', '2016-10-26 09:17:55', 'Inne Aprianna Kabeakan'),
(8, '2016090001', 'Nurul Azis', 'Umum', 26, 'N', '2016-10-26 09:18:02', 'Inne Aprianna Kabeakan'),
(9, '2016090002', 'Gibran Senja', 'Umum', 26, 'N', '2016-10-26 09:18:05', 'Inne Aprianna Kabeakan'),
(10, '2016102501', 'Akrom', 'Umum', 26, 'n', '2016-10-26 09:18:08', 'Inne Aprianna Kabeakan'),
(1, '2017030911', 'Dio Riandika', 'Umum', 9, 'N', '2017-03-09 16:11:01', 'Inne Aprianna Kabeakan'),
(1, '2016080001', 'Matthew Hutama Wong', 'Umum', 29, 'N', '2017-03-29 18:07:37', 'Inne Aprianna Kabeakan'),
(2, '2017032118', 'Andrew Hutama Wong', 'Umum', 29, 'N', '2017-03-29 18:16:30', 'Inne Aprianna Kabeakan'),
(1, '2016080001', 'Matthew Hutama Wong', 'Umum', 31, 'Y', '2017-03-31 15:17:37', 'Inne Aprianna Kabeakan'),
(1, '2017032118', 'Andrew Hutama Wong', 'Umum', 2, 'Y', '2017-04-02 22:47:29', 'Inne Aprianna Kabeakan');

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
  `kode_resep` char(8) NOT NULL,
  `kode_obat` char(6) NOT NULL,
  `nama_obat` varchar(200) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga_satuan` int(11) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_resep`
--

INSERT INTO `detail_resep` (`kode_detail`, `kode_resep`, `kode_obat`, `nama_obat`, `jumlah`, `harga_satuan`, `tanggal_entri`, `nama_pengentri`) VALUES
(1, '20160906', '400100', 'Betadine', 5, 5000, '2016-09-06 17:52:40', 'dr. Heri Muhardi'),
(2, '20160907', 'O10103', 'Dextro', 5, 5000, '2016-09-07 10:44:45', 'dr. Heri Muhardi'),
(3, '20160907', 'O10102', 'Paramex', 5, 3000, '2016-09-07 10:53:48', 'dr. Heri Muhardi'),
(4, '20160970', '400100', 'Betadine', 1, 5000, '2016-09-07 11:15:13', 'dr. Juwitasari Setyaningtyas'),
(5, '20160970', 'O10102', 'Paramex', 2, 3000, '2016-09-07 11:15:33', 'dr. Juwitasari Setyaningtyas'),
(6, '20160970', 'O10103', 'Dextro', 3, 5000, '2016-09-07 11:16:58', 'dr. Juwitasari Setyaningtyas'),
(7, '20161003', '400100', 'Betadine', 25, 5000, '2016-10-27 17:58:53', 'dr. Heri Muhardi'),
(8, '2016970', 'O10102', 'Paramex', 10, 3000, '2016-10-27 18:01:30', 'dr. Heri Muhardi'),
(9, '2011111', 'O10102', 'Paramex', 50, 3000, '2016-10-27 18:07:28', 'dr. Heri Muhardi'),
(10, '24544545', 'o10102', 'Paramex', 22, 3000, '2016-10-27 18:11:31', 'dr. Heri Muhardi'),
(11, '12121212', 'O10102', 'Paramex', 30, 3000, '2016-10-27 18:14:45', 'dr. Heri Muhardi'),
(12, '546454', 'o10102', 'Paramex', 50, 3000, '2016-10-27 18:18:26', 'dr. Heri Muhardi'),
(13, '123546', 'o10102', 'Paramex', 50, 3000, '2016-10-27 18:20:31', 'dr. Heri Muhardi'),
(14, '456874', 'o10102', 'Paramex', 30, 3000, '2016-10-27 18:26:25', 'dr. Heri Muhardi'),
(15, '21354654', 'o10102', 'Paramex', 5, 3000, '2016-10-27 18:27:04', 'dr. Heri Muhardi'),
(16, '25646489', '400101', 'Panadol Biru', 6, 6000, '2016-10-28 08:18:19', 'dr. Heri Muhardi'),
(17, '25646456', '400100', 'Betadine', 25, 5000, '2016-10-28 08:20:27', 'dr. Heri Muhardi'),
(18, '26464848', '400101', 'Panadol Biru', 15, 6000, '2016-10-28 09:00:07', 'dr. Heri Muhardi'),
(19, '40010100', '400101', 'Panadol Biru', 6, 6000, '2016-10-28 09:00:43', 'dr. Heri Muhardi'),
(20, '23161894', 'O10103', 'Dextro', 10, 5000, '2016-10-28 09:03:56', 'dr. Heri Muhardi'),
(21, '25848484', 'O10102', 'Paramex', 13, 3000, '2016-10-28 09:04:30', 'dr. Heri Muhardi'),
(22, '25564984', '400100', 'Betadine', 19, 5000, '2016-10-28 11:29:47', 'dr. Heri Muhardi'),
(23, '25564984', '400101', 'Panadol Biru', 4, 6000, '2016-10-28 11:31:03', 'dr. Heri Muhardi'),
(24, '25564984', 'O10103', 'dextro', 1, 5000, '2016-10-28 11:33:46', 'dr. Heri Muhardi'),
(25, '23564484', '400101', 'Panadol Biru', 1, 6000, '2016-10-28 13:47:34', 'dr. Heri Muhardi'),
(26, '22110050', '400100', 'Betadine', 1, 5000, '2016-10-28 15:07:51', 'dr. Heri Muhardi'),
(27, '56448444', '400101', 'Panadol Biru', 2, 6000, '2016-11-08 15:46:36', 'dr. Heri Muhardi'),
(28, '17090300', '400101', 'Panadol Biru', 1, 6000, '2017-03-09 16:46:31', 'dr. Heri Muhardi');

-- --------------------------------------------------------

--
-- Table structure for table `detail_tindakan`
--

CREATE TABLE IF NOT EXISTS `detail_tindakan` (
  `kode_detail` int(11) NOT NULL,
  `nomor_rekam_medis` char(10) NOT NULL,
  `kode_tindakan` char(6) NOT NULL,
  `nama_tindakan` varchar(200) NOT NULL,
  `harga` int(11) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_pengentri` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `kode_resep` char(8) NOT NULL,
  `no_registrasi` char(10) NOT NULL,
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
('', '2016090001', 'Nurul Azis', 0, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:05:25'),
('12121212', '2016090001', 'Nurul Azis', 90000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:14:20'),
('123546', '2016090002', 'Gibran Senja', 150000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:20:11'),
('13132131', '2017032118', 'Andrew Hutama Wong', 0, '2017-04-02', NULL, 'dr. Heri Muhardi', '2017-04-02 22:47:47'),
('17090300', '2017030911', 'Dio Riandika', 5000, '2017-03-09', 'pakai 3x sejam', 'dr. Heri Muhardi', '2017-03-09 16:41:03'),
('2011111', '2016090001', 'Nurul Azis', 150000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:05:39'),
('20160906', '2016080001', 'Matthew Hutama Wong', 25000, '2016-09-06', NULL, 'dr. Heri Muhardi', '2016-09-06 17:52:17'),
('20160907', '2016090001', 'Nurul Azis', 40000, '2016-09-07', NULL, 'dr. Heri Muhardi', '2016-09-07 10:44:02'),
('20160970', '2016090002', 'Gibran Senja', 26000, '2016-09-07', NULL, 'dr. Juwitasari Setyaningtyas', '2016-09-07 11:14:33'),
('20161001', '2016090002', 'Gibran Senja', 0, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 17:47:00'),
('20161003', '2016090002', 'Gibran Senja', 125000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 17:58:33'),
('2016970', '2016090002', 'Gibran Senja', 30000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:01:03'),
('21354654', '2016090002', 'Gibran Senja', 15000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:26:49'),
('21555494', '2016090001', 'Nurul Azis', 0, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 15:00:01'),
('22110050', '2016090001', 'Nurul Azis', 5000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 15:07:32'),
('22354148', '2016090001', 'Nurul Azis', 0, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 14:54:47'),
('22355648', '2016090002', 'Gibran Senja', 0, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 14:47:37'),
('23161894', '2016080001', 'Matthew Hutama Wong', 50000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 09:03:26'),
('23346894', '2016090001', 'Nurul Azis', 0, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 13:52:39'),
('23564484', '2016090002', 'Gibran Senja', 6000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 13:46:09'),
('24544545', '2016090002', 'Gibran Senja', 66000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:11:18'),
('25564984', '2016090002', 'Gibran Senja', 124000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 11:25:45'),
('25646456', '2016090002', 'Gibran Senja', 125000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 08:19:51'),
('25646489', '2016080001', 'Matthew Hutama Wong', 36000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 08:17:44'),
('25848484', '2016080001', 'Matthew Hutama Wong', 39000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 09:04:12'),
('26464848', '2016090002', 'Gibran Senja', 90000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 08:57:28'),
('40010100', '2016090002', 'Gibran Senja', 36000, '2016-10-28', NULL, 'dr. Heri Muhardi', '2016-10-28 09:00:34'),
('45458484', '', '', 0, '2016-11-08', NULL, 'dr. Heri Muhardi', '2016-11-08 15:34:22'),
('456874', '2016090002', 'Gibran Senja', 90000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:25:59'),
('546454', '2016090001', 'Nurul Azis', 150000, '2016-10-27', NULL, 'dr. Heri Muhardi', '2016-10-27 18:18:07'),
('56448444', '', '', 12000, '2016-11-08', NULL, 'dr. Heri Muhardi', '2016-11-08 15:46:21');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE IF NOT EXISTS `karyawan` (
  `kode_karyawan` varchar(6) NOT NULL,
  `nama_karyawan` varchar(200) NOT NULL,
  `jk_karyawan` enum('L','P') NOT NULL,
  `jabatan` enum('Dokter Umum','Dokter Gigi','Bidan','Perawat','Analis','Apoteker','Tenaga Gizi','Administrasi') NOT NULL,
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
('BD0001', 'Umi Rahmawati, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 14:43:01', 'Admin'),
('BD0002', 'Wina Novita, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 14:43:24', 'Admin'),
('BD0003', 'Fairuza Wakhida, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 14:43:43', 'Admin'),
('BD0004', 'Yulia Syarif, AM.Keb', 'P', 'Bidan', '1', '2016-08-30 14:44:48', 'Admin'),
('BD0005', 'Novi Eka Andriyanti, AM.Keb', 'P', 'Bidan', '1', '2016-08-30 15:57:48', 'Admin'),
('BD0006', 'Novi Enitasari, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 15:58:22', 'Admin'),
('BD0007', 'Astriana, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 15:58:42', 'Admin'),
('BD0008', 'Mega Sintauli Simanjuntak, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 15:59:05', 'Admin'),
('BD0009', 'Wellita Apriamala E.C, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 15:59:38', 'Admin'),
('BD0010', 'Nuraini, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 16:02:27', 'Admin'),
('BD0011', 'Pramulia Yuni Syara, Am.Keb', 'P', 'Bidan', '1', '2016-08-30 16:04:19', 'Admin'),
('DG0001', 'drg. Afrida Aryani', 'P', 'Dokter Gigi', '1', '2016-08-30 14:42:20', 'Admin'),
('DU0001', 'dr. Heri Muhardi', 'L', 'Dokter Umum', '1', '2016-08-30 14:40:59', 'Admin'),
('DU0002', 'dr. Tubagus Aria Santika', 'L', 'Dokter Umum', '1', '2016-08-30 14:41:28', 'Admin'),
('DU0003', 'dr. Juwitasari Setyaningtyas', 'P', 'Dokter Umum', '1', '2016-08-30 14:41:43', 'Admin'),
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
('400100', 'Betadine', 'Tablet', 5000, 11, '0000-00-00 00:00:00', ''),
('400101', 'Panadol Biru', 'Tablet', 6000, 5, '0000-00-00 00:00:00', ''),
('O10102', 'Paramex', 'Tablet', 3000, 2, '0000-00-00 00:00:00', ''),
('O10103', 'Dextro', 'Tablet', 5000, 1, '0000-00-00 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE IF NOT EXISTS `pasien` (
  `no_registrasi` char(10) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `tempat_lahir` varchar(15) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jk_pasien` enum('L','P') NOT NULL,
  `gol_darah` enum('A','B','O','AB','Other') NOT NULL,
  `alamat` varchar(25) NOT NULL,
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
('2012100009', '', '', '1997-11-13', 'L', 'A', '', '', '', '', '', '0000-00-00 00:00:00', ''),
('2013100001', 'Debby', 'Jakarta', '1996-12-13', 'P', 'O', 'jl. kranji barat', '08776653801', '', '', '', '0000-00-00 00:00:00', ''),
('2013100523', '', '', '1994-11-04', 'L', 'A', '', '', '', '', '', '0000-00-00 00:00:00', ''),
('2013100525', 'Alfian', 'Jakarta', '1995-01-11', 'L', 'B', 'Jl tipar Cakung', '08989772615', '', '', '', '0000-00-00 00:00:00', ''),
('2013100677', 'Danny', 'Batam', '1980-01-26', 'L', 'O', 'jl. abdada', '201102109', '', '', '', '0000-00-00 00:00:00', ''),
('2016080001', 'Matthew Hutama Wong', 'Jakarta', '1996-08-02', 'L', 'O', 'jl. kelapa gading 2', '089641355854', '', '', '', '2016-08-10 11:28:35', 'Rista'),
('2016080002', 'Habib Wong', 'Baghdad', '1997-08-29', 'P', 'B', 'asasdasds', '089977846548', '', '', '', '2016-08-03 09:12:32', ''),
('2016080004', 'Faris Hakim', 'Serang', '1990-08-31', 'L', 'B', 'jl. ketupat', '089749684818', '', '', '', '2016-08-03 09:35:10', 'Mario Teguh'),
('2016090001', 'Nurul Azis', 'Gabon', '1998-09-12', 'P', 'A', 'jl. sana', '089463418568', '', '', '', '2016-09-07 10:42:11', 'Inne Aprianna Kabeakan'),
('2016090002', 'Gibran Senja', 'Bandung', '1997-09-12', 'L', 'AB', 'jl. delima 5', '081315886547', '', '', '', '2016-09-07 11:13:02', 'Ely Epy Juita Simbolon'),
('2016100002', 'Andi Zahir', 'Jakarta', '1996-10-17', 'L', 'Other', 'jl. taruna', '089745663122', 'Sumarni', 'Ibu', '0001348657901', '2016-10-26 10:08:55', 'Inne Aprianna Kabeakan'),
('2016100004', 'Hafis Mukidi', 'Jombang', '1996-10-19', 'L', 'Other', 'Jl. Kemayoran 1', '089946211588', 'Habib Wong', 'Sahabat', '0001413256467', '2016-10-26 10:51:52', 'Inne Aprianna Kabeakan'),
('2016100005', 'Haris Rafdi', 'Bekasi', '1995-10-13', 'L', 'O', 'jl. harapan baru', '081310055689', 'Mugi', 'Ayah', '0001213546569', '2016-10-26 14:32:40', 'Inne Aprianna Kabeakan'),
('2017030911', 'Dio Riandika', 'Jakarta', '1994-03-10', 'L', 'O', 'aaaaa', '089821821821', 'Budi', 'Ayah', '', '2017-03-09 16:11:10', 'Inne Aprianna Kabeakan'),
('2017032118', 'Andrew Hutama Wong', 'Jombang', '1945-03-10', 'P', 'O', 'aaaa', '121212121212', 'aaa', 'Ibu', '1212121212121', '2017-03-29 18:14:36', 'Inne Aprianna Kabeakan');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE IF NOT EXISTS `pembayaran` (
  `kode_transaksi` char(10) NOT NULL,
  `nomor_rekam_medis` char(10) NOT NULL,
  `no_registrasi` char(10) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `tindakan` enum('Rawat Inap','Rawat Jalan') NOT NULL,
  `kode_rawat_inap` char(6) NOT NULL,
  `kode_resep` char(8) NOT NULL,
  `total_harga_resep` int(10) NOT NULL,
  `jenis_bayar` enum('Asuransi','Debit','Tunai') NOT NULL,
  `total_tagihan` int(10) NOT NULL,
  `tgl_transaksi` datetime NOT NULL,
  `nama_petugas` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`kode_transaksi`, `nomor_rekam_medis`, `no_registrasi`, `nama_pasien`, `tindakan`, `kode_rawat_inap`, `kode_resep`, `total_harga_resep`, `jenis_bayar`, `total_tagihan`, `tgl_transaksi`, `nama_petugas`) VALUES
('1023155614', '1201160011', '2013100525', 'Alfian', 'Rawat Jalan', '', '12345678', 25000, 'Tunai', 125000, '2016-07-29 11:20:46', '');

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
  `nomor_rekam_medis` char(10) NOT NULL,
  `tgl_rekam_medis` date NOT NULL,
  `no_registrasi` char(10) NOT NULL,
  `nama_pasien` varchar(200) NOT NULL,
  `kode_resep` char(8) NOT NULL,
  `keluhan` varchar(30) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nama_dokter` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rekam_medis`
--

INSERT INTO `rekam_medis` (`nomor_rekam_medis`, `tgl_rekam_medis`, `no_registrasi`, `nama_pasien`, `kode_resep`, `keluhan`, `tanggal_entri`, `nama_dokter`) VALUES
('0509160001', '2016-09-05', '2016080001', 'Matthew Hutama Wong', '', 'Sakit aja', '2016-09-05 19:32:29', 'dr. Heri Muhardi'),
('0709160001', '2016-09-07', '2016090001', 'Nurul Azis', '20160907', 'sakit perut', '2016-09-07 10:56:29', 'dr. Heri Muhardi'),
('0709160002', '2016-09-07', '2016090002', 'Gibran Senja', '20160970', 'Sakit Kepala', '2016-09-07 11:17:56', 'dr. Juwitasari Setyaningtyas'),
('0903170001', '2017-03-09', '2017030911', 'Dio Riandika', '17090300', 'sakit perut', '2017-03-09 16:52:41', 'dr. Heri Muhardi');

-- --------------------------------------------------------

--
-- Table structure for table `tindakan`
--

CREATE TABLE IF NOT EXISTS `tindakan` (
  `kode_tindakan` char(6) NOT NULL,
  `nama_tindakan` varchar(200) NOT NULL,
  `jenis_tindakan` enum('imunisasi bayi','injection','kb','jasa dan lain lain') NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tindakan`
--

INSERT INTO `tindakan` (`kode_tindakan`, `nama_tindakan`, `jenis_tindakan`, `harga`) VALUES
('IMN001', 'Imunisasi Hepatitis', 'imunisasi bayi', 30000),
('IMN002', 'Imunisasi BCG / Polio', 'imunisasi bayi', 35000),
('IMN003', 'Imunisasi DPT combo / Polio', 'imunisasi bayi', 40000),
('IMN004', 'Imunisasi Campak / Polio', 'imunisasi bayi', 35000),
('IMN005', 'Imunisasi Campak', 'imunisasi bayi', 30000),
('IMN006', 'Imunisasi DPT Infantrik / Polio', 'imunisasi bayi', 285000),
('IMN007', 'Imunisasi HIB', 'imunisasi bayi', 230000),
('IMN008', 'Imunisasi MMR', 'imunisasi bayi', 130000),
('IMN009', 'Imunisasi DPT, HIB, Polio (PEDIACEL)', 'imunisasi bayi', 450000),
('IMN010', 'Tindik + Sunat', 'imunisasi bayi', 40000),
('INJ001', 'Injection Aminopilin', 'injection', 30000),
('INJ002', 'Injection Cefotaxim', 'injection', 25000),
('INJ003', 'Injection Chloramex', 'injection', 25000),
('INJ004', 'Injection Dexa', 'injection', 20000),
('INJ005', 'Injection Dexa 3 amp', 'injection', 30000),
('INJ006', 'Injection Duvadilan', 'injection', 35000),
('INJ007', 'Injection Dondril', 'injection', 15000),
('INJ008', 'Injection Epidosin', 'injection', 25000),
('INJ009', 'Injection Neurobion', 'injection', 25000),
('INJ010', 'Injection Papaverin', 'injection', 15000),
('INJ011', 'Injection Primperan', 'injection', 20000),
('INJ012', 'Injection Ranitidin', 'injection', 25000),
('INJ013', 'Injection Transamin', 'injection', 35000),
('INJ014', 'Injection TT', 'injection', 20000),
('JLA001', 'Tarif Pemeriksaan Bidan', 'jasa dan lain lain', 15000),
('JLA002', 'Tarif Pemeriksaan Dokter Umum', 'jasa dan lain lain', 20000),
('JLA003', 'Tarif Pemeriksaan Dokter Spesialis Kandungan', 'jasa dan lain lain', 90000),
('KB0001', 'Suntik Cyclo', 'kb', 20000),
('KB0002', 'Suntik Depo Geston', 'kb', 18000),
('KB0003', 'Suntik Depo Neo', 'kb', 25000),
('KB0004', 'Pasang IUD Copper T', 'kb', 150000),
('KB0005', 'Pasang IUD Copper Nova T', 'kb', 400000),
('KB0006', 'Aff IUD', 'kb', 40000),
('KB0007', 'Pasang Implan', 'kb', 100000),
('KB0008', 'Aff + Pasang Implan', 'kb', 175000);

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
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`kode_transaksi`);

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
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_resep`
--
ALTER TABLE `detail_resep`
  MODIFY `kode_detail` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
