package cricketleague;

import com.censusanalyser.OpenCsvBuilder;
import com.csvbuilderexception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class CSVBuildFactory implements IPLBuilder {
    @Override
    public Iterator getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        return  new OpenCsvBuilder().getCSVFileIterator(reader, csvClass);
    }

    public <E> List getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {
        return  new OpenCsvBuilder().getCSVFileList(reader, csvClass);

    }
}
