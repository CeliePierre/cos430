/** *********************************************************************
 * REVISION HISTORY (Newest First)
 ***********************************************************************
 *
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ********************************************************************** */
package baseballdriver;

import java.util.TreeMap;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

/**
 * A class to represent a team and its roster. It needs data members for the
 * name of the team, which will be String w/o embedded blanks, for example,
 * RedSox, BlueJay, Yankees, and WhiteSox, and a Java Collection class for the
 * players on the team.
 * <br>
 * You must use a Map member for the players. You can set up the Map to lookup
 * players by number, which is more efficient than iterating over a list of
 * players.
 * <br>
 * You must define
 * <ol>
 * <li> a public constructor that takes a name and a map of Player objects(which
 * will be invoked by the League class constructor).
 * <code>public Team(String tname, TreeMap<Integer, Player> roster)</code> and
 * creates an instance for that team
 *
 * <li> a public method <code>String  lookupPlayer(int n)</code>
 *
 * that returns the appropriate one of the follow two results (assuming t is the
 * name of this team)
 * <br>
 * No player with number n is on the roster for the t.<br>
 * or<br>
 * <pre><stats for that player></pre>
 * <br>
 * The format of the latter is given in the program assignment and is based on
 * whether the player is a pitcher or a position player. You should write the
 * toString method for Pitcher and PositionPlayer to return the appropriate
 * portion of that result. Dynamic dispatch will take care of the rest.</li>
 *
 * <li> a public method <code>String calcPitchingStats()</code>
 * <br>
 * that returns the appropriate choice from the following two results(assuming t
 * is the name of the team)
 * <ol>
 * <li>The t appear to have no pitchers at this time.</li>
 * <li> <code><aggregated pitching stats for t></code></li>
 * </ol>
 * where the format of the latter is discussed in the program assignment.</li>
 *
 * <li> code a public method String calcHittingStats()
 *
 * that returns the appropriate choice from the following two results(assuming t
 * is the name of the team)
 * <ol>
 * <li>The t appear to have no hitters at this time.</li>
 *
 * <li><code><aggregated hitting  stats for t><code><li>
 * </ol>
 * where the format of the latter is discussed in the program assignment.</ol>
 * If you do the extra credits, you will need to define additional methods.
 * Note, all data members should be private, so only the Team object instance
 * can directly access them.
 *
 * @author dbriggs
 */
public class Team {
    // class implementation goes here
    private String teamName;
    private TreeMap<Integer, Player> players;
     private HashMap<String, Player> statsPlayers;
     private ArrayList<Player> playersList;
      private List<PositionPlayer> hitters;
    
   // private String name;
    private int wins;
    private int losses;
    //private Map<String, Player> statsPlayers;
    

    public Team(String teamName, TreeMap<Integer, Player> players) {
        this.teamName = teamName;
        this.players = players;
         //this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.statsPlayers = new HashMap<String, Player>();
         this.playersList = new ArrayList<>();
     
    }
     
    
   public List<PositionPlayer> getHitters() {
    return this.hitters;
}
    
    
    
    
     public Player[] statsPlayersToArray(){
        Player[] ps = new Player[statsPlayers.size()];
        ps = statsPlayers.values().toArray(ps);
        return ps;
    }
      public void printPitcherPlateAppearances() {
        int pitPA = 0;
        for (Player p : playersList) {
            if (p instanceof Pitcher) {
                pitPA += ((Pitcher) p).getPlateAppearances();
            }
        }
        System.out.println("Total plate appearances by pitchers: " + pitPA);
    }

      
      public Map<Integer, Player> getPlayers() {
    return players;
}

    
    public void calcStats(int leftHandedPitchers, int rightHandedPitchers,
                          int leftHandedPositionPlayers, int rightHandedPositionPlayers) {
        // Add stats players to the team
        Pitcher rtPitch = new Pitcher(2, "rightHandedPitchers", 1, true, 0, 0, 0, 0, 0, 0);
        this.statsPlayers.put("rightHandedPitchers", rtPitch);

        Pitcher lftPitch = new Pitcher(3, "leftHandedPitchers", 1, false, 0, 0, 0, 0, 0, 0);
        this.statsPlayers.put("leftHandedPitchers", lftPitch);

        PositionPlayer rtHit = new PositionPlayer(4, "rightHandedHitters", 2, true, 0, 0, 0, 0, 0, 0, 0, 0);
        this.statsPlayers.put("rightHandedHitters", rtHit);

        PositionPlayer lftHit = new PositionPlayer(5, "leftHandedHitters", 2, false, 0, 0, 0, 0, 0, 0, 0, 0);
        this.statsPlayers.put("leftHandedHitters", lftHit);

        // Calculate stats for the team
        int totalPitchers = leftHandedPitchers + rightHandedPitchers;
        int totalPositionPlayers = leftHandedPositionPlayers + rightHandedPositionPlayers;

        // Add stats to the stats players
    }
    
    
    
    
    public Player getplayer(int num){
        if(players.containsKey(num))
        return players.get(num);
    else 
        return null;    
}
    
 
        
  public void updatePlayer(int playerNumber, int plateAppearances, int walks, int strikeouts, int hits,
                             int var1, int var2, int var3, int var4) {
        Player player = players.get(playerNumber);
        if (player != null) {
        }
    }

    
    private String noPlayer (int num){
        
        StringBuilder str = new StringBuilder();
        str.append("no team named ").append(num);
        str.append(" is in the league. ").append(teamName);
        return str.toString();
        
    }
            

    public String lookupPlayer(int n) {
        StringBuilder str = new StringBuilder();
        if (players.containsKey(n)) {
            Player p = players.get(n);
            str.append(String.format("The %s player number %d is a %s "
                    + "named %s with statistics%n", teamName,
                    p.playerNumber, p.getPositionName(),
                    p.playerName));
            str.append(p.toString());
        } else {
            str.append("No player with number ").append(n).
                    append(" is on the roster for the ").
                    append(teamName).append(".");
        }
        return str.toString();
    }

    

    public String calcPitchingStats() {
        // Check if there are any pitchers on the team
        StringBuilder str = new StringBuilder();

        int count = 0;
        int totalPa = 0, totalw = 0, totalSo = 0, totalH = 0, totalIp = 0, totalRbi = 0, totalEr = 0;
        double teamERA = 0.0;
        double teamWHIP = 0.0;
        Pitcher p;
        for (Integer playerNum : players.keySet()) {
            if (players.get(playerNum) instanceof Pitcher) {
                p = (Pitcher)players.get(playerNum);
                totalPa += p.getPlateAppearances();
                totalw += p.getWalks();
                totalSo += p.getStrikeouts();
                totalH += p.getHits();
                totalIp += p.getIP();
                //totalRbi += p.getRunsBattedIn();
                totalEr += p.getEarnedRuns();
                count++;
            }
            // Calculate team ERA and WHIP

            // Construct output string
            if (count > 0) {
                p = new Pitcher(0, "dummy", 1, true, totalPa, totalw, totalSo, totalH, totalIp, totalEr);
                str.append("There are ").append(count);
                str.append("Pitcher on the ").append(teamName);
                str.append(" and their aggregated statistics are \n ");

                //return "The team appears to have no hitters at this time.";
            } else {

                str.append("There are no hitters on the ").append(teamName);
            }

            

        }
           return str.toString();
    }


    public String calcHittingStats() {
        StringBuilder str = new StringBuilder();
        //int numHitters = 0;
        int count = 0;
        int totalPa = 0, totalw = 0, totalSo = 0, totalH = 0, totalAb = 0, totalRbi = 0, totalHr = 0, totalHbp = 0;
        double battingAverage = 0.0;
        double onBasePercentage = 0.0;
        PositionPlayer p;
        boolean hasHitters = false;
        for (Integer playerNum : players.keySet()) {
            if (players.get(playerNum) instanceof PositionPlayer) {

                count++;
                p = (PositionPlayer) players.get(playerNum);
                totalPa += p.getPlateAppearances();
                totalw += p.getWalks();
                totalSo += p.getStrikeouts();
                totalH += p.getHits();
                totalAb += p.getAtBats();
                totalRbi += p.getRBIs();
                totalHr += p.getHomeRuns();
                totalHbp += p.getHitByPitches();
            }
        }

        if (count > 0) {
            p = new PositionPlayer(0, "dummy", 1, true, totalPa, totalw, totalSo, totalH, totalAb, totalRbi,
                    totalHr, totalHbp);
            str.append("There are ").append(count);
            str.append(" hitters on the ").append(teamName);
            str.append(" and their aggregated statistics are \n ");

            //return "The team appears to have no hitters at this time.";
        } else {

            //str.append("There are no hitters on the ").append(teamName);
        }

        return str.toString();

    }
    
    
      public String calculateHandedness() {
        StringBuilder str = new StringBuilder();
        String eol = System.lineSeparator();
        int rightHandedPitchers = 0;
        int leftHandedPitchers = 0;
        int rightHandedPositionPlayers = 0;
        int leftHandedPositionPlayers = 0;


         for (Player player : players.values()) {
        if (player instanceof Pitcher) {
            Pitcher pitcher = (Pitcher) player;
            if (pitcher.throwsRightHanded) {
                rightHandedPitchers++;
            } else {
                leftHandedPitchers++;
            }
        } else if (player instanceof PositionPlayer) {
            PositionPlayer positionPlayer = (PositionPlayer) player;
            if (positionPlayer.isThrowsRightHanded()) {
                rightHandedPositionPlayers++;
            } else {
                leftHandedPositionPlayers++;
            }
        }
    }

    str.append(teamName).append(":").append(eol);
    str.append("  Left-handed pitchers: ").append(leftHandedPitchers).append(eol);
    str.append("  Right-handed pitchers: ").append(rightHandedPitchers).append(eol);
    str.append("  Left-handed position players: ").append(leftHandedPositionPlayers).append(eol);
    str.append("  Right-handed position players: ").append(rightHandedPositionPlayers).append(eol);

    return str.toString();
}
    
    
    
    

    /**
     * Unit test for Team - Creates a map with two players and calls the Team
     * Constructor There is no need to comment this out! Do NOT Change except to
     * uncomment the stats calls. Leave it at the bottom of the file!
     *
     * @param args command line args
     */
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeMap<Integer, Player> players = new TreeMap<>();
        players.put(65, new Pitcher(65, "Jonathan", 1, true, 416, 23, 80,
                111, 259, 32));
        players.put(35, new Pitcher(35, "Eric", 1, true, 2, 2, 2, 2, 2, 2));
        players.put(30, new Pitcher(30, "Derek", 1, true, 3, 3, 3, 3, 3, 3));

        players.put(26, new PositionPlayer(26, "Brock", 4, true, 1443, 33, 83,
                422, 454, 274, 50, 2));
        Team team = new Team("RedSox", players);
        for (Integer num : players.keySet()) {
            System.out.println(players.get(num));
        }
        System.out.println("Expected Output: \n"
                + "PA: 1443 BB: 33 SO: 83 H: 422 AB: 454 BI: 274 HR: 50 "
                + "HBP: 2 BA: 0.930  OBP: 0.317\n"
                + "PA: 416 BB: 23 SO: 80 H: 111 IP: 86 1/3 ER: 32 ERA: 3.34 "
                + "WHIP: 1.55 \n\n");
        System.out.println(team.lookupPlayer(65));
        System.out.println("Expected Output: \n"
                + "The RedSox's player number 65 is a pitcher named Jonathan "
                + "with statistics:\n"
                + "PA: 416 BB: 23 SO: 80 H: 111 IP: 86 1/3 ER: 32 ERA: 3.34 "
                + "WHIP: 1.55\n\n");
        System.out.println(team.lookupPlayer(3));
        System.out.println("Expected Output: \n"
                + "No player with number 3 is on the roster for RedSox.");
        System.out.println(team.calcPitchingStats());
        System.out.println("There are 3 pitchers on the RedSox and their "
                + "aggregated statistics are \n"
                + "PA: 421 BB: 28 SO: 85 H: 116 IP: 264 ER: 37 ERA: 3.784 "
                + "WHIP: 1.64");
        System.out.println(team.calcHittingStats());
        System.out.println("There are 1 hitters on the RedSox and their "
                + "aggregated statistics are \n"
                + "PA: 1443 BB: 33 SO: 83 H: 422 AB: 454 BI: 274 HR: 50 "
                + "HBP: 2 BA: 0.930 OBP: 0.317");
    }
}
