package cricketleague;

import com.google.gson.Gson;


import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalysis<player> {
    HashMap<String, AnalyseDAO> analyseMap = new HashMap<>();

    public int loadCricketRunAnalysisData(String... IplFilePath) throws CensusAnalyserException {
        analyseMap = new CricketAnalysisLoader().loadCricketAnalysis(IplFilePath, IplRunAnalysesData.class);
        return analyseMap.size();
    }

    public int loadCricketWicketAnalysisData(String... IplFilePath) throws CensusAnalyserException {
        analyseMap = new CricketAnalysisLoader().loadCricketAnalysis(IplFilePath, IplWktAnalyseData.class);
        return analyseMap.size();
    }

    private void sort(List<AnalyseDAO> analyseDao, Comparator<AnalyseDAO> censusComparator) {
        for (int firstIndex = 0; firstIndex < analyseDao.size() - 1; firstIndex++) {
            for (int secondIndex = 0; secondIndex < analyseDao.size() - firstIndex - 1; secondIndex++) {
                AnalyseDAO censusCSV1 = analyseDao.get(secondIndex);
                AnalyseDAO censusCSV2 = analyseDao.get(secondIndex + 1);
                if (censusComparator.compare(censusCSV1, censusCSV2) < 0) {
                    analyseDao.set(secondIndex, censusCSV2);
                    analyseDao.set(secondIndex + 1, censusCSV1);
                }
            }
        }
    }

    public String getAverageWiseSorted() throws CensusAnalyserException {
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<AnalyseDAO> iplMostRunsComparator = Comparator.comparing(census -> census.average);
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        this.sort(analyseDAO, iplMostRunsComparator);
        String sortedCensusJson = new Gson().toJson(analyseDAO);
        return sortedCensusJson;
    }

    public String getStrikeRateWiseSorted() throws CensusAnalyserException {
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<AnalyseDAO> iplMostRunsComparator = Comparator.comparing(census -> census.strikeRate);
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        this.sort(analyseDAO, iplMostRunsComparator);
        String sortedCensusJson = new Gson().toJson(analyseDAO);
        return sortedCensusJson;
    }

    public String getEconomyWiseSorted() throws CensusAnalyserException {
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<AnalyseDAO> iplMostRunsComparator = Comparator.comparing(census -> census.economy);
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        this.sort(analyseDAO, iplMostRunsComparator);
        String sortedCensusJson = new Gson().toJson(analyseDAO);
        return sortedCensusJson;
    }

    public String getSixAndFourWiseSorted() throws CensusAnalyserException {
        String player = null;
        double maxValue = 0.0;
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        Iterator iterator = analyseDAO.iterator();
        while (iterator.hasNext()) {
            AnalyseDAO iplRunAnalysesData = (AnalyseDAO) iterator.next();
            double maxBoundary = iplRunAnalysesData.fourBoundaryOrWkt + iplRunAnalysesData.maxBoundaryRunOrWkt;
            if (maxBoundary > maxValue) {
                maxValue = maxBoundary;
                player = iplRunAnalysesData.player;
            }
        }
        return player;
    }

    public String getBestStrikeRateWiseSorted() throws CensusAnalyserException {
        double bestStrikeRate = 0;
        double maxSixFour = 0;
        String player = null;

        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        Iterator iterator = analyseDAO.iterator();
        while (iterator.hasNext()) {
            AnalyseDAO iplRunAnalysesData = (AnalyseDAO) iterator.next();
            double maxBoundary = iplRunAnalysesData.fourBoundaryOrWkt + iplRunAnalysesData.maxBoundaryRunOrWkt;
            double strikeRate = iplRunAnalysesData.strikeRate;
            double result = strikeRate / maxBoundary;

            if (result > bestStrikeRate && maxBoundary > maxSixFour) {
                bestStrikeRate = result;
                maxSixFour = maxBoundary;
                player = iplRunAnalysesData.player;
            }
        }
        return player;
    }


    public String getBestAverageRateWiseSorted() throws CensusAnalyserException {
        double max = 0;
        double max2 = 0;
        String player = null;
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        Iterator iterator = analyseDAO.iterator();
        while (iterator.hasNext()) {
            AnalyseDAO iplRunAnalysesData = (AnalyseDAO) iterator.next();
            double average = iplRunAnalysesData.average;
            double result = iplRunAnalysesData.strikeRate / iplRunAnalysesData.average;
            if (result > max && average > max2) {
                max = result;
                max2 = average;
                player = iplRunAnalysesData.player;
            }
        }
        return player;


    }

    public String getMaxRunWiseWiseSorted() throws CensusAnalyserException {
        double max = 0;
        double maxRun = 0;
        String player = null;
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        Iterator iterator = analyseDAO.iterator();
        while (iterator.hasNext()) {
            AnalyseDAO iplRunAnalysesData = (AnalyseDAO) iterator.next();
            double Runs = iplRunAnalysesData.Runs;
            if (Runs > maxRun) {
                maxRun = Runs;
                player = iplRunAnalysesData.player;
            }
        }
        return player;


    }


}
