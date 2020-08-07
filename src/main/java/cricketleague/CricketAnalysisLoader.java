package cricketleague;

import com.csvbuilderexception.CSVBuilderException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CricketAnalysisLoader<E> {
    public HashMap<String, AnalyseDAO> loadCricketAnalysis(String[] IplFilePath, Class cricketAnalysesClass) throws CensusAnalyserException {
        HashMap<String, AnalyseDAO> analyseMap = new HashMap<>();
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(IplFilePath[0]));) {
            Iterator<E> censusIterator = csvBuilder.getCSVFileIterator(reader, cricketAnalysesClass);
            Iterable<E> csvIterable = () -> censusIterator;
            if (cricketAnalysesClass.getName().equals("cricketleague.IplRunAnalysesData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplRunAnalysesData.class::cast)
                        .forEach(runAnalysis -> analyseMap.put(runAnalysis.player, new AnalyseDAO(runAnalysis)));
                return analyseMap;
            } else if (cricketAnalysesClass.getName().equals("cricketleague.IplWktAnalyseData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplWktAnalyseData.class::cast)
                        .forEach(runAnalysis -> analyseMap.put(runAnalysis.player, new AnalyseDAO(runAnalysis)));
                return analyseMap;
            }
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
        return analyseMap;
    }

}
