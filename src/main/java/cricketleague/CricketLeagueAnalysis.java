package cricketleague;

import com.csvbuilderexception.CSVBuilderException;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalysis<E> {
    HashMap<String, AnalyseDAO> analyseMap = new HashMap<>();

    public int loadCricketAnalysisData(String IplFilePath) throws CensusAnalyserException {
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(IplFilePath));) {
            Iterator<E> censusIterator = csvBuilder.getCSVFileIterator(reader, IplRunAnalysesData.class);
            Iterable<E> csvIterable = () -> censusIterator;
            if (IplRunAnalysesData.class.getName().equals("cricketleague.IplRunAnalysesData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplRunAnalysesData.class::cast)
                        .filter(iplRunAnalysesData ->iplRunAnalysesData.battingAvg>0.00)
                        .forEach(runAnalysis -> analyseMap.put( runAnalysis.player, new AnalyseDAO(runAnalysis)));
                return analyseMap.size();
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
        return analyseMap.size();
    }
    private void sort(List<AnalyseDAO> analyseDAO, Comparator<AnalyseDAO> censusComparator) {
        for (int firstIndex = 0; firstIndex < analyseDAO.size() - 1; firstIndex++) {
            for (int secondIndex = 0; secondIndex < analyseDAO.size() - firstIndex - 1; secondIndex++) {
                AnalyseDAO censusCSV1 = analyseDAO.get(secondIndex);
                AnalyseDAO censusCSV2 = analyseDAO.get(secondIndex + 1);
                if (censusComparator.compare(censusCSV1, censusCSV2) < 0) {
                    analyseDAO.set(secondIndex, censusCSV2);
                    analyseDAO.set(secondIndex + 1, censusCSV1);
                }
            }
        }
    }

    public String givenTopBattingAverageWiseSortedCodeData() throws CensusAnalyserException, IOException {
        if (analyseMap == null || analyseMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<AnalyseDAO> censusComparator = Comparator.comparing(census -> census.average);
        List<AnalyseDAO> censusDAOS = analyseMap.values().stream().collect(Collectors.toList());
       // censusDAOS.sort((AnalyseDAO a1,AnalyseDAO a2)->a1.getAverage().compareTo(a2.getAverage()));
        this.sort(censusDAOS, censusComparator);
        String sortedAreaCensusJson = new Gson().toJson(censusDAOS);
        FileWriter fileWriter = new FileWriter("TopBattingAverages.json");
        fileWriter.write(sortedAreaCensusJson);
        fileWriter.close();
        return sortedAreaCensusJson;

    }
}

