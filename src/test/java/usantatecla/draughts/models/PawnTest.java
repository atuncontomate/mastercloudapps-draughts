package usantatecla.draughts.models;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class PawnTest extends AbstractPieceTest {

    @Test
    public void givenPawnWhenIsCorrectDiagonalMovementWithAnyErrorThenShouldReturnThatError(){
        Coordinate blackOrigin = new CoordinateBuilder().row(0).column(1).build();
        Coordinate blackTarget = new CoordinateBuilder().row(2).column(3).build();

        assertThat(blackPiece.isCorrectDiagonalMovement(
                eatenPieces(0), 0, blackOrigin, blackOrigin),
                is(Error.NOT_ADVANCED));

        assertThat(blackPiece.isCorrectDiagonalMovement(eatenPieces(0), pair(0), blackOrigin, blackTarget),
                is(Error.WITHOUT_EATING));

        assertThat(blackPiece.isCorrectDiagonalMovement(eatenPieces(0), pair(0), blackOrigin,
                new CoordinateBuilder()
                        .row(blackTarget.getRow() +1)
                        .column(blackTarget.getColumn() +1)
                        .build()),
                is(Error.TOO_MUCH_ADVANCED));

        assertNull(blackPiece.isCorrectDiagonalMovement(eatenPieces(1), pair(0), blackOrigin, blackTarget));
    }

    @Test
    public void givenPieceWhenGetCodeThenShouldReturnColorCodeProperly(){
        assertThat(blackPiece.getCode(), is("n"));
        assertThat(whitePiece.getCode(), is("b"));
    }

    @Override
    protected Piece createPiece(Color color){
        return new Pawn(color);
    }

}
