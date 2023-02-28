import com.mysql.cj.jdbc.Driver;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.io.IOException;

import static java.lang.Math.pow;


public class menu5 extends Main{
    public static void main() throws SQLException{
        Scanner scan = new Scanner(System.in);
        DriverManager.registerDriver(new Driver());
        String mysqlUrl4 = "jdbc:mysql://localhost/test1";
        Connection con5 = DriverManager.getConnection(mysqlUrl4, "root", "root");
        System.out.println("Успешное подключение к базе данных!");
        System.out.print("Введите таблицу: ");
        String tablename = scan.next();
        System.out.print("Введите код поста: ");
        String kod_posta = scan.next();
        Statement stmt5 = con5.createStatement();

        ResultSet rs2 = stmt5.executeQuery("SELECT * FROM " + tablename + " where Kod_Posta=" + "'" + kod_posta + "'");
        System.out.println("Результат: ");
        while (rs2.next()) {
            System.out.print(Arrays.toString(rs2.getString((1)).split(" "))); // Вывод первого столбца
            System.out.print(Arrays.toString(rs2.getString((2)).split(" "))); // Вывод второго столбца
            System.out.print(Arrays.toString(rs2.getString((3)).split(" "))); // Вывод 3 столбца
            System.out.print(Arrays.toString(rs2.getString((4)).split(" "))); // Вывод 4 столбца
            System.out.print(Arrays.toString(rs2.getString((5)).split(" "))); // Вывод 5 столбца
            System.out.print(Arrays.toString(rs2.getString((6)).split(" "))); // Вывод 6 столбца
            System.out.print(Arrays.toString(rs2.getString((7)).split(" "))); // Вывод 7 столбца
            System.out.print(Arrays.toString(rs2.getString((8)).split(" "))); // Вывод 8 столбца
            System.out.print(Arrays.toString(rs2.getString((9)).split(" "))); // Вывод 9 столбца
            System.out.print(Arrays.toString(rs2.getString((10)).split(" "))); // Вывод 10 столбца
            System.out.print(Arrays.toString(rs2.getString((11)).split(" "))); // Вывод 11 столбца

            System.out.println();
        }
    }
}
