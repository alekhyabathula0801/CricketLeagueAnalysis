package cricketleagueanalysis;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IPLComparator {

    static Map<CricketLeagueAnalysis.CompareType, Comparator<CricketAnalysisDAO>> iplComparator = new HashMap<>();

    static  {
        Comparator<CricketAnalysisDAO> compareByBattingStrike = Comparator.comparing(iplData -> iplData.batsmanStrikeRate);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BATSMAN_STRIKE,compareByBattingStrike);
        Comparator<CricketAnalysisDAO> compareByBattingAverage = Comparator.comparing(iplData -> iplData.batsmanAverage);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE,compareByBattingAverage);
        Comparator<CricketAnalysisDAO> compareByRuns = Comparator.comparing(iplData -> iplData.runs);
        iplComparator.put(CricketLeagueAnalysis.CompareType.RUNS,compareByRuns);
        Comparator<CricketAnalysisDAO> compareBySixAndFour = Comparator.comparing(iplData -> (iplData.six * 6 + iplData.four * 4));
        iplComparator.put(CricketLeagueAnalysis.CompareType.SIX_AND_FOUR,compareBySixAndFour);
        Comparator<CricketAnalysisDAO> compareByBattingAverageWithStrike = compareByBattingAverage.thenComparing(compareByBattingStrike);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE_WITH_STRIKE,compareByBattingAverageWithStrike);
        Comparator<CricketAnalysisDAO> compareByStrikeWithSixsAndFour = compareBySixAndFour.thenComparing(compareByBattingStrike);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BATSMAN_STRIKE_WITH_SIX_AND_FOUR,compareByStrikeWithSixsAndFour);
        Comparator<CricketAnalysisDAO> compareByRunsWithAverage = compareByRuns.thenComparing(compareByBattingAverage);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BATSMAN_RUNS_WITH_AVERAGE,compareByRunsWithAverage);
        Comparator<CricketAnalysisDAO> compareByBowlerAverage = Comparator.comparing(iplData -> iplData.bowlerAverage);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BOWLER_AVERAGE,compareByBowlerAverage);
        Comparator<CricketAnalysisDAO> compareByBowlerStrikeRate = Comparator.comparing(iplData -> iplData.bowlerStrikeRate);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BOWLER_STRIKE,compareByBowlerStrikeRate);
        Comparator<CricketAnalysisDAO> compareByEconomy = Comparator.comparing(iplData -> iplData.economy);
        iplComparator.put(CricketLeagueAnalysis.CompareType.ECONOMY,compareByEconomy);
        Comparator<CricketAnalysisDAO> compareByFiveAndFourWickets = Comparator.comparing(iplData -> (iplData.fiveWickets * 5 + iplData.fourWickets * 4));
        iplComparator.put(CricketLeagueAnalysis.CompareType.FOUR_AND_FIVE_WICKETS,compareByFiveAndFourWickets);
        Comparator<CricketAnalysisDAO> compareByStrikeWithFiveAndFourWickets = compareByFiveAndFourWickets.thenComparing(compareByBowlerStrikeRate);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS,compareByStrikeWithFiveAndFourWickets);
        Comparator<CricketAnalysisDAO> compareByAverageWithStrike = compareByBowlerAverage.thenComparing(compareByBowlerStrikeRate);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BOWLER_AVERAGE_WITH_STRIKE,compareByAverageWithStrike);
        Comparator<CricketAnalysisDAO> compareByWickets = Comparator.comparing(iplData -> iplData.wickets);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BOWLER_WICKETS,compareByWickets);
        Comparator<CricketAnalysisDAO> compareByWicketsWithAverage = compareByWickets.thenComparing(compareByBowlerAverage);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BOWLER_WICKETS_WITH_AVERAGE,compareByWicketsWithAverage);
        Comparator<CricketAnalysisDAO> compareByBattingAndBowlingAverage = compareByBattingAverage.thenComparing(compareByBowlerAverage);
        iplComparator.put(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE_WITH_BOWLER_AVERAGE,compareByBattingAndBowlingAverage);
        Comparator<CricketAnalysisDAO> compareByRunsAndWickets = compareByRuns.thenComparing(compareByWickets);
        iplComparator.put(CricketLeagueAnalysis.CompareType.RUNS_WITH_WICKETS,compareByRunsAndWickets);
    }

    public Comparator<CricketAnalysisDAO> getIPLDataComparator(CricketLeagueAnalysis.CompareType compareType) {
        return iplComparator.get(compareType);
    }

}
