import com.mysql.cj.jdbc.Driver;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.io.IOException;

import static java.lang.Math.pow;


public class menu2 extends Main{
    public static void main() throws SQLException{
        Scanner scan = new Scanner(System.in);
        DriverManager.registerDriver(new Driver());
        String mysqlUrl1 = "jdbc:mysql://localhost/test1";
        Connection con1 = DriverManager.getConnection(mysqlUrl1, "root", "root");
        System.out.println("Успешное подключение к БД!");
        Statement stmt1 = con1.createStatement();
        System.out.print("Введите название столбца: ");
        String tablename = scan.nextLine();
        String query = "CREATE TABLE IF NOT EXISTS " + tablename + " (Object_ID varchar(255), Kod_Posta varchar(255), Kod_Parametra varchar(255)," +
                " Date varchar(255), Water_LVL varchar(255), Description varchar(255), t_vozd varchar(255), Atm_press varchar(255), Wint_speed varchar(255), Snow varchar(255), Kol_osad varchar(255))";
        stmt1.executeUpdate(query);
        ResultSet rs1 = stmt1.executeQuery("Show tables");
        System.out.println("Таблицы из текущей базы данных: ");
        while (rs1.next()) {
            System.out.print(rs1.getString(1));
            System.out.println();
        }
    }
}
