package cricketleagueanalysis;

import java.util.Map;

public class BowlerDataAdapter extends CricketLeagueDataAdapter{

    public Map<String, CricketAnalysisDAO> loadIPLData(String csvFilePath) throws CricketLeagueAnalysisException {
        return super.getCricketLeagueData(BowlerDataCsv.class,csvFilePath);
    }

}
