import java.util.Scanner;

import madang.Madang;

public class DbApp {
    
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Madang book = new Madang();
        System.out.print("책 검색 > ");
        String keyword = scanner.nextLine();
        book.select(keyword);
    }
}
