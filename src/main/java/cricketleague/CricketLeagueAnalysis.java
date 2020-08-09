package cricketleague;

import com.google.gson.Gson;


import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalysis {
    private final AnalysisLoader analysisLoader;
    HashMap<String, AnalyseDAO> analyseMap = new HashMap<>();

    public CricketLeagueAnalysis(AnalysisLoader analysisLoader) {
        this.analysisLoader = analysisLoader;
    }

    public int query(String iplFilePath) throws CensusAnalyserException {
        return this.analysisLoader.loadCricketAnalysis(iplFilePath);
    }

    public int loadCricketRunAnalysisData(String... iplFilePath) throws CensusAnalyserException {
        analyseMap = new CricketAnalysisLoader().loadCricketAnalysis(iplFilePath, IplRunAnalysesData.class);
        return analyseMap.size();
    }

    public int loadCricketWicketAnalysisData(String... iplFilePath) throws CensusAnalyserException {
        analyseMap = new CricketAnalysisLoader().loadCricketAnalysis(iplFilePath, IplWktAnalyseData.class);
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

    public String getMaxWiseWiseSorted() throws CensusAnalyserException {
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
            double Runs = iplRunAnalysesData.maxRunsOrWkts;
            double Average = iplRunAnalysesData.average;
            if (Runs > maxRun && Average > max) {
                maxRun = Runs;
                max = Average;
                player = iplRunAnalysesData.player;
            }
        }
        return player;


    }


    public String getBestBatBowlAvgWiseSorted() throws CensusAnalyserException {
        double maxBatAvg = 0;
        double maxBowlAvg = 0;
        String player = null;
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        Iterator iterator = analyseDAO.iterator();
        while (iterator.hasNext()) {
            AnalyseDAO iplRunAnalysesData = (AnalyseDAO) iterator.next();
            double bowlAverage = iplRunAnalysesData.bowlAverage;
            double batAverage = iplRunAnalysesData.average;
            if (batAverage > maxBatAvg && bowlAverage > maxBowlAvg) {
                maxBatAvg = batAverage;
                maxBowlAvg = bowlAverage;
                player = iplRunAnalysesData.player;
            }
        }
        return player;

    }

    public String getMaxRunAndWktWiseSorted() throws CensusAnalyserException {
        double maxRun = 0;
        double maxWkt = 0;
        String player = null;
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        Iterator iterator = analyseDAO.iterator();
        while (iterator.hasNext()) {
            AnalyseDAO iplRunAnalysesData = (AnalyseDAO) iterator.next();
            double maxRuns = iplRunAnalysesData.maxRunsOrWkts;
            double maxWkts = iplRunAnalysesData.mostWickets;
            if (maxRuns > maxRun && maxWkts > maxWkt) {
                maxRun = maxRuns;
                maxWkt = maxWkts;
                player = iplRunAnalysesData.player;
            }
        }
        return player;

    }

    public String getMaxHundredWiseSorted() throws CensusAnalyserException {
        double maxHun = 0;
        double maxavg = 0;
        String player = null;
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        Iterator iterator = analyseDAO.iterator();
        while (iterator.hasNext()) {
            AnalyseDAO iplRunAnalysesData = (AnalyseDAO) iterator.next();
            double maxHundred = iplRunAnalysesData.hundred;
            double maxAvg = iplRunAnalysesData.average;
            if (maxHundred > maxHun && maxAvg > maxavg) {
                maxHun = maxHundred;
                maxavg = maxAvg;
                player = iplRunAnalysesData.player;
            }
        }
        return player;

    }

    public String getNoFiftyAndHunderdWiseSorted() throws CensusAnalyserException {
        double maxavg = 0;
        String player = null;
        if (analyseMap.size() == 0 || analyseMap == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        List<AnalyseDAO> analyseDAO = analyseMap.values().stream().collect(Collectors.toList());
        Iterator iterator = analyseDAO.iterator();
        while (iterator.hasNext()) {
            AnalyseDAO iplRunAnalysesData = (AnalyseDAO) iterator.next();
            double maxHundred = iplRunAnalysesData.hundred;
            double fifty = iplRunAnalysesData.fifties;
            double maxAvg = iplRunAnalysesData.average;
            if (maxHundred == 0 && maxAvg > maxavg && fifty == 0) {
                maxavg = maxAvg;
                player = iplRunAnalysesData.player;
            }
        }
        return player;

    }


}

