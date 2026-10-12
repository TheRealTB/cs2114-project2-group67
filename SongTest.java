package musicPlaylist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

// -------------------------------------------------------------------------
/**
 * Tests the song class
 * 
 * @author Tyler
 * @version Sep 26, 2026
 */
public class SongTest {
    private Song song;
    private Song exactSame;
    private Song isNull;
    private Song allSame;
    private Song sameName;
    private Song sameRuntime;
    private Song sameArtist;
    private Object object;

    // ----------------------------------------------------------
    /**
     * Sets up songs to test the song class
     */
    @BeforeEach
    public void setUp() {
        song = new Song("Song", "None", 180);
        exactSame = song;
        isNull = null;
        allSame = new Song("Song", "None", 180);
        sameName = new Song("Song", "Someone", 120);
        sameRuntime = new Song("Dance", "Someone", 180);
        sameArtist = new Song("Dance", "None", 120);
        object = new Object();
    }

    // ----------------------------------------------------------
    /**
     * Tests the overridden equals method
     */
    @Test
    public void testEquals() {
        assertTrue(song.equals(song));
        assertTrue(song.equals(exactSame));
        assertTrue(song.equals(allSame));
        assertEquals(false, song.equals(isNull));
        assertEquals(false, song.equals(sameName));
        assertEquals(false, song.equals(sameRuntime));
        assertEquals(false, song.equals(sameArtist));
        assertEquals(false, song.equals(object));
    }

    // ----------------------------------------------------------
    /**
     * Tests the getName method
     */
    @Test
    public void testGetName() {
        assertEquals("Song", song.getName());
    }

    // ----------------------------------------------------------
    /**
     * Tests the getArtist method
     */
    @Test
    public void testGetArtist() {
        assertEquals("None", song.getCreator());
    }

    // ----------------------------------------------------------
    /**
     * Tests the getRuntime method
     */
    @Test
    public void testGetRuntime() {
        assertEquals(180, song.getRuntime());
    }

    // ----------------------------------------------------------
    /**
     * Tests the setName method
     */
    @Test
    public void testSetName() {
        song.setName("Dance");
        assertEquals("Dance", song.getName());
    }

    // ----------------------------------------------------------
    /**
     * Tests the setArtist method
     */
    @Test
    public void testSetArtist() {
        song.setCreator("Someone");
        assertEquals("Someone", song.getCreator());
    }

    // ----------------------------------------------------------
    /**
     * Tests the setRuntime method
     */
    @Test
    public void testSetRuntime() {
        song.setRuntime(120);
        assertEquals(120, song.getRuntime());
    }

    // ----------------------------------------------------------
    /**
     * Tests the toString method
     */
    @Test
    public void testToString() {
        assertEquals("Song,None,180", song.toString());
    }
}
