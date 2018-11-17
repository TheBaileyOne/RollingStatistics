package rollingstatistics;
import java.util.*;
import java.time.*;
/**
 * Main class for obtaining inputs for statistical analysis
 * @author Alex Bailey
 */
public class RollingStatistics {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner reader = new Scanner(System.in);
        boolean excep;
        int choice;
        System.out.println("Would you like to use test data or own data: 1) test data 2)Own data");
        do{
            excep = false;
            System.out.println("1) test data 2)Own data");
            try{
                choice = reader.nextInt();
                switch(choice){
                    case 1:
                        testItems();
                        break;
                    case 2:
                        getInputs();
                        break;
                    default:
                        System.out.println("Invalid option choice, type 1 or 2:");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please type integer choice: 1 or 2");
                reader.next();
                excep = true;
            }
        }while(excep);
        
        
        
    }
    public static void testItems() throws InterruptedException{
        DataSet test1 = new DataSet(8);
        test1.addData(3);
        test1.addData(10);
        test1.addData(2);
        test1.addData(38);
        test1.addData(23);
        test1.addData(38);
        test1.addData(23);
        test1.addData(21);
        test1.addData(14);
        System.out.println("Tests statistics on set of data: 10, 2, 38, 23, 38, 23, 21, 14");
        System.out.println("Sum: " + test1.getSum(0, test1.getLength()-1));
        System.out.println("Median: " + test1.getMedian(0, test1.getLength()-1));
        System.out.println("Mean: " + test1.getMean(0, test1.getLength()-1));
        System.out.println("Standard deviation: " + test1.getStandardDeviation(0, test1.getLength()-1));
        System.out.println("Tests statistics on above data set but from range of 2nd to 5th item (2,36,23,38)");
        System.out.println("Sum: " + test1.getSum(1, 4));
        System.out.println("Median: " + test1.getMedian(1,4));
        System.out.println("Mean: " + test1.getMean(1,4));
        System.out.println("Standard deviation: " + test1.getStandardDeviation(1,4));
        System.out.println("Tests for deleting data added after duration of set has expired ");
        System.out.println("This set has data expire after 2 seconds");
        Duration duration = Duration.ofSeconds(2);
        DataSet test2 = new DataSet(duration);
        test2. addData(3);
        test2.addData(5);
        test2.outputData(0, test2.getLength()-1);
        Thread.sleep(2000);
        test2.addData(10);
        test2.addData(2.5);
        test2.addData(38);
        test2.addData(23);
        test2.addData(38);
        test2.addData(23);
        test2.addData(21);
        test2.addData(14);
        System.out.println("Tests statistics on set of data: 10, 2.5, 38, 23, 38, 23, 21, 14");
        System.out.println("Sum: " + test2.getSum(0, test2.getLength()-1));
        System.out.println("Median: " + test2.getMedian(0, test2.getLength()-1));
        System.out.println("Mean: " + test2.getMean(0, test2.getLength()-1));
        System.out.println("Standard deviation: " + test2.getStandardDeviation(0, test2.getLength()-1));
        test2.outputData(0, test2.getLength()-1);
        
        
        
    }
    public static void getInputs(){
        boolean exit = false;
        Scanner reader = new Scanner(System.in);
        boolean valid = false;
        boolean val = false;
        DataSet set = null;
        boolean excep;
        System.out.println("Would you like configure data set by 1)length or 2)time");
        do{
            try{
                int choice = reader.nextInt();
                switch(choice){
                    case 1:
                        do{
                            excep = false;
                            System.out.println("What is the maximum length of the data set?"); 
                            try{
                                int maxLength = reader.nextInt();
                                set = new DataSet(maxLength);
                                valid = true;
                            }
                            catch(InputMismatchException a){
                                System.out.println("invalid integer value of legnth");
                                reader.next();
                                excep = true;
                            }
                        }while(excep);
                        break;
                    case 2:
                        valid = true;
                        System.out.println("Which unit would you like to configure maximum data age? 1)seconds 2)minutes 3)hours 4)days "); 
                        do{
                            excep = false;
                            try{
                                Duration duration = Duration.ZERO;
                                //do{
                                choice = reader.nextInt();
                                do{
                                    excep = false;
                                    switch(choice){
                                        case 1:
                                            System.out.println("Input max age of data (seconds)");
                                            try{
                                                int secs = reader.nextInt();
                                                duration = Duration.ofSeconds(secs);
                                            }
                                            catch(InputMismatchException a){
                                                System.out.println("Invalid input - input integer value");
                                                excep = true;
                                                reader.next();
                                            }
                                            break;
                                        case 2:
                                            try{
                                                System.out.println("Input max age of data (minutes)");
                                                int mins = reader.nextInt();
                                                duration = Duration.ofMinutes(mins);
                                            }
                                            catch(InputMismatchException a){
                                                System.out.println("Invalid input - input integer value");
                                                excep = true;
                                                reader.next();
                                            }
                                            break;
                                        case 3:
                                            try{
                                                System.out.println("Input max age of data (hours)");
                                                int hours = reader.nextInt();
                                                duration = Duration.ofHours(hours);
                                            }
                                            catch(InputMismatchException a){
                                                System.out.println("Invalid input - input integer value");
                                                excep = true;
                                                reader.next();
                                            }
                                            break;
                                        case 4: 
                                            try{
                                                System.out.println("Input max age of data (days)");
                                                int days = reader.nextInt();
                                                duration = Duration.ofDays(days);
                                            }
                                            catch(InputMismatchException a){
                                                System.out.println("Invalid input - input integer value");
                                                excep = true;
                                                reader.next();
                                            }
                                            break;
                                        default:
                                            System.out.println("invalid input. choose: 1)seconds 2)minutes 3)hours 4)days");
                                            reader.next();
                                            excep = true;
                                    }
                                }while(excep);
                                set = new DataSet(duration);
                            }
                            catch(InputMismatchException a){
                                System.out.println("invalid input. choose: 1)seconds 2)minutes 3)hours 4)days");
                                reader.next();
                                excep = true;
                            }
                        }while(excep);
                        break;
                    default:
                        System.out.println("Invalid option choice, type 1 or 2:");
                        reader.next();
                    }
            }
            catch (InputMismatchException a) {
                System.out.println("Please type integer choice: 1)length or 2)time: ");
                reader.nextLine();
            }
        }while(!valid);
        System.out.println("Start inputting data, to exit type 'x'");
        int data;
        String input;
        
        do{
            excep = false;
            exit = false;
            System.out.println("Enter data (or 'x' for exit): ");
            try{
                input = reader.next();
                if(input.equals("x")){
                    if(set.getLength()<=0){
                        System.out.println("Data is empty - Add some data");
                        excep = true;
                    }
                    else{
                        exit = true;
                        break;
                    }
                    
                }
                else{
                    try{
                        data = Integer.parseInt(input);
                        set.addData(data); 
                    }
                    catch(NumberFormatException e){
                        System.out.println("Invalid input, please enter data value or 'x' to exit");
                        excep = true;
                    }
                }
            }
            catch(InputMismatchException a){
                System.out.println("Invalid input, please enter data value or 'x' to exit");
                reader.next();
                excep = true;
            }
        }while(!exit||excep);
        System.out.println("Would you like to view statistics for all data or a specified range of data");
        int choice;
        int low = 0;
        int high = 0;
        do{
            excep = false;
            System.out.println("1)All data 2)Range of Data");
            try{
                choice = 0;
                choice = reader.nextInt();
                switch(choice){
                    case 1:
                        getStatistics(0,set.getLength()-1, set);
                        break;
                    case 2:
                        
                        do{
                            excep = false;
                            System.out.println("Input first data point in range");
                            try{
                                low = reader.nextInt();
                                if(low>(set.getLength() - 1) || low<0){
                                    excep = true;
                                    System.out.println("invalid first data point, must be between:0 and " + (set.getLength()-1));
                                }
                                
                            }
                            catch(InputMismatchException a){
                                System.out.println("invalid first data point, must be between:0 and " + (set.getLength()-1));
                                reader.next();
                                excep = true;
                            }
                        }while(excep);
                        do{
                            excep = false;
                            System.out.println("Input last data point in range");
                            try{
                                high = reader.nextInt();
                                if(high>(set.getLength() - 1) || high<low){
                                    excep = true;
                                    System.out.println("invalid last data point, must be between:0 and " + (set.getLength()-1));
                                }
                            }
                            catch(InputMismatchException a){
                                System.out.println("invalid last data point, must be between:0 and " + (set.getLength()-1));
                                reader.next();
                                excep = true;
                            }
                        }while(excep);
                        getStatistics(low, high, set);
                        break;
                    default:
                        System.out.println("Invalid option choice: 1)All data 2)Range of Data");
                        excep = true;
                }
            }
            catch (InputMismatchException a) {
                System.out.println("Please type integer choice: 1)All data 2)Range of Data");
                reader.next();
                excep = true;
            }
            System.out.println("Type 1 to choose statistic viewing option again (any other character to exit)");
            try{
                input = reader.next();
                if(input.equals("1")){
                    excep = true;
                }
            }
            catch(InputMismatchException a){
                System.out.println("invalid input");
            }
        }while(excep);
        
    }
    public static void getStatistics(int low, int high, DataSet set){
        boolean excep;
        Scanner reader = new Scanner(System.in);
        String input;
        boolean exit = false;
        do{
            int choice;
            excep = false;
            System.out.println("1)View data 2)Sum 3)Median 4)Mean 5)Standard Deviation ('x' to exit)");
            try{
                input = reader.next();
                if(input.equals("x")){
                    exit = true;
                    break;
                }
                else{
                    try{
                        choice = Integer.parseInt(input);
                        switch(choice){
                            case 1:
                                set.outputData(low, high);
                                break;
                            case 2:
                                System.out.println("Sum: " + set.getSum(low,high));
                                break;
                            case 3:
                                System.out.println("Median: " + set.getMedian(low,high));
                                break;
                            case 4:
                                System.out.println("Mean: " + set.getMean(low,high));
                                break;
                            case 5:
                                System.out.println("Standard Deviation: " + set.getStandardDeviation(low,high));
                                break;
                            default:
                                System.out.println("Invalid input - Choose from: 1)View data 2)Sum 3)Median 4)Mean 5)Standard Deviation ('x' to exit)");
                        }
                    }
                    catch(NumberFormatException a){
                        System.out.println("Invalid input, please enter data value or 'x' to exit");
                        excep = true;
                    }
                }
            }
            catch(InputMismatchException a){
                
            }
        }while(!exit||excep);
    }
    
}
