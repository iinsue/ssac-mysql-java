package madang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Book {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/madang?serverTimezone=Asia/Seoul";
    private static final String DB_USER = "madang";
    private static final String DB_PASSWRD = "madang";
    Connection con;

    public Book() {
        // #1 JDBC 드라이버를 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWRD);
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패");
        }
    }

    public void select() {
        String sql = "select bookid, bookname, publisher, price from book";

        try {
            //Statement 객체를 생성
            Statement stmt = con.createStatement();
            // 쿼리 실행 및 실행 결과를 반환
            ResultSet rs = stmt.executeQuery(sql);
            // 실행 결과를 출력
            while(rs.next()) {
                System.out.print(rs.getInt("bookid") + "\t");
                System.out.print(rs.getString("bookname") + "\t");
				System.out.print(rs.getString("publisher") + "\t");
				System.out.println(rs.getInt("price"));
            }
        } catch (SQLException e) {
            System.out.println("쿼리 실행 오류");
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
