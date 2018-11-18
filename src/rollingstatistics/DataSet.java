package rollingstatistics;

import java.util.*;
import java.time.*;
/**
 * Class to represent a set of data, with methods for statistical analysis on said data
 * @author Alex Bailey
 */
public class DataSet {
    
    private ArrayList<DataItem> dataSet = new ArrayList<>();;
    private int maxLength;
    private Duration maxTime;
    private int option;
    /**
     * Constructor to construct DataSet based on the number of items stored
     * @param maxLength the maximum number of items to be stored in the set of data
     */
    public DataSet(int maxLength){
        this.maxLength = maxLength;
        option = 0;
        
    }
    /**
     * Constructor to construct DataSet based on a specified time range
     * @param maxTime the oldest age (in seconds) for which data can be stored
     */
    public DataSet(Duration maxTime){
        this.maxTime = maxTime;
        option = 1;
    }
    /**
     * Method to output the data in the DataSet
     * @param low the low data location from the range of data required
     * @param high the high data location value from the range of data required
     */
    public void outputData(int low, int high){
        for(;low<=high;low++){
            System.out.println(String.format("%-20s %s", String.format("%-5s %s", low, dataSet.get(low).getData()), dataSet.get(low).getTimestamp()));
        }
    }
    /**
     * Method to add new data item to the set of data. Checks if size of data collection is within specified parameters
     * @param value the value to be stored
     */
    public synchronized void addData(double value){
        DataItem data = new DataItem(value);
        switch(option){
            case 0:
                
                if(dataSet.size()>= maxLength){
                    System.out.println("Maximum number of items - Removing oldest item, Adding new item");
                    dataSet.remove(0);
                }
                dataSet.add(data);
                break;
            case 1:
                if(!dataSet.isEmpty()){
                    LocalDateTime oldest = dataSet.get(0).getTimestamp();
                    Duration duration = Duration.between(oldest, LocalDateTime.now());
                    if(duration.compareTo(maxTime)>0){
                        System.out.println("Some items too old - Removing old data items.");
                        do{
                            dataSet.remove(0);
                            if(!dataSet.isEmpty()){
                                oldest = dataSet.get(0).getTimestamp();
                                duration = Duration.between(oldest, LocalDateTime.now()); 
                            }
                            else{
                                break;
                            }
                        }while(duration.compareTo(maxTime)>0);
                    }
                }
                dataSet.add(data);              
                break;
            default:
                throw new Error("invalid option choice");
        }
    }
        /**
     * Method to get length of the data set
     * @return size of the dataSet 
     */
    public int getLength(){
        return dataSet.size();
    }
    /**
     * Method to extract the values of the DataItem object
     * @param low the low data location from the range of data required
     * @param high the high data location value from the range of data required
     * @return sorted ArrayList of the data values in the DataItem object
     */
    private ArrayList<Double> getDataValues(int low, int high){
        ArrayList<Double> dataValues = new ArrayList<>();
        for(;low<=high;low++){
            dataValues.add(dataSet.get(low).getData());
        }
        Collections.sort(dataValues);
        return dataValues;
    }
    /**
     * Method to calculate the sum of the data within the specified range
     * @param low the low data location from the range of data required
     * @param high the high data location value from the range of data required
     * @return The sum of the data in the dataValues
     */
    public double getSum(int low, int high){
        double total = 0;
        ArrayList<Double> dataValues = getDataValues(low, high);
        for(double num:dataValues){
            total += num;
        }
        return total;
    }
    /**
     * Method to calculate the mean of the data within the specified range
     * @param low the low data location from the range of data required
     * @param high the high data location value from the range of data required
     * @return  The mean of the data in the dataValues
     */
    public double getMean(int low, int high){
        double total = getSum(low, high);
        return total/((high - low) + 1);
    }
    /**
     * Method to find the median of the data within the specified range
     * @param low the low data location from the range of data required
     * @param high the high data location value from the range of data required
     * @return The median of the data in dataValues
     */
    public double getMedian(int low, int high){
        ArrayList<Double> dataValues = getDataValues(low, high);
        if((high-low)%2==0){
            return dataValues.get((high-low)/2);
        }
        else{
            double value1 = dataValues.get((int)Math.floor((high-low)/2.0));
            double value2 = dataValues.get((int)Math.ceil((high-low)/2.0));
            return (value1+value2)/2;
        }
    }
    /**
     * Method to calculate the variance of the data within the specified range, required for calculating standard deviation 
     * @param low the low data location from the range of data required
     * @param high the high data location value from the range of data required
     * @return The variance of the data in dataValues
     */
    private double getVariance(int low, int high){
        ArrayList<Double> dataValues = getDataValues(low, high);
        double mean = getMean(low, high);
        
        double total = 0;
        for(double num:dataValues){
            total += Math.pow(num - mean, 2);        
        }
        return total/(high-low);
    }
    /**
     * Method to calculate the standard deviation of the data within the specified range
     * @param low the low data location from the range of data required
     * @param high the high data location value from the range of data required
     * @return the standard deviation of the data in dataValues
     */
    public double getStandardDeviation(int low, int high){
        double variance = getVariance(low, high);
        return Math.sqrt(variance);
    }
}
