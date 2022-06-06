package commands;

import structure.Mood;
import structure.WeaponType;
import сoloringText.ColorClass;

import java.util.Arrays;
import java.util.Scanner;

public class CheckTheCorrect {
    public static Boolean checkTheCorrect(boolean type)  {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                String local = scanner.next();
                if (!local.equals("true") && !local.equals("false")) {
                    throw new NumberFormatException();
                }

                type = Boolean.parseBoolean(local);
                return type;
            } catch (Exception e) {
                ColorClass.colorPrintln(ColorClass.RED, "Переменная должна быть типа Boolean. Повторите ввод");
            }
        }
    }

    public static Integer checkTheCorrect(int type)  {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ColorClass.colorPrintln(ColorClass.RED, "Ошибка ввода. Повторите ввод");
                continue;
            }

            return type;
        }
    }

    public static Long checkTheCorrect(Long type) {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = Long.valueOf(scanner.next());
                return type;
            } catch (NumberFormatException e) {
                ColorClass.colorPrintln(ColorClass.RED, "Переменная должна быть типа Long. Повторите ввод");
            }
        }
    }

    public static String checkTheCorrect(String type)  {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = scanner.nextLine();
                if (type != null && !type.trim().isEmpty()) {
                    return type;
                }

                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                ColorClass.colorPrintln(ColorClass.RED, "Переменная должна быть типа String. Повторите ввод");
            }
        }
    }

    public static Double checkTheCorrect(Double type) {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = Double.valueOf(scanner.next());
                return type;
            } catch (NumberFormatException e) {
                ColorClass.colorPrintln(ColorClass.RED, "Переменная должна быть типа Double. Повторите ввод");
            }
        }
    }

    public static Mood checkTheCorrect(Mood type){
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                ColorClass.colorPrintln(ColorClass.YELLOW, "Введите значение Mood из представленных ниже");
                Mood[] obj = Mood.values();

                for (Mood localObj : obj) {
                    System.out.print(localObj + " ");
                }
                System.out.println();
                if (!scanner.hasNext()) {
                    System.exit(1);
                }

                type = Mood.valueOf(scanner.nextLine());
                return type;
            } catch (IllegalArgumentException e) {
                ColorClass.colorPrintln(ColorClass.RED, "Переменная должна быть типа Mood. Повторите ввод");
            }
        }
    }

    public static WeaponType checkTheCorrect(WeaponType type) {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                ColorClass.colorPrintln(ColorClass.YELLOW, "Введите значение WeaponType из представленных ниже");
                WeaponType[] obj = WeaponType.values();

                for (WeaponType localObj : obj) {
                    System.out.print(localObj + " ");
                }

                System.out.println();
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = WeaponType.valueOf(scanner.nextLine());
                return type;
            } catch (IllegalArgumentException e) {
                ColorClass.colorPrintln(ColorClass.RED, "Переменная должна быть типа WeaponType. Повторите ввод");
            }
        }
    }

    public static String[] fillingArgs(String[] args){
        if(args.length!=9) args = arrayToDesiredSize(args,9);
        try {
            String name = String.valueOf(args[0]);
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            ColorClass.colorPrint(ColorClass.YELLOW, "Укажите имя: ");
            args[0] = CheckTheCorrect.checkTheCorrect("");
        }

        try {
            Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            ColorClass.colorPrint(ColorClass.YELLOW, "Укажите координату X (Integer): ");
            args[1] = CheckTheCorrect.checkTheCorrect(Integer.valueOf(0)).toString();
        }

        try {
            Double.valueOf(args[2]);
        } catch (NumberFormatException e) {
            ColorClass.colorPrint(ColorClass.YELLOW, "Укажите координату Y (Double): ");
            args[2] = CheckTheCorrect.checkTheCorrect(Double.valueOf(0)).toString();
        }

        try {
            if (!args[3].equals("true") && !args[3].equals("false")) {
                throw new NumberFormatException();
            }
            Boolean.valueOf(args[3]);
        } catch (NumberFormatException e) {
            ColorClass.colorPrint(ColorClass.YELLOW, "Укажите realHero (Boolean): ");
            args[3] = CheckTheCorrect.checkTheCorrect(Boolean.FALSE).toString();
        }

        try {
            if (!args[4].equals("true") && !args[4].equals("false")) {
                throw new NumberFormatException();
            }
            Boolean.valueOf(args[4]);
        } catch (NumberFormatException e) {
            ColorClass.colorPrint(ColorClass.YELLOW, "Укажите hasToothPick (Boolean): ");
            args[4] = CheckTheCorrect.checkTheCorrect(Boolean.FALSE).toString();
        }

        try {
            Long.valueOf(args[5]);
        } catch (NumberFormatException e) {
            ColorClass.colorPrint(ColorClass.YELLOW, "Укажите impactSpeed (Long): ");
            args[5] = CheckTheCorrect.checkTheCorrect(Long.valueOf(0)).toString();
        }

        try {
            WeaponType.valueOf(args[6]);
        } catch (IllegalArgumentException e) {
            WeaponType weaponType = null;
            args[6] = CheckTheCorrect.checkTheCorrect(weaponType).toString();
        }

        try {
            Mood.valueOf(args[7]);
        } catch (IllegalArgumentException e) {
            Mood mood = null;
            args[7] = CheckTheCorrect.checkTheCorrect(mood).toString();
        }

        try {
            if (!args[8].equals("true") && !args[8].equals("false")) {
                throw new NumberFormatException();
            }
            args[8] = Boolean.valueOf(args[8]).toString();
        } catch (NumberFormatException e) {
            ColorClass.colorPrint(ColorClass.YELLOW, "Укажите CarIsCool (Boolean): ");
            args[8]=CheckTheCorrect.checkTheCorrect(Boolean.FALSE).toString();
        }
        return args;
    }

    public static String[] arrayToDesiredSize(String[] args, int x){
        String[] local = args;
        if (args.length > x) {
            args = new String[x];
            args = (String[]) Arrays.copyOfRange(local, 0, args.length);
        }
        if (args.length < x) {
            args = new String[x];
            Arrays.fill(args, "");

            System.arraycopy(local, 0, args, 0, local.length);

        }
        return args;
    }
}
