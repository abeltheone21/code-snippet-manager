package dataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionEstablish {
 public  static Connection getConnection(){
     Connection conn=null;

     try {
          conn = DriverManager.getConnection(
                 "jdbc:postgresql://localhost:5432/snippet_management",
                 "postgres",
                 "admin123");

         System.out.println("the database is connected successfully");

     } catch (Exception e) {
         System.out.println("the database faild to connect");
         e.printStackTrace();
     }
     return conn;

 }
    public static void main(String[] args) {


    }
}