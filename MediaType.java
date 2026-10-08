package musicPlaylist;
// -------------------------------------------------------------------------
/**
 *  Media types are implemented by song and movie and can be added to the 
 *  shuffle box together using generic logic
 * 
 *  @author Tyler
 *  @version Oct 7, 2026
 */
public interface MediaType {
     // ----------------------------------------------------------
     /**
      * Returns name
      * @return name
      */
     //~Public  Methods ........................................................
     public String getName();
     // ----------------------------------------------------------
     /**
      * Returns creator name
      * @return artist
      */
     public String getCreator();
     // ----------------------------------------------------------
     /**
      * Return runtime
      * @return runtimeInSeconds
      */
     public int getRuntime();
     // ----------------------------------------------------------
     /**
      * Changes name
      * @param name
      */
     public void setName(String name);
     // ----------------------------------------------------------
     /**
      * Changes creator
      * @param creator
      */
     public void setCreator(String creator);
     // ----------------------------------------------------------
     /**
      * Changes runtime
      * @param runtimeInSeconds
      */
     public void setRuntime(int runtimeInSeconds);
     // ----------------------------------------------------------
     /**
      * Returns if two MediaTypes are equal (same name, artist, & runtime)
      * @return if the two are equal
      */
     @Override
     public boolean equals(Object other);
  // ----------------------------------------------------------
     /**
      * ToString for printing to console
      * @return pretty string
      */
     public String formattedToString();
     // ----------------------------------------------------------
     /**
      * Formats the runtime to Hr:Min:Sec
      * @return runtime
      */
     public String runtimeFormatter();
 }
