/*
 * CarParkModel class, contains logical methods for handling car park management
 * @author Christopher Burrell
 */
package car.park.mvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * initialise class variables
 */
public class CarParkModel 
{
    private Vehicle[] carPark = new Vehicle[16];
    private final int numLargeVehicles = 4;
    private double dailyTotal = 0;
    private final int maxHours = 24; //assumed
    private int foundSpace = -1; // -1 indicates no space found
    private double originalCharge = -1; // -1 indicates no original charge

    /**
     * Default constructor
     */
    public CarParkModel()
    {
     
    }

    /**
     * checks if the registration is valid
     * @param reg the registration to validate
     * @return true if valid, false if not
     */       
    public boolean isRegValid(String reg)
    {
        if (reg.length() == 0 || reg.length() > 8)
        {
            return false;
        }   
        for (int i = 0; i < reg.length(); i++)
        {
            if (!Character.isLetter(reg.charAt(i)) && !Character.isDigit(reg.charAt(i)))
            {
                return false;
            }
        }         
        return true;
    }
    
    /**
     * checks if the hours are valid
     * @param time the hours to validate
     * @return true if valid, false if not
     */     
    public boolean isHoursValid(int time)
    {
        return !(time < 1 || time > maxHours);
    }

    /**
     * add new Car object to carPark array, adds to dailyTotal
     * @param registration car registration
     * @param hours hours parked for
     * @param overSix true if vehicle is over six metres, false if not
     * @param disabled true if disabled, false if not
     * @return response code to be processed by CarParkView
     */     
    public int addCar(String registration, int hours, boolean overSix, boolean disabled)
    {
        if (!isRegValid(registration))
        {
            return 101;
        }
        if (!isHoursValid(hours))
        {
            return 102;
        }
        checkIsEdited();
        carPark[foundSpace] = new Car (registration, hours, overSix, disabled);
        dailyTotal += carPark[foundSpace].getCharge();
        return 100;
    }
    
    /**
     * add new Lorry object to carPark array, adds to dailyTotal
     * @param registration lorry registration
     * @param twentyPlus true if over 20 tonnes, false if not
     * @param thirtyFivePlus true if over 35 tonnes, false if not
     * @return response code to be processed by CarParkView
     */      
    public int addLorry(String registration, boolean twentyPlus,boolean thirtyFivePlus)
    {
        if (!isRegValid(registration))
        {
            return 101;
        }
        if (thirtyFivePlus)
        {
            return 103;
        }
        checkIsEdited();
        carPark[foundSpace] = new Lorry (registration, twentyPlus);
        dailyTotal += carPark[foundSpace].getCharge();
        return 100;
    }

    /**
     * add new Coach object to carPark array, adds to dailyTotal
     * @param registration coach registration
     * @param overTwenty true if over 20 passengers, false if not
     * @param touristOp true if is tourist operator, false if not
     * @return response code to be processed by CarParkView
     */      
    public int addCoach(String registration, boolean overTwenty,boolean touristOp)
    {
        if (!isRegValid(registration))
        {
            return 101;
        }
        checkIsEdited();
        carPark[foundSpace] = new Coach (registration, overTwenty, touristOp);
        dailyTotal += carPark[foundSpace].getCharge();
        return 100;
    }   

    /**
     * checks if an edit is occurring, amending originalCharge and dailyTotal accordingly
     */      
    public void checkIsEdited()
    {
        if (carPark[foundSpace] != null) // if an existing vehicle is being edited
        {
            originalCharge = carPark[foundSpace].getCharge();
            dailyTotal -= carPark[foundSpace].getCharge();
        }
        else // new vehicle, set originalCharge to default value
        {
            originalCharge = -1;
        }
    }
    
    /**
     * find empty space index in carPark array and set foundSpace variable to that value, -1 if none found
     * @param startSpace array index to begin searching
     * @param endSpace array index to stop searching
     */     
    public void findSpace(int startSpace, int endSpace)
    {
        for (int i = startSpace; i < endSpace; i++)
        {
            if (carPark[i] == null)
            {
                foundSpace = i;
                return;
            }
        }
        foundSpace =  -1;
    }

    /**
     * Empties car park.  Sets all carPark indices to null
     */
    public void clearAll()
    {
        for (int i = 0; i < carPark.length; i++)
        {
            carPark[i] = null;
        }
    }

    /**
     * Saves carPark array and dailyTotal to file
     * @param fileName the name of the file
     * @throws java.io.FileNotFoundException
     */      
    public void save(String fileName) throws FileNotFoundException, IOException
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream (fileName + ".dat"))) 
        {
            out.writeObject(carPark);
            out.writeObject(dailyTotal);
        }
    }

    /**
     * Loads carPark array and dailyTotal from file
     * @param fileName the name of the file
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */  
    public void load(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName + ".dat"));
        carPark = (Vehicle[])in.readObject();
        dailyTotal = (Double)in.readObject();        
    }

    /**
     * Calculates the total for all vehicles currently in car park
     * @return the current total
     */  
    public double getCurrentTotal()
    {
        double total = 0;
        for (Vehicle v : carPark) 
        {
            if (v != null) 
            {
                total += v.getCharge();
            }
        }
        return total;
    }

    /**
     * Get the daily total
     * @return dailyTotal
     */  
    public double getDailyTotal() 
    {
        return dailyTotal;
    }

    /**
     * Get the number of large vehicles allowed in car park
     * @return numLargeVehicles
     */     
    public int getNumLargeVehicles() 
    {
        return numLargeVehicles;
    }

    /**
     * Get carPark array
     * @return carPark
     */ 
    public Vehicle[] getCarPark() 
    {
        return carPark;
    }
    
    /**
     * 
     * @param vehicleType String description of the vehicle subclass
     * @return true is a space is free for the specific subclass, false if not
     */
    public boolean isSpaceFree(String vehicleType)
    {
        return ((vehicleType.equals("car") && !isSmallSpaceFree())) ||
                ((vehicleType.equals("lorry") || vehicleType.equals("coach")) && !isBigSpaceFree());                   
    }
    
    /**
     * Check if there are any small vehicle spaces available
     * @return true if space available, false if not
     */     
    public boolean isSmallSpaceFree()
    {
        findSpace(numLargeVehicles,carPark.length);
        return foundSpace == -1;
    }

    /**
     * Check if there are any big vehicle spaces available
     * @return true if space available, false if not
     */       
    public boolean isBigSpaceFree()
    {
        findSpace(0,numLargeVehicles);
        return foundSpace == -1;
    } 

    /**
     * getter for foundSpace variable
     * @return foundSpace
     */ 
    public int getFoundSpace() 
    {
        return foundSpace;
    }

    /**
     * setter for foundSpace variable
     * @param index value to set class variable foundSpace
     */     
    public void setFoundSpace(int index) 
    {
        foundSpace = index;
    }
    
    /**
     * Removes vehicle from carPark array and reduces daily total
     */  
    public void removeAddedInError()
    {
        dailyTotal -= carPark[foundSpace].getCharge();
        carPark[foundSpace] = null;
    }
    
    /**
     * Removes vehicle from carPark array, daily total is unchanged
     */      
    public void removeVehicleHasLeft()
    {
        carPark[foundSpace] = null;
    } 
    
    /**
     * getter for originalCharge variable
     * @return originalCharge
     */ 
    public double getOriginalCharge() 
    {
        return originalCharge;
    }   
}