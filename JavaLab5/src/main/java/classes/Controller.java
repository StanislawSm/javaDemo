package classes;


import java.sql.PreparedStatement;
import java.util.Optional;

public class Controller {
    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> result = repository.find(name);
        if(result.isEmpty()){
            return "not found";
        }
        return result.toString();
    }

    public String delete(String name) {
        try{
            repository.delete(name);
            return "done";
        } catch (IllegalArgumentException ex) {
            System.err.println("Illegal argument");
        }
        return "not found";
    }

    public String save(String name, int level) {
        try{
            Mage mageToAdd = new Mage(name, level);
            repository.save(mageToAdd);
            return "done";
        } catch (IllegalArgumentException ex) {
            System.err.println("Illegal argument");
        }
        return "bad request";
    }
}
