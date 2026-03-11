package student;

/**
 *
 * @author kalalajoel
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class PhraseRanking {

    public static int rankPhrase(String lyrics, String lyricsPhrase) {
        // Initialize the best rank with maximum value
        int bestRank = Integer.MAX_VALUE;

        // Make the lyrics and lyrics phrase lower case for case-insensitive comparison
        String changedLyrics = lyrics.toLowerCase().replace("\n", " ").replace("\r", " ");
        lyricsPhrase = lyricsPhrase.toLowerCase();

        // Return the length of the lyrics phrase if it is directly contained in the lyrics
        if (changedLyrics.contains(lyricsPhrase)) {
            return lyricsPhrase.length();
        }

        // Split the lyrics phrase into words
        String[] words = lyricsPhrase.split("[^a-zA-Z]+");

        // Iterate over each word in the phrase
        int startIndex = 0;
        for (String word : words) {
            int wordStart = changedLyrics.indexOf(word, startIndex);
            if (wordStart == -1) {
                return -1; // Word not found
            }
            int wordEnd = wordStart + word.length();
            boolean frontSpace = (wordStart == 0) || !Character.isLetter(changedLyrics.charAt(wordStart - 1));
            boolean backSpace = (wordEnd == changedLyrics.length()) || !Character.isLetter(changedLyrics.charAt(wordEnd));

            // Check if the word is standalone
            if (frontSpace && backSpace) {
                startIndex = wordEnd; // Move to the end of the current word
            } else {
                return -1; // The word is part of another word, not a standalone match
            }
        }

        // Calculate the rank based on the start and end indices of the phrase
        if (startIndex != 0) {
            int phraseStart = changedLyrics.indexOf(words[0]);
            int phraseEnd = changedLyrics.lastIndexOf(words[words.length - 1]) + words[words.length - 1].length();
            bestRank = phraseEnd - phraseStart;
        }

        return bestRank == Integer.MAX_VALUE ? -1 : bestRank;
    }

    private static int findEndIndex(String lyrics, String[] phraseWords, int start) {
        int currentIdx = start;
        for (String word : phraseWords) {
            currentIdx = lyrics.indexOf(word, currentIdx);
            if (currentIdx == -1) {
                return -1;
            }
            currentIdx += word.length();
        }
        return currentIdx - 1;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java PhraseRanking <songFile> \"<lyricsPhrase>\"");
            System.exit(1);
        }

        // Initialize SongCollection with the first argument
        SongCollection sc = new SongCollection(args[0]); // Assuming args[0] is the song file path
        Song[] allSongs = sc.getAllSongs(); // Retrieve all songs

        // Initialize SearchByLyricsWords with the song collection
        SearchByLyricsWords sblw = new SearchByLyricsWords(sc);

        // Search for matches with the lyrics phrase (second argument)
        Song[] matches = sblw.search(args[1]);
        System.out.println("Length of matches: " + matches.length);

        // Create a list to store ranked songs
        ArrayList<RankedSong> rsList = new ArrayList<>();

        // Loop through each match and rank them
        for (int i = 0; i < matches.length; i++) {
            int rank = rankPhrase(matches[i].getLyrics(), args[1]); // Assuming a getLyrics method in Song class
            if (rank >= 0) { // Add the song to the list if rank is 0 or more
                RankedSong rs = new RankedSong(rank, matches[i]);
                rsList.add(rs);
            }
        }

        // Sort the list of ranked songs
        Collections.sort(rsList);

        // Convert the list to an array
        RankedSong[] rankedResult = new RankedSong[rsList.size()];
        rankedResult = rsList.toArray(rankedResult);

        System.out.println("Number of ranked songs: " + rankedResult.length);

        // Print each ranked song using Stream API
        Stream.of(rankedResult)
                .limit(10)
                .forEach(System.out::println);
    }
}
