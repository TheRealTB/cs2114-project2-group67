package musicPlaylist;

// -------------------------------------------------------------------------
/**
 *  Creates a node with type T and a reference to another node
 *  @param <T>
 * 
 *  @author Tyler
 *  @version Sep 26, 2026
 */
public class Node<T> {

    private T data;
    private Node<T> next;

    // ----------------------------------------------------------
    /**
     * Creates a default node
     */
    public Node(){
        this.data = null;
        this.next = null;
    }
    // ----------------------------------------------------------
    /**
     * Creates a node with data of type T
     * @param data
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    // ----------------------------------------------------------
    /**
     * Creates a node with data of type T and a reference to another node
     * @param data
     * @param next
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    // ----------------------------------------------------------
    /**
     * Returns data
     * @return data
     */
    public T getData() {
        return data;
    }
    // ----------------------------------------------------------
    /**
     * Returns the reference to the next node
     * @return next
     */
    public Node<T> getNext(){
        return next;
    }
    // ----------------------------------------------------------
    /**
     * Sets the data of the node
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }
    // ----------------------------------------------------------
    /**
     * Sets the reference to the next node
     * @param next
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        if(this==other) {
            return true;
        }
        if ((other == null) || (other.getClass() != this.getClass())) {
            return false;
        }
        return(this.getData()==((Node<T>)other).getData()&&this.getNext()==((Node<T>)other).getNext());
    }
}
