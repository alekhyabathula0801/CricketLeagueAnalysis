package cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class BowlerData {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Avg", required = true)
    public double bowlerAverage;

    @CsvBindByName(column = "SR", required = true)
    public double bowlerStrikeRate;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    public BowlerData() {}

    public BowlerData(String player, double bowlerAverage, double bowlerStrikeRate, double economy, int fourWickets, int fiveWickets, int wickets) {
        this.fiveWickets = fiveWickets;
        this.bowlerAverage = bowlerAverage;
        this.fourWickets = fourWickets;
        this.economy = economy;
        this.player = player;
        this.bowlerStrikeRate = bowlerStrikeRate;
        this.wickets = wickets;
    }

}
