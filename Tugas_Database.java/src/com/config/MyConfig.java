package com.config;

import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyConfig {

    private static final String DB_URL ="jdbc:mysql://localhost:3306/db_product";
    private static final String DB_USER ="root";
    private static final String DB_PASS= "";

    private static Connection connect;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;
    
    public static void connection(){
        try {
            connect = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
                       
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getDatabase(){
        connection();

        try {
            String query ="SELECT NAMA,HARGA,JUMLAH FROM `tb_product` ORDER BY ID DESC";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("NAMA"));
                System.out.println("Rp "+resultSet.getInt("HARGA"));
                System.out.println(resultSet.getInt("JUMLAH"));
            }
            statement.close();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(){
        String namaBaru;
        long hargaBaru;
        int jumlahBaru;

        Scanner input = new Scanner(System.in);

        System.out.print("Nama  : ");
        namaBaru = input.nextLine();

        System.out.print("Harga : ");
        hargaBaru = input.nextLong();

        System.out.print("Jumlah : ");
        jumlahBaru = input.nextInt();

        MyConfig.connection();

        try {
            String query = "INSERT INTO tb_product (`NAMA`, `HARGA`, `JUMLAH`) VALUES (?, ?, ?)";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, namaBaru);
            preparedStatement.setLong(2, hargaBaru);
            preparedStatement.setInt(3, jumlahBaru);
    
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data berhasil disimpan");
            } else {
                System.out.println("Gagal menyimpan data");
            }
    
            preparedStatement.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateData(int id,  long harga, int jumlah) {
        connection();
        String query = "UPDATE tb_product SET HARGA=?, JUMLAH=? WHERE Id=?";
        try {
            preparedStatement = connect.prepareStatement(query);
            
            preparedStatement.setLong(1, harga);
            preparedStatement.setInt(2, jumlah);
            preparedStatement.setInt(3, id);
            
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data berhasil diperbarui.");
            } else {
                System.out.println("Gagal memperbarui data.");
            }
            
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteData(String nama){
        connection();
        String query="DELETE FROM tb_product WHERE NAMA=?";
        try {
            preparedStatement=connect.prepareStatement(query);
            preparedStatement.setString(1,nama);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Data berhasil dihapus");
        
        } catch (SQLException e) {
            e.printStackTrace();        
        }
    }
}
