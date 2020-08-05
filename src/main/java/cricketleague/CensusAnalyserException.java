package cricketleague;


public class CensusAnalyserException extends Exception {
    public static ExceptionType NO_CENSUS_DATA;

    enum ExceptionType {
        CENSUS_FILE_PROBLEM;
    }
    ExceptionType type;
    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type=type;
    }


}
