package collections;

import сoloringText.ColorClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InfoFail {
    public static String info;

    public static void readFile() {
        String path = (new File("")).getAbsolutePath();
        String outputFileName = path + File.separator + "file.txt";
        StringBuilder line = new StringBuilder();
        BufferedReader reader;
        Scanner scanner = new Scanner(System.in);
        try {
            reader = new BufferedReader(new FileReader(outputFileName));
        }catch(IOException e) {
            System.out.println(ColorClass.red + "Проблема с файлом" + ColorClass.reset);
            while (true) {
                System.out.print(ColorClass.yellow + "Укажите новый путь: " + ColorClass.reset);
                if (!scanner.hasNext()) {
                    System.out.println(ColorClass.red + "Неправильный ввод, принудительное отключение" + ColorClass.reset);
                    System.exit(1);
                }
                outputFileName = scanner.nextLine();
            }
        }
        String local;
        try {
            while ((local = reader.readLine()) != null) {
                line.append(local + "\n");
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println(ColorClass.red + "Проблема с файлом" + ColorClass.reset);
        }
        if (line.length() <= 0) {
            System.out.println(ColorClass.red + "В файле ничего нет" + ColorClass.reset);
        }
        info = line.toString();
    }

    public static String infoCol(int x) {
        StringBuilder line = new StringBuilder(info);
        line.append(ColorClass.blue + "Длина коллекции: " + x + ColorClass.reset);
        return line.toString().trim();
    }
}
