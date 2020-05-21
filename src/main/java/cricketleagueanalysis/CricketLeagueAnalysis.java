package cricketleagueanalysis;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalysis {

    Map<String,CricketAnalysisDAO> cricketLeagueData = null;

    public enum CompareType {BATSMAN_STRIKE, BATSMAN_AVERAGE, RUNS, SIX_AND_FOUR, BATSMAN_AVERAGE_WITH_STRIKE,
                             BATSMAN_STRIKE_WITH_SIX_AND_FOUR, BATSMAN_RUNS_WITH_AVERAGE, FOUR_AND_FIVE_WICKETS,
                             BOWLER_AVERAGE, BOWLER_STRIKE, BOWLER_WICKETS,BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS,
                             ECONOMY,BOWLER_AVERAGE_WITH_STRIKE,BOWLER_WICKETS_WITH_AVERAGE,RUNS_WITH_WICKETS,
                             BATSMAN_AVERAGE_WITH_BOWLER_AVERAGE}

    enum Cricket {BATTING,BOWLING,BATTING_BOWLING}
    private Cricket cricket;

    public CricketLeagueAnalysis(Cricket cricket) {
        this.cricket = cricket;
    }

    public int loadIPLData(String... csvFilePath) throws CricketLeagueAnalysisException {
        cricketLeagueData = new CricketLeagueDataAdapterFactory().getCricketLeagueData(cricket,csvFilePath);
        return cricketLeagueData.size();
    }

    public String getSortedData(CompareType compareType) throws CricketLeagueAnalysisException {
        if (cricketLeagueData == null || cricketLeagueData.size() == 0) {
            throw new CricketLeagueAnalysisException("No Data", CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        }
        Comparator<CricketAnalysisDAO> iplComparator = new IPLComparator().getIPLDataComparator(compareType);
        List sortedCricketLeagueData = cricketLeagueData.values().stream()
                                                                 .sorted(iplComparator.reversed())
                                                                 .map(iplDAO -> iplDAO.getIPLDTO(cricket))
                                                                 .collect(Collectors.toList());
        String sortedCricketLeagueDataInJson = new Gson().toJson(sortedCricketLeagueData);
        return sortedCricketLeagueDataInJson;
    }

}
