import com.mysql.cj.jdbc.Driver;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.io.IOException;

import static java.lang.Math.pow;


public class menu1 extends Main{
    public static void main() throws SQLException{
        DriverManager.registerDriver(new Driver());
        String mysqlUrl = "jdbc:mysql://localhost/test1";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        System.out.println("Успешное подключение к БД!");
        Statement stmt = con.createStatement(); //Необходимо для отправки sql запросов в базу даннных
        ResultSet rs = stmt.executeQuery("Show tables");
        System.out.println("Таблицы из текущей базы данных: ");
        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.println();
        }
    }
}
