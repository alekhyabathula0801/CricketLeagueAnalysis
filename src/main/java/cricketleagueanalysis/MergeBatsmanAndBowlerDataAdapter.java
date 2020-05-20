package cricketleagueanalysis;

import java.util.HashMap;
import java.util.Map;

public class MergeBatsmanAndBowlerDataAdapter extends CricketLeagueDataAdapter{

    Map<String, CricketAnalysisDAO> batsmanData = new HashMap<>();
    Map<String, CricketAnalysisDAO> bowlersData = new HashMap<>();

    public Map<String, CricketAnalysisDAO> loadIPLData(String... csvFilePath) throws CricketLeagueAnalysisException {
        batsmanData = super.getCricketLeagueData(BatsmanData.class,csvFilePath[0]);
        bowlersData = super.getCricketLeagueData(BowlerData.class,csvFilePath[1]);
        bowlersData.values().stream()
                            .filter(iplData -> batsmanData.get(iplData.player) != null)
                            .forEach(iplData -> {
                                batsmanData.get(iplData.player).bowlerAverage = iplData.bowlerAverage;
                                batsmanData.get(iplData.player).wickets = iplData.wickets;
                            });
        return batsmanData;
    }

}
