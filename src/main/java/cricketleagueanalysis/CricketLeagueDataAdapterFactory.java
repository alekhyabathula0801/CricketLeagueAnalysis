package cricketleagueanalysis;

import java.util.Map;

public class CricketLeagueDataAdapterFactory {

    public Map<String, CricketAnalysisDAO> getCricketLeagueData(CricketLeagueAnalysis.Cricket cricket, String... csvFilePath) throws CricketLeagueAnalysisException {
        if (cricket.equals(CricketLeagueAnalysis.Cricket.BATTING))
            return new BatsmanDataAdapter().loadIPLData(csvFilePath[0]);
        if (cricket.equals(CricketLeagueAnalysis.Cricket.BOWLING))
            return new BowlerDataAdapter().loadIPLData(csvFilePath[0]);
        return new MergeBatsmanAndBowlerDataAdapter().loadIPLData(csvFilePath);
    }

}
