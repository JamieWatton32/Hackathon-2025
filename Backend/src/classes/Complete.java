package classes;
import com.opencsv.bean.CsvBindByPosition;
public class Complete {

    @CsvBindByPosition(position = 0)
    private String id;
    @CsvBindByPosition(position = 3)
    private String location;



}
