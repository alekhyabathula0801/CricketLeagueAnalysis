package cricketleagueanalysis;

public class CricketAnalysisDAO {

    public String player;
    public int four;
    public int six;
    public int runs;
    public double batsmanAverage;
    public double bowlerAverage;
    public double batsmanStrikeRate;
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
        this.batsmanAverage = batsmanData.batsmanAverage;
        this.batsmanStrikeRate = batsmanData.batsmanStrikeRate;
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

    public CricketAnalysisDAO(BatsmanAndBowlerData batsmanAndBowlerData) {
        this.player = batsmanAndBowlerData.player;
        this.batsmanAverage = batsmanAndBowlerData.batsmanAverage;
        this.batsmanStrikeRate = batsmanAndBowlerData.batsmanStrikeRate;
        this.runs = batsmanAndBowlerData.runs;
        this.four = batsmanAndBowlerData.four;
        this.six = batsmanAndBowlerData.six;
        this.bowlerAverage = batsmanAndBowlerData.bowlerAverage;
        this.wickets = batsmanAndBowlerData.wickets;
    }

    public Object getIPLDTO(CricketLeagueAnalysis.Cricket cricket) {
        if(cricket.equals(CricketLeagueAnalysis.Cricket.BATTING))
            return new BatsmanData(player,runs, batsmanAverage, batsmanStrikeRate,four,six);
        if(cricket.equals(CricketLeagueAnalysis.Cricket.BOWLING))
            return new BowlerData(player, bowlerAverage, bowlerStrikeRate,economy,fourWickets,fiveWickets,wickets);
        return new BatsmanAndBowlerData(player,runs, batsmanAverage, batsmanStrikeRate,four,six,bowlerAverage,wickets);
    }

}
