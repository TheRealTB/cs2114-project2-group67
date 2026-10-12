package musicPlaylist;

import student.TestCase;

public class ShuffleBoxRunnerTest extends TestCase
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................
    public void testGetItemByNameSong() {
        MediaType item = ShuffleBoxRunner.getItemByName("Bohemian Rhapsody", 1);
        assertNotNull(item);
        assertTrue(item instanceof Song);
        
        Song song = (Song) item;
        assertEquals("Bohemian Rhapsody", song.getName());
        assertEquals("Queen", song.getCreator());
        assertEquals(355, song.getRuntime());
    }

    // ----------------------------------------------------------
    /**
     * Test getItemByName with a valid movie string.
     */
    public void testGetItemByNameMovie() {
        MediaType item = ShuffleBoxRunner.getItemByName("The Shawshank Redemption", 1);
        assertNotNull(item);
        assertTrue(item instanceof Movie);
        
        Movie movie = (Movie) item;
        assertEquals("The Shawshank Redemption", movie.getName());
        assertEquals(8520, movie.getRuntime());
    }

    // ----------------------------------------------------------
    /**
     * Test getItemByName with an invalid name to ensure 
     * it handles default/error prompting gracefully.
     */
    public void testGetItemByNameInvalid() {
        // Passing an unrecognized name triggers the default case, 
        // which calls prompt(i) and returns null.
        MediaType item = ShuffleBoxRunner.getItemByName("NotARealSong", 1);
        assertNull(item);
    }
}
    //~Public  Methods ........................................................


