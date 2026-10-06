package musicPlaylist;
import java.util.*;
import java.nio.file.*;
import java.io.*;
// -------------------------------------------------------------------------
/**
 *  Welcome to ShuffleBox, an interactive music playlist that can read
 *  history from a file and modify the list with user input, then it can
 *  write the playlist to a file so that it can be saved for later.
 * 
 *  @author Tyler
 *  @version Oct 5, 2026
 */
public class ShuffleBoxRunner {
    private static Playlist<Song> list;
    private static Scanner scanFile;
    private static BufferedWriter writer;
    private static File f;
    // ----------------------------------------------------------
    /**
     * Runs the ShuffleBox
     * @param args
     */
    public static void main(String[] args) {
        //Initialize and read from history file
        System.out.println(
              "--------------------------------\n"
            + "     Welcome to ShuffleBox!     "
            + "\n--------------------------------"
            );
        list = new Playlist<Song>();
        readHistory();
        System.out.printf("Your playlist contains %d songs:", list.size()+1);
        //TODO:Options
        /*
         * add to end (queue)
         * add to front (play)
         * remove front (end song)
         * remove by name (remove song)
         * clear (clear)
         * shuffle (shuffle)
         * display playlist (view songs)
         * exit
         */
    }
    
    // ----------------------------------------------------------
    /**
     * Reads a history file for contents of playlist
     */
    public static void readHistory() {
        try {
            f = new File("History");
            scanFile = new Scanner(f);
            String s = scanFile.nextLine();
            if(!s.equals("Playlist is empty")) {
                while(scanFile.hasNextLine()) {
                    s=scanFile.nextLine();
                    //Songs in Title,Artist,Runtime order
                    String[] sArray = s.split(",");
                    list.addToEnd(new Song(sArray[0], sArray[1], Integer.parseInt(sArray[2])));
                }
            }
        }catch(FileNotFoundException e) {
            System.out.println("Input does not exist or is not found");
        }finally {
            if(scanFile!=null) {
                scanFile.close();   
            }
        }
    }
    
    // ----------------------------------------------------------
    /**
     * Writes playlist to a history file
     */
    public static void writeHistory() {
        try {
            writer = new BufferedWriter(new FileWriter(f, false));
            writer.write(list.toString());
        } catch (IOException io1) {
            System.out.println("IOException");
        }finally {
            try {
                writer.close();
            }catch (IOException io2) {
                System.out.println("IOException");
            }
        }
    }
}
