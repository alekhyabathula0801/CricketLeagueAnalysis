package cricketleagueanalysis;

public class CricketAnalysisDAO {

    public String player;
    public int four;
    public int six;
    public int runs;
    public double average;
    public double strikeRate;

    public CricketAnalysisDAO(BatsmanDataCsv batsmanDataCsv) {
        this.player = batsmanDataCsv.player;
        this.four = batsmanDataCsv.four;
        this.six = batsmanDataCsv.six;
        this.runs = batsmanDataCsv.runs;
        this.average = batsmanDataCsv.average;
        this.strikeRate = batsmanDataCsv.strikeRate;
    }

}
