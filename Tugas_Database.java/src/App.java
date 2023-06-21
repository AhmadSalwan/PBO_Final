import java.util.Scanner;

import com.config.MyConfig;

import controller.Controller;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("-----------------------");
        System.out.println("Welcome To");
        System.out.println("1.] Read Data");
        System.out.println("2.] Insert Data");
        System.out.println("3.] Edit Data");
        System.out.println("4.] Delete Data");
        Scanner input=new Scanner(System.in);
        
        Controller.run(input);
        
        }

    }


//create
//read
//update
//delete
// -----------------------
//  WELCOME TO ...
// ------------------------
// 1.] Read Data
// 2.] Insert data
// 3.] Edit Data
// 4.] Delete data
// ------------------------
// Pilih: 5
// pilihan tersedian
// ------------------------
//  WELCOME TO ...
// ------------------------
// 1.] Read Data
// 2.] Insert data
// 3.] Edit Data
// 4.] Delete data
// ------------------------
// Pilih: 


// NIM_TUGAS_PROYEK.zip
// mfadi.stat@gmail.com
// 0853-4096-0793 wa