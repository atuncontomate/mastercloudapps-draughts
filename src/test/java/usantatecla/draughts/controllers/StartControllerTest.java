package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.StateValue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartControllerTest {

    private Game game;
    private State state;
    private StartController sut;

    @Before
    public void before(){
        game = mock(Game.class);
        state = new State();
        sut = new StartController(game, state);
    }

    @Test(expected = AssertionError.class)
    public void givenSUTConstructorWhenNullGameShouldThrowAssertionError(){
        new StartController(null, state);
    }

    @Test(expected = AssertionError.class)
    public void givenSUTConstructorWhenNullStateShouldThrowAssertionError(){
        new StartController(game, null);
    }

    @Test
    public void givenSUTWhenStartThenStateShouldAdvanceToTheNextPhase(){
        sut.start();
        assertThat(state.getValueState(), is(StateValue.IN_GAME));
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
