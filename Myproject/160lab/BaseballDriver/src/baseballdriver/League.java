/** *********************************************************************
 * REVISION HISTORY (Newest First)
 * **********************************************************************
 *
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ********************************************************************** */
package baseballdriver;

import java.util.*;
import java.io.FileNotFoundException;

/**
 * A class to hold a number of team rosters for the teams of a league. You must
 * use a Map data member to hold the Team objects, either a HashMap or a TreeMap
 * may be used. You must
 * <ol>
 * <li> code a constructor that takes a Scanner object and which loads the Map
 * data member with the collection of Team objects from the file given by the
 * Scanner.</li>
 * <li> code a public method String lookupPlayer(String t, int n) that returns
 * the appropriate choice from the following three results
 * <ol>
 * <li>No team named t is in the league.</li>
 * <li>No player with number n is on the roster for the t.</li>
 * <li><code><player stats for player n of team t></code></li>
 * </ol>
 * where the format of the last is given in the program assignment.</li>
 * <li> code a public method String calcPitchingStats(String t) that returns the
 * appropriate choice from the following three results</li>
 * <ol>
 * <li>No team named t is in the league.</li>
 * <li>The t appear to have no pitchers at this time.</li>
 * <li><code><pitching stats for t></code></li>
 * where the format of the last is given in the program assignment.</li>
 * <li> code a public method String calcHittingStats(String t) that returns the
 * appropriate choice from the following three results</li>
 * <ol>
 * <li>No team named t is in the league.<li>
 * <li>The t appear to have no hitters at this time.</li>
 * <li><code><hitting stats for t></code></li>
 * where the format of the last is given in the program assignment. The other
 * two methods are extra credit options that you may do if you wish. Consult the
 * program assignment for their specifications.</li>
 * </ol>
 *
 * @author David Briggs
 */
public class League {
    

    //Properties 
    //private Map<String, Team> teams;
       private Map<String, Team> teams;
 
        
            public League() {
        teams = new HashMap<>();
        
        
        
    }





    /**
     * Constructor for League. Reads the input file given and validated on the
     * command line.
     *
     * @param s a Scanner object that has already been connected to a physical
     * file in the driver.
     */
    public League(Scanner s) {

        // initialize TreeMap
    // initialize TreeMap
    teams = new TreeMap<>();

    try {
        // open the file using a Scanner object
        Scanner fileScanner = new Scanner(new File("teams.txt"));

        while (fileScanner.hasNextLine()) {
            String teamName = fileScanner.nextLine();
            Team team = new Team(teamName, new TreeMap<Integer, Player>());

            int playerNum = fileScanner.nextInt();
            while (playerNum != -1) {
                // get common player data attributes
                int playerPosition = fileScanner.nextInt();
                String playerName = fileScanner.next();
                boolean throwsRightHanded = fileScanner.nextBoolean();
                int plateAppearances = fileScanner.nextInt();
                int walks = fileScanner.nextInt();
                int strikeouts = fileScanner.nextInt();
                int hits = fileScanner.nextInt();

                if (playerPosition == 1) {
                    // get pitcher-specific attributes
                    int inningsPitched = fileScanner.nextInt();
                    int earnedRuns = fileScanner.nextInt();
                    Pitcher player = new Pitcher(playerNum, playerName, playerPosition, throwsRightHanded,
                            plateAppearances, walks, strikeouts, hits, inningsPitched, earnedRuns);
                    team.addPlayer(player);
                } else {
                    // get position player-specific attributes
                    int atBats = fileScanner.nextInt();
                    int hitsByPlayer = fileScanner.nextInt();
                    int runsBattedIn = fileScanner.nextInt();
                    int homeRuns = fileScanner.nextInt();
                    int hitByPitch = fileScanner.nextInt();
                    PositionPlayer player = new PositionPlayer(playerNum, playerName, playerPosition, throwsRightHanded,
                            plateAppearances, walks, strikeouts, hits, atBats, runsBattedIn, homeRuns, hitByPitch);

                    team.addPlayer(player);
                }

                playerNum = fileScanner.nextInt();
            }

            teams.put(teamName, team);
        }

        // close the file
        fileScanner.close();
    } catch (FileNotFoundException e) {
        System.out.println("Error: file not found.");
    }
}

public Team getTeam(String teamName) {
    return teams.get(teamName);
}

    
    /**
     * Lookup a specific player on a specific team. be sure to use ignore case
     * for all string inputs
     *
        * @param team a String representing the team name
        * @param playerNum an int representing the player we are looking up
        * @return a string representing the results of the lookup.
     */
    public String lookup(String team, int playerNum) {
          // Check if the team is in the league
    Team t = getTeam(team);
    if (t == null) {
        return "No team named " + team + " is in the league.";
    }
    
    // Look up the player in the team's roster
    Player p = t.getPlayer(playerNum);
    if (p == null) {
        return "No player with number " + playerNum + " is on the roster for the " + team + ".";
    }
    
    // Return the player's information
    return p.toString();
}

    /**
     * Calculate the pitching statistics for a given team be sure to use ignore
     * case for all string inputs
     *
     * @param team a String that is the team name - the key for the map
     * @return a String representing the results of the calculations
     */
    

    public String calcPitchingStats(String team) {
         // Check if the team is in the league
    Team t = getTeam(team);
    if (t == null) {
        return "No team named " + team + " is in the league.";
    }

    // Check if the team has any pitchers
    // Get the map of pitchers for the team
    Map<Integer, Pitcher> pitchers = team.getPitchers(); // Call getPitchers() on the Team object
    
    // Check if the team has any pitchers
    if (pitchers.isEmpty()) {
        return "The " + team + " appear to have no pitchers at this time.";
    }
    // Calculate the team's pitching statistics
    int totalInningsPitched = 0;
    int totalRunsAllowed = 0;
    int totalStrikeouts = 0;

    for (Player player : pitchers.values()) {
        Pitcher pitcher = (Pitcher) player;
        totalInningsPitched += pitcher.getInningsPitched();
        totalRunsAllowed += pitcher.getRuns();
        totalStrikeouts += pitcher.getStrikeouts();
    }

    double earnedRunAverage = (double) totalRunsAllowed / (double) totalInningsPitched * 9.0;
    double strikeoutsPerNineInnings = (double) totalStrikeouts / (double) totalInningsPitched * 9.0;

    // Format the results as a string
    String result = String.format("Pitching stats for the %s:\n", team);
    result += String.format("  Total innings pitched: %d\n", totalInningsPitched);
    result += String.format("  Earned run average: %.2f\n", earnedRunAverage);
    result += String.format("  Strikeouts per nine innings: %.2f\n", strikeoutsPerNineInnings);

    return result;
}

    /**
     * Calculate the hitting statistics for a given team be sure to use ignore
     * case for all string inputs
     *
     * @param team a String that is the team name - the key for the map
     * @return a String representing the results of the calculations
     */
    public String calcHittingStats(String team) {
        
     StringBuilder str = new StringBuilder();
    if (!teams.containsKey(team)) {
        str.append("No team named ").append(team).append(" is in the league.");
    } else {
     Team t = teams.get(team);
    List<Player> hitters = team.getHitters(); // Call getHitters on the Team object
    if (hitters.isEmpty()) {
        str.append("The ").append(team).append(" appear to have no hitters at this time.");
    } else {
            // Calculate hitting stats
            str.append("Hitting stats for ").append(team).append(":");
            int numHits = 0;
            int numAtBats = 0;
            double battingAvg = 0.0;
            for (Player hitter : hitters) {
                numHits += hitter.getHits();
                numAtBats += hitter.getAtBats();
            }
            if (numAtBats > 0) {
                battingAvg = (double) numHits / numAtBats;
            }
            str.append("\nNumber of hits: ").append(numHits);
            str.append("\nNumber of at-bats: ").append(numAtBats);
            str.append("\nBatting average: ").append(String.format("%.3f", battingAvg));
        }
    }
    return str.toString();
}

    

    /**
     * Allows the players on a team to update statistics based on the outcome of
     * a current game. (ideally will update two teams from one game) There is no
     * need to comment this out! Do NOT Change until you implement it.
     *
     * @param scnr a keyboard Scanner object
     * @return A String as specified
     */
    public String update(Scanner scnr) {
         StringBuilder str = new StringBuilder();

    // Read the first team name
    String teamName1 = scnr.next();
    Team team1 = teams.get(teamName1);
    int count1 = 0;

    // Update stats for players on the first team
    while (scnr.hasNextInt()) {
        int number = scnr.nextInt();
        if (number == -1) {
            break;
        }
        int plateAppearances = scnr.nextInt();
        int walks = scnr.nextInt();
        int strikeouts = scnr.nextInt();
        int hits = scnr.nextInt();
        Player player = team1.getPlayer(number);
        if (player != null) {
            player.updateBattingStats(plateAppearances, walks, strikeouts, hits);
            str.append(player.getName()).append(" updated\n");
            count1++;
        }
    }

    // Read the second team name
    String teamName2 = scnr.next();
    Team team2 = teams.get(teamName2);
    int count2 = 0;

    // Update stats for players on the second team
    while (scnr.hasNextInt()) {
        int number = scnr.nextInt();
        if (number == -1) {
            break;
        }
        int inningsPitched = scnr.nextInt();
        int earnedRuns = scnr.nextInt();
        int atBats = scnr.nextInt();
        int runsBattedIn = scnr.nextInt();
        int homeRuns = scnr.nextInt();
        int hitByPitch = scnr.nextInt();
        Player player = team2.getPlayer(number);
        if (player != null) {
            if (player.getPosition() == Position.PITCHER) {
                player.updatePitchingStats(inningsPitched, earnedRuns);
            } else {
                player.updateBattingStats(atBats, runsBattedIn, homeRuns, hitByPitch);
            }
            str.append(player.getName()).append(" updated\n");
            count2++;
        }
    }

    // Construct and return the result string
    str.insert(0, "Updated " + (count1 + count2) + " players on " + teamName1 + " and " + teamName2 + ".\n");
    return str.toString();
}

    /**         
     * Calculates the statistics for the league for right handed and left \
     * handed pitchers and hitters There is no need to comment this out! Do NOT
     * Change until you implement it.
     *
     * @return a String as specified
     */
    public String calculateHandedness() {
        StringBuilder str = new StringBuilder();
        str.append("handedness stub");
        return str.toString();
    }

}
