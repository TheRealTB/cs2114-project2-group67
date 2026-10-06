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
              "--------------------------------\n"
            + "     Welcome to ShuffleBox!     "
            + "\n--------------------------------"
            );
        list = new Playlist<Song>();
        readHistory();
        System.out.printf("Your playlist contains %d songs:\n", list.size());
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
            System.out.print(
                  "[1] Play Song\n"
                + "[2] Queue Song\n"
                + "[3] Next Song\n"
                + "[4] Remove Song\n"
                + "[5] Shuffle Playlist\n"
                + "[6] Clear Playlist\n"
                + "[7] View Playlist\n"
                + "[8] Exit ShuffleBox\n"
                + "Enter Your Selection: "
                );
            scanUser = new Scanner(System.in);
            in = scanUser.nextLine();
            i =Integer.parseInt(in.trim().substring(0,1));
            forceClear();
            prompt(i);
        }else {
            scanUser = new Scanner(System.in);
            System.out.print("[Any] Back: ");
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
    public static void prompt(int i){
        scanUser = new Scanner(System.in);
        switch(i) {
            case 1: //add()
                break;
            case 2: //addToEnd()
                break;
            case 3: //remove()
                break;
            case 4: //remove(T entry)
                break;
            case 5: //shuffle()
                break;
            case 6: //clear()
                break;
            case 7: System.out.println(list.toString()); 
            //TODO: Fix formatting (Name, Artist, Minutes:Seconds)
                break;
            case 8: System.out.println("Thank you for using ShuffleBox!");
            default:
                scanUser.close();
        }
        writeHistory();
        if(i!=8) {
            prompt(true);
        }
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
            
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try{
            Thread.sleep(100);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
