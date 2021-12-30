import java.sql.Connection;
import java.util.Scanner;

import madang.Madang;
import madang.ConnManager;

public class DbApp {
    
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Connection c = ConnManager.getConnection();

        Madang book = new Madang(c);

        book.select();
        System.out.print("책 검색 > ");
        String keyword = scanner.nextLine();
        book.select(keyword);

        try {
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
