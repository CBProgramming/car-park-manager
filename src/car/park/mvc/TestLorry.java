/*
 * TestLorry class
 * @author Christopher Burrell
 */
package car.park.mvc;

/**
 *
 * Test class for lorry subclass
 */
public class TestLorry 
{    
    /**
    * Main class to create and test lorry subclass
    * @param args the command line arguments
    */
    public static void main(String[] args) 
    {
        System.out.println("Default lorry constructor called:");
        Lorry testLorry = new Lorry();
        printValues(testLorry);
        newValues(testLorry,"12345",true);
        testLorry.calcCharge();
        printValues(testLorry);

        System.out.println("\nCalling constructor with following values (istration: 98765, Hours: 3, Over Six: True, Disabled: True)");
        testLorry = new Lorry("98765",true);
        printValues(testLorry);
        newValues(testLorry,"12345",false);
        testLorry.calcCharge();
        printValues(testLorry);
        
        testToStringArray(testLorry);   
        
        System.out.println("\nTesting toString method: " + testLorry.toString());
    }
    
    /**
     * Print lorry variables using getters
     * @param lorry the lorry object to be printed
     */     
    public static void printValues(Lorry lorry)
    {
        System.out.println("Lorry registration: " + lorry.getRegistration());
        System.out.println("Over twenty: " + lorry.getOverTwenty());
        System.out.println("Charge: " + lorry.getCharge());        
    }

    /**
     * Set new values for a lorry object
     * @param lorry the lorry object to be altered
     * @param reg new vehicle registration
     * @param overTwenty new value for overTwenty
     */     
    public static void newValues(Lorry lorry, String reg, boolean overTwenty)
    {
        System.out.println("\nAttempting to set new values for lorry (Registration: " + reg + ", Over Twenty: " + overTwenty);
        lorry.setRegistration(reg);
        lorry.setOverTwenty(overTwenty);
    }
    
    /**
     * Test the dataToStringArray method
     * @param lorry the lorry object whose variables are to be added to String array
     */      
    public static void testToStringArray(Lorry lorry)
    {
        System.out.println("\nPrinting lorry.dataToStringArray(): ");
        String [] array = lorry.dataToStringArray();
        for (String data : array) 
            System.out.println(data);
    }
}