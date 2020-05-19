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

    public int loadBatsmanData(String csvFilePath) throws CricketLeagueAnalysisException {
       iplAnalysisMap = new CricketLeagueDataLoader().getCricketLeagueData(BatsmanDataCsv.class,csvFilePath);
       return iplAnalysisMap.size();
    }

    public int loadBowlerData(String csvFilePath) throws CricketLeagueAnalysisException {
        iplAnalysisMap = new CricketLeagueDataLoader().getCricketLeagueData(BowlerDataCsv.class, csvFilePath);
        return iplAnalysisMap.size();
    }

    public String getSortedDataAccordingToAverage(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> censusComparator = Comparator.comparing(iplData -> iplData.average);
        return this.getSortedCricketLeagueData(censusComparator.reversed(),cricket);
    }

    public String getSortedDataAccordingToStrikingRate(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> censusComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        return this.getSortedCricketLeagueData(censusComparator.reversed(),cricket);
    }

    public String getSortedDataAccordingToSixsAndFours(Cricket cricket) throws CricketLeagueAnalysisException {
        Comparator<CricketAnalysisDAO> censusComparator = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
        return this.getSortedCricketLeagueData(censusComparator.reversed(),cricket);
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
