package cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;


public class CricketLeagueAnalysisTest {
    private static final String IPL_CRICKETLEAGUE_RUNANALYSIS = "./src/test/resources/MostRuns.csv";
    private static final String IPL_CRICKETLEAGUE_BOWLINGANALYSIS = "./src/test/resources/MostWkts.csv";


    @Test
    public void givenCricketLeagueAnalysisReturnsTopBattingAverage() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String sortedStateCodeData = cricketLeagueAnalysis.getAverageWiseSorted();
        IplRunAnalysesData[] censusCSV = new Gson().fromJson(sortedStateCodeData, IplRunAnalysesData[].class);
        Assert.assertEquals("MS Dhoni", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsStrikeRate() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String sortedStateCodeData = cricketLeagueAnalysis.getStrikeRateWiseSorted();
        IplRunAnalysesData[] censusCSV = new Gson().fromJson(sortedStateCodeData, IplRunAnalysesData[].class);
        Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaximumSixAndFour() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getSixAndFourWiseSorted();
        Assert.assertEquals("Andre Russell", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestStrikeRate() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getBestStrikeRateWiseSorted();
        Assert.assertEquals("Sunil Narine", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestAverage() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getBestAverageRateWiseSorted();
        Assert.assertEquals("Andre Russell", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxRun() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getMaxRunWiseWiseSorted();
        Assert.assertEquals("David Warner ", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxAverageWkt() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getAverageWiseSorted();
        IplWktAnalyseData[] censusCSV = new Gson().fromJson(player, IplWktAnalyseData[].class);
        Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxStrikeRateWkt() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getStrikeRateWiseSorted();
        IplWktAnalyseData[] censusCSV = new Gson().fromJson(player, IplWktAnalyseData[].class);
        Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxEconomy() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getEconomyWiseSorted();
        IplWktAnalyseData[] censusCSV = new Gson().fromJson(player, IplWktAnalyseData[].class);
        Assert.assertEquals("Ben Cutting", censusCSV[0].player);
    }
}
