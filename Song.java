package musicPlaylist;
// -------------------------------------------------------------------------
/**
 *  A song with a name and runtime
 * 
 *  @author Tyler
 *  @version Sep 26, 2026
 */
public class Song {
    //~ Fields ................................................................
    private String name;
    private String artist;
    private int runtimeInSeconds;
    // ----------------------------------------------------------
    /**
     * Creates a song with name and runtime
     * @param name
     * @param artist
     * @param runtimeInSeconds
     */
    //~ Constructors ..........................................................
    public Song(String name, String artist, int runtimeInSeconds) {
        this.name = name;
        this.artist = artist;
        this.runtimeInSeconds = runtimeInSeconds;
    }
    // ----------------------------------------------------------
    /**
     * Returns name
     * @return name
     */
    //~Public  Methods ........................................................
    public String getName() {
        return name;
    }
    // ----------------------------------------------------------
    /**
     * Returns artist name
     * @return artist
     */
    public String getArtist() {
        return artist;
    }
    // ----------------------------------------------------------
    /**
     * Return runtime
     * @return runtimeInSeconds
     */
    public int getRuntime() {
        return runtimeInSeconds;
    }
    // ----------------------------------------------------------
    /**
     * Changes name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    // ----------------------------------------------------------
    /**
     * Changes artist
     * @param artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }
    // ----------------------------------------------------------
    /**
     * Changes runtime
     * @param runtimeInSeconds
     */
    public void setRuntime(int runtimeInSeconds) {
        this.runtimeInSeconds = runtimeInSeconds;
    }
    // ----------------------------------------------------------
    /**
     * Returns if two songs are equal (same name, artist, & runtime)
     * @return if the two are equal
     */
    @Override
    public boolean equals(Object other) {
        if(this==other) {
            return true;
        }
        if ((other == null) || (other.getClass() != this.getClass())) {
            return false;
        }
        return(this.getName().equals(((Song)other).getName())&&
            this.getArtist().equals(((Song)other).getArtist())&&
            this.getRuntime()==((Song)other).getRuntime());
    }
    // ----------------------------------------------------------
    /**
     * Returns a string with info of the song
     * @return string
     */
    @Override
    public String toString() {
        return this.name + " by: " + this.artist + ", runtime: " + 
    this.runtimeInSeconds + " seconds";
    }
}
