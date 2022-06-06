package сoloringText;

public class ColorClass {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public static void colorPrint(String color, String text){
        System.out.print(color + text + RESET);
    }

    public static void colorPrintln(String color, String text){
        System.out.println(color + text + RESET);
    }
}
