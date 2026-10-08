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
//TODO: Ensure methods throw proper exceptions; re-prompt logic w/setters
//TODO: Test cases for Song, Movie, Node, Playlist, and ShuffleBoxRunner
//TODO: All other TODOs within classes
public class ShuffleBoxRunner {
    private static Playlist<MediaType> list;
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
        list = new Playlist<MediaType>();
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
                if(sArray.length==3) {
                    list.addToEnd(new Song(sArray[0],
                        sArray[1],
                        Integer.parseInt(sArray[2])
                        ));
                    while(scanFile.hasNextLine()) {
                        s=scanFile.nextLine();
                        //Songs in Title,Artist,Runtime order
                        String[] sArray1 = s.split(",");
                        if(sArray1.length == 3) {
                            list.addToEnd(new Song(sArray1[0],
                            sArray1[1],
                            Integer.parseInt(sArray1[2])
                            ));
                        }else if(sArray1.length == 4) {
                            list.addToEnd(new Movie(sArray1[0],
                                sArray1[1],
                                Integer.parseInt(sArray1[2]),
                                Integer.parseInt(sArray1[3])
                                ));
                        }
                    }
                }else if(sArray.length==4) {
                    list.addToEnd(new Movie(sArray[0],
                        sArray[1],
                        Integer.parseInt(sArray[2]), 
                        Integer.parseInt(sArray[3])
                        ));
                    while(scanFile.hasNextLine()) {
                        s=scanFile.nextLine();
                        //Songs in Title,Artist,Runtime order
                        String[] sArray1 = s.split(",");
                        if(sArray1.length == 3) {
                            list.addToEnd(new Song(sArray1[0],
                                sArray1[1],
                                Integer.parseInt(sArray1[2])
                                ));
                        }else if(sArray.length == 4) {
                            list.addToEnd(new Movie(sArray1[0],
                                sArray1[1],
                                Integer.parseInt(sArray1[2]),
                                Integer.parseInt(sArray1[3])
                                ));
                        }
                    }
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
    public static void prompt(boolean b) {
        String in;
        int i;
        if(!b) {
            System.out.printf("Your playlist contains %d items:\n", list.size());
            displayRuntime();
            System.out.print(
                  "[1] Play\n"
                + "[2] Queue\n"
                + "[3] Next\n"
                + "[4] Remove\n"
                + "[5] Shuffle\n"
                + "[6] Frequency\n"
                + "[7] Clear\n"
                + "[8] View\n"
                + "[9] Exit\n"
                + "Enter Your Selection: "
                );
            cursorFix();
            scanUser = new Scanner(System.in);
            try {
                in = scanUser.nextLine();
                i =Integer.parseInt(in.trim().substring(0,1));
                forceClear();
                prompt(i);
            }catch(NumberFormatException e) {
                System.out.println("Please enter a number.");
                prompt(false);
            }
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
    public static void prompt(int i){
        scanUser = new Scanner(System.in);
        switch(i) {
            case 1: //add
                forceClear();
                System.out.print("What is the name"
                    + " of the item you want to add? ");
                cursorFix();
                list.add(getItemByName(scanUser.nextLine(),i));
                prompt(8);
                break;
            case 2: //addToEnd
                forceClear();
                System.out.print("What is the name"
                    + " of the item you want to add? ");
                cursorFix();
                list.addToEnd(getItemByName(scanUser.nextLine(),i));
                prompt(8);
                break;
            case 3: //remove
                list.remove(); prompt(8);
                break;
            case 4: //remove(T entry)
                forceClear();
                System.out.print("What is the name"
                    + " of the item you want to remove? ");
                cursorFix();
                String name = scanUser.nextLine();
                while(list.contains(getItemByName(name,i))){
                    list.remove(getItemByName(name,i));
                }
                prompt(8);
                break;
            case 5: list.shuffle(); prompt(8);
                break;
            case 6: //Creator Frequency
                MediaType[] media = new MediaType[list.size()];
                media = list.toArray(media);
                String[] creators = new String[list.size()];
                int c = 0;
                for(MediaType m:media) {
                    creators[c]=m.getCreator();
                    c++;
                }
                ArrayList<String> uCreators = new ArrayList<String>();
                for(String s:creators) {
                    if(!uCreators.contains(s)) {
                        uCreators.add(s);
                    }
                }
                for(String s:uCreators) {
                    int freq = 0;
                    for(MediaType m:media) {
                        if(m.getCreator().equals(s)) {
                            freq++;
                        }
                    }
                    System.out.printf("Frequency of %s: %d\n", s, freq);
                }
                prompt(true);
                break;
            case 7: //clear
                list.clear(); prompt(false);
                break;
            case 8: //display
                displayPlaylist(); prompt(true);
                break;
            case 9: //end
                System.out.println("Thank you for using ShuffleBox!"); 
                scanUser.close();
                break;
            default: //re-prompt
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
        Node<MediaType> current = list.getFirstNode();
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
        System.out.println(sb.toString());
    }
    // ----------------------------------------------------------
    /**
     * Prints all media in the list formatted for console
     */
    public static void displayPlaylist() {
        Node<MediaType> current = list.getFirstNode();
        int i = 1;
        while(current!=null) {
            System.out.printf("[%d] %s\n", i, current.getData().formattedToString());
            i++;
            current=current.getNext();
        }
        
    }
    
    // ----------------------------------------------------------
    /**
     * Allows for searching items by name rather than by object
     * @param name
     * @param i
     * @return MediaType object
     */
    public static MediaType getItemByName(String name, int i) {
        @SuppressWarnings("unused")
        MediaType add;
        switch (name) {
            case "Bohemian Rhapsody":
                return add = new Song("Bohemian Rhapsody", "Queen", 355);
            case "Blinding Lights":
                return add = new Song("Blinding Lights", "The Weeknd", 202);
            case "Billie Jean":
                return add = new Song("Billie Jean", "Michael Jackson", 294);
            case "Rolling in the Deep":
                return add = new Song("Rolling in the Deep", "Adele", 228);
            case "Smells Like Teen Spirit":
                return add = new Song("Smells Like Teen Spirit", "Nirvana", 301);
            case "Hey Jude":
                return add = new Song("Hey Jude", "The Beatles", 431);
            case "Hotel California":
                return add = new Song("Hotel California", "Eagles", 390);
            case "Imagine":
                return add = new Song("Imagine", "John Lennon", 181);
            case "Sweet Child O' Mine":
                return add = new Song("Sweet Child O' Mine", "Guns N' Roses", 355);
            case "One":
                return add = new Song("One", "Metallica", 447);
            case "The Shawshank Redemption":
                return add = new Movie("The Shawshank Redemption", "Niki Marvin", 8520, 1994);
            case "The Godfather":
                return add = new Movie("The Godfather", "Albert S. Ruddy", 10500, 1972);
            case "The Dark Knight":
                return add = new Movie("The Dark Knight", "Emma Thomas", 9120, 2008);
            case "The Godfather Part II":
                return add = new Movie("The Godfather Part II", "Francis Ford Coppola", 12120, 1974);
            case "12 Angry Men":
                return add = new Movie("12 Angry Men", "Henry Fonda", 5760, 1957);
            case "Schindler's List":
                return add = new Movie("Schindler's List", "Steven Spielberg", 11700, 1993);
            case "The Lord of the Rings: The Return of the King":
                return add = new Movie("The Lord of the Rings: The Return of the King", "Barrie M. Osborne", 12060, 2003);
            case "Pulp Fiction":
                return add = new Movie("Pulp Fiction", "Lawrence Bender", 9240, 1994);
            case "Inception":
                return add = new Movie("Inception", "Emma Thomas", 8880, 2010);
            case "Fight Club":
                return add = new Movie("Fight Club", "Art Linson", 8340, 1999);

            default:
                System.out.println("Enter a valid item name");
                prompt(i);
                return null;
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
