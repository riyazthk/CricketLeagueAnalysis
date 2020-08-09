package cricketleague;

import com.csvbuilderexception.CSVBuilderException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class AnalysisLoader {

    public int loadCricketAnalysis(String iplFilePath) throws CensusAnalyserException {
        HashMap<String, AnalyseDAO> analyseMap = new HashMap<>();
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(iplFilePath));) {
            Iterator<IplRunAnalysesData> censusIterator = csvBuilder.getCSVFileIterator(reader, IplRunAnalysesData.class);
            Iterable<IplRunAnalysesData> csvIterable = () -> censusIterator;
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplRunAnalysesData.class::cast)
                        .forEach(runAnalysis -> analyseMap.put(runAnalysis.player, new AnalyseDAO(runAnalysis)));

             } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
        return analyseMap.size();

    }

}
