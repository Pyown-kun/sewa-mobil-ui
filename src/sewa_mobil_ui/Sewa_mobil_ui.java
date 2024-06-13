package sewa_mobil_ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Sewa_mobil_ui {

    static final int HARGA_MOBIL_KECIL = 300000;
    static final int HARGA_MOBIL_SEDANG = 500000;
    static final int HARGA_MOBIL_BESAR = 700000;

    static final int BIAYA_SUPIR = 150000;
    static final int BIAYA_ASURANSI = 100000;
    static final int BIAYA_GPS = 50000;

    static final double DISKON_SATU = 0.05;
    static final double DISKON_DUA = 0.1;
    static final double DISKON_TIGA = 0.15;

    public static void main(String[] args) throws IOException {

        String[] posisi = new String[2];
        posisi[0] = "Pembeli";
        posisi[1] = "Admin";

        Object pilihPosisi = JOptionPane.showInputDialog(null, "", "Login Menu", JOptionPane.QUESTION_MESSAGE, null, posisi, "Pembeli");

        if (pilihPosisi == "Admin") {

            String userName = JOptionPane.showInputDialog("Username");

            if ("admin".equals(userName)) {
                BufferedReader br = new BufferedReader(new FileReader("notaPembelian.txt"));
                String data = "";
                while ((data = br.readLine()) != null) {
                    System.out.println(data);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Coba Lagi");
            }

        } else {

            // Pilih Mobil
            String[] mobil = new String[3];
            mobil[0] = "Kecil";
            mobil[1] = "Sedang";
            mobil[2] = "Besar";

            Object pilihMobil = JOptionPane.showInputDialog(null, "Pilih Tipe Mobil Anda", "Tipe MObil", JOptionPane.QUESTION_MESSAGE, null, mobil, "Kecil");

            // Input Hari
            Integer hari = Integer.parseInt(JOptionPane.showInputDialog("Lama Sewa (Dalam bentuk  hari)"));

            // Sopir
            String[] supir = new String[2];
            supir[0] = "ya";
            supir[1] = "tidak";

            Object pilihSupir = JOptionPane.showInputDialog(null, "Apakah Anda ingin menggunakan layanan Supir?", "Layanan Supir", JOptionPane.QUESTION_MESSAGE, null, supir, "ya");

            // Asuransi
            String[] asuransi = new String[2];
            asuransi[0] = "ya";
            asuransi[1] = "tidak";

            Object pilihAsuransi = JOptionPane.showInputDialog(null, "Apakah Anda ingin menggunakan layanan Asuransi?", "Layanan Asuransi", JOptionPane.QUESTION_MESSAGE, null, asuransi, "ya");

            // GPS
            String[] gps = new String[2];
            gps[0] = "ya";
            gps[1] = "tidak";

            Object pilihGps = JOptionPane.showInputDialog(null, "Apakah Anda ingin menggunakan layanan GPS?", "Layanan GPS", JOptionPane.QUESTION_MESSAGE, null, gps, "ya");

            // Kode
            String kode = JOptionPane.showInputDialog("Masukkan Kode Promo");

            // Proses
            int hargaMobil = 0;
            if (pilihMobil == "Kecil") {
                hargaMobil = HARGA_MOBIL_KECIL;
            } else if (pilihMobil == "Sedang") {
                hargaMobil = HARGA_MOBIL_SEDANG;
            } else if (pilihMobil == "Besar") {
                hargaMobil = HARGA_MOBIL_BESAR;
            }

            double diskonHari = 0;
            if (hari >= 3) {
                diskonHari = DISKON_SATU;
            } else if (hari >= 5) {
                diskonHari = DISKON_DUA;
            } else if (hari >= 7) {
                diskonHari = DISKON_TIGA;
            }

            int hargaSupir = 0;
            if (pilihSupir == "ya") {
                hargaSupir = 150000;
            } else {
                hargaSupir = 0;
            }

            int hargaAsuransi = 0;
            if (pilihAsuransi == "ya") {
                hargaAsuransi = 100000;
            } else {
                hargaAsuransi = 0;
            }

            int hargaGps = 0;
            if (pilihGps == "ya") {
                hargaGps = 50000;
            } else {
                hargaGps = 0;
            }

            double biayaMobil = hargaMobil * hari;
            double biayaSupir = hargaSupir * hari;
            double biayaAsuransi = hargaAsuransi * hari;

            String kodePromo = "";

//            System.out.println(kode);
            double biayaGps = 0;
            if ("GRATISGPS".equals(kode) && hari >= 3) {
                biayaGps = 0;
                kodePromo = "Gratis Layanan GPS";
            } else {
                biayaGps = hargaGps * hari;
            }

            double hasil = biayaMobil + biayaSupir + biayaAsuransi + biayaGps;

            double biayaTotal = 0;
            double biayaKurang = 0;
            if ("DISKON20".equals(kode) && (hasil <= 2000000 || hasil >= 1000000)) {
                biayaKurang = hasil * (0.20 + diskonHari);
                biayaTotal = hasil - biayaKurang;
                kodePromo = "Diskon 20%";
            } else {
                biayaKurang = hasil * diskonHari;
                biayaTotal = hasil - biayaKurang;
            }

            JOptionPane.showMessageDialog(null,
                    "Jenis Mobil : " + pilihMobil + "\n"
                    + "Biaya Mobil : " + biayaMobil + "\n"
                    + "Total Lama Meminjam : " + hari + " Hari" + "\n"
                    + "Biaya Supir : " + biayaSupir + "\n"
                    + "Biaya Asuransi : " + biayaAsuransi + "\n"
                    + "Biaya GPS : " + biayaGps + "\n"
                    + "Kode Promo : " + kodePromo + "\n"
                    + "Totsl Biaya : " + biayaTotal + "\n");

            BufferedWriter bw = new BufferedWriter(new FileWriter("notaPembelian.txt"));
            bw.write(
                    "Jenis Mobil : " + pilihMobil + "\n"
                    + "Biaya Mobil : " + biayaMobil + "\n"
                    + "Total Lama Meminjam : " + hari + " Hari" + "\n"
                    + "Biaya Supir : " + biayaSupir + "\n"
                    + "Biaya Asuransi : " + biayaAsuransi + "\n"
                    + "Biaya GPS : " + biayaGps + "\n"
                    + "Kode Promo : " + kodePromo + "\n"
                    + "Totsl Biaya : " + biayaTotal);
//        System.out.println("Success");
            bw.close();

//        System.out.println("Biaya Mobil : " + biayaMobil);
//        System.out.println("Total Lama Meminjam : " + hari + " Hari");
//        System.out.println("Biaya Supir : " + biayaSupir);
//        System.out.println("Biaya Asuransi : " + biayaAsuransi);
//        System.out.println("Biaya GPS : " + biayaGps);
//        System.out.println("Kode Promo : " + kodePromo);
//        System.out.println("Totsl Biaya : " + biayaTotal);
        }

    }

}
