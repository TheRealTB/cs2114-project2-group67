// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- tuetranminh
// LLM Statement:
//I have not used any assistance for the assignment beyond course resources and staff.
package musicPlaylist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

// -------------------------------------------------------------------------
/**
 * Tests the playlist class
 * 
 * @author Tyler, Tue Tran
 * @version Sep 27, 2026
 */
public class PlaylistTest {
    private Playlist<Song> p;

    // ----------------------------------------------------------
    /**
     * Sets up playlist to test
     */
    @BeforeEach
    public void setUp() {
        p = new Playlist<Song>();
        p.add(new Song("Song 5", "Artist", 5));
        p.add(new Song("Song 4", "Artist", 4));
        p.add(new Song("Song 3", "Artist", 3));
        p.add(new Song("Song 2", "Artist", 2));
        p.add(new Song("Song 1", "Artist", 1));

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
    public void testSize() {
        assertEquals(5, p.size());
    }

    // ----------------------------------------------------------
    /**
     * Test: Checks if the playlist contains a song with a specific name
     */
    @Test
    public void testContains() {
        assertTrue(p.contains(new Song("Song 5", "Artist", 5)));
    }

    // ----------------------------------------------------------
    /**
     * Test: Adds a song to the front of the playlist
     */
    @Test
    public void testAdd() {
        assertTrue(p.add(new Song("Name", "Artist", 0)));
        // change to the assertThrown logic
        Exception thrown = null;
        try {
            p.add(null);
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Cannot add null to chain", thrown.getMessage());

        thrown = null;

    }

    // ----------------------------------------------------------
    /**
     * Test: Adds a song to end of playlsit
     */
    @Test
    public void testAddToEnd() {
        assertTrue(p.addToEnd(new Song("Name", "Artist", 0)));
        // change to the assertThrown logic
        Exception thrown = null;
        try {
            p.addToEnd(null);
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Cannot add null to chain", thrown.getMessage());

        thrown = null;
    }

    // ----------------------------------------------------------
    /**
     * Test: Removal logic
     */
    @Test
    public void testRemove() {
        assertEquals(new Song("Song 1", "Artist", 1), p.remove());
        assertEquals(false, p.remove(null));

    }

    // ----------------------------------------------------------
    /**
     * Test: Removes all songs in playlist
     */
    @Test
    public void testClear() {
        p.clear();
        assertTrue(p.isEmpty());
    }

    // ----------------------------------------------------------
    /**
     * Test: Gets the entry at given index
     */
    @Test
    public void testGet() {
        assertEquals("Song 1", p.get(0).getName());
        // test index oob exception
        Exception thrown = null;
        try {
            p.get(100);
        } catch (IndexOutOfBoundsException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Index 100 out of bounds for size 5", thrown.getMessage());

        thrown = null;
    }

    // ----------------------------------------------------------
    /**
     * Test: Is the array empty
     */
    public void testIsEmpty() {
        p.clear();
        assertTrue(p.isEmpty());
    }

    // ----------------------------------------------------------
    /**
     * Test: Creates an array with entries in playlsit
     */
    @Test
    public void testToArray() {
        Song[] testArray = new Song[p.size()];
        testArray = p.toArray(testArray);
        for (int i = 0; i < p.size(); i++) {
            assertTrue(testArray[i].equals(p.get(i)));
        }
    }

    // ----------------------------------------------------------
    /**
     * Test: String of all songs in playlsit
     */
    @Test
    public void testToString() {
        for (int i = 0; i < 4; i++) {
            p.remove();
        }
        assertEquals("Song 5,Artist,5", p.toString());
        p.remove();
        assertEquals("Playlist is empty", p.toString());
    }

    // ----------------------------------------------------------
    /**
     * Test: Shuffles the playlist
     */
    @Test
    public void testShuffle() {
        for (int i = 0; i < 10; i++) {
            p.add(new Song("Name", "Artist", i));
        }
        Song[] before = new Song[p.size()];
        before = p.toArray(before);
        // System.out.println(Arrays.toString(before));
        p.shuffle();
        Song[] after = new Song[p.size()];
        after = p.toArray(after);
        // System.out.println(Arrays.toString(after));
        boolean allNotSame = false;
        for (int i = 0; i < p.size(); i++) {
            if (!before[i].toString().equals(after[i].toString())) {
                allNotSame = true;
            }
            // genuinely pray they don't accidentally shuffle to same in testing
            assertTrue(allNotSame);
        }
    }

    // ----------------------------------------------------------
    /**
     * Test: setFirstNode rejects null
     */
    @Test
    public void testSetFirstNodeNull() {
        Exception thrown = null;
        try {
            p.setFirstNode(null);
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("First node must not be null", thrown.getMessage());
    }

    // ----------------------------------------------------------
    /**
     * Test: remove(T) on empty, missing, null, and existing entries
     */
    @Test
    public void testRemoveEntry() {
        Playlist<Song> empty = new Playlist<Song>();
        Exception thrown = null;
        try {
            empty.remove(new Song("A", "B", 1));
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Entry is not in the playlist", thrown.getMessage());

        thrown = null;
        try {
            p.remove(new Song("None", "Nobody", 1));
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Entry is not in the playlist", thrown.getMessage());

        thrown = null;
        try {
            p.remove(null);
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Entry must not be null", thrown.getMessage());

        assertTrue(p.remove(new Song("Song 3", "Artist", 3)));
        assertEquals(4, p.size());
        assertFalse(p.contains(new Song("Song 3", "Artist", 3)));
    }

    // ----------------------------------------------------------
    /**
     * Test: remove(T) removes only the first duplicate
     */
    @Test
    public void testRemoveFirstDuplicate() {
        Playlist<Song> d = new Playlist<Song>();
        d.addToEnd(new Song("A", "B", 1));
        d.addToEnd(new Song("A", "B", 1));
        assertTrue(d.remove(new Song("A", "B", 1)));
        assertEquals(1, d.size());
    }

    // ----------------------------------------------------------
    /**
     * Test: Counts how many times a song appears
     */
    @Test
    public void testCount() {
        assertEquals(1, p.count(new Song("Song 5", "Artist", 5)));
        p.add(new Song("Song 5", "Artist", 5));
        assertEquals(2, p.count(new Song("Song 5", "Artist", 5)));
        assertEquals(0, p.count(new Song("Nope", "Nobody", 1)));
        assertEquals(0, p.count(null));
    }

    // ----------------------------------------------------------
    /**
     * Test: requireNotNull accepts non-null and rejects null
     */
    @Test
    public void testRequireNotNull() {
        p.requireNotNull(new Song("A", "B", 1));
        Exception thrown = null;
        try {
            p.requireNotNull(null);
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Cannot add null to chain", thrown.getMessage());
    }

    // ----------------------------------------------------------
    /**
     * Test: toArray rejects null and too small arrays
     */
    @Test
    public void testToArrayBadInput() {
        Exception thrown = null;
        try {
            p.toArray(null);
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Array must not be null", thrown.getMessage());

        thrown = null;
        try {
            p.toArray(new Song[2]);
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
    }

    // ----------------------------------------------------------
    /**
     * Test: get rejects a negative index
     */
    @Test
    public void testGetNegativeIndex() {
        Exception thrown = null;
        try {
            p.get(-1);
        } catch (IndexOutOfBoundsException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Index -1 out of bounds for size 5", thrown.getMessage());
    }

    // ----------------------------------------------------------
    /**
     * Test: Counts entries made by an artist
     */
    @Test
    public void testGetFreq() {
        assertEquals(5, p.getFreq("Artist"));
        assertEquals(0, p.getFreq("Nobody"));
        Exception thrown = null;
        try {
            p.getFreq(null);
        } catch (IllegalArgumentException exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertEquals("Artist must not be null", thrown.getMessage());
    }
}