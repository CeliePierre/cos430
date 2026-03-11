package student;

/**
 *
 * @author kalalajoel
 */
import java.util.*;

public class SearchByLyricsWords {

    private TreeMap<String, TreeSet<Song>> lyricsMap;
    private Set<String> commonWords;

    public SearchByLyricsWords(SongCollection songCollection) {
        lyricsMap = new TreeMap<>();
        commonWords = loadCommonWords(); // Assume this method loads the common words

        for (Song song : songCollection.getAllSongs()) {
            String lyrics = song.getLyrics().toLowerCase();
            String[] words = lyrics.split("[^a-zA-Z]+");

            for (String word : words) {
                if (!commonWords.contains(word) && word.length() > 1) {
                    lyricsMap.computeIfAbsent(word, k -> new TreeSet<>()).add(song);
                }
            }
        }
    }

    private Set<String> loadCommonWords() {
        // Placeholder for loading common words. You can load this from a file or initialize it directly.
        return new HashSet<>(Arrays.asList("to","the", "and", "a", "in", "it", "is", "you", "that", "he", "was", "for", "on", "are", "with", "as", "I", "his", "they", "be", "at", "one", "have", "this", "from", "or", "had", "by", "not", "word", "but", "what", "some", "we", "can", "out", "other", "were", "all", "there", "when", "up", "use", "your", "how", "said", "an", "each", "she"));
    }

    public void statistics() {
        // Print the number of keys in the map
        System.out.println("Number of keys in the map: " + lyricsMap.size());

        // Calculate and print the total number of Song references stored in all the sets in the map
        long totalSongReferences = lyricsMap.values().stream()
                .mapToLong(TreeSet::size)
                .sum();
        System.out.println("Total number of Song references: " + totalSongReferences);

        // Space used by the map (assuming each reference is 4 bytes and each tree overhead is 32 bytes)
        long mapSpace = lyricsMap.size() * (32L + 4);
        System.out.println("Estimated space used by the map: " + mapSpace + " bytes");

        // Space used by all the sets (assuming each TreeSet has 32 bytes overhead and each Song reference is 4 bytes)
        long setsSpace = lyricsMap.values().stream()
                .mapToLong(set -> 32L + set.size() * 4L)
                .sum();
        System.out.println("Estimated total space used by all the sets: " + setsSpace + " bytes");

        // Include additional statistics gathering as required by your assignment here
    }

    public void top10words() {
        // Create a priority queue that will keep the largest entries on top
        PriorityQueue<Map.Entry<String, TreeSet<Song>>> pq = new PriorityQueue<>(
                (e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()));
  //System.out.println("Debug: Inside top10words method");
        // Add all entries from the lyrics map
        pq.addAll(lyricsMap.entrySet());

        // Print the top 10 words with their counts
        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            Map.Entry<String, TreeSet<Song>> entry = pq.poll();
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }
    }

    // Example main method for testing
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java SearchByLyricsWords <songFile> [-top10words]");
            return;
        }

        String songFile = args[0];
        boolean top10WordsFlag = false;

        // Check for "-top10words" argument
        for (String arg : args) {
            if ("-top10words".equals(arg)) {
                top10WordsFlag = true;
                break;
            }
        }

        // Create a song collection from the given file
        SongCollection songCollection = new SongCollection(songFile);

        // Create SearchByLyricsWords object with the song collection
        SearchByLyricsWords search = new SearchByLyricsWords(songCollection);

        // Call statistics method to print out statistics
        search.statistics();

        // If "-top10words" flag is set, call top10words method
        if (top10WordsFlag) {
            search.top10words();
        }
    }
}
