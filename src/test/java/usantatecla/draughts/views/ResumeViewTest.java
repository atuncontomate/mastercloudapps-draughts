package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.utils.YesNoDialog;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ResumeViewTest {

    @Mock
    private YesNoDialog yesNoDialog;

    @Mock
    private ResumeController resumeController;

    @InjectMocks
    private ResumeView sut;

    @Before
    public void before(){
        initMocks(this);
    }

    @Test(expected = AssertionError.class)
    public void givenSUTWhenInteractWithNullControllerThenShouldThrowAssertionError(){
        sut.interact(null);
    }

    @Test
    public void givenSUTWhenInteractWithYesInputMsgThenShouldResetTheGame(){
        when(yesNoDialog.read(anyString())).thenReturn(true);
        sut.interact(resumeController);
        verify(resumeController).reset();
    }

    @Test
    public void givenSUTWhenInteractWithNoInputMsgThenShouldFinishTheGame(){
        when(yesNoDialog.read(anyString())).thenReturn(false);
        sut.interact(resumeController);
        verify(resumeController).next();
    }
}
