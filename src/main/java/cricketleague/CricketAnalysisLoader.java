package cricketleague;

import com.csvbuilderexception.CSVBuilderException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;


public class CricketAnalysisLoader<E> {
    HashMap<String, AnalyseDAO> analyseMap = new HashMap<>();

    public HashMap<String, AnalyseDAO> loadCricketAnalysis(String[] IplFilePath, Class cricketAnalysesClass) throws CensusAnalyserException {
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(IplFilePath[0]));) {
            Iterator<E> censusIterator = csvBuilder.getCSVFileIterator(reader, cricketAnalysesClass);
            Iterable<E> csvIterable = () -> censusIterator;
            if (cricketAnalysesClass.getName().equals("cricketleague.IplRunAnalysesData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplRunAnalysesData.class::cast)
                        .forEach(runAnalysis -> analyseMap.put(runAnalysis.player, new AnalyseDAO(runAnalysis)));
            } else if (cricketAnalysesClass.getName().equals("cricketleague.IplWktAnalyseData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplWktAnalyseData.class::cast)
                        .forEach(runAnalysis -> analyseMap.put(runAnalysis.player, new AnalyseDAO(runAnalysis)));
                return analyseMap;
            }
            if (IplFilePath.length == 1) return analyseMap;
            this.loadCricketWktAnalsis(IplFilePath[1], IplFilePath[2]);
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

    private HashMap<String, AnalyseDAO> loadCricketWktAnalsis(String analysisFilePath, String flag) throws IOException, CensusAnalyserException {
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(analysisFilePath));) {
            Iterator<E> censusIterator = csvBuilder.getCSVFileIterator(reader, IplWktAnalyseData.class);
            Iterable<E> csvIterable = () -> censusIterator;
            if (flag.equals("true")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplWktAnalyseData.class::cast)
                        .filter(wktCSV -> analyseMap.get(wktCSV.player) != null)
                        .forEach(wktCSV -> analyseMap.get(wktCSV.player).bowlAverage = wktCSV.Avg);
            } else if (flag.equals(false)) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplWktAnalyseData.class::cast)
                        .filter(wktCSV -> analyseMap.get(wktCSV.player) != null)
                        .forEach(wktCSV -> analyseMap.get(wktCSV.player).mostWickets = wktCSV.wickets);
            }

        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
        return analyseMap;
    }

}


