/**
 * File: SearchByLyricsPhrase.java
 ************************************************************************
 *                     Revision History (newest first)
 ************************************************************************
 * 12/12/2023 - James Tedder wrote constructor
 * 12/12/2023 - Courtney Jackson wrote search method
 * 12/12/2023 - James Tedder and Courtney Jackson wrote main method
 ************************************************************************
 */
package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 *
 * @author James Tedder and Courtney Jackson
 */
public class SearchByLyricsPhrase {

    Map<String, TreeSet<Song>> tm = new TreeMap<>();
/**
 * Creates a constructor for the class
 * @param sc a SongCollection
 * @author James Tedder
 */
    public SearchByLyricsPhrase(SongCollection sc) {
        Song[] songs = sc.getAllSongs();
        for (Song s : songs) {
            String[] words = s.getLyrics().toLowerCase().split("[^a-zA-Z]+");
            TreeSet<String> allWords = new TreeSet<>();
            for (String word : words) {
                if (word.length() > 1) {
                    allWords.add(word);
                }
            }
            for (String word : allWords) {
                if (tm.containsKey(word)) {
                    tm.get(word).add(s);
                } else {
                    TreeSet<Song> ts = new TreeSet<>();
                    ts.add(s);
                    tm.put(word.toLowerCase(), ts);
                }
            }
        }
    }
/**
 * 
 * @param lyricsPhrase the lyrics phrase you are looking for
 * @author Courtney Jackson
 * @return Song[] containing all the songs that contain that lyric phrase
 */
    public Song[] search(String lyricsPhrase) {
        TreeSet<Song> ts = new TreeSet<>();
        String[] lyricWordsSplit = lyricsPhrase.toLowerCase().split("[^a-zA-Z]+");

        Map<Integer, TreeSet<Song>> tm1 = new TreeMap<>();

        ts = (TreeSet<Song>) tm.get(lyricWordsSplit[0]);
        for (int i = 1; i < lyricWordsSplit.length; i++) {
            if (lyricWordsSplit[i].length() > 1) {
                ts.retainAll(tm.get(lyricWordsSplit[i]));
            }
        }
        for (Song song : ts) {
            String lyrics = song.getLyrics();
            int rank = PhraseRanking.rankPhrase(lyrics, lyricsPhrase);
            if (rank > 1) {
                if (tm1.containsKey(rank)) {
                    tm1.get(rank).add(song);
                } else {
                    TreeSet<Song> ts1 = new TreeSet<>();
                    ts1.add(song);
                    tm1.put(rank, ts1);
                }
            }
        }
        ArrayList<Song> retArrayList = new ArrayList<>();
        TreeSet<Song> ts2 = new TreeSet<>();
        for (int i : tm1.keySet()) {
            ts2.addAll(tm1.get(i));
            for (Song song : tm1.get(i)) {
                retArrayList.add(song);
            }
        }
        Song[] retArray = new Song[retArrayList.size()];
        for (int i = 0; i < retArrayList.size(); i++) {
            retArray[i] = retArrayList.get(i);
        }
        return retArray;
    }

    /**
     * 
     * @param args 
     * @author Courtney Jackson and James Tedder
     */
    public static void main(String[] args) {
        SongCollection sc = new SongCollection(args[0]);
        SearchByLyricsPhrase sblp = new SearchByLyricsPhrase(sc);
        if (args.length > 1) {
            System.out.println("searching for: " + args[1]);
            Song[] byLyricWords = sblp.search(args[1]);

            System.out.println("Total Number of Matches: " + byLyricWords.length);
            Stream.of(byLyricWords).limit(10).forEach(System.out::println);
        }
    }
}