package cricketleagueanalysis;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalysisTest {

    private static final String IPL_BATSMAN_DATA ="C:\\Users\\arun kumar\\IdeaProjects\\CricketLeagueAnalysis\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_PATH ="C:\\Users\\arun kumar\\IdeaProjects\\CricketLeagueAnalysis\\src\\main\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_DELIMITER = "C:\\Users\\arun kumar\\IdeaProjects\\CricketLeagueAnalysis\\src\\test\\resources\\IPL2019FactsheetMostRunsWithWrongDelimiter.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_HEADER ="C:\\Users\\arun kumar\\IdeaProjects\\CricketLeagueAnalysis\\src\\test\\resources\\IPL2019FactsheetMostRunsWithWrongHeader.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_TYPE ="C:\\Users\\arun kumar\\IdeaProjects\\CricketLeagueAnalysis\\src\\test\\resources\\IPL2019FactsheetMostRuns.txt";;
    private static final String IPL_BATSMAN_DATA_EMPTY_FILE ="C:\\Users\\arun kumar\\IdeaProjects\\CricketLeagueAnalysis\\src\\test\\resources\\IPL2019FactsheetMostRunsEmptyFile.csv";;
    private static final String IPL_BOWLER_DATA ="C:\\Users\\arun kumar\\IdeaProjects\\CricketLeagueAnalysis\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPL2019BatsmanData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            int numOfPlayers = cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            Assert.assertEquals(100,numOfPlayers);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_WithWrongPath_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_WRONG_PATH);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.IPL_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_WhenEmpty_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_EMPTY_FILE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_WithWrongType_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_WRONG_TYPE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_WithWrongHeader_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_WRONG_HEADER);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA_WRONG_DELIMITER);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToBattingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToBattingAverage(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
            Assert.assertEquals("Harpreet Brar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToStrikeRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToBattingStrikingRate(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Ishant Sharma", iplBatsmanData[0].player);
            Assert.assertEquals("Bhuvneshwar Kumar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToSixsAndFours_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToSixsAndFours(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
            Assert.assertEquals("Shakib Al Hasan", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToSixsAndFours_shouldReturnSrikeRate() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToSixsAndFours(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(204.81, iplBatsmanData[0].battingStrikeRate,0.001);
            Assert.assertEquals(90.0, iplBatsmanData[99].battingStrikeRate,0.001);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToStrikeRate_shouldReturnBattingAverage() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToBattingStrikingRate(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(0.0, iplBatsmanData[0].battingAverage,0.001);
            Assert.assertEquals(4.0, iplBatsmanData[99].battingAverage,0.001);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToBattingAverage_shouldReturnTotalNumberOfRunsOfPlayer() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToBattingAverage(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(416, iplBatsmanData[0].runs);
            Assert.assertEquals(20, iplBatsmanData[99].runs);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToStrikeRateWithSixsAndFours_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToBattingStrikeRateWithSixsAndFours(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
            Assert.assertEquals("Shakib Al Hasan", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToBattingAverageWithStrikeRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToBattingAverageWithBattingStrikeRate(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
            Assert.assertEquals("Tim Southee", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToRunsWithBattingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedDataAccordingToRunsWithBattingAverage(CricketLeagueAnalysis.Cricket.BATTING);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("David Warner ", iplBatsmanData[0].player);
            Assert.assertEquals("Tim Southee", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BowlerData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            int numOfPlayers = cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            Assert.assertEquals(99,numOfPlayers);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BowlersData_whenSortedAccordingToBowlingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedDataAccordingToBowlerAverage(CricketLeagueAnalysis.Cricket.BOWLING);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Tim Southee", iplBowlersData[1].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BowlersData_whenSortedAccordingToStrikeRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedDataAccordingToBowlerStrikeRate(CricketLeagueAnalysis.Cricket.BOWLING);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Prasidh Krishna", iplBowlersData[1].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BowlersData_whenSortedAccordingToEconomy_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedDataAccordingToEconomy(CricketLeagueAnalysis.Cricket.BOWLING);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Ben Cutting", iplBowlersData[0].player);
            Assert.assertEquals("Shivam Dube", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BowlersData_whenSortedAccordingToStringRateWithFiveWicketsAndFourWicket_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedDataAccordingToBowlerStrikeRateWith5WicketsAnd4Wickets(CricketLeagueAnalysis.Cricket.BOWLING);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Lasith Malinga", iplBowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BowlersData_whenSortedAccordingToBowlingAverageWithBowlingStrikingRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedDataAccordingToBowlingAverageWithBowlingStrikeRate(CricketLeagueAnalysis.Cricket.BOWLING);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BowlersData_whenSortedAccordingToWicketsWithBowlingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedDataAccordingToWicketsWithBowlingAverage(CricketLeagueAnalysis.Cricket.BOWLING);
            BowlerData[] bowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Imran Tahir", bowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", bowlersData[98].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanAndBowlerData_shouldReturnTotalNumberOfRecords() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING);
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            Assert.assertEquals(100,numOfRecords);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BowlersAndBatsmanData_whenSortedAccordingToBattingAndBowlingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalysis.getSortedDataAccordingToBattingAndBowlingAverage(CricketLeagueAnalysis.Cricket.BATTING_BOWLING);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("MS Dhoni", cricketLeagueData[0].player);
            Assert.assertEquals("Harpreet Brar", cricketLeagueData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

}
