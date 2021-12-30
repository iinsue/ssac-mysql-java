package madang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Madang {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/madang?serverTimezone=Asia/Seoul";
    private static final String DB_USER = "madang";
    private static final String DB_PASSWRD = "madang";
    Connection con;

    public Madang() {
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

    public void select(String keyword) {

        // 안전한 코드
        // #1 쿼리의 구조를 정의 = 변수 부분을 물음표로 마킹 (데이터 타입을 고려하지 않음)
        String sql = "select bookid, bookname, publisher, price from book where bookname like ?";

        try {
            // #2 PreparedStatement 객체를 생성
            // connection.prepareSatement 메소드를 이용 (쿼리문의 구조를 파라미터로 전달)
            PreparedStatement stmt = con.prepareStatement(sql);

            // #3 변수에 값을 할당하고 쿼리를 실행
            // PreparedStatement 객체에서 제공하는 set 메소드를 이용 (데이터 타입을 고려해서 사용)
            // 쿼리 실행에 별도의 SQL을 전달하지 않아도 됨.
            stmt.setString(1, '%' + keyword + '%');
            ResultSet rs = stmt.executeQuery();
            
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

