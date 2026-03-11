package student;

/**
 *
 * @author kalalajoel
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SearchByLyricsWords {

    private Song[] songs; // Direct reference to the song array
    private TreeSet<String> commonWordsSet = new TreeSet<String>(); // Set for common words
    private TreeMap<String, TreeSet<Song>> lyricsWordMap = null; // Map for lyrics to songs
    private int totalIndexTerms = 0;

    public SearchByLyricsWords(SongCollection sc) {
        if (lyricsWordMap != null) {
            return; // Already initialized
        }

        songs = sc.getAllSongs();
        // System.out.println("Number of songs: " + songs.length);

        buildSetOfCommonWords();
        lyricsWordMap = buildLyricsMap(); // No argument passed
    }

    public Song[] search(String lyricsWords) {
        // Print the search query
        System.out.println("Looking for: " + lyricsWords);

        // Split the lyricsWords into individual words and convert them to lowercase
        String[] words = lyricsWords.toLowerCase().split("[^a-zA-Z]+");
        Set<String> keySet = new TreeSet<>(Arrays.asList(words));

        // Store the original keywords for comparison
        Set<String> originalKeySet = new TreeSet<>(keySet);

        // Remove common words
        keySet.removeAll(commonWordsSet);

        // Print the common words that are ignored
        originalKeySet.removeAll(keySet); // This will leave only the common words in originalKeySet
        System.out.println("search ignores these common words: " + originalKeySet);

        TreeSet<Song> matchSet = new TreeSet<>();
        boolean isFirstWord = true;

        for (String word : keySet) {
            TreeSet<Song> songsForWord = lyricsWordMap.get(word);
            if (songsForWord == null) {
                continue; // Skip if no songs are found for this word
            }

            // Print the number of matches for each keyword
            System.out.println("key: " + word + " had " + songsForWord.size() + " matches");

            if (isFirstWord) {
                matchSet.addAll(songsForWord); // Initialize matchSet with the first valid word's songs
                isFirstWord = false;
            } else {
                matchSet.retainAll(songsForWord); // Intersect with current word's songs
            }
        }

        System.out.println("Total songs = " + matchSet.size());
        return matchSet.toArray(new Song[0]);
    }

    public void buildSetOfCommonWords() {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("commonWords.txt"));
            while (sc.hasNext()) {
                String word = sc.next();
                commonWordsSet.add(word); // Corrected from commonWordSet to commonWordsSet
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    private TreeMap<String, TreeSet<Song>> buildLyricsMap() {
        TreeMap<String, TreeSet<Song>> map = new TreeMap<>();

        // Reset totalIndexTerms for the class
        this.totalIndexTerms = 0;

        for (Song song : songs) {
            String[] lyrics = song.getLyrics().toLowerCase().split("[^a-zA-Z]+");
            TreeSet<String> lyricsWordsSet = new TreeSet<>(Arrays.asList(lyrics));

            // Remove words that are short or are common words
            lyricsWordsSet.removeIf(word -> word.length() <= 1 || commonWordsSet.contains(word));

            // Update total index terms for the class
            this.totalIndexTerms += lyricsWordsSet.size();

            for (String word : lyricsWordsSet) {
                if (map.containsKey(word)) {
                    TreeSet<Song> songSet = map.get(word);
                    songSet.add(song);
                    map.put(word, songSet);
                } else {
                    TreeSet<Song> newSongSet = new TreeSet<>();
                    newSongSet.add(song);
                    map.put(word, newSongSet);
                }
            }
        }

        // Print statistics directly after building the map
        printMapStatistics(map);

        return map;
    }

    // Method to gather and display statistics about the data structure
    private void printMapStatistics(TreeMap<String, TreeSet<Song>> map) {
        if (map == null) {
            System.out.println("Lyrics word map is not initialized.");
            return;
        }

        int numberOfSongs = songs.length;
        int numberOfKeys = map.size();
        long totalSongReferences = map.values().stream().mapToLong(TreeSet::size).sum();
        double averageIndexTermsPerSong = (double) totalIndexTerms / numberOfSongs;
        double averageSongRefsPerKey = (double) totalSongReferences / numberOfKeys;

        System.out.println("Lyrics map statistics:");
        System.out.println("  a. Total indexing terms: " + totalIndexTerms);
        System.out.println("  b. Average indexing terms/song: " + String.format("%.2f", averageIndexTermsPerSong));
        System.out.println("  c. Number of keys in map: " + numberOfKeys);
        System.out.println("  d. Total mapped song references: " + totalSongReferences);
        System.out.println("  e. Average song refs per key: " + String.format("%.2f", averageSongRefsPerKey));
    }

    public void top10words() {
        long startTime = System.currentTimeMillis();
        PriorityQueue<Map.Entry<String, TreeSet<Song>>> pq = new PriorityQueue<>(
                (e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()));

        pq.addAll(lyricsWordMap.entrySet());

        System.out.println("Extra Credit most common lyrics words:");
        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            Map.Entry<String, TreeSet<Song>> entry = pq.poll();
            System.out.println((i + 1) + ": " + entry.getKey() + " " + entry.getValue().size());

        }
        long endTime = System.currentTimeMillis(); // End time measurement
        long totalTime = endTime - startTime;
        System.out.println("Time : " + totalTime + "ms");
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java SearchByLyricsWords <songFile> <lyricWords>");
            return;
        }

        String songFile = args[0];
        String lyricWords = args[1];

        try {
            SongCollection songCollection = new SongCollection(songFile);
            SearchByLyricsWords searchByLyricsWords = new SearchByLyricsWords(songCollection);

            // The statistics will be printed during the initialization of searchByLyricsWords
            // Perform the search with the provided lyrics
            Song[] foundSongs = searchByLyricsWords.search(lyricWords);

            // Print out the total number of matches and details of the first 10 matches
            System.out.println("Total number of matches: " + foundSongs.length);
            for (int i = 0; i < Math.min(foundSongs.length, 10); i++) {
                Song song = foundSongs[i];
                System.out.println((i + 1) + ". Artist: " + song.getArtist() + ", Title: " + song.getTitle());
            }

            // Here you could add more code, for example, handling different operations,
            // additional searches, or other functionalities.
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
