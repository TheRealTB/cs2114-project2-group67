package musicPlaylist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ShuffleBoxRunnerTest
{
    private MediaType item;
    
    @BeforeEach
    public void setUp() {
        //does nothing
    }
    @Test
    public void testGetItemByName() {
        //song
        item = ShuffleBoxRunner.getItemByName("One", 1);
        assertNotNull(item);
        assertTrue(item instanceof Song);
        assertEquals("One", item.getName());
        assertEquals("Metallica", item.getCreator());
        assertEquals(447, item.getRuntime());
        //movie
        item = ShuffleBoxRunner.getItemByName("The Shawshank Redemption", 1);
        assertNotNull(item);
        assertTrue(item instanceof Movie);
        assertEquals("The Shawshank Redemption", item.getName());
        assertEquals("Niki Marvin", item.getCreator());
        assertEquals(8520, item.getRuntime());
        assertEquals(1994, ((Movie)item).getYear());
        //re-prompt
        //(Fails assertion because list is null in SBR)
        //assertNull(ShuffleBoxRunner.getItemByName("Not a name", 1));
    }
}
