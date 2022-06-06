package commands;

import collection.IdCollection;
import collections.ArrayListCollection;
import structure.*;
import сoloringText.ColorClass;

import org.apache.commons.csv.CSVRecord;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class WriteTheValues {
    public static boolean createObject(String[] args)  {
        Mood mood = null;
        WeaponType weaponType = null;
        try {
            Long id = Long.valueOf(args[0]);
            String name = String.valueOf(args[1]);
            Integer x = Integer.valueOf(args[2]);
            Double y = Double.valueOf(args[3]);
            boolean realHero = Boolean.valueOf(args[4]);
            boolean hasToothpick = Boolean.valueOf(args[5]);
            Long impactSpeed = Long.valueOf(args[6]);
            weaponType = WeaponType.valueOf(args[7]);
            mood = Mood.valueOf(args[8]);
            boolean cool = Boolean.valueOf(args[9]);


            ArrayListCollection.entitiesCollection.add(new HumanBeing(id, name, new Coordinates(x, y), realHero, hasToothpick, impactSpeed, weaponType, mood, new Car(cool)));
            return true;
        }
        catch (NumberFormatException|ArrayIndexOutOfBoundsException e){
            ColorClass.colorPrintln(ColorClass.RED, "Неправильный номер или тип аргументов");
            return false;
        }
    }

    public static boolean createObject(CSVRecord arguments) {
        Mood mood = null;
        WeaponType weaponType = null;

        String name;
        Double y;
        Integer x;
        Long impactSpeed;
        boolean realHero;
        boolean hasToothpick;
        boolean cool;
        Long id;
        LocalDateTime localDateTime;
        try {
            id = Long.valueOf(Integer.valueOf(arguments.get(0)));
            if (IdCollection.idCollection.contains(id)) {
                return false;
            }

            name = String.valueOf(arguments.get(1));
            if (name == null || name.trim().isEmpty()) {
                return false;
            }

            x = Integer.valueOf(arguments.get(2));
            y = Double.valueOf(Integer.valueOf(arguments.get(3)));
            int year = Integer.valueOf(arguments.get(4));
            int month = Integer.valueOf(arguments.get(5));
            int dayOfMonth = Integer.valueOf(arguments.get(6));
            int hour = Integer.valueOf(arguments.get(7));
            int minute = Integer.valueOf(arguments.get(8));
            localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            realHero = Boolean.valueOf(arguments.get(9));
            hasToothpick = Boolean.valueOf(arguments.get(10));
            impactSpeed = Long.valueOf(arguments.get(11));
            mood = Mood.valueOf(arguments.get(13));
            weaponType = WeaponType.valueOf(arguments.get(12));
            cool = Boolean.valueOf(arguments.get(14));
        } catch (IllegalArgumentException e) {
            return false;
        } catch (DateTimeException e) {
            return false;
        }

        ArrayListCollection.entitiesCollection.add(new HumanBeing(id, name, new Coordinates(x, y), localDateTime, realHero, hasToothpick, impactSpeed, weaponType, mood, new Car(cool)));
        return true;
    }
}
