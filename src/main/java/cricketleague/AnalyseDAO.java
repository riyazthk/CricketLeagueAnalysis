package cricketleague;

public class AnalyseDAO {
    public double strikeRate;
    public String average;
    public String player;
    public int four;
    public int six;


    public AnalyseDAO(IplRunAnalysesData iplRunAnalysesData) {
          average=iplRunAnalysesData.battingAvg;
          four=iplRunAnalysesData.four;
          player=iplRunAnalysesData.player;
          six=iplRunAnalysesData.six;
          strikeRate=iplRunAnalysesData.strikeRate;


    }


}
