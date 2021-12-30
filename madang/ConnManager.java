package madang;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConnManager {
    private static Connection con;
    
    public static Connection getConnection() {
        if(con==null) {
            new ConnManager();
        }
        return con;
    }

    // DB 접속 정보
    private static final String DB_URL = "jdbc:mysql://localhost:3306/madang?serverTimezone=Asia/Seoul";
    private static final String DB_USER = "madang";
    private static final String DB_PASSWRD = "madang";

    // 생성자
    private ConnManager() {
        // #1 JDBC 드라이버를 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없습니다");
            e.printStackTrace();
        }

        // #2 DB에 연결을 생성
        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWRD);
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패");
            e.printStackTrace();
        }
    }
}
