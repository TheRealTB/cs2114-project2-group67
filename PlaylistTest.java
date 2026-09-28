package musicPlaylist;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
// -------------------------------------------------------------------------
/**
 *  Tests the playlist class
 * 
 *  @author Tyler
 *  @version Sep 27, 2026
 */
public class PlaylistTest {
    private Playlist p;
    // ----------------------------------------------------------
    /**
     * Sets up playlist to test
     */
    @BeforeEach
    public void setUp() {
        p=new Playlist();
        p.addSongToFront("Song 5", "Artist", 5);
        p.addSongToFront("Song 4", "Artist", 4);
        p.addSongToFront("Song 3", "Artist", 3);
        p.addSongToFront("Song 2", "Artist", 2);
        p.addSongToFront("Song 1", "Artist", 1);
        
    }
    // ----------------------------------------------------------
    /**
     * Test: Returns the first node
     */
    @Test
    public void testGetFirstNode() {
        assertTrue(p.getFirstNode().getData().getName().equals("Song 1"));
    }
    // ----------------------------------------------------------
    /**
    * Test: Sets the first node of the playlsit and updates the number of entries
    */
    @Test
    public void testSetFirstNode() {
        Song s = new Song("Name", "Artist", 0);
        Node<Song> n = new Node<Song>(s);
        p.setFirstNode(n);
        assertTrue(n.equals(p.getFirstNode()));
        
    }
    
    // ----------------------------------------------------------
    /**
     * Test: How many songs in playlist
     */
    @Test
    public void testGetNumEntries() {
        assertEquals(5, p.getNumEntries());
    }
    
    // ----------------------------------------------------------
    /**
     * Test: Sets the number of entries to the number of songs in the playlist
     */
    @Test
    public void testSetNumEntries() {
        p.addSongToFront("Name", "Artist", 0);
        assertEquals(6, p.setNumEntries());
    }
    
    // ----------------------------------------------------------
    /**
     * Test: Checks if the playlist contains a song with a specific name
     */
    @Test
    public void testContains() {
        assertTrue(p.contains("Song 5"));
    }
    
    // ----------------------------------------------------------
    /**
     * Test: Adds a song to the front of the playlist
     */
    @Test
    public void testAddSongToFront() {
        assertTrue(p.addSongToFront("Name", "Artist", 0));
        assertEquals(false, p.addSongToFront(null, "Artist", 0));
        assertEquals(false, p.addSongToFront("Song 1", "Artist", 0));
    }
    
    // ----------------------------------------------------------
    /**
     * Test: Adds a song to end of playlsit
     */
    @Test
    public void testAddSongToEnd() {
        assertTrue(p.addSongToEnd("Name", "Artist", 0));
        assertEquals(false, p.addSongToEnd(null, "Artist", 0));
        assertEquals(false, p.addSongToEnd("Name", "Artist", 0));
       }
    
    // ----------------------------------------------------------
    /**
     * Test: Adds a song to a specific index (0 = firstNode) of the playlist
     * Probably very janky but this should work please don't modify
     */
    @Test
    public void testAddSongToIndex() {
        //index = 0
        assertTrue(p.addSongToIndex("Name", "Artist", 0, 0));
        //index between 1 & numEntries-2
        assertTrue(p.addSongToIndex("Name 1", "Artist", 0, 2));
        //index at numEntries-1
        assertTrue(p.addSongToIndex("Name 2", "Artist", 0, 6));
        //name is null
        assertEquals(false, p.addSongToIndex(null, "Artist", 0, 2));
        //song already present
        assertEquals(false, p.addSongToIndex("Song 1", "Artist", 0, 2));
    }
 // ----------------------------------------------------------
    /**
     * Test: Removes the first node and sets firstNode.next() as first node
     */
    @Test
    public void testRemoveSongAtFront() {
        assertTrue(p.removeSongAtFront());
    }
    // ----------------------------------------------------------
    /**
     * Test: Removes the last node in the playlist
     */
    @Test
    public void testRemoveSongAtEnd() {
        assertTrue(p.removeSongAtEnd());
    }
    // ----------------------------------------------------------
    /**
     * Test: Removes a node at a specified index (firstNode=0)
     */
    @Test
    public void testRemoveSongAtIndex() {
      //index = 0
        assertTrue(p.removeSongAtIndex(0));
        //index between 1 & numEntries-2
        assertTrue(p.removeSongAtIndex(1));
        //index at numEntries-1
        assertTrue(p.removeSongAtIndex(2));
        //name is null
        assertEquals(false, p.removeSongAtIndex(-1));
        //song already present
        assertEquals(false, p.removeSongAtIndex(4));
    }
    // ----------------------------------------------------------
    /**
     * Test: Checks if a song with a specific name is in playlsit and removes it
     */
    @Test
    public void testRemoveSongByName() {
        assertTrue(p.removeSongByName("Song 1"));
        assertTrue(p.removeSongByName("Song 3"));
        assertEquals(false, p.removeSongByName(null));
        assertEquals(false, p.removeSongByName("Not here"));
    }
    // ----------------------------------------------------------
    /**
     * Test: String of all songs in playlsit
     */
    @Test
    public void testToString() {
        assertEquals("Playlist containing 5 songs: Song 1 by: Artist, runtime: 1"
            + " seconds, Song 2 by: Artist, runtime: 2 seconds, Song 3 by:"
            + " Artist, runtime: 3 seconds, Song 4 by: Artist, runtime: 4 "
            + "seconds, Song 5 by: Artist, runtime: 5 seconds", p.toString());
        for(int i = 0; i < 5; i++) {
            p.removeSongAtFront();
        }
        assertEquals("Playlist is empty", p.toString());
        
    }
}
