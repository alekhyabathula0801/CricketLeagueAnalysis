package cricketleagueanalysis;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalysisTest {

    private static final String IPL_BATSMAN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_PATH ="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_DELIMITER = "./src/test/resources/IPL2019FactsheetMostRunsWithWrongDelimiter.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_HEADER ="./src/test/resources/IPL2019FactsheetMostRunsWithWrongHeader.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_TYPE ="./src/test/resources/IPL2019FactsheetMostRuns.txt";
    private static final String IPL_BATSMAN_DATA_EMPTY_FILE ="./src/test/resources/IPL2019FactsheetMostRunsEmptyFile.csv";
    private static final String IPL_BOWLER_DATA ="./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenBatsmanData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            int numOfPlayers = cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            Assert.assertEquals(100,numOfPlayers);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_WithWrongPath_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_WRONG_PATH);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.IPL_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenBatsmanData_WhenEmpty_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_EMPTY_FILE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenBatsmanData_WithWrongType_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_WRONG_TYPE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenBatsmanData_WithWrongHeader_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_WRONG_HEADER);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenBatsmanData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_WRONG_DELIMITER);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToBattingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
            Assert.assertEquals("Harpreet Brar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToStrikeRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Ishant Sharma", iplBatsmanData[0].player);
            Assert.assertEquals("Bhuvneshwar Kumar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToSixsAndFours_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
            Assert.assertEquals("Shakib Al Hasan", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToSixsAndFours_shouldReturnSrikeRate() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(204.81, iplBatsmanData[0].batsmanStrikeRate,0.001);
            Assert.assertEquals(90.0, iplBatsmanData[99].batsmanStrikeRate,0.001);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToStrikeRate_shouldReturnBattingAverage() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(0.0, iplBatsmanData[0].batsmanAverage,0.001);
            Assert.assertEquals(4.0, iplBatsmanData[99].batsmanAverage,0.001);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToBattingAverage_shouldReturnTotalNumberOfRunsOfPlayer() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(416, iplBatsmanData[0].runs);
            Assert.assertEquals(20, iplBatsmanData[99].runs);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToStrikeRateWithSixsAndFours_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_STRIKE_WITH_SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
            Assert.assertEquals("Shakib Al Hasan", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToBattingAverageWithStrikeRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE_WITH_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
            Assert.assertEquals("Tim Southee", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToRunsWithBattingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_RUNS_WITH_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("David Warner ", iplBatsmanData[0].player);
            Assert.assertEquals("Tim Southee", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlerData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            int numOfPlayers = cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            Assert.assertEquals(99,numOfPlayers);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToBowlingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_AVERAGE);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Tim Southee", iplBowlersData[1].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToStrikeRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_STRIKE);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Prasidh Krishna", iplBowlersData[1].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToEconomy_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.ECONOMY);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Ben Cutting", iplBowlersData[0].player);
            Assert.assertEquals("Shivam Dube", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToStringRateWithFiveWicketsAndFourWicket_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Lasith Malinga", iplBowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToBowlingAverageWithBowlingStrikingRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_AVERAGE_WITH_STRIKE);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToWicketsWithBowlingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_WICKETS_WITH_AVERAGE);
            BowlerData[] bowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Imran Tahir", bowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", bowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanAndBowlerData_shouldReturnTotalNumberOfRecords() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING);
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            Assert.assertEquals(100,numOfRecords);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersAndBatsmanData_whenSortedAccordingToBattingAndBowlingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE_WITH_BOWLER_AVERAGE);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("MS Dhoni", cricketLeagueData[0].player);
            Assert.assertEquals("Harpreet Brar", cricketLeagueData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersAndBatsmanData_whenSortedAccordingToRunsAndWickets_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.RUNS_WITH_WICKETS);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("David Warner ", cricketLeagueData[0].player);
            Assert.assertEquals("Tim Southee", cricketLeagueData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersAndBatsmanData_whenSortedAccordingToRunsAndWickets_shouldReturnDataOf16thPlayer() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.RUNS_WITH_WICKETS);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("Hardik Pandya", cricketLeagueData[15].player);
            Assert.assertEquals(14, cricketLeagueData[15].wickets);
            Assert.assertEquals(402, cricketLeagueData[15].runs);
            Assert.assertEquals(27.85, cricketLeagueData[15].bowlerAverage,0.001);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_WhenDataNotLoaded_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.NO_DATA,e.type);
        }
    }

}
