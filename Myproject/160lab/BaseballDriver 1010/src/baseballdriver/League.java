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
                if (H.equals("t")) {
                    throwsRightHanded = true;
                }
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

    public void updatePlayer(int playerNumber, int plateAppearances, int walks, int strikeouts, int hits,
            int p1, int p2, int p3, int p4) {
        Player player = players.get(playerNumber);
        if (player != null) {
        }
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
            str.append(noTeam(team));
            //str.append("TEAM NOT FOUND");
        }
        return str.toString();

    }

    private String leagueStatString(int count, String base) {
        String eol = System.lineSeparator();
        StringBuilder str = new StringBuilder();
        if (count > 0) {

            str.append("There are ").append(count).append(base);
            str.append("in the league and their aggregate stastics are:");
        } else {
            str.append("There are no").append(base);
            str.append("in the league");

        }
        str.append(eol);
        return str.toString();

    }

    /**
     * Calculate the hitting statistics for a given team be sure to use ignore
     * case for all string inputs
     *
     * @param team a String that is the team name - the key for the map
     * @return a String representing the results of the calculations
     */public String calcHittingStats(String team) {
        
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

    public String noTeam(String team) {
        StringBuilder str = new StringBuilder();
        str.append("no team named ").append(team);
        str.append(" is in the league. ");
        return str.toString();

    }

    public String getTeamRoster(String team) {
        StringBuilder str = new StringBuilder();
        if (league.containsKey(team)) {
            str.append(league.get(team).toString());

        } else {
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
    String name;
    int playerCount;
    int num, pa, w, s, h;
    int var1, var2, var3, var4;

    playerCount = 0;
    System.out.println("Enter the team name  ==>");
    name = scnr.next();

    if (league.containsKey(name)) {
        Team t = league.get(name);
        System.out.println("Enter player number or -1 to quit  ==>");
        num = scnr.nextInt();

        while(num != -1) {
            Player p = t.getplayer(num);

            if (p != null) {
                System.out.println("Enter pa walks so hits ==>");

                if(p instanceof Pitcher) {
                    System.out.println("ip and er with space " + "in between");
                    pa = scnr.nextInt();
                    w = scnr.nextInt();
                    s = scnr.nextInt();
                    h = scnr.nextInt();
                     var1 = scnr.nextInt();
                    var2 = scnr.nextInt();
                  ((Pitcher) p).update(pa, w, s, h, var1, var2);

                } else if (p instanceof PositionPlayer) {
                    System.out.println("st rbi br " + "in between");
                    pa = scnr.nextInt();
                    w = scnr.nextInt();
                    s = scnr.nextInt();
                    h = scnr.nextInt();
                    var1 = scnr.nextInt();
                    var2 = scnr.nextInt();
                    var3 = scnr.nextInt();
                    var4 = scnr.nextInt();
                    ((PositionPlayer) p).update(pa, w, s, h, var1, var2, var3, var4);
                }

                str.append(name + "’s player number " + num + " is " + p.getPlayerName() + " with statistics:" + eol);
                str.append(p.toString() + eol);
                playerCount++;
            }

            System.out.println("Enter player number or -1 to quit  ==>");
            num = scnr.nextInt();
        }

        str.append(name + " updated " + playerCount + " players" + eol);
    } else {
        str.append("Team not found"+ eol);
    }


    return str.toString();
}   
   
                        
    
        /**
         * Calculates the statistics for the league for right handed and left \
         * handed pitchers and hitters There is no need to comment this out! Do
         * NOT Change until you implement it.
         *
         * @return a String as specified
         */
  


public String calculateHandedness() {
    StringBuilder str = new StringBuilder();
     //StringBuilder str = new StringBuilder(); 
    String eol = System.lineSeparator();
    int rightHandedPitchers = 0; 
    int leftHandedPitchers = 0; 
    int rightHandedPlayers = 0; 
    int leftHandedPlayers = 0;
    int totalPa = 0, totalw = 0, totalSo = 0, totalH = 0, totalAb = 0, totalRbi = 0, totalHr = 0, totalHbp = 0;
    double totalIp = 0, totalEr = 0, totalWhip = 0;
    double totalBa = 0, totalObp = 0;

    for (Team team : league.values()) {
        for (Player player : team.getPlayers().values()) {
            if (player instanceof Pitcher) {
                Pitcher pitcher = (Pitcher) player;
                if (pitcher.throwsRightHanded) {
                    rightHandedPitchers++;
                    totalPa += pitcher.getPlateAppearances();
                    totalw += pitcher.getWalks();
                    totalSo += pitcher.getStrikeouts();
                    totalH += pitcher.getHits();
                    totalAb += pitcher.getHitBatters();
                    totalRbi += pitcher.getRunsBattedIn();
                    totalHr += pitcher.getHomeRunsAllowed();
                    totalHbp += pitcher.getHitBatters();
                    totalIp += pitcher.getInningsPitched();
                    totalEr += pitcher.getEarnedRuns();
                } else {
                    leftHandedPitchers++;
                }
               } else if (player instanceof PositionPlayer) {
                PositionPlayer positionPlayer = (PositionPlayer) player;
                if (positionPlayer.throwsRightHanded) {
                    rightHandedPlayers++;
                    totalPa += positionPlayer.getPlateAppearances();
                    totalw += positionPlayer.getWalks();
                    totalSo += positionPlayer.getStrikeouts();
                    totalH += positionPlayer.getHits();
                    totalAb += positionPlayer.getAtBats();
                    totalRbi += positionPlayer.getRunsBattedIn();
                    totalHr += positionPlayer.getHomeRuns();
                    totalHbp += positionPlayer.getHitByPitch();
                    totalBa += positionPlayer.getBattingAverage();
                    totalObp += positionPlayer.getOnBasePercentage();
                } else {
                    leftHandedPlayers++;
                    totalPa += positionPlayer.getPlateAppearances();
                    totalw += positionPlayer.getWalks();
                    totalSo += positionPlayer.getStrikeouts();
                    totalH += positionPlayer.getHits();
                    totalAb += positionPlayer.getAtBats();
                    totalRbi += positionPlayer.getRunsBattedIn();
                    totalHr += positionPlayer.getHomeRuns();
                    totalHbp += positionPlayer.getHitByPitch();
                    totalBa += positionPlayer.getBattingAverage();
                    totalObp += positionPlayer.getOnBasePercentage();
                }
            }
        }
    }
    
    
     Pitcher rightieP = new Pitcher(1, "right", 0, true,totalPa, 
                totalw, totalSo,totalH  ,
              totalAb , totalRbi); 
  //There are 79 right{handed pitchers in the league and their aggregate statistics are:
   
 str.append("There are ").append(rightHandedPitchers).append(" right-handed pitchers in the league and their aggregate statistics are:").append(eol);
str.append("PA: ").append(rightieP.getPlateAppearances()).append(" BB: ").append(rightieP.getWalks())
.append(" SO: ").append(rightieP.getStrikeouts()).append(" H: ").append(rightieP.getHits());
str.append(" IP: ").append(String.format("%d", rightieP.getInningsPitched())).append(" ER: ")
      .append(rightieP.getEarnedRuns()).append(" ERA: ").append(String.format("%.2f", rightieP.getEarnedRunAverage()));
str.append(" WHIP: ").append(String.format("%.2f", rightieP.getWhip())).append(eol);

    Pitcher leftieP = new Pitcher(1,"left", 0, true,totalPa, 
                totalw, totalSo,totalH  ,
              totalAb , totalRbi); 
    str.append("There are ").append(leftHandedPitchers).append(" left-handed pitchers in the league and their aggregate statistics are:").append(eol);
 str.append("PA: ").append(leftieP.getPlateAppearances()).append(" BB: ").append(leftieP.getWalks())
 .append(" SO: ").append(leftieP.getStrikeouts()).append(" H: ").append(leftieP.getHits());
str.append(" IP: ").append(String.format("%d", leftieP.getInningsPitched())).append(" ER: ").append(leftieP.getEarnedRuns()).
append(" ERA: ").append(String.format("%.2f", leftieP.getEarnedRunAverage()));
str.append(" WHIP: ").append(String.format("%.2f", leftieP.getWhip())).append(eol);


PositionPlayer right = new PositionPlayer(1, "right", 0, true, totalPa, 
totalw, totalSo, totalH, totalAb, totalRbi, totalHr, totalHbp);

str.append("There are ").append(rightHandedPlayers).append(" right-handed position players in the league and their aggregate statistics are:").append(eol);
str.append("PA: ").append(right.getPlateAppearances()).append(" BB: ").append(right.getWalks()).append(" SO: ").append(right.getStrikeouts()).append(" H: ").append(right.getHits());
str.append(" AB: ").append(right.getAtBats()).append(" BI: ").append(right.getRunsBattedIn()).append(" HR: ").append(right.getHomeRuns()).append(" HBP: ").append(right.getHitByPitch());
str.append(" BA: ").append(String.format("%.3f", totalBa/rightHandedPlayers)).append(" OBP: ").append(String.format("%.3f", totalObp/rightHandedPlayers)).append(eol);



PositionPlayer left = new PositionPlayer(1, "left", 0, false, totalPa, 
totalw, totalSo, totalH, totalAb, totalRbi, totalHr, totalHbp);
str.append("There are ").append(leftHandedPlayers).append(" left-handed position players in the league and their aggregate statistics are:").append(eol);
str.append("PA: ").append(left.getPlateAppearances()).append(" BB: ").append(left.getWalks()).append
(" SO: ").append(left.getStrikeouts()).append(" H: ").append(left.getHits());
str.append(" AB: ").append(left.getAtBats()).append(" BI: ").append(left.getRunsBattedIn()).append
(" HR: ").append(left.getHomeRuns()).append(" HBP: ").append(left.getHitByPitch()).append(" BA: ")
.append(String.format("%.3f", totalBa/leftHandedPlayers)).append(" OBP: ").append(String.format
 ("%.3f", totalObp/leftHandedPlayers)).append(eol);




    
    return str.toString();
}
}
