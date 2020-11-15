package usantatecla.draughts.models;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class DraughtTest extends AbstractPieceTest {

    @Test
    public void givenDraughtWhenIsCorrectDiagonalMovementWithAnyErrorThenShouldReturnThatError(){
        Coordinate blackOrigin = new CoordinateBuilder().row(0).column(1).build();
        Coordinate blackTarget = new CoordinateBuilder().row(2).column(3).build();

        assertThat(blackPiece.isCorrectDiagonalMovement(
                eatenPieces(0), 0, blackOrigin, blackOrigin),
                is(Error.NOT_ADVANCED));    // TODO: Fix code to pass this test on green.

        assertThat(blackPiece.isCorrectDiagonalMovement(
                eatenPieces(2), pair(0), blackOrigin, blackTarget),
                is(Error.TOO_MUCH_EATINGS));

        assertNull(blackPiece.isCorrectDiagonalMovement(
                eatenPieces(0), pair(0), blackOrigin, blackTarget));

    }

    @Test
    public void givenPieceWhenGetCodeThenShouldReturnColorCodeProperly(){
        assertThat(blackPiece.getCode(), is("N"));
        assertThat(whitePiece.getCode(), is("B"));
    }

    @Override
    protected Piece createPiece(Color color){
        return new Draught(color);
    }

}
