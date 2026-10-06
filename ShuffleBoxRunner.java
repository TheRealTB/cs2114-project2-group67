package musicPlaylist;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
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
    private static Scanner scanUser;
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
              "------------------------------------------\n"
            + "          Welcome to ShuffleBox!     "
            + "\n------------------------------------------"
            );
        list = new Playlist<Song>();
        readHistory();
        prompt(false);
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
                String[] sArray = s.split(",");
                list.addToEnd(new Song(sArray[0],
                    sArray[1],
                    Integer.parseInt(sArray[2])
                    ));
                while(scanFile.hasNextLine()) {
                    s=scanFile.nextLine();
                    //Songs in Title,Artist,Runtime order
                    String[] sArray1 = s.split(",");
                    list.addToEnd(new Song(sArray1[0],
                        sArray1[1],
                        Integer.parseInt(sArray1[2])
                        ));
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
    
    // ----------------------------------------------------------
    /**
     * Prompts the user on their input and takes in their input with the scanner
     * @param b
     */
    //TODO: handle NumberFormatException
    public static void prompt(boolean b) {
        String in;
        int i;
        if(!b) {
            System.out.printf("Your playlist contains %d songs:\n", list.size());
            displayRuntime();
            System.out.print(
                  "[1] Play Song\n"
                + "[2] Queue Song\n"
                + "[3] Next Song\n"
                + "[4] Remove Song\n"
                + "[5] Shuffle Playlist\n"
                + "[6] Artist Frequency\n"
                + "[7] Clear Playlist\n"
                + "[8] View Playlist\n"
                + "[9] Exit ShuffleBox\n"
                + "Enter Your Selection: "
                );
            cursorFix();
            scanUser = new Scanner(System.in);
            in = scanUser.nextLine();
            i =Integer.parseInt(in.trim().substring(0,1));
            forceClear();
            prompt(i);
        }else {
            scanUser = new Scanner(System.in);
            System.out.print("[Any] Back: ");
            cursorFix();
            in = scanUser.nextLine();
            forceClear();
            prompt(!b);
        }
    }
    // ----------------------------------------------------------
    /**
     * Takes the input from prompt() and outputs the result
     * @param i
     */
    //TODO: Finish method implementation
    //TODO: Maybe make a list of song objects so that users can add by name alone
    public static void prompt(int i){
        scanUser = new Scanner(System.in);
        switch(i) {
            case 1: //add()
                /*
                 * ask for name, artist, runtime (separate lines)
                 * initialize song with those values
                 * initialize node with song
                 * list.add(node)
                 * prompt(7)
                 */
                break;
            case 2: //addToEnd()
                /*
                 * ask for name, artist, runtime (separate lines)
                 * initialize song with those values
                 * initialize node with song
                 * list.addToEnd(node)
                 * prompt(7)
                 */
                break;
            case 3: list.remove(); prompt(7);
                break;
            case 4: //remove(T entry)
                /*
                 * ask for name, artist, runtime (separate lines)
                 * initialize song with those values
                 * list.remove(song)
                 * prompt(7)
                 */
                break;
            case 5: list.shuffle(); prompt(7);
                break;
            case 6: //Artist Frequency
                /*
                 * list -> array
                 * array<Song> -> array<String>
                 * iterate through each unique string
                 * if iteratedString.equals(arrayString)
                 * add to count
                 * print interatedString and count
                 * prompt(true)
                 */
                break;
            case 7: list.clear();
                break;
            case 8: System.out.println(list.toString()); prompt(true);
            //TODO: Fix formatting (Name, Artist, Minutes:Seconds)
            case 9: System.out.println("Thank you for using ShuffleBox!"); 
                scanUser.close();
                break;
            default:
                System.out.printf("[%d] is invalid, enter a number [1-9]\n", i);
                prompt(false);
        }
        writeHistory();
    }
    
    // ----------------------------------------------------------
    /**
     * Sums the runtime of all song nodes in playlist.
     * Following this, it converts seconds to Hour, Minute, & Second
     * Appends this to a StringBuilder and prints the formatted runtime.
     */
    public static void displayRuntime() {
        StringBuilder sb = new StringBuilder();
        sb.append("Runtime: ");
        int runtimeHours;
        int runtimeMinutes;
        int runtimeSeconds = 0;
        Node<Song> current = list.getFirstNode();
        while(current!=null) {
            runtimeSeconds+=current.getData().getRuntime();
            current=current.getNext();
        }
        if(runtimeSeconds>=60) {
            runtimeMinutes = runtimeSeconds/60;
            runtimeSeconds%=60;
            if(runtimeMinutes>=60) {
                runtimeHours = runtimeMinutes/60;
                runtimeMinutes%=60;
                sb.append(runtimeHours>1 ? runtimeHours
                    + " Hours ": runtimeHours + " Hour ");
            }
            sb.append(runtimeMinutes>1 ? runtimeMinutes
                + " Minutes ": runtimeMinutes + " Minute ");
        }
        sb.append(runtimeSeconds>1 ? runtimeSeconds + " Seconds ": runtimeSeconds + " Second ");
        System.out.println(sb.toString());
    }
    
    // ----------------------------------------------------------
    /**
     * Clears the console waits 100ms to ensure prompt isn't cleared
     * Code was generated by Google Gemini
     */
    public static void forceClear() {
        try {
            Robot robot = new Robot();
            
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_ALT);
            
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_F10);
            robot.keyRelease(KeyEvent.VK_F10);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            
            robot.keyPress(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_R);
            try{
                Thread.sleep(50);
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }    
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
    }
    // ----------------------------------------------------------
    /**
     * Moves the cursor to the end of text so that it's ready for input
     */
    public static void cursorFix() {
        try {
            try {
                Thread.sleep(50);
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Robot robot = new Robot();
            int presses = (list.size()>11)? list.size():11;
            for(int i = 0; i < presses; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }
            try {
                Thread.sleep(50);
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }catch(AWTException e) {
            e.printStackTrace();
        }
    }
}
