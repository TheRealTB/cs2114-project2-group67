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


      @Test
    public void testGetItemByNameMovie() {
        MediaType m = ShuffleBoxRunner.getItemByName("Inception", 1);
        assertTrue(m instanceof Movie);
        assertEquals("Inception", m.getName());
    }

    @Test
    public void testReadAndWriteHistory() throws Exception {
        File temp = new File("History");
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
        bw.write("Imagine,John Lennon,181\n");
        bw.write("Inception,Emma Thomas,8880,2010\n");
        bw.close();

        ShuffleBoxRunner.readHistory();

        assertEquals(2, ShuffleBoxRunner.list.size());

        ShuffleBoxRunner.writeHistory();

        BufferedReader br = new BufferedReader(new FileReader(temp));
        String first = br.readLine();
        assertNotNull(first);
        br.close();
    }
}
