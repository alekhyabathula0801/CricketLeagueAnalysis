package cricketleagueanalysis;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class CricketLeagueAnalysisTest {

    public String IPL_BATSMAN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    public String IPL_BOWLER_DATA ="./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Mock
    CricketLeagueDataAdapterFactory cricketLeagueDataAdapterFactory;

    @Rule
    public MockitoRule mockitoRule = new MockitoJUnit().rule();

    @Test
    public void givenBatsmanData_whenLoaded_ShouldReturnSize() throws CricketLeagueAnalysisException {
        Map<String, CricketAnalysisDAO> data = new HashMap<>();
        BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
        BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
        BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
        BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
        BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
        BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
        data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
        data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
        data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
        data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
        data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
        data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
        when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
        int cricketLeagueData = cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
        Assert.assertEquals(6, cricketLeagueData);
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToBattingAverage_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToStrikeRate_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Ishant Sharma", iplBatsmanData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToSixsAndFours_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToSixsAndFours_shouldReturnSrikeRate() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING, cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(204.81, iplBatsmanData[0].batsmanStrikeRate,0.001);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToStrikeRate_shouldReturnBattingAverage() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(0.0, iplBatsmanData[0].batsmanAverage,0.001);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToBattingAverage_shouldReturnTotalNumberOfRunsOfPlayer() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(416, iplBatsmanData[0].runs);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToStrikeRateWithSixsAndFours_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_STRIKE_WITH_SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToBattingAverageWithStrikeRate_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE_WITH_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_whenSortedAccordingToRunsWithBattingAverage_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanData batsman1Data = new BatsmanData("David Warner ", 692, 69.2,  143.86, 57, 21);
            BatsmanData batsman2Data = new BatsmanData("KL Rahul",593,53.9,135.38, 49, 25);
            BatsmanData batsman3Data = new BatsmanData("Quinton de Kock",529,35.26,132.91, 45, 25);
            BatsmanData batsman4Data = new BatsmanData("MS Dhoni",416,83.2,134.62, 22, 23);
            BatsmanData batsman5Data = new BatsmanData("Ishant Sharma",10,0,333.33, 1, 1);
            BatsmanData batsman6Data = new BatsmanData("Andre Russell",510,56.66,204.81, 31, 52);
            data.put("David Warner ", new CricketAnalysisDAO(batsman1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(batsman2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(batsman3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(batsman4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(batsman5Data));
            data.put("Andre Russell", new CricketAnalysisDAO(batsman6Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING,IPL_BATSMAN_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA);
            String sortedBatsmanData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_RUNS_WITH_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("David Warner ", iplBatsmanData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_WhenCorrect_ShouldReturnSize() throws CricketLeagueAnalysisException {
        Map<String, CricketAnalysisDAO> data = new HashMap<>();
        BowlerData bowler1Data = new BowlerData("Krishnappa Gowtham",166.0,120.0,8.3,0,0,1);
        BowlerData bowler2Data = new BowlerData("Ben Cutting",27.0,12.0,13.5,0,0,1);
        BowlerData bowler3Data = new BowlerData("Lasith Malinga",27.37,16.81,9.76,2,0,16);
        BowlerData bowler4Data = new BowlerData("Yusuf Pathan",0,0.0,8,0,0,0);
        BowlerData bowler5Data = new BowlerData("Imran Tahir",16.57,14.84,6.69,2,0,26);
        data.put("David Warner ", new CricketAnalysisDAO(bowler1Data));
        data.put("KL Rahul", new CricketAnalysisDAO(bowler2Data));
        data.put("Quinton de Kock", new CricketAnalysisDAO(bowler3Data));
        data.put("MS Dhoni", new CricketAnalysisDAO(bowler4Data));
        data.put("Ishant Sharma", new CricketAnalysisDAO(bowler5Data));
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING,cricketLeagueDataAdapterFactory);
        when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BOWLING,IPL_BOWLER_DATA)).thenReturn(data);
        int cricketLeagueData = cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
        Assert.assertEquals(5, cricketLeagueData);
    }


    @Test
    public void givenBowlersData_whenSortedAccordingToBowlingAverage_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BowlerData bowler1Data = new BowlerData("Krishnappa Gowtham",166.0,120.0,8.3,0,0,1);
            BowlerData bowler2Data = new BowlerData("Ben Cutting",27.0,12.0,13.5,0,0,1);
            BowlerData bowler3Data = new BowlerData("Lasith Malinga",27.37,16.81,9.76,2,0,16);
            BowlerData bowler4Data = new BowlerData("Yusuf Pathan",0,0.0,8,0,0,0);
            BowlerData bowler5Data = new BowlerData("Imran Tahir",16.57,14.84,6.69,2,0,26);
            data.put("David Warner ", new CricketAnalysisDAO(bowler1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(bowler2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(bowler3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(bowler4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(bowler5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BOWLING,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_AVERAGE);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[data.size()-1].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToStrikeRate_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BowlerData bowler1Data = new BowlerData("Krishnappa Gowtham",166.0,120.0,8.3,0,0,1);
            BowlerData bowler2Data = new BowlerData("Ben Cutting",27.0,12.0,13.5,0,0,1);
            BowlerData bowler3Data = new BowlerData("Lasith Malinga",27.37,16.81,9.76,2,0,16);
            BowlerData bowler4Data = new BowlerData("Yusuf Pathan",0,0.0,8,0,0,0);
            BowlerData bowler5Data = new BowlerData("Imran Tahir",16.57,14.84,6.69,2,0,26);
            data.put("David Warner ", new CricketAnalysisDAO(bowler1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(bowler2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(bowler3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(bowler4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(bowler5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BOWLING,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_STRIKE);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[data.size()-1].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToEconomy_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BowlerData bowler1Data = new BowlerData("Krishnappa Gowtham",166.0,120.0,8.3,0,0,1);
            BowlerData bowler2Data = new BowlerData("Ben Cutting",27.0,12.0,13.5,0,0,1);
            BowlerData bowler3Data = new BowlerData("Lasith Malinga",27.37,16.81,9.76,2,0,16);
            BowlerData bowler4Data = new BowlerData("Yusuf Pathan",0,0.0,8,0,0,0);
            BowlerData bowler5Data = new BowlerData("Imran Tahir",16.57,14.84,6.69,2,0,26);
            data.put("David Warner ", new CricketAnalysisDAO(bowler1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(bowler2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(bowler3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(bowler4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(bowler5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BOWLING,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.ECONOMY);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Ben Cutting", iplBowlersData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToStringRateWithFiveWicketsAndFourWicket_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BowlerData bowler1Data = new BowlerData("Krishnappa Gowtham",166.0,120.0,8.3,0,0,1);
            BowlerData bowler2Data = new BowlerData("Ben Cutting",27.0,12.0,13.5,0,0,1);
            BowlerData bowler3Data = new BowlerData("Lasith Malinga",27.37,16.81,9.76,2,0,16);
            BowlerData bowler4Data = new BowlerData("Yusuf Pathan",0,0.0,8,0,0,0);
            BowlerData bowler5Data = new BowlerData("Imran Tahir",16.57,14.84,6.69,2,0,26);
            data.put("David Warner ", new CricketAnalysisDAO(bowler1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(bowler2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(bowler3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(bowler4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(bowler5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BOWLING,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Lasith Malinga", iplBowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[data.size()-1].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToBowlingAverageWithBowlingStrikingRate_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BowlerData bowler1Data = new BowlerData("Krishnappa Gowtham",166.0,120.0,8.3,0,0,1);
            BowlerData bowler2Data = new BowlerData("Ben Cutting",27.0,12.0,13.5,0,0,1);
            BowlerData bowler3Data = new BowlerData("Lasith Malinga",27.37,16.81,9.76,2,0,16);
            BowlerData bowler4Data = new BowlerData("Yusuf Pathan",0,0.0,8,0,0,0);
            BowlerData bowler5Data = new BowlerData("Imran Tahir",16.57,14.84,6.69,2,0,26);
            data.put("David Warner ", new CricketAnalysisDAO(bowler1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(bowler2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(bowler3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(bowler4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(bowler5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BOWLING,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_AVERAGE_WITH_STRIKE);
            BowlerData[] iplBowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBowlersData[data.size()-1].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersData_whenSortedAccordingToWicketsWithBowlingAverage_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BowlerData bowler1Data = new BowlerData("Krishnappa Gowtham",166.0,120.0,8.3,0,0,1);
            BowlerData bowler2Data = new BowlerData("Ben Cutting",27.0,12.0,13.5,0,0,1);
            BowlerData bowler3Data = new BowlerData("Lasith Malinga",27.37,16.81,9.76,2,0,16);
            BowlerData bowler4Data = new BowlerData("Yusuf Pathan",0,0.0,8,0,0,0);
            BowlerData bowler5Data = new BowlerData("Imran Tahir",16.57,14.84,6.69,2,0,26);
            data.put("David Warner ", new CricketAnalysisDAO(bowler1Data));
            data.put("KL Rahul", new CricketAnalysisDAO(bowler2Data));
            data.put("Quinton de Kock", new CricketAnalysisDAO(bowler3Data));
            data.put("MS Dhoni", new CricketAnalysisDAO(bowler4Data));
            data.put("Ishant Sharma", new CricketAnalysisDAO(bowler5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BOWLING,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BOWLER_DATA);
            String sortedBowlersData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BOWLER_WICKETS_WITH_AVERAGE);
            BowlerData[] bowlersData = new Gson().fromJson(sortedBowlersData, BowlerData[].class);
            Assert.assertEquals("Imran Tahir", bowlersData[0].player);
            Assert.assertEquals("Yusuf Pathan", bowlersData[data.size()-1].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanAndBowlerData_shouldReturnTotalNumberOfRecords() {
        try{
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanAndBowlerData cricketer1Data = new BatsmanAndBowlerData("MS Dhoni",416,83.2,134.62, 22, 23,0,0);
            BatsmanAndBowlerData cricketer2Data = new BatsmanAndBowlerData("David Warner ", 692, 69.2,  143.86, 57, 21,0,0);
            BatsmanAndBowlerData cricketer3Data = new BatsmanAndBowlerData("Harpreet Brar", 20, 0,  166.66, 2, 2,0,0);
            BatsmanAndBowlerData cricketer4Data = new BatsmanAndBowlerData("Tim Southee", 9, 0,  100.00, 2, 1,0,0);
            BatsmanAndBowlerData cricketer5Data = new BatsmanAndBowlerData("Hardik Pandya", 402, 44.66,  191.42, 28, 29,27.85,14);
            data.put("MS Dhoni", new CricketAnalysisDAO(cricketer1Data));
            data.put("David Warner ", new CricketAnalysisDAO(cricketer2Data));
            data.put("Harpreet Brar", new CricketAnalysisDAO(cricketer3Data));
            data.put("Tim Southee", new CricketAnalysisDAO(cricketer4Data));
            data.put("Hardik Pandya", new CricketAnalysisDAO(cricketer5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,IPL_BATSMAN_DATA,IPL_BOWLER_DATA)).thenReturn(data);
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            Assert.assertEquals(5,numOfRecords);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersAndBatsmanData_whenSortedAccordingToBattingAndBowlingAverage_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanAndBowlerData cricketer1Data = new BatsmanAndBowlerData("MS Dhoni",416,83.2,134.62, 22, 23,0,0);
            BatsmanAndBowlerData cricketer2Data = new BatsmanAndBowlerData("David Warner ", 692, 69.2,  143.86, 57, 21,0,0);
            BatsmanAndBowlerData cricketer4Data = new BatsmanAndBowlerData("Tim Southee", 9, 0,  100.00, 2, 1,0,0);
            BatsmanAndBowlerData cricketer3Data = new BatsmanAndBowlerData("Harpreet Brar", 20, 0,  166.66, 2, 2,0,0);
            BatsmanAndBowlerData cricketer5Data = new BatsmanAndBowlerData("Hardik Pandya", 402, 44.66,  191.42, 28, 29,27.85,14);
            data.put("MS Dhoni", new CricketAnalysisDAO(cricketer1Data));
            data.put("David Warner ", new CricketAnalysisDAO(cricketer2Data));
            data.put("Harpreet Brar", new CricketAnalysisDAO(cricketer3Data));
            data.put("Tim Southee", new CricketAnalysisDAO(cricketer4Data));
            data.put("Hardik Pandya", new CricketAnalysisDAO(cricketer5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,IPL_BATSMAN_DATA,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE_WITH_BOWLER_AVERAGE);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("MS Dhoni", cricketLeagueData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersAndBatsmanData_whenSortedAccordingToRunsAndWickets_shouldReturnSortedResults() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanAndBowlerData cricketer1Data = new BatsmanAndBowlerData("MS Dhoni",416,83.2,134.62, 22, 23,0,0);
            BatsmanAndBowlerData cricketer2Data = new BatsmanAndBowlerData("David Warner ", 692, 69.2,  143.86, 57, 21,0,0);
            BatsmanAndBowlerData cricketer3Data = new BatsmanAndBowlerData("Harpreet Brar", 20, 0,  166.66, 2, 2,0,0);
            BatsmanAndBowlerData cricketer4Data = new BatsmanAndBowlerData("Tim Southee", 9, 0,  100.00, 2, 1,0,0);
            BatsmanAndBowlerData cricketer5Data = new BatsmanAndBowlerData("Hardik Pandya", 402, 44.66,  191.42, 28, 29,27.85,14);
            data.put("MS Dhoni", new CricketAnalysisDAO(cricketer1Data));
            data.put("David Warner ", new CricketAnalysisDAO(cricketer2Data));
            data.put("Harpreet Brar", new CricketAnalysisDAO(cricketer3Data));
            data.put("Tim Southee", new CricketAnalysisDAO(cricketer4Data));
            data.put("Hardik Pandya", new CricketAnalysisDAO(cricketer5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,IPL_BATSMAN_DATA,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.RUNS_WITH_WICKETS);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("David Warner ", cricketLeagueData[0].player);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBowlersAndBatsmanData_whenSortedAccordingToRunsAndWickets_shouldReturnDataOf3rdPlayer() {
        try {
            Map<String, CricketAnalysisDAO> data = new HashMap<>();
            BatsmanAndBowlerData cricketer1Data = new BatsmanAndBowlerData("MS Dhoni",416,83.2,134.62, 22, 23,0,0);
            BatsmanAndBowlerData cricketer2Data = new BatsmanAndBowlerData("David Warner ", 692, 69.2,  143.86, 57, 21,0,0);
            BatsmanAndBowlerData cricketer3Data = new BatsmanAndBowlerData("Harpreet Brar", 20, 0,  166.66, 2, 2,0,0);
            BatsmanAndBowlerData cricketer4Data = new BatsmanAndBowlerData("Tim Southee", 9, 0,  100.00, 2, 1,0,0);
            BatsmanAndBowlerData cricketer5Data = new BatsmanAndBowlerData("Hardik Pandya", 402, 44.66,  191.42, 28, 29,27.85,14);
            data.put("MS Dhoni", new CricketAnalysisDAO(cricketer1Data));
            data.put("David Warner ", new CricketAnalysisDAO(cricketer2Data));
            data.put("Harpreet Brar", new CricketAnalysisDAO(cricketer3Data));
            data.put("Tim Southee", new CricketAnalysisDAO(cricketer4Data));
            data.put("Hardik Pandya", new CricketAnalysisDAO(cricketer5Data));
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,cricketLeagueDataAdapterFactory);
            when(cricketLeagueDataAdapterFactory.getCricketLeagueData(CricketLeagueAnalysis.Cricket.BATTING_BOWLING,IPL_BATSMAN_DATA,IPL_BOWLER_DATA)).thenReturn(data);
            cricketLeagueAnalysis.loadIPLData(IPL_BATSMAN_DATA,IPL_BOWLER_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.RUNS_WITH_WICKETS);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("Hardik Pandya", cricketLeagueData[2].player);
            Assert.assertEquals(14, cricketLeagueData[2].wickets);
            Assert.assertEquals(402, cricketLeagueData[2].runs);
            Assert.assertEquals(27.85, cricketLeagueData[2].bowlerAverage,0.0);
        } catch (CricketLeagueAnalysisException e) {}
    }

    @Test
    public void givenBatsmanData_WhenDataNotLoaded_ShouldThrowException() {
        try {
            CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(CricketLeagueAnalysis.Cricket.BATTING,cricketLeagueDataAdapterFactory);
            cricketLeagueAnalysis.getSortedData(CricketLeagueAnalysis.CompareType.BATSMAN_AVERAGE);
        } catch (CricketLeagueAnalysisException e) {
            Assert.assertEquals(CricketLeagueAnalysisException.ExceptionType.NO_DATA,e.type);
        }
    }

}
