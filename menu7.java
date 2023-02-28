import com.mysql.cj.jdbc.Driver;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.io.IOException;

import static java.lang.Math.pow;


public class menu7 extends Main{
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

        DriverManager.registerDriver(new Driver());
        String mysqlUrl2 = "jdbc:mysql://localhost/test1";
        Connection con2 = DriverManager.getConnection(mysqlUrl2, "root", "root");
        System.out.println("Успешное подключение к базе данных!");
        System.out.print("Введите таблицу: ");
        String tablename = scan.nextLine();
        System.out.println(tablename);
        Statement stmt3 = con2.createStatement();
        ResultSet rs2 = stmt3.executeQuery("SELECT * FROM " + tablename + "");
        String[][] rows = new String[lines.size()-1][11];
        int m = 0;
        while (rs2.next()) {
            rows[m][0] = Arrays.toString(rs2.getString((1)).split(" ")).replace("[","").replace("]", "");
            rows[m][1] = Arrays.toString(rs2.getString((2)).split(" ")).replace("[","").replace("]", "");
            rows[m][2] = Arrays.toString(rs2.getString((3)).split(" ")).replace("[","").replace("]", "");
            rows[m][3] = Arrays.toString(rs2.getString((4)).split(" ")).replace("[","").replace("]", "");
            rows[m][4] = Arrays.toString(rs2.getString((5)).split(" ")).replace("[","").replace("]", "");
            rows[m][5] = Arrays.toString(rs2.getString((6)).split(" ")).replace("[","").replace("]", "");
            rows[m][6] = Arrays.toString(rs2.getString((7)).split(" ")).replace("[","").replace("]", "");
            rows[m][7] = Arrays.toString(rs2.getString((8)).split(" ")).replace("[","").replace("]", "");
            rows[m][8] = Arrays.toString(rs2.getString((9)).split(" ")).replace("[","").replace("]", "");
            rows[m][9] = Arrays.toString(rs2.getString((10)).split(" ")).replace("[","").replace("]", "");
            rows[m][10] = Arrays.toString(rs2.getString((11)).split(" ")).replace("[","").replace("]", "");
            m+=1;
        }





        int k = 0;
        for (int i = 0; i < lines.size()-1;i++) {
            if (rows[i][6] != "") {
                k += 1;
            }
        }

        String[][] train_temp = new String[2][k];
        k = 0;
        for (int i = 0; i < lines.size()-1;i++){
            if (!Objects.equals(rows[i][6], "")) {
                train_temp[0][k] = String.valueOf(Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] + rows[i][3].split("\\.")[2]));
                train_temp[1][k] = rows[i][6];
                k+=1;
            }
        }


        for (int i = 0; i < lines.size()-1; i++){
            if (rows[i][6].isEmpty()){
                rows[i][6] = String.valueOf(Get_neigbors(train_temp, Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] +
                        rows[i][3].split("\\.")[2])));
            }
        }

        k = 0;
        for (int i = 0; i < lines.size()-1;i++) {
            if (rows[i][0] != "") {
                k += 1;
            }
        }

        String[][] train_obj = new String[2][k];
        k = 0;
        for (int i = 0; i < lines.size()-1;i++){
            if (rows[i][0] != "") {
                train_obj[0][k] = String.valueOf(Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] + rows[i][3].split("\\.")[2]));
                train_obj[1][k] = rows[i][0];
                k+=1;
            }
        }


        for (int i = 0; i < lines.size()-1; i++){
            if (rows[i][0].isEmpty()){
                rows[i][0] = String.valueOf(Get_neigbors(train_obj, Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] +
                        rows[i][3].split("\\.")[2])));
            }
        }

        k = 0;
        for (int i = 0; i < lines.size()-1;i++) {
            if (rows[i][1] != "") {
                k += 1;
            }
        }

        String[][] train_kod_posta = new String[2][k];
        k = 0;
        for (int i = 0; i < lines.size()-1;i++){
            if (rows[i][1] != "") {
                train_kod_posta[0][k] = String.valueOf(Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] + rows[i][3].split("\\.")[2]));
                train_kod_posta[1][k] = rows[i][1];
                k+=1;
            }
        }


        for (int i = 0; i < lines.size()-1; i++){
            if (rows[i][1].isEmpty()){
                rows[i][1] = String.valueOf(Get_neigbors(train_kod_posta, Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] +
                        rows[i][3].split("\\.")[2])));
            }
        }

        k = 0;
        for (int i = 0; i < lines.size()-1;i++) {
            if (rows[i][4] != "") {
                k += 1;
            }
        }

        String[][] train_water_lvl = new String[2][k];
        k = 0;
        for (int i = 0; i < lines.size()-1;i++){
            if (rows[i][4] != "") {
                train_water_lvl[0][k] = String.valueOf(Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] + rows[i][3].split("\\.")[2]));
                train_water_lvl[1][k] = rows[i][4];
                k+=1;
            }
        }


        for (int i = 0; i < lines.size()-1; i++){
            if (rows[i][4].isEmpty()){
                rows[i][4] = String.valueOf(Get_neigbors(train_water_lvl, Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] +
                        rows[i][3].split("\\.")[2])));
            }
        }

        k = 0;
        for (int i = 0; i < lines.size()-1;i++) {
            if (rows[i][7] != "") {
                k += 1;
            }
        }

        String[][] train_atm_press = new String[2][k];
        k = 0;
        for (int i = 0; i < lines.size()-1;i++){
            if (rows[i][7] != "") {
                train_atm_press[0][k] = String.valueOf(Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] + rows[i][3].split("\\.")[2]));
                train_atm_press[1][k] = rows[i][7];
                k+=1;
            }
        }


        for (int i = 0; i < lines.size()-1; i++){
            if (rows[i][7].isEmpty()){
                rows[i][7] = String.valueOf(Get_neigbors(train_atm_press, Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] +
                        rows[i][3].split("\\.")[2])));
            }
        }

        k = 0;
        for (int i = 0; i < lines.size()-1;i++) {
            if (rows[i][8] != "") {
                k += 1;
            }
        }

        String[][] train_wint_speed = new String[2][k];
        k = 0;
        for (int i = 0; i < lines.size()-1;i++){
            if (rows[i][8] != "") {
                train_wint_speed[0][k] = String.valueOf(Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] + rows[i][3].split("\\.")[2]));
                train_wint_speed[1][k] = rows[i][8];
                k+=1;
            }
        }


        for (int i = 0; i < lines.size()-1; i++){
            if (rows[i][8].isEmpty()){
                rows[i][8] = String.valueOf(Get_neigbors(train_wint_speed, Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] +
                        rows[i][3].split("\\.")[2])));
            }
        }

        k = 0;
        for (int i = 0; i < lines.size()-1;i++) {
            if (rows[i][9] != "") {
                k += 1;
            }
        }

        String[][] train_snow = new String[2][k];
        k = 0;
        for (int i = 0; i < lines.size()-1;i++){
            if (rows[i][9] != "") {
                train_snow[0][k] = String.valueOf(Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] + rows[i][3].split("\\.")[2]));
                train_snow[1][k] = rows[i][9];
                k+=1;
            }
        }


        for (int i = 0; i < lines.size()-1; i++){
            if (rows[i][9].isEmpty()){
                rows[i][9] = String.valueOf(Get_neigbors(train_snow, Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] +
                        rows[i][3].split("\\.")[2])));
            }
        }

        k = 0;
        for (int i = 0; i < lines.size()-1;i++) {
            if (rows[i][10] != "") {
                k += 1;
            }
        }

        String[][] train_kol_osad = new String[2][k];
        k = 0;
        for (int i = 0; i < lines.size()-1;i++){
            if (rows[i][10] != "") {
                train_kol_osad[0][k] = String.valueOf(Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] + rows[i][3].split("\\.")[2]));
                train_kol_osad[1][k] = rows[i][10];
                k+=1;
            }
        }


        for (int i = 0; i < lines.size()-1; i++){
            if (rows[i][10].isEmpty()){
                rows[i][10] = String.valueOf(Get_neigbors(train_kol_osad, Double.parseDouble(rows[i][3].split("\\.")[1] + rows[i][3].split("\\.")[0] +
                        rows[i][3].split("\\.")[2])));
            }
        }



        Statement stmt = con2.createStatement(); //Необходимо для отправки sql запросов в базу даннных
        stmt.executeUpdate("TRUNCATE TABLE "+ tablename);

        DriverManager.registerDriver(new Driver());

        String query2 = "INSERT INTO " + tablename + " (Object_ID, Kod_Posta, Kod_Parametra, Date, Water_LVL, Description, t_vozd, Atm_press, Wint_speed, Snow, Kol_osad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt4 = con2.prepareStatement(query2);
        for (String[] i : rows) {
            stmt4.setString(1, i[0]);
            stmt4.setString(2, i[1]);
            stmt4.setString(3, i[2]);
            stmt4.setString(4, i[3]);
            stmt4.setString(5, i[4]);
            stmt4.setString(6, i[5]);
            stmt4.setString(7, i[6]);
            stmt4.setString(8, i[7]);
            stmt4.setString(9, i[8]);
            stmt4.setString(10, i[9]);
            stmt4.setString(11, i[10]);
            stmt4.executeUpdate();
        }

        
        System.out.println("Результат успешно сохранён в MySQL!");
        ResultSet rs3 = stmt4.executeQuery("SELECT * FROM " + tablename + "");
        System.out.println("Результат: ");
        while (rs3.next()) {
            System.out.print(Arrays.toString(rs3.getString((1)).split(" "))); // Вывод первого столбца
            System.out.print(Arrays.toString(rs3.getString((2)).split(" "))); // Вывод второго столбца
            System.out.print(Arrays.toString(rs3.getString((3)).split(" "))); // Вывод 3 столбца
            System.out.print(Arrays.toString(rs3.getString((4)).split(" "))); // Вывод 4 столбца
            System.out.print(Arrays.toString(rs3.getString((5)).split(" "))); // Вывод 5 столбца
            System.out.print(Arrays.toString(rs3.getString((6)).split(" "))); // Вывод 6 столбца
            System.out.print(Arrays.toString(rs3.getString((7)).split(" "))); // Вывод 7 столбца
            System.out.print(Arrays.toString(rs3.getString((8)).split(" "))); // Вывод 8 столбца
            System.out.print(Arrays.toString(rs3.getString((9)).split(" "))); // Вывод 9 столбца
            System.out.print(Arrays.toString(rs3.getString((10)).split(" "))); // Вывод 10 столбца
            System.out.print(Arrays.toString(rs3.getString((11)).split(" "))); // Вывод 11 столбца

            System.out.println();
        }
    }
}
