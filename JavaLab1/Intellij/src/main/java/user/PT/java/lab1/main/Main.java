package user.PT.java.lab1.main;
import user.PT.java.lab1.exemplaryCollections.Mage;
import user.PT.java.lab1.exemplaryCollections.MageComparator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1 - without sorting
        // 2 - natural
        // 3 - alternative

        String input = "1";
        MageComparator mageComparator = new MageComparator();
        Mage m1 = new Mage("m1", 2, 4.0);
        Mage m2 = new Mage("m2", 4, 8.0);
        Mage m3 = new Mage("m3", 2, 3.0);
        Mage m4 = new Mage("m4", 7, 4.0);
        Mage m5 = new Mage("m5", 5, 1.0);
        Mage m6 = new Mage("m6", 4, 2.0);
        Mage m7 = new Mage("m7", 9, 0.0);
        Mage m8 = new Mage("m8", 4, 4.0);
        Mage m9 = new Mage("m9", 6, 6.0);
        Mage m10 = new Mage("m10", 2, 7.0);
        Mage m11 = new Mage("m11", 8, 3.0);

        Set<Mage> set1;
        Set<Mage> set2;
        Set<Mage> set3;
        Set<Mage> set4;
        Set<Mage> set5;
        Set<Mage> set6;

        switch (input){
            case "1":
                set1 = new HashSet<Mage>();
                set2 = new HashSet<Mage>();
                set3 = new HashSet<Mage>();
                set4 = new HashSet<Mage>();
                set5 = new HashSet<Mage>();
                set6 = new HashSet<Mage>();
                break;
            case "2":
                set1 = new TreeSet<Mage>();
                set2 = new TreeSet<Mage>();
                set3 = new TreeSet<Mage>();
                set4 = new TreeSet<Mage>();
                set5 = new TreeSet<Mage>();
                set6 = new TreeSet<Mage>();
                break;
            default:
                set1 = new TreeSet<Mage>(mageComparator);
                set2 = new TreeSet<Mage>(mageComparator);
                set3 = new TreeSet<Mage>(mageComparator);
                set4 = new TreeSet<Mage>(mageComparator);
                set5 = new TreeSet<Mage>(mageComparator);
                set6 = new TreeSet<Mage>(mageComparator);
                break;
        }


        set1.add(m1);
        set1.add(m5);
        set1.add(m6);

        set2.add(m2);
        set2.add(m3);
        set2.add(m4);

        set3.add(m7);

        set4.add(m8);

        set5.add(m10);
        set5.add(m11);

        set6.add(m9);

        m1.setApprentices(set2);
        m5.setApprentices(set3);
        m6.setApprentices(set4);
        m2.setApprentices(set5);
        m8.setApprentices(set6);


        //writing
        for (Mage mage : set1){
            writing(mage, 1);
        }

        Map<String, Integer> map = createMap(input);
        for (Mage mage1 : set1) {
            countStatistics(map, mage1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void writing (Mage mage, int level){
        for (int  i = 0; i < level; i ++){
            System.out.print('-');
        }
        System.out.println(mage.toString());
        if (mage.getApprentices() != null){
            for (Mage mage1 : mage.getApprentices()){
                writing(mage1, level+1);
            }
        }
    }

    public static Map<String, Integer> createMap (String input){
        Map<String, Integer> map;
        switch (input){
            case "1":
                map = new HashMap<String, Integer>();
                break;
            default:
                map = new TreeMap<String, Integer>();
                break;
        }
        return map;
    }

    public static int countChildren(Mage mage){
        int counter = 0;
        if (mage.getApprentices() != null){
            counter += mage.getApprentices().size();
            for (Mage mage1 : mage.getApprentices()) {
                counter += countChildren(mage1);
            }
        }
        return counter;
    }

    public static void countStatistics(Map<String, Integer> map, Mage mage){
        map.put(mage.getName(), countChildren(mage));
        if (mage.getApprentices() != null) {
            for (Mage mage1 : mage.getApprentices()) {
                countStatistics(map, mage1);
            }
        }
    }

}
