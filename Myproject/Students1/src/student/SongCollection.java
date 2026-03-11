/**
 * File: SongCollection.java
 ************************************************************************
 *                     Revision History (newest first)
 ************************************************************************
 * 
 * 8.2016 - Anne Applin - formatting and JavaDoc skeletons added   
 * 2015 -   Prof. Bob Boothe - Starting code and main for testing  
 ************************************************************************
 */

 package student;

import java.util.stream.Stream;
 import java.util.*;
import java.nio.file.*;
import java.io.*;

/**
 * SongCollection.java 
 * Reads the specified data file and build an array of songs.
 * @author boothe
 */
public class SongCollection {

    private Song[] songs;

    /**
     * Note: in any other language, reading input inside a class is simply not
     * done!! No I/O inside classes because you would normally provide
     * precompiled classes and I/O is OS and Machine dependent and therefore 
     * not portable. Java runs on a virtual machine that IS portable. So this 
     * is permissable because we are programming in Java and Java runs on a 
     * virtual machine not directly on the hardware.
     *
     * @param filename The path and filename to the datafile that we are using
     * must be set in the Project Properties as an argument.
     */
    public SongCollection(String filename) {

	// use a try catch block
        // read in the song file and build the songs array
        // there are several ways to read in the lyrics correctly.  
        // the line feeds between lines and the blank lines between verses
        // must be retained.
        
        
        // sort the songs array using Arrays.sort (see the Java API)
        // this will use the compareTo() in Song to do the job.
        
        
          List<Song> songList = new ArrayList<>();
          
          
        
          try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("ARTIST=")) {
                    String artist = lines.get(++i);
                     System.out.println(" artist: " + artist);
                    if (i + 1 < lines.size() && lines.get(i + 1).startsWith("TITLE=")) {
                        String title = lines.get(++i);
                        StringBuilder lyrics = new StringBuilder();
                        while (i + 1 < lines.size() && !lines.get(i + 1).startsWith("ARTIST=")) {
                            lyrics.append(lines.get(++i)).append("\n");
                        }
                        //songList.add(new Song("artist", "title", "lyrics"));
                    }
                }
            }

            songs = new Song[songList.size()];
            songs = songList.toArray(songs);
            Arrays.sort(songs);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
          
          
    /**
     * this is used as the data source for building other data structures
     * @return the songs array
     */
    public Song[] getAllSongs() {
        return songs;
    }
 
    /**
     * unit testing method
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage  java allSongs input");
            return;
        }

        SongCollection sc = new SongCollection(args[0]);
        System.out.println("Total Songs= " + sc.getAllSongs().length);
        
        Stream.of(sc.getAllSongs()).limit(10).forEach(System.out::println);
    }
}

