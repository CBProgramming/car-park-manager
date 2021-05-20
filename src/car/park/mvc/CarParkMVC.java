/*
 * CarParkMVC class, launches Car Park Management Suite
 * @author Christopher Burrell
 */
package car.park.mvc;

/**
 * launches Car Park Management Suite using MVC design pattern
 */
public class CarParkMVC 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        CarParkModel model = new CarParkModel();
        CarParkView view = new CarParkView(model);
        CarParkController controller = new CarParkController(model,view);
    }    
}
