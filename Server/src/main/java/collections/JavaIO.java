package collections;

import ServerMain.ServerMain;
import commands.WriteTheValues;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import сoloringText.ColorClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class JavaIO {
    public static void writeToFile(String example) {
        File file = new File(ServerMain.path_to_file);
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(example.getBytes());
                fileOutputStream.close();
                return;
            } catch (IOException e) {
                System.out.println(ColorClass.red + "Проблемы с файлом. Укажите путь до другого файла" + ColorClass.reset);
                if (!scanner.hasNext()) {
                    System.exit(0);
                }
                file = new File(scanner.nextLine());
            }
        }
    }

    public static void CSVCreateObject() {
        Scanner scanner = new Scanner(System.in);
        String pathCSV = ServerMain.path_to_file;
        File file = new File(pathCSV);

        File file2 = file.getAbsoluteFile();
        String absPath = file2.getAbsolutePath();
        String relative = file2.getParent();
        String child = file.getName();

        new StringBuilder();

        while(true) {
            try {
                File path = new File(relative, child);
                CSVParser parser = CSVParser.parse(path, Charset.defaultCharset(), CSVFormat.RFC4180);
                List<CSVRecord> parserlocal = parser.getRecords();
                Iterator iterator = parserlocal.iterator();

                while(iterator.hasNext()) {
                    CSVRecord record = (CSVRecord)iterator.next();
                    if (record.size() == 16) {
                        WriteTheValues.createObject(record);
                    }
                }

                return;
            } catch (IOException e) {
                System.out.println(ColorClass.red + "Возникли проблемы с файлом (не существует или нет прав)" + ColorClass.reset);
                System.out.print(ColorClass.yellow + "Укажите путь к файлу: " + ColorClass.reset);
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                relative = scanner.nextLine();
                String[] strings ;
                strings = relative.split("/");
                child = strings[strings.length-1];
            }
        }
    }
}
