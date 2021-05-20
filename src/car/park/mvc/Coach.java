/*
 * Coach class
 * @author Christopher Burrell
 */
package car.park.mvc;

/**
 * Coach subclasses of vehicle superclass
 */
public class Coach extends Vehicle
{
    private boolean overTwenty;
    private boolean touristOperator;
    private final double discount = 10;  // percentage discount applied

    /**
     * Coach constructor
     * @param reg vehicle registration
     * @param twentyPlus true if over 20 passengers
     * @param tOperator true if a tourist operator
     */
    Coach(String reg, boolean twentyPlus, boolean tOperator)
    {
        super(reg);
        overTwenty = twentyPlus;
        touristOperator = tOperator;
        calcCharge();
    }     

    /**
     * Default coach constructor
     */    
    public Coach()
    {

    }
        
    /**
     * Calculate charge for coach
     */  
    @Override
    public void calcCharge()
    {
        if (overTwenty)
            charge = 6;
        else
            charge = 4.5;
        if (touristOperator)
            charge = charge * (100 - discount) / 100;
    }

    /**
     * @return overTwenty, true if over 20 passengers, false if not
     */ 
    public boolean getOverTwenty() 
    {
        return overTwenty;
    }

    /**
     * @return touristOperator, true if tourist operator, false if not
     */ 
    public boolean getTouristOperator() 
    {
        return touristOperator;
    }

    /**
     * Get percentage discount
     * @return discount
     */    
    public double getDiscount() 
    {
        return discount;
    }

    /**
     * Set if over twenty passengers
     * @param twentyPlus true if tourist operator, false if not
     */ 
    public void setOverTwenty(boolean twentyPlus) 
    {
        overTwenty = twentyPlus;
    }

    /**
     * Set if tourist operator
     * @param touristOp true if tourist operator, false if not
     */ 
    public void setTouristOperator(boolean touristOp) 
    {
        touristOperator = touristOp;
    }

    /**
     * @return String array of vehicle data
     */     
    @Override
    public String [] dataToStringArray()
    {
        String [] data = new String [4];
        data [0] = registration;
        data [1] = overTwenty == true ? "21+" : "1-20";
        data [2] = touristOperator == true ? "Yes" : "No";
        data [3] = chargeToString();        
        return data;
    }
    
    /**
     * @return String variable containing description of coach variables
     */
    @Override
    public String toString()
    {
        return "\nThe car registration is " + registration + 
                "\nThe coach has " + (overTwenty == true ? "over twenty":"twenty or less") + " passengers" + 
                "\nThe coach " + (touristOperator == true ? "is" : "is not") + " a tourist operator" + 
                "\nThe charge is " + chargeToString();
    }    
}