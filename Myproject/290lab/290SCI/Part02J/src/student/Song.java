/**
 * File: Song.java
 * **********************************************************************
 *                     Revision History (newest first)
 ************************************************************************
 *
 * 9.2023 -Joel Kalala - I added private member variables for the artist, title, and lyrics. 
 * I initialized them in the constructor
 * I implemented the toString() method as specified.
 * I implemented the compareTo() method. First, it compares the artist of this song to the artist of that song.
 * If they are not the same (i.e., the result is not 0), it returns the
 * I updated the Comparable interface to be type-specific (Comparable<Song>) for
 * type safety
 * I added The CmpCnt class is designed to keep track of the number of comparisons performed
 * I tested the compareTo() method of the Song class
 * 
 * 9.2023 - Yusra Mugorewase i Compared two Song objects based on their artist's name 
 * 9.2023 - Joel Kalala - I added the constructor, getter methods, toString,
 * compareTo, and javadoc. 8.2016 - Anne Applin - formatting and JavaDoc
 * skeletons added 2015 - Prof. Bob Boothe - Starting code and main for testing
 *
 ************************************************************************
 */
package student;

import java.util.*;

public class Song implements Comparable {

    // private fields
    private String artist;
    private String title;
    private String lyrics;

    /**
     * Parameterized constructor
     *
     * @param artist the author of the song
     * @param title the title of the song
     * @param lyrics the lyrics as a string with linefeeds embedded we define a
     * class Song with three private instance variable : artist,title and
     * lyrics.that take three parameters and initializes the instance variable
     * @author kalalajoel
     *
     */
    public Song(String artist, String title, String lyrics) {
        this.artist = artist;
        this.title = title;
        this.lyrics = lyrics;
    }

    /**
     * return the artist
     *
     * @return artist
     * @author kalalajoel
     */
    public String getArtist() {
        return artist;
    }

    /**
     * return the lyrics
     *
     * @return lyrics
     * @author kalalajoel
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * return title
     *
     * @return title
     * @author kalalajoel
     */
    public String getTitle() {
        return title;
    }

    /**
     * returns name and title ONLY on one line in the form: artist, "title"
     *
     * @return a formatted string with comma and quotes added
     * @author kalalajoel
     */
    @Override
    public String toString() {
        return artist + ", \"" + title + "\"";
    }

    /**
     * the default comparison of songs primary key: artist, secondary key: title
     * used for sorting and searching the song array if two songs have the same
     * artist and title they are considered the same
     * @param that
     * @param song2
     * @return a negative number, positive number or 0 depending on whether this
     * song should be before, after or is the same. Used for a "natural" sorting
     * order. In this case first by author then by title so that the all of an
     * artist's songs are together, but in alpha order. Follow the given
     * example.
     * @author Joelkalala The compareTo method compares song objects based on
     * their artists and their titles ,retuning a negative value if the current
     * song should come before the other song and a positive values if it should
     * come after and 0 if they are equal.
     */
    @Override
    public int compareTo(Object that) {
        Song otherSong = (Song) that;
        int comparison = 0;
        if (that == null) {
            comparison = 1;
        } else if (this == that) {
            comparison = 0;
        } else {
            comparison = this.artist.compareToIgnoreCase(otherSong.artist);
            if (comparison == 0) {
                comparison = this.title.compareToIgnoreCase(otherSong.title);
            }
        }
        return comparison;
    }

        /**
         * 
         * @author Yusra Mugorewase
         * Compare two Song objects based on their artist's name (case-insensitively).
         * @ param s1 The first song to be compared.
         * @ param s2 The second song to be compared.
         * @ return a negative integer, zero, or a positive integer if the artist of the first song 
         * is lexicographically less than, equal to, or greater than the artist of the second song.
         * @author Joelkalala
         *  The CmpCnt class is designed to keep track of the number of comparisons performed.
         * provides methods to retrieve and reset the comparison count.
         * Holds the count of comparisons made.
         *  @ return the number of comparisons made.
         * Reset the comparison count to zero.
          */
        
         
       public static class CmpArtist extends CmpCnt 
                     implements Comparator<Song> {
        @Override
        public int compare(Song s1, Song s2) {
            cmpCnt++;
            return s1.artist.compareToIgnoreCase(s2.artist);
        }
    }

            /**
             * testing method to unit test this class
             *
             * @param args
             * @author Joelkalala
             * I tested the compareTo() method of the Song class
             *  I tested the CmpArtist comparator class
             * I Created a CmpArtist object to compare songs by their artist
             */

        public static void main(String[] args) {
        Song s1 = new Song("Professor B",
                "Small Steps",
                "Write your programs in small steps\n"
                + "small steps, small steps\n"
                + "Write your programs in small steps\n"
                + "Test and debug every step of the way.\n");

        Song s2 = new Song("Brian Dill",
                "Ode to Bobby B",
                "Professor Bobby B., can't you see,\n"
                + "sometimes your data structures mystify me,\n"
                + "the biggest algorithm pro since Donald Knuth,\n"
                + "here he is, he's Robert Boothe!\n");

        Song s3 = new Song("Professor B",
                "Debugger Love",
                "I didn't used to like her\n"
                + "I stuck with what I knew\n"
                + "She was waiting there to help me,\n"
                + "but I always thought print would do\n\n"
                + "Debugger love .........\n"
                + "Now I'm so in love with you\n");

          System.out.println("testing getArtist: " + s1.getArtist());
        System.out.println("testing getTitle: " + s1.getTitle());
        System.out.println("testing getLyrics:\n" + s1.getLyrics());

        System.out.println("testing toString:\n");
        System.out.println("Song 1: " + s1);
        System.out.println("Song 2: " + s2);
        System.out.println("Song 3: " + s3);

        System.out.println("testing compareTo:");
        System.out.println("Song1 vs Song2 = " + s1.compareTo(s2));
        System.out.println("Song2 vs Song1 = " + s2.compareTo(s1));
        System.out.println("Song1 vs Song3 = " + s1.compareTo(s3));
        System.out.println("Song3 vs Song1 = " + s3.compareTo(s1));
        System.out.println("Song1 vs Song1 = " + s1.compareTo(s1));
        
        
        // comparator test
        CmpArtist cmp = new CmpArtist();
        Song s4 = new Song("Prof","Debugger Love2", "");
        System.out.printf("\nComparator s1 vs s2: %d\n", cmp.compare(s1, s2));
        System.out.printf("Comparator s2 vs s1: %d\n", cmp.compare(s2, s1));
        System.out.printf("Comparator s1 vs s3: %d\n", cmp.compare(s1, s3));
        
    }
}
