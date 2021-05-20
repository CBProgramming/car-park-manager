/*
 * Vehicle class
 * @author Christopher Burrell
 */
package car.park.mvc;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Abstract superclass for vehicle subclasses
 */
public abstract class Vehicle implements Serializable
{
    protected String registration;
    protected double charge;
    protected DecimalFormat money = new DecimalFormat("0.00");

    /**
     * Constructor for Vehicle object
     * @param reg the registration of the vehicle
     */    
    public Vehicle(String reg)
    {
        registration = reg;
    }

    /**
     * Default vehicle constructor
     */    
    public Vehicle()
    {

    }
    
    /**
     * Abstract method for calculating the charge of the vehicle
     */
    public abstract void calcCharge();
    
    /**
     * @return vehicle registration
     */
    public String getRegistration() 
    {
        return registration;
    }

    /**
     * @return vehicle charge
     */    
    public double getCharge() 
    {
        return charge;
    }

    /**
     * set the vehicle registration
     * @param reg the value to set registration to
     */    
    public void setRegistration(String reg) 
    {
        registration = reg;
    }

    /**
     * set the vehicle charge
     * @param amount the value to set charge to
     */ 
    public void setCharge(double amount) 
    {
        charge = amount;
    }  
    
    /**
     * @return charge formatted to "£" + money variable format
     */ 
    public String chargeToString()
    {
        return "£" + money.format(charge);
    }
           
    /**
     * Overridable method for vehicle subclasses
     * @return String array of vehicle data
     */  
    public abstract String [] dataToStringArray(); 
}
