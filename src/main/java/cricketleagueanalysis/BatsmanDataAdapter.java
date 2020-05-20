package cricketleagueanalysis;

import java.util.Map;

public class BatsmanDataAdapter extends CricketLeagueDataAdapter {

    public Map<String, CricketAnalysisDAO> loadIPLData(String... csvFilePath) throws CricketLeagueAnalysisException {
        return super.getCricketLeagueData(BatsmanData.class,csvFilePath);
    }

}
