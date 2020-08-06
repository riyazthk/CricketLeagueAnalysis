package cricketleague;

import com.google.gson.Gson;


import java.util.*;

public class CricketLeagueAnalysis<player> {
    List<IplRunAnalysesData> analyseList = new ArrayList<>();

    public int loadCricketAnalysisData(String IplFilePath) throws CensusAnalyserException {
        analyseList= new CricketAnalysisLoader().loadCricketAnalysis(IplFilePath, IplRunAnalysesData.class);
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
        String player = null;
        double maxValue = 0.0;
        if (analyseList.size() == 0 || analyseList == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Iterator<IplRunAnalysesData> iterator = analyseList.iterator();
        while (iterator.hasNext()) {
            IplRunAnalysesData iplRunAnalysesData = iterator.next();
            double maxBoundary = iplRunAnalysesData.four + iplRunAnalysesData.six;
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

        if (analyseList.size() == 0 || analyseList == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Iterator<IplRunAnalysesData> iterator = analyseList.iterator();
        while (iterator.hasNext()) {
            IplRunAnalysesData iplRunAnalysesData = iterator.next();
            double maxBoundary = iplRunAnalysesData.four + iplRunAnalysesData.six;
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
        if (analyseList.size() == 0 || analyseList == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Iterator<IplRunAnalysesData> iterator = analyseList.iterator();
        while (iterator.hasNext()) {
            IplRunAnalysesData iplRunAnalysesData = iterator.next();
            double average = iplRunAnalysesData.battingAvg;
            double result = iplRunAnalysesData.strikeRate / iplRunAnalysesData.battingAvg;
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
        if (analyseList.size() == 0 || analyseList == null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Iterator<IplRunAnalysesData> iterator = analyseList.iterator();
        while (iterator.hasNext()) {
            IplRunAnalysesData iplRunAnalysesData = iterator.next();
            double average = iplRunAnalysesData.Runs;
            double result = iplRunAnalysesData.Runs / iplRunAnalysesData.battingAvg;
            if (result > max && average > maxRun) {
                max = result;
                maxRun = average;
                player = iplRunAnalysesData.player;
            }
        }
        return player;


    }
}
