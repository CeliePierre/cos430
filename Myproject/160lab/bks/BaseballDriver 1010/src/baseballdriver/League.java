/** *********************************************************************
 * REVISION HISTORY (Newest First)
 * **********************************************************************
 *
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ********************************************************************** */
package baseballdriver;

import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import baseballdriver.Player;


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
   // private Map<String, Team> teams;
    private Map<String, Team> league;
    private HashMap<Integer, Player> players;
   

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
        league = new TreeMap<>();
        Team team = null;

        // open the file using a Scanner object
        //  Scanner fileScanner = new Scanner(new File("teams.txt"));
        while (s.hasNext()) {

            TreeMap<Integer, Player> players = new TreeMap<>();
            Player p = null;
            String teamName = s.next();
            // read in ONE team
            int playerNum = s.nextInt();
            while (playerNum != -1) {
                // get common player data attributes
                String playerName = s.next();
                int playerPosition = s.nextInt();
                //boolean throwsRightHanded = s.nextBoolean();
                boolean throwsRightHanded = false;
                String H = s.next();
                if (H.equals(" t "))
                    throwsRightHanded = true;
                int plateAppearances = s.nextInt();
                int walks = s.nextInt();
                int strikeouts = s.nextInt();
                int hits = s.nextInt();

                if (playerPosition == 1) {
                    // get pitcher-specific attributes
                    int inningsPitched = s.nextInt();
                    int earnedRuns = s.nextInt();
                    p = new Pitcher(playerNum, playerName,
                            playerPosition, throwsRightHanded,
                            plateAppearances, walks, strikeouts, hits,
                            inningsPitched, earnedRuns);

                } else {

                    // get position player-specific attributes
                    int atBats = s.nextInt();
                    int runsBattedIn = s.nextInt();
                    int homeRuns = s.nextInt();
                    int hitByPitch = s.nextInt();
                    p = new PositionPlayer(playerNum, playerName,
                            playerPosition, throwsRightHanded,
                            plateAppearances, walks, strikeouts, hits,
                            atBats, runsBattedIn, homeRuns, hitByPitch);

                }
                players.put(playerNum, p);
                playerNum = s.nextInt();
            }
            team = new Team(teamName, players);
            league.put(teamName.toLowerCase(), team);
        }

        // close the file
        s.close();

    }
    
    

    public String getTeam(String teamName) {
        StringBuilder str = new StringBuilder();
        if (league.containsKey(teamName.toLowerCase())) {
       str.append(league.get(teamName.toLowerCase().toString()));
        } else {
            str.append(String.format("No team named %s is in the league.", teamName));
        }

        return str.toString();
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
        StringBuilder str = new StringBuilder();
        if (league.containsKey(team)) {
            str.append(league.get(team).lookupPlayer(playerNum));
        } else {
            str.append("No team named ").append(team).append(" is in the league.");
        }

        return str.toString();
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
    StringBuilder str = new StringBuilder();

    // Check if the team is in the league
    if (league.containsKey(team)) {
        str.append(league.get(team).calcPitchingStats());
  
       
     } else {

    // Format the results as a string
    str.append(league.get(team).calcPitchingStats());
    }   
    return str.toString();

    }
    /**
     * Calculate the hitting statistics for a given team be sure to use ignore
     * case for all string inputs
     *
     * @param team a String that is the team name - the key for the map
     * @return a String representing the results of the calculations
     */
    public String calcHittingStats(String team ) {
    StringBuilder str = new StringBuilder();
    // Check if the team is in the league
    if (league.containsKey(team.toLowerCase())) {
        str.append(league.get(team.toLowerCase()).calcHittingStats());
    } else {
        str.append("The team " + team + " is not in the league.");
    }
    
    // Format the results as a string
    return str.toString();
}
    
    public String noTeam(String team){
        StringBuilder str = new StringBuilder();
        str.append("no team named ").append(team);
        str.append(" is in the league. ");
        return str.toString();
        
    }
    public String getTeamRoster(String team){
          StringBuilder str = new StringBuilder();
          if (league.containsKey(team)){
              str.append(league.get(team).toString());
              
          }else{
              str.append(noTeam(team));
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
    String eol = System.lineSeparator();
    int playerCount;
    int num, pa, w, s, h;
    int var1, var2, var3, var4;
    
    for(int team = 0; team < 2; team++) {
        playerCount = 0;
        System.out.println("Enter the team name  ==>");
        //name = scnr.next();
           String name = "";
        
        if (league.containsKey(name)) {
            Team t = league.get(name);
            System.out.println("Enter player number or -1 to quit  ==>");
            num = scnr.nextInt();
            
            while(num != -1) {
                Player p = t.getplayer(num);
                
                if (p != null) {
                    System.out.println("Enter pa walks so hits ==>");
                    
                    if(p instanceof Pitcher) {
                        System.out.println("ip and er with space ");
                    } else {
                        System.out.println("st rbi br in between");
                    }
                    
                    pa = scnr.nextInt();
                    w = scnr.nextInt();
                    s = scnr.nextInt();
                    h = scnr.nextInt();
                    var1 = scnr.nextInt();
                    var2 = scnr.nextInt();
                    
                    if (p instanceof PositionPlayer) {
                        var3 = scnr.nextInt();
                        var4 = scnr.nextInt();
                        t.updatePlayer(num, pa, w, s, h, var1, var2, var3, var4);
                    }
                    
                    playerCount++;
                } else {
                    System.out.println("No player number " + num + " on the team " + name);
                }
                
                System.out.println("Enter player number or -1 to quit ==>");
                num = scnr.nextInt();
            }
            
            str.append("Updated ").append(playerCount);
            str.append(" player(s) on ").append(name).append(eol);
            str.append(t.toString()).append(eol);
        } else {
            System.out.println("No team named " + name + " is in the league.");
        }
    }
    
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
        String eol = System.lineSeparator();
        int rigthHandedPitchers = 0 ; int leftHandedPitchers= 0; int rigthHandedPlayers= 0; int leftHandedPlayers= 0;
      
           for (Team team : league.values()) {
    for (Player player : team.getPlayers().values()) {
        if (player instanceof Pitcher) {
            Pitcher pitcher = (Pitcher) player;
            if (pitcher.throwsRightHanded) {
                rigthHandedPitchers++;
            } else {
                leftHandedPitchers++;
            }
        } else if (player instanceof PositionPlayer) {
            PositionPlayer positionPlayer = (PositionPlayer) player;
             if (positionPlayer.throwsRightHanded) {
                rigthHandedPlayers++;
            } else {
                leftHandedPlayers++;
            }
        }
    }
}

str.append("Right-handed pitchers: ").append(rigthHandedPitchers).append(eol);
str.append("Left-handed pitchers: ").append(leftHandedPitchers).append(eol);
str.append("Right-handed players: ").append(rigthHandedPlayers).append(eol);
str.append("Left-handed players: ").append(leftHandedPlayers).append(eol);





    
    return str.toString();
}
}

