/*
 * Car class
 * @author Christopher Burrell
 */
package car.park.mvc;

/**
 * Car subclasses of vehicle superclass
 */
public class Car extends Vehicle
{
    private int hours;
    private boolean overSix;
    private boolean disabled;

    /**
     * Constructor for Car object
     * @param reg vehicle registration
     * @param time hours in car park
     * @param sixPlus true if over six metres, false if not
     * @param dis true if disabled, false if not
     */       
    Car(String reg, int time, boolean sixPlus, boolean dis)
    {
        super(reg);
        hours = time;
        overSix = sixPlus;
        disabled = dis;
        calcCharge();
    }

    /**
     * Default car constructor
     */    
    public Car()
    {
        
    }

    /**
     * Calculate charge for car
     */    
    @Override
    public void calcCharge()
    {
        if (disabled)
        {
            charge = 0;
        }
        else
        {
            double rate;
            if (overSix)
                rate = 1.5;
            else
                rate = 1;
            charge = rate * hours;
        }
    }

    /**
     * @return hours
     */  
    public int getHours() 
    {
        return hours;
    }

    /**
     * @return true if over six metres, false if not
     */  
    public boolean getOverSix() 
    {
        return overSix;
    }

    /**
     * @return true if disabled, false if not
     */  
    public boolean getDisabled() 
    {
        return disabled;
    }

    /**
     * Set hours
     * @param hrs value to set hours to
     */      
    public void setHours(int hrs) 
    {
        hours = hrs;
    }
 
    /**
     * Set overSix
     * @param sixPlus true if over six metres, false if not
     */  
    public void setOverSix(boolean sixPlus) 
    {
        overSix = sixPlus;
    }

    /**
     * Set disabled
     * @param dis true if disabled, false if not
     */     
    public void setDisabled(boolean dis) 
    {
        disabled = dis;
    }

    /**
     * @return String array of vehicle data
     */      
    @Override
    public String [] dataToStringArray()
    {
        String [] data = new String [5];
        data [0] = registration;
        data [1] = Integer.toString(hours);
        data [2] = overSix == true ? "Six metres and over" : "Under six metres";
        data [3] = disabled == true ? "Yes" : "No";
        data [4] = chargeToString();        
        return data;
    }
    
    /**
     * @return String variable containing description of car variables
     */
    @Override
    public String toString()
    {
        return "\nThe car registration is " + registration + 
                "\nThe number of hours is " + hours + 
                "\nThe car is " + (overSix == true ? "over six metres":"under six metres") + 
                "\nThe driver " + (disabled == true ? "is" : "is not") + " disabled" + 
                "\nThe charge is " + chargeToString();
    }
}