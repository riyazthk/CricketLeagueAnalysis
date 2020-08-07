package cricketleague;

public class AnalyseDAO {
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
        Runs=iplRunAnalysesData.Runs;

    }


    public AnalyseDAO(IplWktAnalyseData iplWktAnalyseData) {
        player=iplWktAnalyseData.player;
        average=iplWktAnalyseData.Avg;
        fourBoundaryOrWkt=iplWktAnalyseData.fourWkt;
        maxBoundaryRunOrWkt=iplWktAnalyseData.fiveWkt;
        strikeRate=iplWktAnalyseData.strikeRate;
        economy=iplWktAnalyseData.economy;
    }
}
