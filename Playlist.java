package musicPlaylist;
// -------------------------------------------------------------------------
/**
 *  Playlist full of song nodes
 * 
 *  @author Tyler
 *  @version Sep 26, 2026
 */
public class Playlist {
    private Node<Song> firstNode;
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
    public Node<Song> getFirstNode(){
        return firstNode;
    }
    // ----------------------------------------------------------
    /**
     * Sets the first node of the playlsit and updates the number of entries
     * @param firstNode
     */
    public void setFirstNode(Node<Song> firstNode) {
        this.firstNode = firstNode;
        this.setNumEntries();
    }
    
    // ----------------------------------------------------------
    /**
     * Sets the number of entries to the number of songs in the playlist
     * @return numberOfEntries
     */
    public int setNumEntries() {
        if(firstNode==null) {
            numberOfEntries=0;
        }else {
            numberOfEntries=1;
            Node<Song> current=firstNode;
            while(current.getNext()!=null) {
                current=current.getNext();
                numberOfEntries++;
            }
        }
        return numberOfEntries;
    }
    // ----------------------------------------------------------
    /**
     * How many songs in playlist
     * @return numberOfEntries
     */
    public int getNumEntries() {
        return numberOfEntries;
    }
    // ----------------------------------------------------------
    /**
     * Checks if the playlist contains a song with a specific name
     * @param name
     * @return if the playlist contains a song with the specified name
     */
    public boolean contains(String name) {
        if(numberOfEntries==0) {
            return false;
        }
        Node<Song> current = firstNode;
        while(current!=null) {
            if(current.getData()!=null&&name.equals(current.getData().getName())) {
                return true;
            }
            current=current.getNext();
        }
        return false;
    }
    
    // ----------------------------------------------------------
    /**
     * Adds a song to the front of the playlist
     * @param name
     * @param artist
     * @param runtime
     * @return if the add is successful or not
     */
    public boolean addSongToFront(String name, String artist, int runtime) {
        if(name==null) {
            return false;
        }
        Song song=new Song(name, artist, runtime);
        Node<Song> songNode=new Node<Song>(song);
        if(!this.contains(songNode.getData().getName())) {
            if(firstNode==null) {
                this.setFirstNode(songNode);
                return true;
            }
            songNode.setNext(firstNode);
            this.setFirstNode(songNode);
            return true;
        }
        return false;
    }
    
    // ----------------------------------------------------------
    /**
     * Adds a song to end of playlsit
     * @param name
     * @param artist
     * @param runtime
     * @return if the add is successful or not
     */
    public boolean addSongToEnd(String name, String artist, int runtime) {
        if(name==null) {
            return false;
        }
        Song song=new Song(name, artist, runtime);
        Node<Song> songNode=new Node<Song>(song);
        if(!this.contains(songNode.getData().getName())) {
            if(firstNode==null) {
                this.setFirstNode(songNode);
                return true;
            }
            Node<Song> current = firstNode;
            while(current.getNext()!=null) {
                current=current.getNext();
            }
            current.setNext(songNode);
            this.setNumEntries();
            return true;
        }
        return false;
    }
    
    // ----------------------------------------------------------
    /**
     * Adds a song to a specific index (0 = firstNode) of the playlist
     * Probably very janky but this should work please don't modify
     * @param name
     * @param artist
     * @param runtime
     * @param index
     * @return if the add is successful or not
     */
    public boolean addSongToIndex(String name, String artist, int runtime, int index) {
        if (name == null || index < 0 || index > numberOfEntries) {
            return false;
        }
        if (index == 0) {
            return addSongToFront(name, artist, runtime);
        }
        if (index == numberOfEntries) {
            return addSongToEnd(name, artist, runtime);
        }
        if (this.contains(name)) {
            return false;
        }
        Song song = new Song(name, artist, runtime);
        Node<Song> songNode = new Node<Song>(song);
        Node<Song> current = firstNode;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        songNode.setNext(current.getNext());
        current.setNext(songNode);
        setNumEntries();
        return true;
    }
    // ----------------------------------------------------------
    /**
     * Removes the first node and sets firstNode.next() as first node
     * @return if the node was removed
     */
    public boolean removeSongAtFront() {
        if(firstNode!=null) {
            if(firstNode.getNext()!=null) { 
                Node<Song> next = firstNode.getNext();
                this.setFirstNode(next);
                this.setNumEntries();
                return true;
            }
            firstNode = null;
            this.setNumEntries();
            return true;
        }
        return false;
    }
    // ----------------------------------------------------------
    /**
     * Removes the last node in the playlist
     * @return if the node was removed
     */
    public boolean removeSongAtEnd() {
        if (firstNode == null) {
            return false;
        }
        if (firstNode.getNext() == null) {
            firstNode = null;
            setNumEntries();
            return true;
        }

        Node<Song> penNode = firstNode;
        while (penNode.getNext().getNext() != null) {
            penNode = penNode.getNext();
        }
        penNode.setNext(null);
        setNumEntries();
        return true;
    }
    // ----------------------------------------------------------
    /**
     * Removes a node at a specified index (firstNode=0)
     * Very jank code please don't touch
     * @param index
     * @return if the node was removed
     */
    public boolean removeSongAtIndex(int index) {
        if (index < 0 || index >= numberOfEntries) {
            return false;
        }

        if (index == 0) {
            return removeSongAtFront();
        }
        if (index == numberOfEntries - 1) {
            return removeSongAtEnd();
        }

        Node<Song> current = firstNode;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        current.setNext(current.getNext().getNext());
        setNumEntries();
        return true;
    }
    // ----------------------------------------------------------
    /**
     * Checks if a song with a specific name is in playlsit and removes it
     * @param name
     * @return if the node was removed
     */
    public boolean removeSongByName(String name) {
        if (firstNode == null || name == null) {
            return false;
        }

        if (firstNode.getData().getName().equals(name)) {
            return removeSongAtFront();
        }

        Node<Song> current = firstNode;
        while (current.getNext() != null) {
            if (current.getNext().getData().getName().equals(name)) {
                current.setNext(current.getNext().getNext());
                setNumEntries();
                return true;
            }
            current = current.getNext();
        }

        return false;
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

        Node<Song> current = firstNode;
        while (current != null) {
            r.append(current.getData().toString());
            if (current.getNext() != null) {
                r.append(", ");
            }
            current = current.getNext();
        }

        return r.toString();
    }
}
