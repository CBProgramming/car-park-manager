/*
 * TestCar class
 * @author Christopher Burrell
 */
package car.park.mvc;

/**
 * Test class for car subclass
 */
public class TestCar 
{    
    /**
     * Main class to create and test car subclass
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("Default car constructor called:");
        Car testCar = new Car();
        printValues(testCar);
        newValues(testCar,"12345",6,true,false);
        testCar.calcCharge();
        printValues(testCar);

        System.out.println("\nCalling constructor with following values (istration: 98765, Hours: 3, Over Six: True, Disabled: True)");
        testCar = new Car("98765",3,true,true);
        printValues(testCar);
        newValues(testCar,"12345",9,false,false);
        testCar.calcCharge();
        printValues(testCar);
        
        testToStringArray(testCar); 
        
        System.out.println("\nTesting toString method: " + testCar.toString());
    }
    
    /**
     * Print car variables using getters
     * @param car the car object to be printed
     */     
    public static void printValues(Car car)
    {
        System.out.println("Car registration: " + car.getRegistration());
        System.out.println("Car hours: " + car.getHours());
        System.out.println("Is car over six metres: " + car.getOverSix());
        System.out.println("Is disabled: " + car.getDisabled());
        System.out.println("Charge: " + car.getCharge());        
    }

    /**
     * Set new values for a car object
     * @param car the car object to be altered
     * @param reg new vehicle registration
     * @param hours new hours
     * @param overSix new value of overSix
     * @param disabled new value of disabled
     */     
    public static void newValues(Car car, String reg, int hours, boolean overSix, boolean disabled)
    {
        System.out.println("\nAttempting to set new values for car (Registration: " + reg + ", Hours: " + hours + ", Over Six: " + overSix + ", Disabled: " + disabled);
        car.setRegistration(reg);
        car.setHours(hours);
        car.setOverSix(overSix);
        car.setDisabled(disabled);        
    }
    
    /**
     * Test the dataToStringArray method
     * @param car the car object whose variables are to be added to String array
     */      
    public static void testToStringArray(Car car)
    {
        System.out.println("\nPrinting car.dataToStringArray(): ");
        String [] array = car.dataToStringArray();
        for (String data : array) 
            System.out.println(data);
    }
}