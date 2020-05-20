package cricketleagueanalysis;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalysis {

    Map<String,CricketAnalysisDAO> cricketLeagueData = null;

    enum Cricket { BATTING,BOWLING,BATTING_BOWLING}
    private Cricket cricket;

    public CricketLeagueAnalysis(Cricket cricket) {
        this.cricket = cricket;
    }

    public int loadIPLData(String... csvFilePath) throws CricketLeagueAnalysisException {
        cricketLeagueData = new CricketLeagueDataAdapterFactory().getCricketLeagueData(cricket,csvFilePath);
        return cricketLeagueData.size();
    }

    public String getSortedDataAccordingToBattingAverage() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByBattingAverage = Comparator.comparing(iplData -> iplData.battingAverage);
        return this.getSortedCricketLeagueData(sortByBattingAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToBattingStrikingRate() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByBattingStrike = Comparator.comparing(iplData -> iplData.battingStrikeRate);
        return this.getSortedCricketLeagueData(sortByBattingStrike.reversed(),cricket);
    }

    public String getSortedDataAccordingToSixsAndFours() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortBySixAndFour = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
        return this.getSortedCricketLeagueData(sortBySixAndFour.reversed(),cricket);
    }

    public String getSortedDataAccordingToBattingStrikeRateWithSixsAndFours() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortBySixAndFour = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
        Comparator<CricketAnalysisDAO> sortByStrikeWithSixsAndFour = sortBySixAndFour.thenComparing(iplData -> iplData.battingStrikeRate);
        return this.getSortedCricketLeagueData(sortByStrikeWithSixsAndFour.reversed(),cricket);
    }

    public String getSortedDataAccordingToBattingAverageWithBattingStrikeRate() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByBattingAverage = Comparator.comparing(iplData -> iplData.battingAverage);
        Comparator<CricketAnalysisDAO> sortByAverageWithStrike = sortByBattingAverage.thenComparing(iplData -> iplData.battingStrikeRate);
        return this.getSortedCricketLeagueData(sortByAverageWithStrike.reversed(),cricket);
    }

    public String getSortedDataAccordingToRunsWithBattingAverage() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByRuns = Comparator.comparing(iplData -> iplData.runs);
        Comparator<CricketAnalysisDAO> sortByRunsWithAverage = sortByRuns.thenComparing(iplData -> iplData.battingAverage);
        return this.getSortedCricketLeagueData(sortByRunsWithAverage.reversed(),cricket);
    }


    public String getSortedDataAccordingToBowlerAverage() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByBowlerAverage = Comparator.comparing(iplData -> iplData.bowlerAverage);
        return this.getSortedCricketLeagueData(sortByBowlerAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToBowlerStrikeRate() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByBowlerStrikeRate = Comparator.comparing(iplData -> iplData.bowlerStrikeRate);
        return this.getSortedCricketLeagueData(sortByBowlerStrikeRate.reversed(),cricket);
    }

    public String getSortedDataAccordingToEconomy() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByEconomy = Comparator.comparing(iplData -> iplData.economy);
        return this.getSortedCricketLeagueData(sortByEconomy.reversed(),cricket);
    }

    public String getSortedDataAccordingToBowlerStrikeRateWith5WicketsAnd4Wickets() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByFiveAndFourWickets = Comparator.comparing(iplData -> (iplData.fiveWickets*5 + iplData.fourWickets*4));
        Comparator<CricketAnalysisDAO> sortByStrikeWithFiveAndFourWickets = sortByFiveAndFourWickets.thenComparing(iplData -> iplData.bowlerStrikeRate);
        return this.getSortedCricketLeagueData(sortByStrikeWithFiveAndFourWickets.reversed(),cricket);
    }

    public String getSortedDataAccordingToBowlingAverageWithBowlingStrikeRate() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByBowlerAverage = Comparator.comparing(iplData -> iplData.bowlerAverage);
        Comparator<CricketAnalysisDAO> sortByAverageWithStrike = sortByBowlerAverage.thenComparing(iplData -> iplData.bowlerStrikeRate);
        return this.getSortedCricketLeagueData(sortByAverageWithStrike.reversed(),cricket);
    }

    public String getSortedDataAccordingToWicketsWithBowlingAverage() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByWickets = Comparator.comparing(iplData -> iplData.wickets);
        Comparator<CricketAnalysisDAO> sortByWicketsWithAverage = sortByWickets.thenComparing(iplData -> iplData.bowlerAverage);
        return this.getSortedCricketLeagueData(sortByWicketsWithAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToBattingAndBowlingAverage() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByBattingAverage = Comparator.comparing(iplData -> iplData.battingAverage);
        Comparator<CricketAnalysisDAO> sortByBattingAndBowlingAverage = sortByBattingAverage.thenComparing(iplData -> iplData.bowlerAverage);
        return this.getSortedCricketLeagueData(sortByBattingAndBowlingAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToRunsAndWickets() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByRuns = Comparator.comparing(iplData -> iplData.runs);
        Comparator<CricketAnalysisDAO> sortByRunsAndWickets = sortByRuns.thenComparing(iplData -> iplData.wickets);
        return this.getSortedCricketLeagueData(sortByRunsAndWickets.reversed(),cricket);
    }

    private String getSortedCricketLeagueData(Comparator<CricketAnalysisDAO> iplDataComparator, Cricket cricket) throws CricketLeagueAnalysisException {
        if(cricketLeagueData == null || cricketLeagueData.size() == 0 ) {
            throw new CricketLeagueAnalysisException("No Data",
                        CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        }
        List sortedCricketLeagueData = cricketLeagueData.values().stream()
                                                         .sorted(iplDataComparator)
                                                         .map(iplDAO -> iplDAO.getIPLDTO(cricket))
                                                         .collect(Collectors.toList());
        String sortedCricketLeagueDataInJson = new Gson().toJson(sortedCricketLeagueData);
        return sortedCricketLeagueDataInJson;
    }

}
