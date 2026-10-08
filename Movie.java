package musicPlaylist;
// -------------------------------------------------------------------------
/**
 *  A movie with name, producer, and runtime
 * 
 *  @author Tyler
 *  @version Oct 7, 2026
 */
public class Movie implements MediaType {
    private String name;
    private String producer;
    private int runtimeInSeconds;
    private int releaseYear;
    // ----------------------------------------------------------
    /**
     * Create a new Movie object.
     * @param name
     * @param producer
     * @param runtimeInSeconds
     * @param releaseYear
     */
    public Movie(String name, String producer, int runtimeInSeconds, int releaseYear) {
        this.name = name;
        this.producer = producer;
        this.runtimeInSeconds = runtimeInSeconds;
        this.releaseYear = releaseYear;
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
     * Returns producer name
     * @return producer
     */
    public String getCreator() {
        return producer;
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
     * Return releaseYear
     * @return releaseYear
     */
    public int getYear() {
        return releaseYear;
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
     * Changes producer
     * @param creator
     */
    public void setCreator(String creator) {
        this.producer = creator;
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
     * Changes releaseYear
     * @param releaseYear
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    // ----------------------------------------------------------
    /**
     * Returns if two movies are equal (same name, artist, runtime, & year)
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
        return(this.getName().equals(((Movie)other).getName())&&
            this.getCreator().equals(((Movie)other).getCreator())&&
            this.getRuntime()==((Movie)other).getRuntime()&&
            this.getYear()==((Movie)other).getYear());
    }
    // ----------------------------------------------------------
    /**
     * Returns a string with info of the movie
     * @return string
     */
    @Override
    public String toString() {
        return this.name+","+this.producer+","+
    this.runtimeInSeconds+","+this.releaseYear;
    }
 // ----------------------------------------------------------
    /**
     * ToString for printing to console
     * @return pretty string
     */
    public String formattedToString() {
        return this.name + " by: " + this.producer + ", " + runtimeFormatter() + ", " + this.releaseYear;
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
