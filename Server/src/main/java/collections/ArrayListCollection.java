package collections;

import lombok.Getter;
import structure.HumanBeing;

import java.util.ArrayList;
import java.util.Date;

public class ArrayListCollection {
    @Getter
    public static ArrayList<HumanBeing> entitiesCollection = new ArrayList<>();
    @Getter
    public static java.util.Date dataCreation = new Date();
}
