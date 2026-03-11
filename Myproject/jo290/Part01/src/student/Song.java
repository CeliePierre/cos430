/**
 * File: Song.java
 * **********************************************************************
 *                     Revision History (newest first)
 ************************************************************************
 * 9.2023 - James Tedder - I updated the compareTo
 * I added private member variables for the artist, title, and lyrics. I initialized them in the constructor
 * I implemented the toString() method as specified.
 * I implemented the compareTo() method. First, it compares the artist of this song to the artist of that song.
 * If they are not the same (i.e., the result is not 0), it returns the
 * I updated the Comparable interface to be type-specific (Comparable<Song>) for
 * type safety
 *
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
     *
     * @param song2
     * @return a negative number, positive number or 0 depending on whether this
     * song should be before, after or is the same. Used for a "natural" sorting
     * order. In this case first by author then by title so that the all of an
     * artist's songs are together, but in alpha order. Follow the given
     * example.
     * @author kalalajoel The compareTo method compares song objects based on
     * their artists and their titles ,retuning a negative value if the current
     * song should come before the other song and a positive values if it should
     * come after and 0 if they are equal. Updated: James Tedder: I changed the
     * compareTo to only have one return statement and to not throw any
     * exceptions.
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
     * testing method to unit test this class
     *
     * @param args
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
    }
}
