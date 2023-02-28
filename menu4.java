import com.mysql.cj.jdbc.Driver;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.io.IOException;

import static java.lang.Math.pow;



public class menu4 extends Main{
    public static void main() throws SQLException{
        Scanner scan = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("C:/Users/denis/Downloads/Urovni2_1_1_new_1_2 (1).csv"))) {

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
        System.out.print("Введите столбы через запятую: ");
        String stolb = scan.nextLine();
        System.out.print("Создать новую таблицу по введенным столбцам? ");
        String otvet = scan.next();
        int dlina = stolb.split(", ").length;
        String[] virazhenie = new String[dlina];
        String tablename = new String();
        if (Objects.equals(otvet, "Да")) {
            for (int i = 0; i < stolb.split(", ").length; i++) {
                virazhenie[i] = stolb.split(", ")[i] + " varchar(255)";
            }
            Statement stmt1 = con2.createStatement();
            System.out.print("Введите название таблицы: ");
            scan.nextLine();
            tablename = scan.nextLine();
            String query = "CREATE TABLE IF NOT EXISTS " + tablename + " " + Arrays.toString(virazhenie).replace("[", "(").replace("]", ")");
            stmt1.executeUpdate(query);
        } else {
            System.out.print("Введите таблицу: ");
            scan.nextLine();
            tablename = scan.nextLine();
        }

        StringBuilder vopros = new StringBuilder();
        for (int i = 0; i < stolb.split(", ").length - 1; i++) {
            vopros.append(", ?");
        }
        String query2 = "INSERT INTO " + tablename + " (" + stolb + ") VALUES (?" + vopros + ");";
        PreparedStatement stmt3 = con2.prepareStatement(query2);

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("Object_ID", 0);
        map.put("Kod_Posta", 1);
        map.put("Kod_Parametra", 2);
        map.put("Date", 3);
        map.put("Water_LVL", 4);
        map.put("Description", 5);
        map.put("t_vozd", 6);
        map.put("Atm_press", 7);
        map.put("Wint_speed", 8);
        map.put("Snow", 9);
        map.put("Kol_osad", 10);

        for (String[] i : rows) {
            for (int j = 0; j < stolb.split(", ").length; j++) {
                stmt3.setString(j + 1, i[map.get(stolb.split(", ")[j])]);
            }
            stmt3.executeUpdate();

        }

        System.out.println("Результат успешно сохранён в MySQL!");
        ResultSet rs2 = stmt3.executeQuery("SELECT * FROM " + tablename + "");
        System.out.println("Результат: ");
        while (rs2.next()) {
            for (int i = 0; i<stolb.split(", ").length; i++)
            {
                System.out.print(Arrays.toString(rs2.getString(i+1).split(" ")));
            }
            System.out.println();
        }
    }
}
