/*
 * TestCoach class
 * @author Christopher Burrell
 */
package car.park.mvc;

/**
 *
 * Test class for coach subclass
 */
public class TestCoach 
{    
    /**
     * Main class to create and test coach subclass
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("Default coach constructor called:");
        Coach testCoach = new Coach();
        printValues(testCoach);
        newValues(testCoach,"12345",true,true);
        testCoach.calcCharge();
        printValues(testCoach);

        System.out.println("\nCalling constructor with following values (istration: 98765, Hours: 3, Over Six: True, Disabled: True)");
        testCoach = new Coach("98765",true,false);
        printValues(testCoach);
        newValues(testCoach,"12345",false,true);
        testCoach.calcCharge();
        printValues(testCoach);
        
        testToStringArray(testCoach);
        
        System.out.println("\nTesting toString method: " + testCoach.toString());
    }
    
    /**
     * Print coach variables using getters
     * @param coach the coach object to be printed
     */     
    public static void printValues(Coach coach)
    {
        System.out.println("Coach registration: " + coach.getRegistration());
        System.out.println("Over twenty: " + coach.getOverTwenty());
        System.out.println("Tourist Operator: " + coach.getTouristOperator());
        System.out.println("Charge: " + coach.getCharge());        
    }

    /**
     * Set new values for a coach object
     * @param coach the coach object to be altered
     * @param reg new vehicle registration
     * @param overTwenty new value for overTwenty
     * @param touristOp new value for touristOperator
     */     
    public static void newValues(Coach coach, String reg, boolean overTwenty, boolean touristOp)
    {
        System.out.println("\nAttempting to set new values for coach (Registration: " + reg + ", Over Twenty: " + overTwenty + ", Tourist Operator: " + touristOp);
        coach.setRegistration(reg);
        coach.setOverTwenty(overTwenty);
        coach.setTouristOperator(touristOp);
    }
    
    /**
     * Test the dataToStringArray method
     * @param coach the coach object whose variables are to be added to String array
     */      
    public static void testToStringArray(Coach coach)
    {
        System.out.println("\nPrinting coach.dataToStringArray(): ");
        String [] array = coach.dataToStringArray();
        for (String data : array) 
            System.out.println(data);
    }
}
