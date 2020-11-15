package usantatecla.draughts.models;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CoodinateTest {

    @Test(expected = AssertionError.class)
    public void givenCoordinateWhenGetInstanceWithNullThenShouldThrowAssertionError(){
        Coordinate.getInstance(null);
    }

    @Test
    public void givenCoordinateWhenGetInstanceWithInputBelowTheLimitShouldReturnNullCoordinate(){
        assertNull(Coordinate.getInstance("01"));
        assertNull(Coordinate.getInstance("10"));
    }

    @Test
    public void givenCoordinateWhenGetInstanceWithInputOverTheLimitShouldReturnNullCoordinate(){
        assertNull(Coordinate.getInstance("98"));
        assertNull(Coordinate.getInstance("89"));
    }

    @Test
    public void givenCoordinateWhenGetInstanceWithWrongFormatShouldReturnNullCoordinate(){
        assertNull(Coordinate.getInstance("ab"));
    }

    @Test
    public void givenCoordinateWhenGetInstanceWithRightFormatShouldReturnProperCoordinate(){
        assertThat(Coordinate.getInstance("81"),
                is(new CoordinateBuilder().row(7).column(0).build()));
    }

    @Test(expected = AssertionError.class)
    public void givenCoordinateWhenGetDirectionWithNullCoordinateThenShouldThrowAssertionError(){
        new CoordinateBuilder().build().getDirection(null);
    }

    @Test
    public void givenCoordinateWhenGetDirectionWithSameCoordinateThenShouldReturnNull(){
        Coordinate coordinate = new CoordinateBuilder().build();
        assertNull(coordinate.getDirection(coordinate));
    }

    @Test
    public void givenCoordinateWhenGetDirectionWithNotDiagonalCoordinateThenShouldReturnNull(){
        Coordinate source = new CoordinateBuilder().row(3).column(4).build();
        Coordinate target = new CoordinateBuilder().row(4).column(4).build();

        assertNull(source.getDirection(target));
    }

    @Test
    public void givenCoordinateWhenGetDirectionNotDiagonalCoordinateThenShouldReturnProperDirection(){
        Coordinate source = new CoordinateBuilder().row(3).column(4).build();
        Coordinate target = new CoordinateBuilder().row(4).column(5).build();

        assertThat(source.getDirection(target), is(Direction.NE));
    }

    @Test(expected = AssertionError.class)
    public void givenCoordinateWhenGetDiagonalDistanceWithNotDiagonalCoordinateThenShouldThrowAssertionError(){
        Coordinate source = new CoordinateBuilder().row(6).column(7).build();
        Coordinate target = new CoordinateBuilder().row(7).column(7).build();

        source.getDiagonalDistance(target);
    }

    @Test
    public void givenCoordinateWhenGetDiagonalDistanceWithRightDiagonalCoordinateThenShouldReturnDistance(){
        Coordinate source = new CoordinateBuilder().row(6).column(7).build();
        Coordinate target = new CoordinateBuilder().row(7).column(8).build();

        assertThat(source.getDiagonalDistance(target), is(1));
    }

    @Test
    public void givenCoordinateWhenGetDiagonalDistanceWithLeftDiagonalCoordinateThenShouldReturnDistance(){
        Coordinate source = new CoordinateBuilder().row(7).column(8).build();
        Coordinate target = new CoordinateBuilder().row(6).column(7).build();

        assertThat(source.getDiagonalDistance(target), is(1));
    }

    @Test(expected = AssertionError.class)
    public void givenCoordinateWhenGetBetweenDiagonalCoordinatesWithNotDiagonalThenShouldReturnEmptyList(){
        assertTrue(new CoordinateBuilder().row(0).column(1).build()
                .getBetweenDiagonalCoordinates(new CoordinateBuilder().row(1).column(1).build())
                .isEmpty());
    }

    @Test
    public void givenCoordinateWhenGetBetweenDiagonalCoordinatesWithTargetAtDistanceOneThenShouldReturnEmptyList(){
        assertTrue(new CoordinateBuilder().row(0).column(1).build()
                .getBetweenDiagonalCoordinates(new CoordinateBuilder().row(1).column(2).build())
                .isEmpty());
    }

    @Test
    public void givenCoordinateWhenGetBetweenDiagonalCoordinatesWithTargetAtDistanceTwoThenShouldReturnSingleCoordinate(){
        List<Coordinate> coordinatesBetweenDiagonal = new CoordinateBuilder().row(0).column(1).build()
                .getBetweenDiagonalCoordinates(new CoordinateBuilder().row(2).column(3).build());
        List<Coordinate> expectedCoordinates = Collections.singletonList(
                new CoordinateBuilder().row(1).column(2).build());

        assertThat(coordinatesBetweenDiagonal, is(expectedCoordinates));
    }

    @Test
    public void givenCoordinateWhenGetDiagonalCoordinatesWithSourceAtCornerThenShouldReturnJustOneCoordinate(){
        Coordinate source = new CoordinateBuilder().row(0).column(1).build();
        List<Coordinate> expectedCoordinates = Collections.singletonList(
                new CoordinateBuilder().row(2).column(3).build());

        assertThat(source.getDiagonalCoordinates(2), is(expectedCoordinates));
    }

    @Test
    public void givenCoordinateWhenGetDiagonalCoordinatesWithSourceSurroundedByEmptySpaceThenShouldReturnFourCoordinates(){
        Coordinate source = new CoordinateBuilder().row(2).column(2).build();
        List<Coordinate> expectedCoordinates = Arrays.asList(
                new CoordinateBuilder().row(1).column(1).build(),
                new CoordinateBuilder().row(1).column(3).build(),
                new CoordinateBuilder().row(3).column(1).build(),
                new CoordinateBuilder().row(3).column(3).build());

        List<Coordinate> targets = source.getDiagonalCoordinates(1);

        assertThat(targets.size(), is(expectedCoordinates.size()));
        for(Coordinate expectedCoordinate : expectedCoordinates){
            assertTrue(targets.contains(expectedCoordinate));
        }
    }

    @Test
    public void givenCoordinateWhenIsBlackThenShouldReturnTrue(){
        assertTrue(new CoordinateBuilder().row(0).column(1).build().isBlack());
        assertFalse(new CoordinateBuilder().row(1).column(1).build().isBlack());
    }

    @Test
    public void givenCoordinateWhenIsLastThenShouldReturnTrue(){
        assertTrue(new CoordinateBuilder().row(7).build().isLast());
        assertFalse(new CoordinateBuilder().row(6).build().isLast());
    }

    @Test
    public void givenCoordinateWhenIsFirstThenShouldReturnTrue(){
        assertTrue(new CoordinateBuilder().row(0).build().isFirst());
        assertFalse(new CoordinateBuilder().row(1).build().isFirst());
    }

}
