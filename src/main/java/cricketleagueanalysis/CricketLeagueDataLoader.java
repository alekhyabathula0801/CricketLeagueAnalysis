package cricketleagueanalysis;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CricketLeagueDataLoader {

    Map<String, CricketAnalysisDAO> iplAnalysisMap = new HashMap<>();

    public Map<String,CricketAnalysisDAO> getCricketLeagueData(String csvFilePath) throws CricketLeagueAnalysisException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BatsmanDataCsv> csvFileIterator = csvBuilder.getCSVFileIterator(reader, BatsmanDataCsv.class);
            Iterable<BatsmanDataCsv> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(),false).
                          forEach(iplDataCsv -> iplAnalysisMap.put(iplDataCsv.player,new CricketAnalysisDAO(iplDataCsv)));
            if(iplAnalysisMap.size() == 0)
                throw new CricketLeagueAnalysisException("NO_DATA",
                            CricketLeagueAnalysisException.ExceptionType.NO_DATA);
            return this.iplAnalysisMap;
        } catch (IOException e) {
            throw new CricketLeagueAnalysisException(e.getMessage(),
                        CricketLeagueAnalysisException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CricketLeagueAnalysisException(e.getMessage(),
                        CricketLeagueAnalysisException.ExceptionType.UNABLE_TO_PARSE);
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalysisException(e.getMessage(),
                        CricketLeagueAnalysisException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

}