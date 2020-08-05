package cricketleague;

import com.csvbuilderexception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;

public interface IPLBuilder {
    <E> Iterator getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;

}
