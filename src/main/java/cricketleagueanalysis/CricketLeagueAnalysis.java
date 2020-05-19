package cricketleagueanalysis;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalysis {

    Map<String,CricketAnalysisDAO> iplAnalysisMap = null;

    enum Cricket { BATTING,BOWLING}
    private Cricket cricket;

    public CricketLeagueAnalysis(Cricket cricket) {
        this.cricket = cricket;
    }

    Comparator<CricketAnalysisDAO> sortByAverage = Comparator.comparing(iplData -> iplData.average);
    Comparator<CricketAnalysisDAO> sortByStrike = Comparator.comparing(iplData -> iplData.strikeRate);
    Comparator<CricketAnalysisDAO> sortBySixAndFour = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
    Comparator<CricketAnalysisDAO> sortByRuns = Comparator.comparing(iplData -> iplData.runs);

    public int loadIPLData(String csvFilePath) throws CricketLeagueAnalysisException {
        iplAnalysisMap = new CricketLeagueDataAdapterFactory().getCricketLeagueData(cricket,csvFilePath);
        return iplAnalysisMap.size();
    }

    public String getSortedDataAccordingToAverage(Cricket cricket) throws CricketLeagueAnalysisException {
        return this.getSortedCricketLeagueData(sortByAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToStrikingRate(Cricket cricket) throws CricketLeagueAnalysisException {
        return this.getSortedCricketLeagueData(sortByStrike.reversed(),cricket);
    }

    public String getSortedDataAccordingToSixsAndFours(Cricket cricket) throws CricketLeagueAnalysisException {
        return this.getSortedCricketLeagueData(sortBySixAndFour.reversed(),cricket);
    }

    public String getSortedDataAccordingToStrikeRateWithSixsAndFours(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByStrikeWithSixsAndFour = sortBySixAndFour.thenComparing(sortByStrike);
        return this.getSortedCricketLeagueData(sortByStrikeWithSixsAndFour.reversed(),cricket);
    }

    public String getSortedDataAccordingToAverageWithStrikeRate(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByAverageWithStrike = sortByAverage.thenComparing(sortByStrike);
        return this.getSortedCricketLeagueData(sortByAverageWithStrike.reversed(),cricket);
    }

    public String getSortedDataAccordingToRunsWithBattingAverage(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByRunsWithAverage = sortByRuns.thenComparing(sortByAverage);
        return this.getSortedCricketLeagueData(sortByRunsWithAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToEconomy(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByEconomy = Comparator.comparing(iplData -> iplData.economy);
        return this.getSortedCricketLeagueData(sortByEconomy.reversed(),cricket);
    }


    public String getSortedDataAccordingToStrikeRateWith5WicketsAnd4Wickets(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByFiveAndFourWickets = Comparator.comparing(iplData -> (iplData.fiveWickets*5 + iplData.fourWickets*4));
        Comparator<CricketAnalysisDAO> sortByStrikeWithFiveAndFourWickets = sortByFiveAndFourWickets.thenComparing(sortByStrike);
        return this.getSortedCricketLeagueData(sortByStrikeWithFiveAndFourWickets.reversed(),cricket);
    }
    
    public String getSortedDataAccordingToWicketsWithAverage(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> sortByWickets = Comparator.comparing(iplData -> iplData.wickets);
        Comparator<CricketAnalysisDAO> sortByWicketsWithAverage = sortByWickets.thenComparing(sortByAverage);
        return this.getSortedCricketLeagueData(sortByWicketsWithAverage.reversed(),cricket);
    }

    private String getSortedCricketLeagueData(Comparator<CricketAnalysisDAO> censusComparator, Cricket cricket) throws CricketLeagueAnalysisException {
        if(iplAnalysisMap == null || iplAnalysisMap.size() == 0 ) {
            throw new CricketLeagueAnalysisException("No Data",
                        CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        }
        List sortedBatsmanData = iplAnalysisMap.values().stream().
                                                         sorted(censusComparator).
                                                         map(iplDAO -> iplDAO.getIPLDTO(cricket)).
                                                         collect(Collectors.toList());
        String sortedBatsmanDataInJson = new Gson().toJson(sortedBatsmanData);
        return sortedBatsmanDataInJson;
    }

}
