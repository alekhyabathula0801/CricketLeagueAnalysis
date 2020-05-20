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

public abstract class CricketLeagueDataAdapter {

    public abstract Map<String, CricketAnalysisDAO> loadIPLData(String... csvFilePath) throws CricketLeagueAnalysisException;

    Map<String, CricketAnalysisDAO> cricketLeagueData = null;

    public <E> Map<String,CricketAnalysisDAO> getCricketLeagueData(Class<E> iplDataClass, String... csvFilePath) throws CricketLeagueAnalysisException {
        cricketLeagueData = new HashMap<>();
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0])))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, iplDataClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if(iplDataClass.getName().equals("cricketleagueanalysis.BatsmanData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                             .map(BatsmanData.class::cast)
                             .forEach(iplDataCsv -> cricketLeagueData.put(iplDataCsv.player, new CricketAnalysisDAO(iplDataCsv)));
            } else if(iplDataClass.getName().equals("cricketleagueanalysis.BowlerData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                             .map(BowlerData.class::cast)
                             .forEach(iplDataCsv -> cricketLeagueData.put(iplDataCsv.player, new CricketAnalysisDAO(iplDataCsv)));
            }
            if(cricketLeagueData.size() == 0)
                throw new CricketLeagueAnalysisException("NO_DATA",
                            CricketLeagueAnalysisException.ExceptionType.NO_DATA);
            return this.cricketLeagueData;
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