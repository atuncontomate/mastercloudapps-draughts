package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.StateValue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ResumeControllerTest {

    private Game game;
    private State state;
    private ResumeController sut;

    @Before
    public void before(){
        game = mock(Game.class);
        state = new State();
        sut = new ResumeController(game, state);
    }

    @Test(expected = AssertionError.class)
    public void givenSUTConstructorWhenNullGameShouldThrowAssertionError(){
        new ResumeController(null, state);
    }

    @Test(expected = AssertionError.class)
    public void givenSUTConstructorWhenNullStateShouldThrowAssertionError(){
        new ResumeController(game, null);
    }

    @Test
    public void givenSUTWhenResetThenStateAndGameShouldBeReset(){
        sut.reset();
        assertThat(state.getValueState(), is(StateValue.INITIAL));
        verify(game).reset();
    }

    @Test(expected = AssertionError.class)
    public void givenSUTWhenAcceptWithNullVisitorThenShouldThrowAssertionError(){
        sut.accept(null);
    }

    @Test
    public void givenSUTWhenAcceptThenControllerVisitorShouldVisitSUT(){
        InteractorControllersVisitor visitor = mock(InteractorControllersVisitor.class);
        sut.accept(visitor);
        verify(visitor).visit(sut);
    }
}
