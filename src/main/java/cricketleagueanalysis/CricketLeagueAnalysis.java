package cricketleagueanalysis;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalysis {

    Map<String,CricketAnalysisDAO> iplAnalysisMap = null;

    public int loadBatsmanData(String csvFilePath) throws CricketLeagueAnalysisException {
       iplAnalysisMap = new CricketLeagueDataLoader().getCricketLeagueData(csvFilePath);
       return iplAnalysisMap.size();
    }

    public String getSortedBatsmanDataAccordingToBattingAverage() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> censusComparator = Comparator.comparing(iplData -> iplData.average);
        return this.getSortedCricketLeagueData(censusComparator.reversed());
    }

    public String getSortedBatsmanDataAccordingToStrikingRate() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> censusComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        return this.getSortedCricketLeagueData(censusComparator.reversed());
    }

    public String getSortedBatsmanDataAccordingToSixsAndFours() throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> censusComparator = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
        return this.getSortedCricketLeagueData(censusComparator.reversed());
    }

    private String getSortedCricketLeagueData(Comparator<CricketAnalysisDAO> censusComparator) throws CricketLeagueAnalysisException {
        if(iplAnalysisMap == null || iplAnalysisMap.size() == 0 ) {
            throw new CricketLeagueAnalysisException("No Data",
                        CricketLeagueAnalysisException.ExceptionType.NO_DATA);
        }
        List sortedBatsmanData = iplAnalysisMap.values().stream().
                                                         sorted(censusComparator).
                                                         collect(Collectors.toList());
        String sortedBatsmanDataInJson = new Gson().toJson(sortedBatsmanData);
        return sortedBatsmanDataInJson;
    }

}
