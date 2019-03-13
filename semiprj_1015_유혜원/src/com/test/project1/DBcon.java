package com.test.project1;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcon {
   static Connection con;

   public DBcon() throws Exception {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      String url = "jdbc:oracle:thin:@localhost:1521:orcl";
      String user = "scott";
      String pass = "123456";

      con = DriverManager.getConnection(url, user, pass);
//      System.out.println("접속성공");
   }

   public static Connection getConnection() throws Exception {
      if (con==null) {
         new DBcon();
      }
      return con;
   }
}