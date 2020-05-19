package cricketleagueanalysis;

import java.util.Map;

public class CricketLeagueDataAdapterFactory {

    public Map<String, CricketAnalysisDAO> getCricketLeagueData(CricketLeagueAnalysis.Cricket cricket, String csvFilePath) throws CricketLeagueAnalysisException {
        if (cricket.equals(CricketLeagueAnalysis.Cricket.BATTING))
            return new BatsmanDataAdapter().loadIPLData(csvFilePath);
        return new BowlerDataAdapter().loadIPLData(csvFilePath);
    }

}
