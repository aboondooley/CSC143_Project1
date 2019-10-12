package test;

import org.junit.Before;
import org.junit.Test;
import world.Home;
import world.Residential;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class HomeTest {
    Residential home;

    @Before
    public void setUp() {
        home = new Home("Abbey Road");
    }

    @Test
    public void HomeConstructorTest() {
        Home home = new Home("Abbey Road");
        assertTrue(true);
    }

    @Test
    public void HomeEmptyTest() {
        assertEquals(Arrays.asList(), home.getOccupants());
    }

    @Test
    public void HomeMoveInTest() {
        home.moveIn("John");
        assertEquals(Arrays.asList("John"), home.getOccupants());
    }

    @Test
    public void HomeIsOccupiedTest() {
        home.moveIn("John");
        home.moveIn("Paul");
        assertEquals(Arrays.asList("John"), home.getOccupants());
    }

    @Test
    public void HomeMoveOutTest() {
        home.moveIn("John");
        home.moveOut("John");
        assertEquals(Arrays.asList(), home.getOccupants());
    }

    @Test
    public void HomeMoveOutWrongResidentTest() {
        home.moveIn("John");
        home.moveOut("Paul");
        assertEquals(Arrays.asList("John"), home.getOccupants());
    }

    @Test
    public void HomeMoveInAndOutTest() {
        home.moveIn("John");
        assertEquals(Arrays.asList("John"), home.getOccupants());
        home.moveOut("John");
        assertEquals(Arrays.asList(), home.getOccupants());
        home.moveIn("Paul");
        assertEquals(Arrays.asList("Paul"), home.getOccupants());
        home.moveOut("Paul");
        assertEquals(Arrays.asList(), home.getOccupants());
    }
}
