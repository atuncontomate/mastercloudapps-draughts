package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.StartController;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class StartViewTest {

    @Mock
    private GameView gameView;

    @Mock
    private StartController startController;

    @InjectMocks
    private StartView sut;

    @Before
    public void before(){
        initMocks(this);
    }

    @Test(expected = AssertionError.class)
    public void givenSUTWhenInteractWithNullControllerThenShouldThrowAssertionError(){
        sut.interact(null);
    }

    @Test
    public void givenSUTWhenInteractThenShouldWriteBoardAndStartGame(){
        sut.interact(startController);

        verify(startController).start();
        verify(gameView).write(startController);    // TODO: Duda -> No funciona, no inyecta el mock al crear el GameView.

    }
}
