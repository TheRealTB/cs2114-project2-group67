package musicPlaylist;
import java.util.*;
import java.nio.file.*;
import java.io.*;
public class ShuffleBoxRunner {
    private static Scanner scan;
    private static BufferedWriter writer;
    public static void main(String[] args) {
        Playlist<Song> list = new Playlist<Song>();
        try {
            File f = new File("History");
            scan = new Scanner(f);
            int ind = 0;
            while(scan.hasNextLine()) {
                String s = scan.nextLine();
                if(ind == 0 && s.charAt(9)=='c') {
                    s=scan.nextLine();
                    //Songs in Title,Artist,Runtime order
                    String[] sArray = s.split(",");
                    for(String str:sArray) {
                        System.out.println(str);
                    }
                    list.add(new Song(sArray[0], sArray[1], Integer.parseInt(sArray[2])));
                    try {
                        writer = new BufferedWriter(new FileWriter(f, false));
                        writer.write(list.toString());
                    } catch (IOException io1) {
                        System.out.println("IOException");
                    }finally {
                        try {
                            writer.close();
                        }
                        catch (IOException io2) {
                        System.out.println("IOException");
                        }
                    }
                }else {
                    //TODO: prompt user they have no history
                }
                ind++;
            }
        }catch(FileNotFoundException e) {
            System.out.println("Input does not exist or is not found");
        }finally {
            if(scan!=null) {
                scan.close();   
            }
        }
    }
}
