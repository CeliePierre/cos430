/**
 * File: SongCollection.java
 ************************************************************************
 *                     Revision History (newest first)
 ************************************************************************
 *
 * 09.16.2023 Courtney Jackson and Yursa Mugorewase fixed constructor and sorting method(sorting method so it is one line)
 * 09.13.2023 Courtney Jackson added sorting method
 * 09.09.2023 Courtney Jackson and Yursa Mugorewase worked with the gui
 * 09.08.2023 Courtney Jackson and Yursa Mugorewase completed the song collection class
 * 8.2016 - Anne Applin - formatting and JavaDoc skeletons added
 * 2015 -   Prof. Bob Boothe - Starting code and main for testing
 ************************************************************************
 */
package student;

import java.util.stream.Stream;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * SongCollection.java Reads the specified data file and build an array of
 * songs.
 *
 * @author boothe, Courtney Jackson, and Yursa Mugorewase
 */
public class SongCollection {

    private Song[] songs;

    /**
     * Note: in any other language, reading input inside a class is simply not
     * done!! No I/O inside classes because you would normally provide
     * precompiled classes and I/O is OS and Machine dependent and therefore not
     * portable. Java runs on a virtual machine that IS portable. So this is
     * permissable because we are programming in Java and Java runs on a virtual
     * machine not directly on the hardware.
     *
     * @author Courtney Jackson and Yursa Mugorewase
     * @param filename The path and filename to the datafile that we are using
     * must be set in the Project Properties as an argument.
     */
    public SongCollection(String filename) {
        String artist = "";
        String title = "";
        String lyrics = "";
        ArrayList<Song> songs1 = new ArrayList<Song>();

        try {
            Scanner scan = new Scanner(new File(filename)); //Initializes the scanner
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.startsWith("ARTIST")) { //Searches for where ARTIST appears
                    artist = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                    line = scan.nextLine(); //moves the scanner to read the next line
                }
                if (line.startsWith("TITLE")) { //Searches for where TITLE appears
                    title = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                    line = scan.nextLine();  //moves the scanner to read the next line
                }
                if (line.startsWith("LYRICS")) {
                    lyrics = line.substring(line.indexOf("\""));
                    line = scan.nextLine();
                    while (!line.equals("\"")) { //searches until the end of the lyrics
                        lyrics = lyrics + "\n" + line;
                        line = scan.nextLine();  //moves the scanner to read the next line
                    }
                }
                Song addingSong = new Song(artist, title, lyrics); //create a song
                songs1.add(addingSong); //adds song to arraylist
            }
            scan.close();
            songs = new Song[songs1.size()];
            songs = songs1.toArray(songs);
            Arrays.sort(songs);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * this is used as the data source for building other data structures
     *
     * @return the songs array
     */
    public Song[] getAllSongs() {
        return songs;
    }

    /**
     * unit testing method
     *
     * @author Courtney Jackson and Yursa Mugorewase
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog songfile");
            return;
        }

        SongCollection sc = new SongCollection(args[0]);
        System.out.println("Total songs = " + sc.getAllSongs().length + ", first songs:");
        Stream.of(sc.getAllSongs()).limit(10).forEach(System.out::println);
    }
}
