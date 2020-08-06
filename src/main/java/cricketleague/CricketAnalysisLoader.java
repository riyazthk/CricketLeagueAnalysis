package cricketleague;

import com.csvbuilderexception.CSVBuilderException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CricketAnalysisLoader {
    public List<IplRunAnalysesData> loadCricketAnalysis(String IplFilePath, Class cricketAnalysesClass) throws CensusAnalyserException {
        List<IplRunAnalysesData> analyseList = new ArrayList<>();
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(IplFilePath));) {
            Iterator<IplRunAnalysesData> censusIterator = csvBuilder.getCSVFileIterator(reader, cricketAnalysesClass);
            Iterable<IplRunAnalysesData> csvIterable = () -> censusIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(runAnalysis -> analyseList.add((IplRunAnalysesData) runAnalysis));
            return analyseList;
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }

    }

}
