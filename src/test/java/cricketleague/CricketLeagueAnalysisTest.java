package cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CricketLeagueAnalysisTest {
    private static final String IPL_CRICKETLEAGUE_ANALYSIS = "./src/test/resources/MostRuns.csv";

    @Test
    public void givenCricketLeagueAnalysisReturnsTopBattingAverage() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        cricketLeagueAnalysis.loadCricketAnalysisData(IPL_CRICKETLEAGUE_ANALYSIS);
        String sortedStateCodeData = cricketLeagueAnalysis.getAverageWiseSorted();
        IplRunAnalysesData[] censusCSV = new Gson().fromJson(sortedStateCodeData, IplRunAnalysesData[].class);
        Assert.assertEquals("MS Dhoni", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsStrikeRate() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        int records = cricketLeagueAnalysis.loadCricketAnalysisData(IPL_CRICKETLEAGUE_ANALYSIS);
        String sortedStateCodeData = cricketLeagueAnalysis.getStrikeRateWiseSorted();
        IplRunAnalysesData[] censusCSV = new Gson().fromJson(sortedStateCodeData, IplRunAnalysesData[].class);
        Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaximumSixAndFour() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        int records = cricketLeagueAnalysis.loadCricketAnalysisData(IPL_CRICKETLEAGUE_ANALYSIS);
        String player = cricketLeagueAnalysis.getSixAndFourWiseSorted();
        Assert.assertEquals("Andre Russell", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestStrikeRate() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        int records = cricketLeagueAnalysis.loadCricketAnalysisData(IPL_CRICKETLEAGUE_ANALYSIS);
        String player = cricketLeagueAnalysis.getBestStrikeRateWiseSorted();
        Assert.assertEquals("Andre Russell", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestAverage() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        int records = cricketLeagueAnalysis.loadCricketAnalysisData(IPL_CRICKETLEAGUE_ANALYSIS);
        String player = cricketLeagueAnalysis.getBestAverageRateWiseSorted();
        Assert.assertEquals("David Warner ", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxRun() throws CensusAnalyserException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        int records = cricketLeagueAnalysis.loadCricketAnalysisData(IPL_CRICKETLEAGUE_ANALYSIS);
        String player = cricketLeagueAnalysis.getMaxRunWiseWiseSorted();
        Assert.assertEquals("David Warner ", player);
    }
}
