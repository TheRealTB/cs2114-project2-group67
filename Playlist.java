package musicPlaylist;
import java.util.*;
// -------------------------------------------------------------------------
/**
 *  Playlist full of song nodes
 * 
 *  @author Tyler
 *  @version Sep 26, 2026
 * @param <T> 
 */
public class Playlist<T> {
    private Node<T> firstNode;
    private int numberOfEntries;
    // ----------------------------------------------------------
    /**
     * Creates an empty playlist
     */
    public Playlist() {
        firstNode=null;
        numberOfEntries=0;
    }
    // ----------------------------------------------------------
    /**
     * Returns the first node
     * @return firstNode
     */
    public Node<T> getFirstNode(){
        return firstNode;
    }
    // ----------------------------------------------------------
    /**
     * Sets the first node of the playlsit and updates the number of entries
     * @param firstNode
     */
    public void setFirstNode(Node<T> firstNode) {
        this.firstNode = firstNode;
    }
    
    // ----------------------------------------------------------
    /**
     * Return number of entries
     * @return numberOfEntries
     */
    public int size() {
        return numberOfEntries;
    }
    // ----------------------------------------------------------
    /**
     * String of all songs in playlsit
     * @return toString of all songs in playlsit
     */
    @Override
    public String toString() {
        if (numberOfEntries == 0) {
            return "Playlist is empty";
        }

        StringBuilder r = new StringBuilder();
        r.append("Playlist containing ").append(numberOfEntries).append(" songs: ");

        Node<T> current = firstNode;
        while (current != null) {
            r.append(current.getData().toString());
            if (current.getNext() != null) {
                r.append(", ");
            }
            current = current.getNext();
        }

        return r.toString();
    }
    /**
     * Adds a new entry to the front of this chain.
     *
     * @param newEntry the object to be added; must not be null
     * @return true
     * @throws IllegalArgumentException if newEntry is null
     */
    public boolean add(T newEntry)
    {
        requireNonNull(newEntry);
        firstNode = new Node<T>(newEntry, firstNode);
        numberOfEntries++;
        return true;
    }

    /**
     * Adds a new entry to the end of this chain.
     *
     * @param newEntry the object to be added; must not be null
     * @return true
     * @throws IllegalArgumentException if newEntry is null
     */
    public boolean addToEnd(T newEntry)
    {
        requireNonNull(newEntry);
        Node<T> newNode = new Node<T>(newEntry);
        if (firstNode == null)
        {
            firstNode = newNode;
        }
        else
        {
            Node<T> current = firstNode;
            while (current.getNext() != null)
            {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        numberOfEntries++;
        return true;
    }

    /**
     * Removes and returns the first entry in this chain, if possible.
     *
     * @return the removed entry, or null if the chain is empty
     */
    public T remove()
    {
        if (firstNode == null)
        {
            return null;
        }
        T result = firstNode.getData();
        firstNode = firstNode.getNext();
        numberOfEntries--;
        return result;
    }

    /**
     * Removes the first occurrence of a given entry from this chain.
     *
     * @param anEntry the entry to be removed
     * @return true if the removal was successful, or false if not
     */
    public boolean remove(T anEntry)
    {
        if (anEntry == null || firstNode == null)
        {
            return false;
        }
        if (anEntry.equals(firstNode.getData()))
        {
            firstNode = firstNode.getNext();
            numberOfEntries--;
            return true;
        }
        Node<T> previous = firstNode;
        Node<T> current = firstNode.getNext();
        while (current != null)
        {
            if (anEntry.equals(current.getData()))
            {
                previous.setNext(current.getNext());
                numberOfEntries--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    /**
     * Removes all entries from this chain.
     */
    public void clear()
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    /**
     * Gets the entry at the given position without removing it.
     *
     * @param index the zero-based position
     * @return the entry at that position
     * @throws IndexOutOfBoundsException if index is negative or
     *         index is greater than or equal to size()
     */
    public T get(int index)
    {
        if (index < 0 || index >= numberOfEntries)
        {
            throw new IndexOutOfBoundsException(
                "Index " + index + " out of bounds for size "
                + numberOfEntries);
        }
        Node<T> current = firstNode;
        for (int i = 0; i < index; i++)
        {
            current = current.getNext();
        }
        return current.getData();
    }

    /**
     * Tests whether this chain contains a given entry.
     *
     * @param anEntry the entry to locate
     * @return true if the chain contains anEntry, or false if not
     */
    public boolean contains(T anEntry)
    {
        return count(anEntry) > 0;
    }

    /**
     * Counts the number of times a given entry appears in this chain.
     *
     * @param anEntry the entry to be counted
     * @return the number of times anEntry appears in the chain
     */
    public int count(T anEntry)
    {
        if (anEntry == null)
        {
            return 0;
        }
        int frequency = 0;
        Node<T> current = firstNode;
        while (current != null)
        {
            if (anEntry.equals(current.getData()))
            {
                frequency++;
            }
            current = current.getNext();
        }
        return frequency;
    }

    /**
     * Sees whether this chain is empty.
     *
     * @return true if the chain has no entries, or false if not
     */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }
    /**
     * Copies every entry in this chain into the given array, first entry
     * first.
     *
     * @param values an array of the correct type to fill; it must have
     *               length at least size()
     * @return the array containing all entries in the chain
     * @throws IllegalArgumentException if values is null or too small
     */
    public T[] toArray(T[] values){
        if (values == null)
        {
            throw new IllegalArgumentException("Array must not be null");
        }
        if (values.length < numberOfEntries)
        {
            throw new IllegalArgumentException(
                "Array of length " + values.length
                + " is too small for " + numberOfEntries + " entries");
        }
        int index = 0;
        Node<T> current = firstNode;
        while (current != null)
        {
            values[index] = current.getData();
            index++;
            current = current.getNext();
        }
        return values;
    }
    // ----------------------------------------------------------
    /**
     * Shuffles the order of the playlist (hopefully)
     */
    @SuppressWarnings("unchecked")
    public void shuffle() {
        T[] before = (T[]) new Object[size()];
        before = toArray(before);
        int[] arr = new int[size()];
        for (int i = 0; i < size(); i++) {
            arr[i] = i;
        }
        Random rand = new Random();
        for (int i = size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        clear();
        for(int i = 0; i < arr.length; i++) {
            addToEnd(before[arr[i]]);
        }
    }
    /**
     * Rejects null entries.
     *
     * @param entry the entry to check
     * @throws IllegalArgumentException if entry is null
     */
    private void requireNonNull(T entry){
        if (entry == null)
        {
            throw new IllegalArgumentException("Cannot add null to chain");
        }
    }
}
