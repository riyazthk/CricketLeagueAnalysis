package cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CricketLeagueAnalysisTest {
    private static final String IPL_CRICKETLEAGUE_ANALYSIS="./src/test/resources/MostRuns.csv";
    @Test
    public void givenCricketLeagueAnalysisReturnsTopBattingAverage() throws CensusAnalyserException, IOException {
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis();
        int records=cricketLeagueAnalysis.loadCricketAnalysisData(IPL_CRICKETLEAGUE_ANALYSIS);
        String sortedStateCodeData =cricketLeagueAnalysis.givenTopBattingAverageWiseSortedCodeData();
        AnalyseDAO[] censusCSV = new Gson().fromJson(sortedStateCodeData, AnalyseDAO[].class);
        Assert.assertEquals(100,records);


    }
}
