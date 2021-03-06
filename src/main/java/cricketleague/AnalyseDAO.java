package cricketleague;

public class AnalyseDAO {
    public int fifties;
    public int hundred;
    public int mostWickets;
    public double bowlAverage;
    public int maxRunsOrWkts;
    public int Runs;
    public double economy;
    public double strikeRate;
    public double average;
    public String player;
    public int fourBoundaryOrWkt;
    public int maxBoundaryRunOrWkt;


    public AnalyseDAO(IplRunAnalysesData iplRunAnalysesData) {
        average = iplRunAnalysesData.battingAvg;
        fourBoundaryOrWkt = iplRunAnalysesData.four;
        player = iplRunAnalysesData.player;
        maxBoundaryRunOrWkt = iplRunAnalysesData.six;
        strikeRate = iplRunAnalysesData.strikeRate;
        maxRunsOrWkts=iplRunAnalysesData.Runs;
        hundred=iplRunAnalysesData.hundred;
        fifties=iplRunAnalysesData.fifties;

    }


    public AnalyseDAO(IplWktAnalyseData iplWktAnalyseData) {
        player=iplWktAnalyseData.player;
        average=iplWktAnalyseData.Avg;
        fourBoundaryOrWkt=iplWktAnalyseData.fourWkt;
        maxBoundaryRunOrWkt=iplWktAnalyseData.fiveWkt;
        strikeRate=iplWktAnalyseData.strikeRate;
        economy=iplWktAnalyseData.economy;
        maxRunsOrWkts=iplWktAnalyseData.wickets;
        bowlAverage=iplWktAnalyseData.Avg;
        mostWickets=iplWktAnalyseData.wickets;
    }

//    public AnalyseDAO(String player, IplRunAnalysesData Batsman) {
//        this.player=Batsman.player;
//        this.average=Batsman.battingAvg;
//        this.strikeRate=Batsman.
//    }
}
