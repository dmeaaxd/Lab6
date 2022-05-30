package structure;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import сoloringText.ColorClass;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = {"id" , "name"})
public class HumanBeing implements java.lang.Comparable<HumanBeing> {
    public HumanBeing(Long id,String name, Coordinates coordinates, boolean realHero,
                      boolean hasToothpick, Long impactSpeed, WeaponType weaponType, Mood mood, Car car){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        creationDate = LocalDateTime.now();
    }
    public HumanBeing(Long id, String name, Coordinates coordinates, LocalDateTime local, boolean realHero,
                      boolean hasToothpick, Long impactSpeed, WeaponType weaponType, Mood mood, Car car){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        creationDate = local;
    }
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private boolean realHero;
    private boolean hasToothpick;
    private Long impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

    @Override
    public String toString() {
        return ColorClass.blue + "HumanBeing{" +
                "id=" + id +
                ", name=" + name +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", weaponType=" + weaponType +
                ", mood=" + mood +
                ", car=" + car +
                "}" + ColorClass.reset;
    }

    public String csvToString() {
        return id +
                "," + name + "," +
                + coordinates.getX() +
                ","+ coordinates.getY() +"," + realHero +
                "," + hasToothpick +
                "," + impactSpeed +
                "," + weaponType +
                "," + mood +
                "," + car.getCool();
    }
    @Override
    public int compareTo(HumanBeing o) {
        if (this == o)
            return 0;
        if (o == null || getClass() != o.getClass())
            return -1;

        return this.name.compareTo(o.name);
    }
}