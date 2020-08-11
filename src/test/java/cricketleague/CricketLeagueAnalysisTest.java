package cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class CricketLeagueAnalysisTest {
    private static final String IPL_CRICKETLEAGUE_RUNANALYSIS = "./src/test/resources/MostRuns.csv";
    private static final String IPL_CRICKETLEAGUE_BOWLINGANALYSIS = "./src/test/resources/MostWkts.csv";
    private CricketAnalysisLoader cricketAnalysisLoader=new CricketAnalysisLoader();


    @Test
    public void givenCricketLeagueAnalysisReturnsTopBattingAverage() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String sortedStateCodeData = cricketLeagueAnalysis.getAverageWiseSorted();
        IplRunAnalysesData[] censusCSV = new Gson().fromJson(sortedStateCodeData, IplRunAnalysesData[].class);
        Assert.assertEquals("MS Dhoni", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsStrikeRate() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String sortedStateCodeData = cricketLeagueAnalysis.getStrikeRateWiseSorted();
        IplRunAnalysesData[] censusCSV = new Gson().fromJson(sortedStateCodeData, IplRunAnalysesData[].class);
        Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaximumSixAndFour() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getSixAndFourWiseSorted();
        Assert.assertEquals("Andre Russell", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestRunStrikeRate() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getBestStrikeRateWiseSorted();
        Assert.assertEquals("Sunil Narine", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestAverage() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getBestAverageRateWiseSorted();
        Assert.assertEquals("Andre Russell", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxRun() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getMaxWiseWiseSorted();
        Assert.assertEquals("David Warner ", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxAverageWkt() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getAverageWiseSorted();
        IplWktAnalyseData[] censusCSV = new Gson().fromJson(player, IplWktAnalyseData[].class);
        Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxStrikeRateWkt() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getStrikeRateWiseSorted();
        IplWktAnalyseData[] censusCSV = new Gson().fromJson(player, IplWktAnalyseData[].class);
        Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxEconomy() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getEconomyWiseSorted();
        IplWktAnalyseData[] censusCSV = new Gson().fromJson(player, IplWktAnalyseData[].class);
        Assert.assertEquals("Ben Cutting", censusCSV[0].player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestBowlStrikeRate() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getBestStrikeRateWiseSorted();
        Assert.assertEquals("Lasith Malinga", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestBowlAverage() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getBestAverageRateWiseSorted();
        Assert.assertEquals("Krishnappa Gowtham", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestMaxWkts() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        cricketLeagueAnalysis.loadCricketWicketAnalysisData(IPL_CRICKETLEAGUE_BOWLINGANALYSIS);
        String player = cricketLeagueAnalysis.getMaxWiseWiseSorted();
        Assert.assertEquals("Umesh Yadav", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsBestBatBowlAvg() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        String flag = "true";
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS, IPL_CRICKETLEAGUE_BOWLINGANALYSIS, flag);
        String player = cricketLeagueAnalysis.getBestBatBowlAvgWiseSorted();
        Assert.assertEquals("Marcus Stoinis", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxRunAndWkt() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        String flag = "false";
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS, IPL_CRICKETLEAGUE_BOWLINGANALYSIS, flag);
        String player = cricketLeagueAnalysis.getMaxRunAndWktWiseSorted();
        Assert.assertEquals("Hardik Pandya", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxHundred() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        String flag = "false";
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS);
        String player = cricketLeagueAnalysis.getMaxHundredWiseSorted();
        Assert.assertEquals("David Warner ", player);
    }

    @Test
    public void givenCricketLeagueAnalysisReturnsMaxAvgWithNoFiftyAndHundred() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        String flag = "false";
        cricketLeagueAnalysis.loadCricketRunAnalysisData(IPL_CRICKETLEAGUE_RUNANALYSIS, IPL_CRICKETLEAGUE_BOWLINGANALYSIS, flag);
        String player = cricketLeagueAnalysis.getNoFiftyAndHunderdWiseSorted();
        Assert.assertEquals("Marcus Stoinis", player);
    }

}
