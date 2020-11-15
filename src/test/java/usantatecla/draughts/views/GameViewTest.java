package usantatecla.draughts.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.InteractorController;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Pawn;
import usantatecla.draughts.models.Piece;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameViewTest {

    private static final int DIMENSION = 6;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private InteractorController interactorController;

    @InjectMocks
    private GameView sut;

    @Before
    public void before(){
        System.setOut(new PrintStream(outputStreamCaptor));
        initMocks(this);
    }

    @Test(expected = AssertionError.class)
    public void givenSUTWhenWriteWithNullControllerShouldThrowAssertionError(){
        sut.write(null);
    }

    @Test
    public void givenSUTWhenWriteWithNullPiecesShouldPrintEmptyBoard(){
        when(interactorController.getDimension()).thenReturn(6);
        sut.write(interactorController);
        assertThat(outputStreamCaptor.toString(), is(emptyBoard()));
    }

    @Test
    public void givenSUTWhenWriteWithWhitePawnInAllCordinatesShouldPringFullBoard(){
        Piece piece = mock(Pawn.class);
        when(piece.getCode()).thenReturn("n");

        when(interactorController.getDimension()).thenReturn(DIMENSION);
        when(interactorController.getPiece(any(Coordinate.class))).thenReturn(piece);

        sut.write(interactorController);
        assertThat(outputStreamCaptor.toString(), is(fullBoard()));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    private String emptyBoard(){
        return  " 123456\n" +
                "1      1\n" +
                "2      2\n" +
                "3      3\n" +
                "4      4\n" +
                "5      5\n" +
                "6      6\n" +
                " 123456\n";
    }

    private String fullBoard(){
        return  " 123456\n" +
                "1nnnnnn1\n" +
                "2nnnnnn2\n" +
                "3nnnnnn3\n" +
                "4nnnnnn4\n" +
                "5nnnnnn5\n" +
                "6nnnnnn6\n" +
                " 123456\n";
    }
}
