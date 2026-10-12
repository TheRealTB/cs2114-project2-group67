
package musicPlaylist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * // -------------------------------------------------------------------------
/**
 *  Test for Movie
 * 
 *  @author Ethan
 *  @version Oct 11, 2026
 */
public class MovieTest {

    @Test
    public void testConstructorAndGetters() {
        Movie m = new Movie("Inception", "Emma Thomas", 8880, 2010);

        assertEquals("Inception", m.getName());
        assertEquals("Emma Thomas", m.getCreator());
        assertEquals(8880, m.getRuntime());
        assertEquals(2010, m.getYear());
    }

    @Test
    public void testSetters() {
        Movie m = new Movie("Old Name", "Old Producer", 100, 1990);

        m.setName("New Name");
        m.setCreator("New Producer");
        m.setRuntime(200);
        m.setReleaseYear(2020);

        assertEquals("New Name", m.getName());
        assertEquals("New Producer", m.getCreator());
        assertEquals(200, m.getRuntime());
        assertEquals(2020, m.getYear());
    }

    @Test
    public void testEqualsSameObject() {
        Movie m = new Movie("Fight Club", "Art Linson", 8340, 1999);
        assertTrue(m.equals(m));
    }

    @Test
    public void testEqualsNull() {
        Movie m = new Movie("Fight Club", "Art Linson", 8340, 1999);
        assertFalse(m.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        Movie m = new Movie("Fight Club", "Art Linson", 8340, 1999);
        assertFalse(m.equals("Fight Club"));
    }

    @Test
    public void testEqualsSameValues() {
        Movie m1 = new Movie("Fight Club", "Art Linson", 8340, 1999);
        Movie m2 = new Movie("Fight Club", "Art Linson", 8340, 1999);

        assertTrue(m1.equals(m2));
    }

    @Test
    public void testEqualsDifferentValues() {
        Movie m1 = new Movie("Fight Club", "Art Linson", 8340, 1999);
        Movie m2 = new Movie("Inception", "Emma Thomas", 8880, 2010);

        assertFalse(m1.equals(m2));
    }

    @Test
    public void testToString() {
        Movie m = new Movie("Inception", "Emma Thomas", 8880, 2010);
        String out = m.toString();

        assertEquals("Inception,Emma Thomas,8880,2010", out);
    }

    @Test
    public void testFormattedToString() {
        Movie m = new Movie("Inception", "Emma Thomas", 8880, 2010);
        String out = m.formattedToString();

        assertTrue(out.contains("Inception"));
        assertTrue(out.contains("Emma Thomas"));
        assertTrue(out.contains("2010"));
        assertTrue(out.contains("Runtime"));
    }

    @Test
    public void testRuntimeFormatterHoursMinutesSeconds() {
        Movie m = new Movie("Test", "Producer", 3665, 2020); 

        String formatted = m.runtimeFormatter();

        assertTrue(formatted.contains("1 Hour"));
        assertTrue(formatted.contains("1 Minute"));
        assertTrue(formatted.contains("5 Seconds"));
    }

    @Test
    public void testRuntimeFormatterOnlySeconds() {
        Movie m = new Movie("Test", "Producer", 45, 2020);

        String formatted = m.runtimeFormatter();

        assertTrue(formatted.contains("45 Seconds"));
    }
}
