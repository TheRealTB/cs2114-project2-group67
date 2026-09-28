package musicPlaylist;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
// -------------------------------------------------------------------------
/**
 *  Tests the Node<T> class
 * 
 *  @author Tyler
 *  @version Sep 26, 2026
 */
public class NodeTest{
    private Node<Integer> node1;
    private Node<Integer> node2;
    private Node<Integer> exactSame;
    private Node<Integer> isNull;
    private Node<Integer> bothSame;
    private Node<Integer> sameData;
    private Node<Integer> sameNext;
    private Object object;
    
    // ----------------------------------------------------------
    /**
     * Sets up nodes to test in tests
     */
    @BeforeEach
    public void setUp() {
        node2=new Node<Integer>(2);
        node1=new Node<Integer>(1, node2);
        
        exactSame=node1;
        isNull=null;
        bothSame=new Node<Integer>(1, node2);
        sameData=new Node<Integer>(1);
        sameNext=new Node<Integer>(2, node2);
        object=new Object();
    }
    
    // ----------------------------------------------------------
    /**
     * Tests the getData method
     */
    @Test
    public void testGetData() {
        assertEquals(1, (int)node1.getData());
    }
    // ----------------------------------------------------------
    /**
     * Tests the equals method
     */
    @Test
    public void testEquals() {
        assertTrue(node1.equals(node1));
        assertEquals(false, node1.equals(isNull));
        assertTrue(node1.equals(exactSame));
        assertTrue(node1.equals(bothSame));
        assertEquals(false, node1.equals(sameData));
        assertEquals(false, node1.equals(sameNext));
        assertEquals(false, node1.equals(object));
    }
    // ----------------------------------------------------------
    /**
     * Tests the getNext method
     */
    @Test
    public void testGetNext() {
        assertTrue(node1.getNext().equals(node2));
    }
    // ----------------------------------------------------------
    /**
     * Tests the setData method
     */
    @Test
    public void testSetData() {
        node1.setData(2);
        assertEquals(2, (int)node1.getData());
    }
    // ----------------------------------------------------------
    /**
     * Tests the setNext method
     */
    @Test
    public void testSetNext() {
        node1.setNext(bothSame);
        assertTrue(node1.getNext().equals(bothSame));
    }

}
