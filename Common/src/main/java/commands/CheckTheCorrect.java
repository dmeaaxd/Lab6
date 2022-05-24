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
                System.out.print(ColorClass.red + "Переменная должна быть типа Boolean. Повторите ввод: " + ColorClass.reset);
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
                System.out.print("Incorrect data entered, please re-enter: ");
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
                System.out.print(ColorClass.red + "Переменная должна быть типа Long. Повторите ввод: " + ColorClass.reset);
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
                System.out.print(ColorClass.red + "Переменная должна быть типа String. Повторите ввод: " + ColorClass.reset);
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
                System.out.print(ColorClass.red + "Переменная должна быть типа Double. Повторите ввод: " + ColorClass.reset);
            }
        }
    }

    public static Mood checkTheCorrect(Mood type){
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println(ColorClass.yellow + "Введите значение Mood из представленных ниже" + ColorClass.reset);
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
                System.out.print(ColorClass.red + "Переменная должна быть типа Mood. Повторите ввод: " + ColorClass.reset);
            }
        }
    }

    public static WeaponType checkTheCorrect(WeaponType type) {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println(ColorClass.yellow + "Введите значение WeaponType из представленных ниже" + ColorClass.reset);
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
                System.out.print(ColorClass.red + "Переменная должна быть типа WeaponType. Повторите ввод: " + ColorClass.reset);
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
            System.out.print(ColorClass.yellow + "Укажите имя: " + ColorClass.reset);
            args[0] = CheckTheCorrect.checkTheCorrect("");
        }

        try {
            Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            System.out.print(ColorClass.yellow + "Укажите координату X (Integer): " + ColorClass.reset);
            args[1] = CheckTheCorrect.checkTheCorrect(Integer.valueOf(0)).toString();
        }

        try {
            Double.valueOf(args[2]);
        } catch (NumberFormatException e) {
            System.out.print(ColorClass.yellow + "Укажите координату Y (Double): " + ColorClass.reset);
            args[2] = CheckTheCorrect.checkTheCorrect(Double.valueOf(0)).toString();
        }

        try {
            if (!args[3].equals("true") && !args[3].equals("false")) {
                throw new NumberFormatException();
            }
            Boolean.valueOf(args[3]);
        } catch (NumberFormatException e) {
            System.out.print(ColorClass.yellow + "Укажите realHero (Boolean): " + ColorClass.reset);
            args[3] = CheckTheCorrect.checkTheCorrect(Boolean.FALSE).toString();
        }

        try {
            if (!args[4].equals("true") && !args[4].equals("false")) {
                throw new NumberFormatException();
            }
            Boolean.valueOf(args[4]);
        } catch (NumberFormatException e) {
            System.out.print(ColorClass.yellow + "Укажите hasToothPick (Boolean): " + ColorClass.reset);
            args[4] = CheckTheCorrect.checkTheCorrect(Boolean.FALSE).toString();
        }

        try {
            Long.valueOf(args[5]);
        } catch (NumberFormatException e) {
            System.out.print(ColorClass.yellow + "Укажите impactSpeed (Long): " + ColorClass.reset);
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
            System.out.print(ColorClass.yellow + "Укажите CarIsCool (Boolean): " + ColorClass.reset);
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
