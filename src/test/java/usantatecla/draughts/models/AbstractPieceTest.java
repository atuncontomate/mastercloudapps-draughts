package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public abstract class AbstractPieceTest {

    protected Piece blackPiece;
    protected Piece whitePiece;

    @Before
    public void init(){
        blackPiece = createPiece(Color.BLACK);
        whitePiece = createPiece(Color.WHITE);
    }

    @Test(expected = AssertionError.class)
    public void givenPieceWhenIsCorrectMovementWithFirstCoordinateFromPairThenShouldThrowAssertionError(){
        blackPiece.isCorrectMovement(
                Collections.singletonList(whitePiece), pair(0), null, new CoordinateBuilder().build());
    }

    @Test(expected = AssertionError.class)
    public void givenPieceWhenIsCorrectMovementWithSecondCoordinateFromPairThenShouldThrowAssertionError(){
        blackPiece.isCorrectMovement(
                Collections.singletonList(whitePiece), pair(0), new CoordinateBuilder().build(), null);
    }

    @Test
    public void givenPieceWhenIsCorrectMovementWithNotDiagonalCoordinatesThenShouldReturnNotDiagonalError(){
        assertThat(blackPiece.isCorrectMovement(
                Collections.singletonList(whitePiece), pair(0), new CoordinateBuilder().row(4).build(), new CoordinateBuilder().row(6).build()),
                is(Error.NOT_DIAGONAL));
    }

    @Test
    public void givenPieceWhenIsCorrectMovementWithSameColorEatenPieceThenShouldReturnColleagueEatingError(){
        assertThat(blackPiece.isCorrectMovement(
                Collections.singletonList(blackPiece), pair(0), new CoordinateBuilder().row(0).column(1).build(), new CoordinateBuilder().row(2).column(3).build()),
                is(Error.COLLEAGUE_EATING));
    }

    @Test
    public void givenPieceWhenIsCorrectMovementThenShouldReturnNull(){
        assertNull(blackPiece.isCorrectMovement(
                Collections.singletonList(whitePiece), pair(0), new CoordinateBuilder().row(0).column(1).build(), new CoordinateBuilder().row(2).column(3).build()));
    }

    @Test
    public void givenPieceWhenIsLimitThenShouldReturnTrue(){
        Coordinate lowerCoordinate = new CoordinateBuilder().row(0).build();
        Coordinate upperCoordinate = new CoordinateBuilder().row(7).build();

        assertTrue(blackPiece.isLimit(upperCoordinate));
        assertFalse(blackPiece.isLimit(lowerCoordinate));

        assertTrue(whitePiece.isLimit(lowerCoordinate));
        assertFalse(whitePiece.isLimit(upperCoordinate));
    }

    @Test(expected = AssertionError.class)
    public void givenPieceWhenIsAdvancedWithNullOriginThenShouldThrowAssertionError(){
        blackPiece.isAdvanced(null, new CoordinateBuilder().build());
    }

    @Test(expected = AssertionError.class)
    public void givenPieceWhenIsAdvancedWithNullTargetThenShouldThrowAssertionError(){
        blackPiece.isAdvanced(new CoordinateBuilder().build(),null);
    }

    @Test
    public void givenPieceWhenIsAdvancedWithProperBlackCoordinatesThenShouldReturnTrue(){
        Coordinate blackOrigin = new CoordinateBuilder().row(0).column(1).build();
        Coordinate blackTarget = new CoordinateBuilder().row(1).column(2).build();

        assertTrue(blackPiece.isAdvanced(blackOrigin, blackTarget));
        assertFalse(blackPiece.isAdvanced(blackTarget, blackOrigin));
        assertFalse(blackPiece.isAdvanced(blackOrigin, blackOrigin));
    }

    @Test
    public void givenPieceWhenIsAdvancedWithProperWhiteCoordinatesThenShouldReturnTrue(){
        Coordinate whiteOrigin = new CoordinateBuilder().row(1).column(1).build();
        Coordinate whiteTarget = new CoordinateBuilder().row(0).column(0).build();

        assertTrue(whitePiece.isAdvanced(whiteOrigin, whiteTarget));
        assertFalse(whitePiece.isAdvanced(whiteTarget, whiteOrigin));
        assertFalse(whitePiece.isAdvanced(whiteOrigin, whiteOrigin));
    }

    protected abstract Piece createPiece(Color color);

    protected int eatenPieces(int numberPieces){
        return numberPieces;
    }

    protected int pair(int pairNumber){
        return pairNumber;
    }
}
