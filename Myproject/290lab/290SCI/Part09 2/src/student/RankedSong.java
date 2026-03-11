package student;

import java.util.Arrays;

/**
 *
 * @author kalalajoel
 */
public class RankedSong implements Comparable<RankedSong> {
    private int rank;
    private Song song;

    public RankedSong(int rank, Song song) {
        this.rank = rank;
        this.song = song;
    }

    @Override
    public int compareTo(RankedSong other) {
        return Integer.compare(this.rank, other.rank); // For ascending order of rank
    }

    @Override
    public String toString() {
        // Assuming you want to display rank, song title, and artist
        return String.format("%4d - %-20s by %s", rank, song.getTitle(), song.getArtist());
    }

    // Getters
    public int getRank() {
        return rank;
    }

    public Song getSong() {
        return song;
    }
}