package rollingstatistics;
import java.time.*;
/**
 * Class to hold data items
 * @author Alex Bailey
 */
public class DataItem {
    private LocalDateTime timestamp;
    private double data;
    /**
     * Constructor of the data item. Initialises the data with a timestamp of the systems curent time 
     * @param data the data to be stored
     */
    public DataItem(double data){
        timestamp = LocalDateTime.now();
        this.data = data;
    }
    /**
     * 
     * @param data the data to be stored
     * @param timestamp 
     */
    public DataItem(double data, LocalDateTime timestamp){
        this.data = data;
        this.timestamp = timestamp;
    }
    /**
     * Method to get the data 
     * @return the data
     */
    public double getData(){
        return data;
    }
    /**
     * Method to get the timestamp of the data
     * @return timeStamp
     */
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
}
