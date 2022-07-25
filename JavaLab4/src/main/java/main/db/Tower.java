package main.db;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import main.db.Mage;
@Entity
@Table(name = "tower")
public class Tower {
    @Id
    private String name;

    private int height;

    @OneToMany(mappedBy = "tower", cascade = CascadeType.REMOVE)
    private List<Mage> mages = new ArrayList<>();




    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public Tower() {
        this.name = null;
        this.height = 0;
        this.mages = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Mage> getMages() {
        return mages;
    }

    public void setMages(List<Mage> mages) {
        this.mages = mages;
    }

    public void print() {
        System.out.println("tower name: " + this.name);
        System.out.println("tower height: " + this.height);
        System.out.println("tower mages:");
        for (Mage mage : this.mages){
            System.out.println(mage.getName());
        }
        System.out.println();
    }
}
