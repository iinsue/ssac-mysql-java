package madang;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Madang {
   Connection con;

   public Madang(Connection con) {
       this.con = con;
   }
    
   //book 테이블 조회
   public void select() {
       String sql = "select bookid, bookname, publisher, price from book";
       try {
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
           while(rs.next()) {
               System.out.print(rs.getInt("bookid") + "\t");
               System.out.print(rs.getString("bookname") + "\t");
               System.out.print(rs.getString("publisher") + "\t");
               System.out.println(rs.getInt("price"));
           } 
       } catch (SQLException e) {
        System.out.println("쿼리 실행 오류");
        e.printStackTrace();
    }
   }
   public void select(String keyword) {
       String sql = "select bookid, bookname, publisher, price from book where bookname like ? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, '%' + keyword + '%');
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getInt("bookid") + "\t");
                System.out.print(rs.getString("bookname") + "\t");
                System.out.print(rs.getString("publisher") + "\t");
                System.out.println(rs.getInt("price"));
            }
        } catch (SQLException e) {
            System.out.println("쿼리 실행 오류");
            e.printStackTrace();
        }
   }
}

