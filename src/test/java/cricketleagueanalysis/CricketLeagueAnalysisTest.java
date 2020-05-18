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

    @Test
    public void givenIPL2019BatsmanData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            int numOfRecords = cricketLeagueAnalysis.loadBatsmanData(IPL_BATSMAN_DATA);
            Assert.assertEquals(100,numOfRecords);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_WithWrongPath_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            cricketLeagueAnalysis.loadBatsmanData(IPL_BATSMAN_DATA_WRONG_PATH);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.IPL_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_WhenEmpty_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            cricketLeagueAnalysis.loadBatsmanData(IPL_BATSMAN_DATA_EMPTY_FILE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_WithWrongType_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            cricketLeagueAnalysis.loadBatsmanData(IPL_BATSMAN_DATA_WRONG_TYPE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_WithWrongHeader_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            cricketLeagueAnalysis.loadBatsmanData(IPL_BATSMAN_DATA_WRONG_HEADER);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            cricketLeagueAnalysis.loadBatsmanData(IPL_BATSMAN_DATA_WRONG_DELIMITER);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToBattingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            cricketLeagueAnalysis.loadBatsmanData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedBatsmanDataAccordingToBattingAverage();
            BatsmanDataCsv[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanDataCsv[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
            Assert.assertEquals("Harpreet Brar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenIPL2019BatsmanData_whenSortedAccordingToStrikeRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
            cricketLeagueAnalysis.loadBatsmanData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedBatsmanDataAccordingToStrikingRate();
            BatsmanDataCsv[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanDataCsv[].class);
            Assert.assertEquals("Ishant Sharma", iplBatsmanData[0].player);
            Assert.assertEquals("Bhuvneshwar Kumar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

}
