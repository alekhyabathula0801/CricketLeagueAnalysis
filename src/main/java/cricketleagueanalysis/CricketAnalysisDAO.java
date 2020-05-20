package cricketleagueanalysis;

public class CricketAnalysisDAO {

    public String player;
    public int four;
    public int six;
    public int runs;
    public double battingAverage;
    public double bowlerAverage;
    public double battingStrikeRate;
    public double bowlerStrikeRate;
    public double economy;
    public int fourWickets;
    public int fiveWickets;
    public int wickets;

    public CricketAnalysisDAO(BatsmanData batsmanData) {
        this.player = batsmanData.player;
        this.four = batsmanData.four;
        this.six = batsmanData.six;
        this.runs = batsmanData.runs;
        this.battingAverage = batsmanData.battingAverage;
        this.battingStrikeRate = batsmanData.battingStrikeRate;
    }

    public CricketAnalysisDAO(BowlerData bowlerData) {
        this.player = bowlerData.player;
        this.fourWickets = bowlerData.fourWickets;
        this.fiveWickets = bowlerData.fiveWickets;
        this.bowlerAverage = bowlerData.bowlerAverage;
        this.bowlerStrikeRate = bowlerData.bowlerStrikeRate;
        this.economy = bowlerData.economy;
        this.wickets = bowlerData.wickets;
    }

    public Object getIPLDTO(CricketLeagueAnalysis.Cricket cricket) {
        if(cricket.equals(CricketLeagueAnalysis.Cricket.BATTING))
            return new BatsmanData(player,runs, battingAverage, battingStrikeRate,four,six);
        else if(cricket.equals(CricketLeagueAnalysis.Cricket.BOWLING))
            return new BowlerData(player, bowlerAverage, bowlerStrikeRate,economy,fourWickets,fiveWickets,wickets);
        return new BatsmanAndBowlerData(player,runs,battingAverage,battingStrikeRate,four,six,bowlerAverage,wickets);
    }

}
