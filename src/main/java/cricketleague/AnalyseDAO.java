package cricketleague;

public class AnalyseDAO {
    public String strikeRate;
    public double average;
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

    public String getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }
}
