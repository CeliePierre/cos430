package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static student.P10timer.quotes;

/****************************************************************************
 **                       Revision History
 *****************************************************************************
 * @author kalalajoel
 * 2023-12-10- I created class is initialized with a collection of songs
 * 2023-12-11- i created A lyrics phrase is provided to the search method.
 * 2023-12-14- I created The sorted list is converted back into an array of Song objects.
 * 2023-12-15- I created The main method, for testing purposes, prints out the total number of songs 
 * 
 * @author Yusra Mugorewase
 * 2013-12-14- I added each song, its lyrics are compared to the given lyrics phrase.
 * 2013-12-14- I the list of RankedSong objects is then sorted based on their rank, ensuring 
 * the best matches are at the top.
 * 2013-12-15 - I added array represents the final search results, ordered by best match first.
 */
public class SearchByLyricsPhrase {
    private Song[] songs;

    public SearchByLyricsPhrase(SongCollection sc) {
        this.songs = sc.getAllSongs();
    }

    
     public Song[] search(String lyricsPhrase) {
        List<RankedSong> matchedSongs = new ArrayList<>();

        for (Song song : songs) {
            int rank = PhraseRanking.rankPhrase(song.getLyrics(), lyricsPhrase);
            if (rank >= 0) {
                matchedSongs.add(new RankedSong(rank, song));
            }
        }

        matchedSongs.sort(Comparator.comparingInt(RankedSong::getRank));
        return matchedSongs.stream().map(RankedSong::getSong).toArray(Song[]::new);
    }

    private int calculateRank(String lyrics, String phrase) {
        // Implement a more accurate ranking logic
        // Placeholder example: frequency of phrase in lyrics
        int count = 0, index = 0;
        while ((index = lyrics.indexOf(phrase, index)) != -1) {
            count++;
            index += phrase.length();
        }
        return count;
    }

    
     public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java SearchByLyricsPhrase <songFile> <lyricsPhrase>");
            System.exit(1);
        }

        SongCollection sc = new SongCollection(args[0]);
        SearchByLyricsPhrase searchByLP = new SearchByLyricsPhrase(sc);

        Song[] songList = searchByLP.search(args[1]);
        System.out.println("Total songs found: " + songList.length);

        // Print out the results
        for (int i = 0; i < Math.min(10, songList.length); i++) {
            Song song = songList[i];
            int rank = PhraseRanking.rankPhrase(song.getLyrics(), args[1]);
            System.out.printf("Rank: %d Artist: %s Title: \"%s\"\n", rank, song.getArtist(), song.getTitle());
        }
    }
}