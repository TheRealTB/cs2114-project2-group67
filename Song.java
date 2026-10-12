// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- tuetranminh
// LLM Statement:
//I have not used any assistance for the assignment beyond course resources and staff.
package musicPlaylist;

// -------------------------------------------------------------------------
/**
 * A song with a name, artist, and runtime
 * 
 * @author Tyler, Tue Tran
 * @version Sep 26, 2026
 */
public class Song implements MediaType {
    // ~ Fields ................................................................
    private String name;
    private String artist;
    private int runtimeInSeconds;

    // ----------------------------------------------------------
    /**
     * Creates a new Song object
     * 
     * @param name
     * @param artist
     * @param runtimeInSeconds
     */
    // ~ Constructors ..........................................................
    public Song(String name, String artist, int runtimeInSeconds) {
        this.name = name;
        this.artist = artist;
        this.runtimeInSeconds = runtimeInSeconds;
    }

    // ----------------------------------------------------------
    /**
     * Returns name
     * 
     * @return name
     */
    // ~Public Methods ........................................................
    public String getName() {
        return name;
    }

    // ----------------------------------------------------------
    /**
     * Returns artist name
     * 
     * @return artist
     */
    public String getCreator() {
        return artist;
    }

    // ----------------------------------------------------------
    /**
     * Return runtime
     * 
     * @return runtimeInSeconds
     */
    public int getRuntime() {
        return runtimeInSeconds;
    }

    /**
     * Changes name
     *
     * @param name the new name, not null
     * @throws IllegalArgumentException if name is null
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        this.name = name;
    }

    /**
     * Changes artist
     * 
     * @param creator the new artist, not null
     * @throws IllegalArgumentException if creator is null
     */
    public void setCreator(String creator) {
        if (creator == null) {
            throw new IllegalArgumentException("Creator must not be null");
        }
        this.artist = creator;
    }

    // ----------------------------------------------------------
    /**
     * Changes runtime
     * 
     * @param runtimeInSeconds the new runtime, not negative
     * @throws IllegalArgumentException if runtimeInSeconds is negative
     */
    public void setRuntime(int runtimeInSeconds) {
        if (runtimeInSeconds < 0) {
            throw new IllegalArgumentException("Runtime must not be negative");
        }
        this.runtimeInSeconds = runtimeInSeconds;
    }

    // ----------------------------------------------------------
    /**
     * Returns if two songs are equal (same name, artist, & runtime)
     * 
     * @return if the two are equal
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if ((other == null) || (other.getClass() != this.getClass())) {
            return false;
        }
        return (this.getName().equals(((Song) other).getName()) &&
                this.getCreator().equals(((Song) other).getCreator()) &&
                this.getRuntime() == ((Song) other).getRuntime());
    }

    // ----------------------------------------------------------
    /**
     * Returns a string with info of the song
     * 
     * @return string
     */
    @Override
    public String toString() {
        return this.name + "," + this.artist + "," + this.runtimeInSeconds;
    }

    // ----------------------------------------------------------
    /**
     * ToString for printing to console
     * 
     * @return pretty string
     */
    public String formattedToString() {
        return this.name + " by: " + this.artist + ", " + runtimeFormatter();
    }

    // ----------------------------------------------------------
    /**
     * Formats the runtime to Hr:Min:Sec
     * 
     * @return runtime
     */
    public String runtimeFormatter() {
        StringBuilder sb = new StringBuilder();
        sb.append("Runtime: ");
        int runtimeHours;
        int runtimeMinutes;
        int runtimeSeconds = runtimeInSeconds;
        if (runtimeSeconds >= 60) {
            runtimeMinutes = runtimeSeconds / 60;
            runtimeSeconds %= 60;
            if (runtimeMinutes >= 60) {
                runtimeHours = runtimeMinutes / 60;
                runtimeMinutes %= 60;
                if (runtimeHours != 0) {
                    sb.append(runtimeHours > 1 ? runtimeHours
                            + " Hours " : runtimeHours + " Hour ");
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            if (runtimeMinutes != 0) {
                sb.append(runtimeMinutes > 1 ? runtimeMinutes
                        + " Minutes " : runtimeMinutes + " Minute ");
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        if (runtimeSeconds != 0) {
            sb.append(runtimeSeconds > 1 ? runtimeSeconds + " Seconds " : runtimeSeconds + " Second");
        } else {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
