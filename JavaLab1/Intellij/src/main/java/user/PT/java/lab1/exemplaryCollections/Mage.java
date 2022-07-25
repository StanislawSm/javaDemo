package user.PT.java.lab1.exemplaryCollections;

import java.util.Objects;
import java.util.Set;

public class Mage implements Comparable {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }

    public void setApprentices(Set<Mage> apprentices) {
        this.apprentices = apprentices;
    }

    public Mage(){
        this.name = "";
        this.level = 0;
        this.power = 0;
        this.apprentices.clear();
    }

    public Mage(String name, int level, double power) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = null;
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Mage mage = (Mage) o;
        if (name != mage.name) {
            return name.compareTo(mage.name);
        } else if (level != mage.level) {
            return level - mage.level;
        } else {
            return (int)(power - mage.power);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && Double.compare(mage.power, power) == 0 && Objects.equals(name, mage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power);
    }
}
