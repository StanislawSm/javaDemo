import classes.Controller;
import classes.Mage;
import classes.Repository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ControllerTest {

    @Test
    public void removeExistingMageRetDone(){
        //Given
        Repository repository = mock(Repository.class);
        Controller controller = new Controller(repository);
        controller.save("as", 3);

        //When
        String result = controller.delete("as");

        //Then
        assertEquals("Should be done", "done", result);
    }

    @Test
    public void removeNonExistingMageRetNotFound(){
        //Given
        Repository repository = mock(Repository.class);
        Mockito.doThrow(IllegalArgumentException.class).when(repository).delete(anyString());
        Controller controller = new Controller(repository);

        //When
        String result = controller.delete("as");

        //Then
        assertEquals("Should be not found", "not found", result);
    }

    @Test
    public void findNonExistingMageRetNotFound(){
        //Given
        Repository repository = mock(Repository.class);
        when(repository.find(anyString())).thenReturn(Optional.empty());
        Controller controller = new Controller(repository);

        //When
        String result = controller.find("as");

        //Then
        assertEquals("Should be not found", "not found", result);
    }

    @Test
    public void findExistingMageRetString(){
        //Given
        Repository repository = mock(Repository.class);
        Optional<Mage> mage = Optional.of(new Mage("as", 2));
        when(repository.find("as")).thenReturn(mage);
        Controller controller = new Controller(repository);

        //When
        String result = controller.find("as");

        //Then
        assertEquals("Should be some String", mage.toString(), result);
    }

    @Test
    public void saveNonExistingMageRetDone(){
        //Given
        Repository repository = mock(Repository.class);
        Controller controller = new Controller(repository);

        //When
        String result = controller.save("as", 2);

        //Then
        verify(repository, times(1)).save(any());
        assertEquals("Should be done", "done", result);
    }


    @Test
    public void saveExistingMageRetBadRequest(){
        //Given
        Repository repository = mock(Repository.class);
        Mockito.doThrow(IllegalArgumentException.class).when(repository).save(any());
        Controller controller = new Controller(repository);

        //When
        String result = controller.save("as", 2);

        //Then
        assertEquals("Should be bad request", "bad request", result);
    }


}
