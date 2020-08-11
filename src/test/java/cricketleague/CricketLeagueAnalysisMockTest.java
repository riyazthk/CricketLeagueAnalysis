package cricketleague;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.Mockito.when;

public class CricketLeagueAnalysisMockTest {
    private static final String IPL_CRICKETLEAGUE_RUNANALYSIS = "./src/test/resources/MostRuns.csv";
    private static final String IPL_CRICKETLEAGUE_BOWLINGANALYSIS = "./src/test/resources/MostWkts.csv";

    HashMap<String,AnalyseDAO> iplBatsmanData=new HashMap<>();
    HashMap<String,AnalyseDAO> iplBowlerData=new HashMap<>();
    @Mock
    CricketAnalysisLoader cricketAnalysisLoader;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Before
    public void setUp(){
        IplRunAnalysesData firstBatsman=new IplRunAnalysesData("Rohit Sharma",28.92,128.57);
        IplRunAnalysesData secondBatsman=new IplRunAnalysesData("Yuvraj Sign",93.4,143.86);
        IplRunAnalysesData thirdBatsman=new IplRunAnalysesData("KL Rahul",53.9,135.38);
        IplRunAnalysesData fourthBatsman=new IplRunAnalysesData("Shikhar Dhawan",34.73,135.67);
        IplRunAnalysesData fifthBatsman=new IplRunAnalysesData("MS Dhoni",83.2,132.91);
        IplRunAnalysesData sixthBatsman=new IplRunAnalysesData("Hardik Pandya",44.66,191.42);
        iplBatsmanData.put(firstBatsman.player,new AnalyseDAO(firstBatsman));
        iplBatsmanData.put(secondBatsman.player,new AnalyseDAO(secondBatsman));
        iplBatsmanData.put(thirdBatsman.player,new AnalyseDAO(thirdBatsman));
        iplBatsmanData.put(fourthBatsman.player,new AnalyseDAO(fourthBatsman));
        iplBatsmanData.put(fifthBatsman.player,new AnalyseDAO(fifthBatsman));
        iplBatsmanData.put(sixthBatsman.player,new AnalyseDAO(sixthBatsman));
        IplWktAnalyseData firstBowler=new IplWktAnalyseData("Imran Tahir",16.57,14.84);
        IplWktAnalyseData secondBowler=new IplWktAnalyseData("Jasprit Bumrah",21.52,19.47);
        IplWktAnalyseData thirdBowler=new IplWktAnalyseData("Yuzvendra Chahal",21.44,16.44);
        IplWktAnalyseData fourthBowler=new IplWktAnalyseData("Lasith Malinga",27.37,16.81);
        IplWktAnalyseData fifthBowler=new IplWktAnalyseData("Bhuvneshwar Kumar",35.46,27.23);
        IplWktAnalyseData sixthBowler=new IplWktAnalyseData("Krunal Pandya",27.91,23);
        iplBowlerData.put(firstBowler.player,new AnalyseDAO(firstBowler));
        iplBowlerData.put(secondBowler.player,new AnalyseDAO(secondBowler));
        iplBowlerData.put(thirdBowler.player,new AnalyseDAO(thirdBowler));
        iplBowlerData.put(fourthBowler.player,new AnalyseDAO(fourthBowler));
        iplBowlerData.put(fifthBowler.player,new AnalyseDAO(fifthBowler));
        iplBowlerData.put(sixthBowler.player,new AnalyseDAO(sixthBowler));

    }

    @Test
    public void testQuery() throws CensusAnalyserException, IOException {
        when(cricketAnalysisLoader.loadCricketAnalysis(IplRunAnalysesData.class,"filePath")).thenReturn(iplBatsmanData);
        CricketLeagueAnalysis cricketLeagueAnalysis = new CricketLeagueAnalysis(cricketAnalysisLoader);
        int size=cricketLeagueAnalysis.loadCricketRunAnalysisData("filePath");
        Assert.assertEquals(6, size);

    }

}
