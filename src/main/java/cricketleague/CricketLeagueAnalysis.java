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

public class CricketLeagueAnalysis<player> {
    List<IplRunAnalysesData> analyseList = new ArrayList<>();


    public int loadCricketAnalysisData(String IplFilePath) throws CensusAnalyserException {
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(IplFilePath));) {
            Iterator<IplRunAnalysesData> censusIterator = csvBuilder.getCSVFileIterator(reader, IplRunAnalysesData.class);
            Iterable<IplRunAnalysesData> csvIterable = () -> censusIterator;
            if (IplRunAnalysesData.class.getName().equals("cricketleague.IplRunAnalysesData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .forEach(runAnalysis -> analyseList.add((IplRunAnalysesData) runAnalysis));
                return analyseList.size();
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
        return analyseList.size();
    }

    private void sort(Comparator censusComparator) {
        for (int firstIndex = 0; firstIndex < analyseList.size() - 1; firstIndex++) {
            for (int secondIndex = 0; secondIndex < analyseList.size() - firstIndex - 1; secondIndex++) {
                IplRunAnalysesData censusCSV1 = analyseList.get(secondIndex);
                IplRunAnalysesData censusCSV2 = analyseList.get(secondIndex + 1);
                if (censusComparator.compare(censusCSV1, censusCSV2) < 0) {
                    analyseList.set(secondIndex, censusCSV2);
                    analyseList.set(secondIndex + 1, censusCSV1);
                }
            }
        }
    }

    public String getAverageWiseSorted() throws CensusAnalyserException {
        if (analyseList.size() == 0 || analyseList == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<IplRunAnalysesData> iplMostRunsComparator = Comparator.comparing(census -> census.battingAvg);
        this.sort(iplMostRunsComparator);
        String sortedCensusJson = new Gson().toJson(analyseList);
        return sortedCensusJson;
    }

    public String getStrikeRateWiseSorted() throws CensusAnalyserException {
        if (analyseList.size() == 0 || analyseList == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<IplRunAnalysesData> iplMostRunsComparator = Comparator.comparing(census -> census.strikeRate);
        this.sort(iplMostRunsComparator);
        String sortedCensusJson = new Gson().toJson(analyseList);
        return sortedCensusJson;
    }

    public String getSixAndFourWiseSorted() throws CensusAnalyserException {
        Comparator<IplRunAnalysesData> iplMostRunsComparator;
        if (analyseList.size() == 0 || analyseList == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        String sortedCensusJson = new Gson().toJson(analyseList);
        return sortedCensusJson;
    }

    public String getBestStrikeRateWiseSorted() throws CensusAnalyserException {
        Comparator<IplRunAnalysesData> iplMostRunsComparator;
        if (analyseList.size() == 0 || analyseList == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        String sortedCensusJson = new Gson().toJson(analyseList);
        return sortedCensusJson;
    }


    public String findBestAverageAndStrinkeRate(IplRunAnalysesData[] censusCSV) {
        double max = 0;
        double max2 = 0;
        String player = null;
        for (int arrayIndex = 0; arrayIndex < analyseList.size(); arrayIndex++) {
            double maxBoundary = censusCSV[arrayIndex].four + censusCSV[arrayIndex].six;
            double strikeRate = censusCSV[arrayIndex].strikeRate;
            double result = strikeRate / maxBoundary;

            if (result > max && maxBoundary > max2) {
                max = result;
                max2 = maxBoundary;
                player = censusCSV[arrayIndex].player;
            }
        }
        return player;
    }
}