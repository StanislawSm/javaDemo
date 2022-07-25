package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Repository {
    private Collection<Mage> collection;

    public Repository() {
        this.collection = new ArrayList<Mage>();
    }

    public Collection<Mage> getCollection() {
        return collection;
    }

    public Optional<Mage> find(String name) {
        for(Mage mage : collection){
            if(mage.getName().equals(name)){
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }
    public void delete(String name) {
        Optional<Mage> mageToDelete = this.find(name);
        if(mageToDelete.isEmpty()){
            throw new IllegalArgumentException();
        } else {
            Mage mage = mageToDelete.get();
            collection.remove(mage);
        }
    }
    public void save(Mage mage) {
        Optional<Mage> mageFound = this.find(mage.getName());
        if(mageFound.isEmpty()){
            collection.add(mage);
        } else {
            throw new IllegalArgumentException();
        }
    }


}
