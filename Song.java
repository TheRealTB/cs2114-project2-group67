package musicPlaylist;
// -------------------------------------------------------------------------
/**
 *  A song with a name, artist, and runtime
 * 
 *  @author Tyler
 *  @version Sep 26, 2026
 */
public class Song implements MediaType{
    //~ Fields ................................................................
    private String name;
    private String artist;
    private int runtimeInSeconds;
    // ----------------------------------------------------------
    /**
     * Creates a new Song object
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
    public String getCreator() {
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
     * @param creator
     */
    public void setCreator(String creator) {
        this.artist = creator;
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
            this.getCreator().equals(((Song)other).getCreator())&&
            this.getRuntime()==((Song)other).getRuntime());
    }
    // ----------------------------------------------------------
    /**
     * Returns a string with info of the song
     * @return string
     */
    @Override
    public String toString() {
        return this.name+","+this.artist+","+this.runtimeInSeconds;
    }
    // ----------------------------------------------------------
    /**
     * ToString for printing to console
     * @return pretty string
     */
    public String formattedToString() {
        return this.name + " by: " + this.artist + ", " + runtimeFormatter();
    }
    // ----------------------------------------------------------
    /**
     * Formats the runtime to Hr:Min:Sec
     * @return runtime
     */
    public String runtimeFormatter() {
        StringBuilder sb = new StringBuilder();
        sb.append("Runtime: ");
        int runtimeHours;
        int runtimeMinutes;
        int runtimeSeconds = runtimeInSeconds;
        if(runtimeSeconds>=60) {
            runtimeMinutes = runtimeSeconds/60;
            runtimeSeconds%=60;
            if(runtimeMinutes>=60) {
                runtimeHours = runtimeMinutes/60;
                runtimeMinutes%=60;
                if(runtimeHours!=0) {    
                    sb.append(runtimeHours>1 ? runtimeHours
                            + " Hours ": runtimeHours + " Hour ");
                }else {
                    sb.deleteCharAt(sb.length()-1);
                }
            }
            if(runtimeMinutes!=0) {
                sb.append(runtimeMinutes>1 ? runtimeMinutes
                    + " Minutes ": runtimeMinutes + " Minute ");
            }else {
                sb.deleteCharAt(sb.length()-1);
            }
        }
        if(runtimeSeconds!=0) {
            sb.append (runtimeSeconds >1 ? runtimeSeconds + " Seconds ": runtimeSeconds + " Second");
        }else{
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
}
