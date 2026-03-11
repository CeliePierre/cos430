/**
 * File: PhraseRanking.java
 ************************************************************************
 *                     Revision History (newest first)
 ************************************************************************
 * 11/28/2023 - James Tedder finished rankPhrase
 * 11/28/2023 - Courtney Jackson finished main
 * 12/12/2023 - James Tedder and Courtney Jackson attempted to fix bugs
 ************************************************************************
 */
package student;

/**
 *@author Courtney Jackson and James Tedder
 */
public class PhraseRanking {
    /**
     * Determines the rank of the phrase
     * @param lyrics the lyrics for the song
     * @param lyricsPhrase the lyric phrase you are looking for
     * @return and int that show the length between the first and last word in
     * the phrase in the song.
     * @author James Tedder
     */
    static int rankPhrase(String lyrics, String lyricsPhrase) {
        lyrics = lyrics.replaceAll("\n", " ");
        lyrics = lyrics.replaceAll("\r", " ");
        lyrics = lyrics.toLowerCase().replaceAll("\\!", " ");
        lyrics = lyrics.replaceAll("\\.", " ");
        lyrics = lyrics.replaceAll("\\?", " ");
        lyrics = lyrics.replaceAll("\\,", " ");
        lyrics = lyrics.replaceAll("\\'", " ");
        lyrics = lyrics.replaceAll("\n", "  ");
        lyrics = lyrics.replaceAll("\"", "  ");
        
        lyricsPhrase = lyricsPhrase.replaceAll("\n", "  ");
        lyricsPhrase = lyricsPhrase.replaceAll("\r", "  ");
        lyricsPhrase = lyricsPhrase.replaceAll("\\.", " ");
        lyricsPhrase = lyricsPhrase.replaceAll("\\?", " ");
        lyricsPhrase = lyricsPhrase.replaceAll("\\!", " ");
        lyricsPhrase = lyricsPhrase.replaceAll("\\'", " ");
        lyricsPhrase = lyricsPhrase.replaceAll("\"", " ");
        lyricsPhrase = lyricsPhrase.replaceAll("\\,", " ");
        String[] lyricsPhraseArray = lyricsPhrase.toLowerCase().split(" ");
        
        for (int i = 0; i < lyricsPhraseArray.length; i++) {
            lyricsPhraseArray[i] = String.format(" %s ", lyricsPhraseArray[i]);
            
        }
        int currIndex = lyrics.indexOf(lyricsPhraseArray[0]);
        int currRank = 10000;
        while (currIndex != -1) {
            int lastWordIndex = currIndex;
            int i = 1;
            while (i < lyricsPhraseArray.length && lastWordIndex != -1) {
                lastWordIndex = lyrics.indexOf(lyricsPhraseArray[i], lastWordIndex + 1);
                i++;
            }
            if (lastWordIndex != -1 && currRank > (lastWordIndex - currIndex)) {
                currRank = lastWordIndex - currIndex + lyricsPhraseArray[lyricsPhraseArray.length - 1].length();
            }

            currIndex = lyrics.indexOf(lyricsPhraseArray[0], currIndex + 1);

        }
        if (currRank == 10000) {
            currRank = -1;
        } else {
            currRank = currRank - 2;
        }
        return currRank;
    }
    /**
     * @param args 
     * @author Courtney Jackson
     */
    public static void main(String[] args) {
        SongCollection sc = new SongCollection(args[0]);
        String lyrics;
        int rank;
        SearchByLyricsWords sbyw = new SearchByLyricsWords(sc);
        Song[] matches = sbyw.search(args[1]);
        for (Song song : matches) {
            lyrics = song.getLyrics();
            rank = rankPhrase(lyrics, args[1]);
            if (rank != -1) {
                System.out.println(rank + " " + song.toString());
            }
        }

    }

}
