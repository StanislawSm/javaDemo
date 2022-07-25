package main.db;
import javax.persistence.*;

import main.db.Tower;

@Entity
@Table(name = "mege")
public class Mage {

    @Id
    private String name;

    private int level;

    @ManyToOne (cascade = CascadeType.REMOVE)
    private Tower tower;

    public Mage(String name, int level){
        this.name = name;
        this.level = level;
    }

    public Mage() {
        this.name = null;
        this.level = 0;
        this.tower = null;
    }

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

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public void print() {
        System.out.println("mage name: " + this.name);
        System.out.println("mage level: " + this.level);
        System.out.println("mage tower: "+ this.tower.getName());
        System.out.println();
    }
}
