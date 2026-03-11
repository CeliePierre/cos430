package student;

/**
 *
 * @author kalalajoel
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class PhraseRanking {

    public static int findExactWord(String word, int startIndex, String lyrics) {
        int wordStart = lyrics.indexOf(word, startIndex);

        while (wordStart != -1) {
            int wordEnd = wordStart + word.length();
            boolean frontSpace = wordStart == 0 || !Character.isLetter(lyrics.charAt(wordStart - 1));
            boolean backSpace = wordEnd == lyrics.length() || !Character.isLetter(lyrics.charAt(wordEnd));

            if (frontSpace && backSpace) {
                return wordStart; // Found the word as a standalone word
            }

            // Search for the next occurrence of the word
            wordStart = lyrics.indexOf(word, wordEnd);
        }
        return -1;
    }

    public static int rankPhrase(String lyrics, String lyricsPhrase) {
        String changedLyrics = lyrics.toLowerCase().replace("\n", " ").replace("\r", " ");
        lyricsPhrase = lyricsPhrase.toLowerCase();
        String[] words = lyricsPhrase.split("[^a-zA-Z]+");

        int startPos = 0;
        boolean allWordsFound = true;

        while (startPos != -1 && allWordsFound) {
            int endPos = startPos;

            for (int i = 0; i < words.length && endPos >= 0; i++) {
                endPos = findExactWord(words[i], endPos, changedLyrics);
                if (endPos == -1) {
                    allWordsFound = false;
                    break; // Word not found as standalone, exit the loop
                }
                endPos += words[i].length(); // Move to the end of the found word
            }

            if (allWordsFound) {
                int phraseStart = changedLyrics.indexOf(words[0], startPos);
                int phraseEnd = endPos;
                return phraseEnd - phraseStart; // Rank of the phrase
            }
        }

        return -1;
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
