import com.mysql.cj.jdbc.Driver;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.io.IOException;

import static java.lang.Math.pow;


public class menu3 extends Main{
    public static void main() throws SQLException{
        Scanner scan = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("C:/Users/denis/Downloads/Urovni2_1_1_new_1_2.csv"))) {

            String line;
            while ((line = br.readLine()) != null) {

                lines.add(line);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String[][] rows = new String[lines.size()-1][11];
        for (int i = 1; i < lines.size(); i++) {
            for (int j = 0; j < 11; j++) {
                if (j < lines.get(i).split(";").length) {
                    rows[i-1][j] = lines.get(i).split(";")[j];
                } else {
                    rows[i-1][j] = "";
                }
            }
        }


        DriverManager.registerDriver(new Driver());
        String mysqlUrl2 = "jdbc:mysql://localhost/test1";
        Connection con2 = DriverManager.getConnection(mysqlUrl2, "root", "root");
        System.out.println("Успешное подключение к базе данных!");
        System.out.print("Введите таблицу: ");
        String tablename = scan.nextLine();
        System.out.println(tablename);
        String query2 = "INSERT INTO " + tablename + " (Object_ID, Kod_Posta, Kod_Parametra, Date, Water_LVL, Description, t_vozd, Atm_press, Wint_speed, Snow, Kol_osad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt3 = con2.prepareStatement(query2);
        for (String[] i : rows) {
            stmt3.setString(1, i[0]);
            stmt3.setString(2, i[1]);
            stmt3.setString(3, i[2]);
            stmt3.setString(4, i[3]);
            stmt3.setString(5, i[4]);
            stmt3.setString(6, i[5]);
            stmt3.setString(7, i[6]);
            stmt3.setString(8, i[7]);
            stmt3.setString(9, i[8]);
            stmt3.setString(10, i[9]);
            stmt3.setString(11, i[10]);
            stmt3.executeUpdate();
        }

        System.out.println("Результат успешно сохранён в MySQL!");
        ResultSet rs2 = stmt3.executeQuery("SELECT * FROM " + tablename + "");
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
