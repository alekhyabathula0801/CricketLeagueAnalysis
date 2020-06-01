package cricketleagueanalysis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class CricketLeagueDataAdapterFactoryTest {

    private static final String IPL_BATSMAN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_PATH ="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_DELIMITER = "./src/test/resources/IPL2019FactsheetMostRunsWithWrongDelimiter.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_HEADER ="./src/test/resources/IPL2019FactsheetMostRunsWithWrongHeader.csv";
    private static final String IPL_BATSMAN_DATA_WRONG_TYPE ="./src/test/resources/IPL2019FactsheetMostRuns.txt";
    private static final String IPL_BATSMAN_DATA_EMPTY_FILE ="./src/test/resources/IPL2019FactsheetMostRunsEmptyFile.csv";
    private static final String IPL_BOWLER_DATA ="./src/test/resources/IPL2019FactsheetMostWkts.csv";

    CricketLeagueDataAdapterFactory cricketLeagueDataAdapterFactory;

    @Before
    public void setUp() throws Exception {
        cricketLeagueDataAdapterFactory = new CricketLeagueDataAdapterFactory();
    }

    @Test
    public void givenBatsmanData_whenLoaded_shouldReturnSize() {
        try {
            Map<String,CricketAnalysisDAO> cricketData = cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING, IPL_BATSMAN_DATA);
            Assert.assertEquals(100,cricketData.size());
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBatsmanData_WithWrongPath_ShouldThrowException() {
        try {
            cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING, IPL_BATSMAN_DATA_WRONG_PATH);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.IPL_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenBatsmanData_WhenEmpty_ShouldThrowException() {
        try {
            cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING, IPL_BATSMAN_DATA_EMPTY_FILE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenBatsmanData_WithWrongType_ShouldThrowException() {
        try {
            cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING, IPL_BATSMAN_DATA_WRONG_TYPE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenBatsmanData_WithWrongHeader_ShouldThrowException() {
        try {
            cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING, IPL_BATSMAN_DATA_WRONG_HEADER);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenBatsmanData_WithWrongDelimiter_ShouldThrowException() {
        try {
            cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING, IPL_BATSMAN_DATA_WRONG_DELIMITER);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenBowlerData_shouldReturnTotalNumberOfPlayers() {
        try{
            Map<String,CricketAnalysisDAO> cricketData = cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BOWLING, IPL_BOWLER_DATA);
            Assert.assertEquals(99,cricketData.size());
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBatsmanAndBowlerData_shouldReturnTotalNumberOfRecords() {
        try{
            Map<String,CricketAnalysisDAO> cricketData = cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            Assert.assertEquals(100,cricketData.size());
        } catch (CricketLeagueAnalysisException e) {
            e.printStackTrace();
        }
    }

}
