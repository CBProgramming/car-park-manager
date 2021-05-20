/*
 * Lorry class
 * @author Christopher Burrell
 */
package car.park.mvc;

/**
 * Lorry subclasses of vehicle superclass
 */
public class Lorry extends Vehicle
{
    private boolean overTwenty;
    private boolean overThirtyFive;

    /**
     * Constructor for Lorry object
     * @param reg vehicle registration
     * @param twentyPlus true if over 20 tonnes, false if not
     */   
    Lorry(String reg, boolean twentyPlus)
    {
        super(reg);
        overTwenty = twentyPlus;
        calcCharge();
    }
 
    /**
     * Default lorry constructor
     */    
    public Lorry()
    {

    }

    /**
     * Calculate charge for lorry
     */  
    @Override
    public void calcCharge()
    {
        if (overTwenty)
            charge = 8;
        else
            charge = 5;
    }  
    
    /**
     * Get if over 20 tonnes
     * @return true if over 20 tonnes, false if not
     */  
    public boolean getOverTwenty() 
    {
        return overTwenty;
    }

    /**
     * Get if over 35 tonnes
     * @return true if over 35 tonnes, false if not
     */      
    public boolean getOverThirtyFive() 
    {
        return overThirtyFive;
    }

    /**
     * Set if over 20 tonnes
     * @param twentyPlus true if over 20 tonnes, false if not
     */  
    public void setOverTwenty(boolean twentyPlus) 
    {
        overTwenty = twentyPlus;
    }

    /**
     * Set if over 35 tonnes
     * @param thirtyFivePlus true if over 35 tonnes, false if not
     */  
    public void setOverThirtyFive(boolean thirtyFivePlus) 
    {
        overThirtyFive = thirtyFivePlus;
    }

    /**
     * @return String array of vehicle data
     */      
    @Override
    public String [] dataToStringArray()
    {
        String [] data = new String [3];
        data [0] = registration;
        data [1] = overTwenty == true ? "20 - 35 tonnes" : "Under 20 tonnes";
        data [2] = chargeToString();         
        return data;
    }
    
    /**
     * @return String variable containing description of lorry variables
     */    
    @Override
    public String toString()
    {
        return "\nThe lorry registration is " + registration + 
                "\nThe lorry is " + (overTwenty == true ? "over twenty":"under twenty") + "tonnes" + 
                "\nThe charge is " + chargeToString();
    }
}