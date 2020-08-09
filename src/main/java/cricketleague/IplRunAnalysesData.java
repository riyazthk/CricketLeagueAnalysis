package cricketleague;

import com.opencsv.bean.CsvBindByName;

public class IplRunAnalysesData {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double battingAvg;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;


    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    @CsvBindByName(column = "Runs", required = true)
    public int Runs;

    @CsvBindByName(column = "100", required = true)
    public int hundred;

    @CsvBindByName(column = "50", required = true)
    public int fifties;

    @Override
    public String toString() {
        return "IplRunAnalysesData{" +
                "player='" + player + '\'' +
                ", battingAvg=" + battingAvg +
                ", strikeRate=" + strikeRate +
                ", four=" + four +
                ", six=" + six +
                ", Runs=" + Runs +
                ", hundred=" + hundred +
                '}';
    }
}
