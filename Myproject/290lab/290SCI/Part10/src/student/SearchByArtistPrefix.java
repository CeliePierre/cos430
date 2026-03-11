package student;


/**
 *
 * @author kalalajoel
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class SearchByArtistPrefix {

    private Song[] songs; // Direct reference to the song array
    private TreeMap<String, TreeSet<Song>> artistPrefixMap = new TreeMap<>();
    private long startTime;
    private long endTime;

    public SearchByArtistPrefix(SongCollection sc) {
        startTime = System.currentTimeMillis();
        songs = sc.getAllSongs();
        buildArtistPrefixMap();
        endTime = System.currentTimeMillis();
        printStatistics();
    }
    
    public Song[] search(String prefix) {
    // Convert the prefix to lower case to ensure case-insensitive search
    prefix = prefix.toLowerCase();

    // Use a TreeSet to store the matching songs and avoid duplicates
    TreeSet<Song> matchingSongs = new TreeSet<>();

    // Iterate over each entry in the artistPrefixMap
    for (Map.Entry<String, TreeSet<Song>> entry : artistPrefixMap.entrySet()) {
        // Check if the key (artist prefix) starts with the given prefix
        if (entry.getKey().startsWith(prefix)) {
            // If it does, add all songs under this prefix to the matchingSongs set
            matchingSongs.addAll(entry.getValue());
        }
    }

    // Convert the set of songs to an array and return it
    return matchingSongs.toArray(new Song[0]);
}

    private void buildArtistPrefixMap() {
        for (Song song : songs) {
        String artistName = song.getArtist().toLowerCase();

        // Create all possible prefixes of the artist's name and add them to the map
        for (int i = 1; i <= artistName.length(); i++) {
            String prefix = artistName.substring(0, i);
            artistPrefixMap.computeIfAbsent(prefix, k -> new TreeSet<>()).add(song);
        }
    
        }
    }

    public Song[] searchByArtistPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        TreeSet<Song> result = artistPrefixMap.get(prefix);

        if (result != null) {
            return result.toArray(new Song[0]);
        } else {
            return new Song[0]; // Return an empty array if no match is found
        }
    }

      private void printStatistics() {
          
          int N = songs.length;

        // Print N in base 10
        System.out.println("N for this data is " + N);

        // Convert N to binary and pad with leading zeros for 16-bit representation
        String binaryRepresentation = Integer.toBinaryString(N);
        binaryRepresentation = String.format("%16s", binaryRepresentation).replace(' ', '0');

        // Manually format the binary string with spaces
        String formattedBinary = "";
        for (int i = 0; i < binaryRepresentation.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formattedBinary += " ";
            }
            formattedBinary += binaryRepresentation.charAt(i);
        }
        System.out.println(N + " base 10 == " + formattedBinary + " base 2");

        // Normalize to get the exponent (log base 2)
        int exponent = (int) (Math.log(N) / Math.log(2)); // This is a simplification

        // Include a line break in the normalization output
        String normalizedBinary = "1." + binaryRepresentation.substring(1);
        System.out.println("normalize to get the exponent ");
        System.out.println(normalizedBinary + " X 2^"+ exponent);

        System.out.println("The exponent is the log base 2");

        // Time taken for building the artist prefix map
       // long timeTaken = endTime - startTime;
        //System.out.println("Time taken: " + timeTaken + "ms");
    }

    // ... other methods ...
}