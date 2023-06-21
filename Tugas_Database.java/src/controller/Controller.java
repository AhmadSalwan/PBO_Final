package controller;

import java.util.Scanner;

import com.config.MyConfig;

import Layout.Layout;
public class Controller {
    public static void run(Scanner input){
        

        boolean exit = false;
  
        while (!exit) {
            int choice = input.nextInt();
            input.nextLine(); // Membuang karakter newline dari input sebelumnya

            switch (choice) {
                case 1:
                    // Panggil method untuk membaca data
                    MyConfig.getDatabase();
                    Layout.runs();

                    break;
                case 2:
                    // Panggil method untuk menambahkan data
                    MyConfig.insertData();
                    Layout.runs();
                    break;
                case 3:
                    // Panggil method untuk mengedit data
                    System.out.print("Masukkan ID data yang akan diubah: ");
                    int id = input.nextInt();
                    input.nextLine(); // Membuang karakter newline dari input sebelumnya
                    System.out.println("Masukkan Harga baru");
                    int hargaBaru=input.nextInt();
                    System.out.print("Masukkan jumlah baru: ");
                    int jumlahBaru = input.nextInt();
                    input.nextLine(); // Membuang karakter newline dari input sebelumnya
                    MyConfig.updateData(id,hargaBaru,jumlahBaru);
                    Layout.runs();
                    break;
                case 4:
                    // Panggil method untuk menghapus data
                    System.out.print("Masukkan nama data yang akan dihapus: ");
                    String nama = input.nextLine();
                    MyConfig.deleteData(nama);
                    Layout.runs();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    Layout.runs();
                    break;
            }
        }

        input.close();
    }
}
