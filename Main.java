
import com.mysql.cj.jdbc.Driver;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.io.IOException;

import static java.lang.Math.pow;


public class Main {

    public static Double normal(Double x){
        Double min = 1011979.0;
        Double max = 31122020.0;

        x = (x-min) / (max-min);

        return x;
    }
    

    public static Double euclide_dist(Double x, Double y) {
        double distance;
        distance = Math.sqrt(pow((x - y), 2));
        return distance;
    }

    public static String Get_neigbors(String[][] train, Double test) {
        Map<Double, String> distances = new HashMap<Double, String>();
        for (int i = 0; i < train[0].length; i++) {
            distances.put(euclide_dist(normal(Double.parseDouble(train[0][i])), normal(test)), train[1][i]);
        }
        Double d_min = 99999.999;

        for (Double i : distances.keySet()) {
            if (i < d_min) {
                d_min = i;
            }

        }
        return distances.get(d_min);

    }


    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        int x = 0;
        String s = "";
        while (!"8".equals(s)) {
            System.out.println("1. Вывести все таблицы из базы данных MySQL.");
            System.out.println("2. Создать таблицу в базе данных MySQL (одна для всего файла).");
            System.out.println("3. Экспортировать все данные из файла в Excel в MySQL.");
            System.out.println("4. Экспорт из эксель-файла по столбцам.");
            System.out.println("5. Вывод из MySQL всех данных по коду гидропоста.");
            System.out.println("6. Вывод из MySQL данных по коду гидропоста и дате.");
            System.out.println("7. Восстановить все пропущенные данные (за исключением столбца «Описание») в MySQL методом k-ближайших соседей (метрика: Евклидово расстояние).");
            System.out.println("8. Выход.");
            System.out.print("Введите действие: ");
            s = scan.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода!");
            }
            switch (x) {
                case 1 -> {menu1.main();}
                case 2 -> {menu2.main();}
                case 3 -> {menu3.main();}
                case 4 -> {menu4.main();}
                case 5 -> {menu5.main();}
                case 6 -> {menu6.main();}
                case 7 -> {menu7.main();}
            }
        }
        System.out.println("Выход из программы!");
    }
}
