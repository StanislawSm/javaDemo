import classes.Mage;
import classes.Repository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RepositoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingMageIllegalArgumentException(){
        //Given
        Repository repository = new Repository();

        //When
        repository.delete("as");

        //Then
        //Exception thrown
    }

    @Test
    public void findNonExistingMageRetEmpty(){
        //Given
        Repository repository = new Repository();

        //When
        Optional<Mage> result = repository.find("as");

        //Then
        assert result.isEmpty() : "should be empty";
    }

    @Test
    public void findExistingMageRetOptMage(){
        //Given
        Repository repository = new Repository();
        Optional<Mage> mage = Optional.of(new Mage("as", 2));
        repository.save(mage.get());

        //When
        Optional<Mage> result = repository.find("as");

        //Then
        assertEquals("Should be equal", result, mage);
    }



    @Test(expected = IllegalArgumentException.class)
    public void saveExistingMageIllegalArgumentException(){
        //Given
        Repository repository = new Repository();
        repository.save(new Mage("as", 1));

        //When
        repository.save(new Mage("as", 1));

        //Then
        //Exception thrown
    }
}
