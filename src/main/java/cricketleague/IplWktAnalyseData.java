package cricketleague;

import com.opencsv.bean.CsvBindByName;

public class IplWktAnalyseData {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double Avg;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "4w", required = true)
    public int fourWkt;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWkt;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    public IplWktAnalyseData(String player, double avg, double strikeRate) {
        this.player=player;
        this.Avg=avg;
        this.strikeRate=strikeRate;
    }
    public IplWktAnalyseData(){

    }

    @Override
    public String toString() {
        return "IplWktAnalyseData{" +
                "player='" + player + '\'' +
                ", Avg=" + Avg +
                ", strikeRate=" + strikeRate +
                ", economy=" + economy +
                ", fiveWkt=" + fiveWkt +
                ", fiveWkt=" + fiveWkt +
                ", wickets=" + wickets +
                '}';
    }

}
