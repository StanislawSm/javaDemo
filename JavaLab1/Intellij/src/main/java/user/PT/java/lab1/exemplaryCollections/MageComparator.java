package user.PT.java.lab1.exemplaryCollections;

import java.util.Comparator;

public class MageComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Mage mage1 = (Mage) o1;
        Mage mage2 = (Mage) o2;
        if (mage2.getLevel() != mage1.getLevel()) {
            return mage2.getLevel() - mage1.getLevel();
        } else if (mage2.getName().equals(mage1.getName())) {
            return mage2.getName().compareTo(mage1.getName());
        } else {
            return (int)(mage2.getPower() - mage1.getPower());
        }
    }
}
