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
        String[] words = lyricsWords.toLowerCase().split("[^a-zA-Z]+");
        TreeSet<Song> result = new TreeSet<>();

        boolean isFirstWord = true;
        for (String word : words) {
            if (word.length() > 1 && !commonWordsSet.contains(word)) {
                TreeSet<Song> songsForWord = lyricsWordMap.get(word);
                if (songsForWord == null) {
                    // If any word is not found in the map, return an empty array
                    return new Song[0];
                }
                if (isFirstWord) {
                    // For the first valid word, initialize the result set
                    result.addAll(songsForWord);
                    isFirstWord = false;
                } else {
                    // Intersect the result set with the set for the current word
                    result.retainAll(songsForWord);
                }
            }
        }

        return result.toArray(new Song[0]);
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
        int totalIndexTerms = 0;

        for (Song song : songs) {
            String[] lyrics = song.getLyrics().toLowerCase().split("[^a-zA-Z]+");
            TreeSet<String> lyricsWordsSet = new TreeSet<>(Arrays.asList(lyrics));

            // Remove word short or are common words
            lyricsWordsSet.removeIf(word -> word.length() <= 1 || commonWordsSet.contains(word));

            totalIndexTerms += lyricsWordsSet.size(); // Update total index terms

            for (String word : lyricsWordsSet) {
                if (map.containsKey(word)) {
                    // If word is already a key, get the corresponding set and add the song
                    TreeSet<Song> songSet = map.get(word);
                    songSet.add(song);
                    map.put(word, songSet); // Update the map with the modified set
                } else {
                    // If word is not a key, create a new set, add the song, and put it in the map
                    TreeSet<Song> newSongSet = new TreeSet<>();
                    newSongSet.add(song);
                    map.put(word, newSongSet);
                }

            }
        }

        //System.out.println("Total index terms: " + totalIndexTerms);
        return map;
    }

    // Method to gather and display statistics about the data structure
    public void statistics() {
        // The total number of songs in the collection
        int numberOfSongs = this.songs.length; // Use the 'songs' array

        // The number of unique words (keys in the map)
        int numberOfKeys = lyricsWordMap.size();

        // The total number of song references (count each song once for each key it appears under)
        long totalSongReferences = lyricsWordMap.values().stream()
                .mapToLong(TreeSet::size)
                .sum();
       
        // Calculate averages
        double averageSongRefsPerSong = (double) totalSongReferences / numberOfSongs;
        double averageSongRefsPerKey = (double) totalSongReferences / numberOfKeys;

        // Print the statistics with formatted numbers
          System.out.println(".lyrics map statistics:");
        System.out.println("a. total indexing terms: " + totalIndexTerms);
        System.out.println("b. average indexing terms/song: " + averageSongRefsPerSong);
        System.out.println("c. number of keys in map: " + numberOfKeys);
        System.out.println("d. total mapped song references: " + totalSongReferences);
        System.out.println("e. average song refs per key: " + averageSongRefsPerKey);
    }

    // Main method and other class methods...
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

        Song[] foundSongs = searchByLyricsWords.search(lyricWords);

        System.out.println("Total number of matches: " + foundSongs.length);
        System.out.println("First 10 matches (or fewer, if less than 10 matches found):");
        for (int i = 0; i < Math.min(foundSongs.length, 10); i++) {
            Song song = foundSongs[i];
            System.out.println((i + 1) + ". Artist: " + song.getArtist() + ", Title: " + song.getTitle());
        }
    } catch (Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        e.printStackTrace();
    }
}
}