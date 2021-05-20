/*
 * CarParkMVC class, launches Car Park Management Suite
 * @author Christopher Burrell
 */
package car.park.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * declare model and view
 */
public class CarParkController
{
    private final CarParkModel model;
    private final CarParkView view;
    
    /**
     * CarParkController constructor
     * @param m CarParkModel object
     * @param v CarParkView object
     */
    CarParkController(CarParkModel m, CarParkView v)
    {
        model = m;
        view = v;
        v.addActionListeners(new ButtonListen());
        v.addMouseListeners(new MouseListen());
        v.addWindowListeners(new WindowListen());
    }
    
    /**
     * obtains data from "Add Car" window, attempts to create car object and then handle any error code returned
     */     
    public void okAddCar()
    {
        String reg = ((JTextField)view.dataFields.get(0)).getText();
        int hours;
        try
        {
            hours = Integer.parseInt(((JTextField)view.dataFields.get(1)).getText());
        }
        catch (NumberFormatException e)
        {
            hours = 0;
        }
        boolean length = ((JComboBox)view.dataFields.get(2)).getSelectedIndex() != 0;
        boolean disabled = ((JCheckBox)view.dataFields.get(3)).isSelected();
        int errorCode = model.addCar(reg,hours,length,disabled);
        view.responseHandler(errorCode);
    }

    /**
     * obtains data from "Add Lorry" window, attempts to create lorry object and then handle any error code returned
     */     
    public void okAddLorry()
    {
        String reg = ((JTextField)view.dataFields.get(0)).getText();
        boolean overTwenty = false;
        boolean overThirtyFive = false;
        
        if (((JComboBox)view.dataFields.get(1)).getSelectedIndex() == 2)
        {
            overThirtyFive = true;
        }
        else if (((JComboBox)view.dataFields.get(1)).getSelectedIndex() == 1)
        {
            overTwenty = true;
        }
        int errorCode = model.addLorry(reg,overTwenty,overThirtyFive);
        view.responseHandler(errorCode);
    }

    /**
     * obtains data from "Add Coach" window, attempts to create coach object and then handle any error code returned
     */    
    public void okAddCoach()
    {
        String reg = ((JTextField)view.dataFields.get(0)).getText();
        boolean overTwenty = false;
        if (((JComboBox)view.dataFields.get(1)).getSelectedIndex() == 1)
        {
            overTwenty = true;
        }
        boolean touristOp = ((JCheckBox)view.dataFields.get(2)).isSelected();
        int errorCode = model.addCoach(reg,overTwenty,touristOp);
        view.responseHandler(errorCode);
    }
    
    /**
     * gets the file name from the save window and saves the file
     */     
    public void okSave()
    {
        try 
        {
            model.save(((JTextField)view.dataFields.get(0)).getText());
            view.responseHandler(200);
        } 
        catch (IOException ex) 
        {
            view.responseHandler(201);
        }        
    }
    
    /**
     * gets the file name from the load window and loads the file
     */     
    public void okLoad()
    {
        try 
        {
            model.load(((JTextField)view.dataFields.get(0)).getText());
            view.responseHandler(300);            
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            view.responseHandler(301);
        }      
    }

    /**
     * obtains removal reason and calls relevant removal method
     */      
    public void okRemove()
    {
        if (((JComboBox)view.dataFields.get(0)).getSelectedIndex() == 0)
        {
            model.removeAddedInError();
        }
        else
        {
            model.removeVehicleHasLeft();
        }
        view.closeInnerFrame();
    }
    
    /**
     * ButtonListen class to act upon action events
     */
    class ButtonListen implements ActionListener
    {
        /**
         * checks if main GUI is active and processes button clicks accordingly
         * @param e action event
         */      
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (view.mainEnabled == true)
            {
                mainButtons(e.getSource());
            }
            else
            {
                secondaryButtons(e.getSource());
            }          
        }
        
        /**
         * process button clicks for main GUI window
         * @param source reference to the button which has been clicked
         */
        public void mainButtons(Object source)
        {
            if (source == view.addCarButton || source == view.addLorryButton || source == view.addCoachButton)
            {
                carButtons(source);
            }
            else
            {
                if (source == view.clearAllButton)
                {
                    view.innerWindow("Clear All");
                }            
                else if (source == view.saveButton)
                {
                    view.innerWindow("Save");
                }            
                else if (source == view.loadButton)
                {
                    view.innerWindow("Load");
                }            
                else if (source == view.currentTotalButton) 
                {
                    view.innerWindow("Current Total");
                }
                else if (source == view.dailyTotalButton)
                {
                    view.innerWindow("Daily Total");
                }                      
            }                    
        }
        
        /**
         * processes vehicle button clicks, checking for room in car park and calling response handler where needed
         * @param source reference to the button which has been clicked
         */
        public void carButtons(Object source)
        {
            if (source == view.addCarButton && model.isSpaceFree("car"))
            {
                view.innerWindow("Car");
            }
            else if (source == view.addLorryButton && model.isSpaceFree("lorry"))
            {
                view.innerWindow("Lorry"); 
            }
            else if (source == view.addCoachButton && model.isSpaceFree("coach"))
            {
                view.innerWindow("Coach");
            }
            else
            {
                view.responseHandler(401);
            }
        } 
                
        /**
         * process button clicks for inner GUI window
         * @param source reference to the button which has been clicked
         */        
        public void secondaryButtons(Object source)
        {
            if (source == view.cancel || source == view.okGeneric)
            {
                view.closeInnerFrame();
            }
            else if (source == view.okAddCar)
            {
                okAddCar();
            }
            else if (source == view.okAddLorry)
            {
                okAddLorry();
            }
            else if (source == view.okAddCoach)
            {
                okAddCoach(); 
            }
            else if (source == view.okClearAll)
            {
                model.clearAll();
                view.closeInnerFrame();
            }
            else if (source == view.okSave)       
            {
               okSave();
            }
            else if (source == view.okLoad)       
            {
               okLoad();
            }            
            else if (source == view.okRemove)
            {
                okRemove();
            }    
        }
    }// end of ButtonListen class

    /**
     * MouseListen class to act upon mouse events
     */    
    class MouseListen implements MouseListener
    {
        /**
         * process mouse pressed events
         * @param e mouse pressed event
         */          
        @Override
        public void mousePressed(MouseEvent e) 
        {
            if (view.mainEnabled == true)
            {
                for (int i = 0;i < view.vehicleLabel.length; i++)
                {
                    if (e.getSource() == view.vehicleLabel[i])
                    {
                        if (model.getCarPark()[i] == null)
                        {
                            view.responseHandler(400);
                        }
                        else
                        {
                            int button = e.getButton();
                            String vehicleType = model.getCarPark()[i].getClass().getSimpleName();
                            switch (button)
                            {
                                case 1: //view vehicle
                                    view.currentLabel = i;
                                    view.innerWindow("View " + vehicleType);
                                    break;
                                case 2: //edit vehicle
                                    view.innerWindow(vehicleType);
                                    view.populateFields(i);
                                    break;
                                case 3: //remove vehicle
                                    view.innerWindow("Remove");
                                    model.setFoundSpace(i);
                                    break;                    
                            }
                        }                        
                    } 
                }
            }
        }
    
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e){}

        @Override
        public void mouseEntered(MouseEvent e){}

        @Override
        public void mouseExited(MouseEvent e) {} 
    }// end of MouseListen class

    /**
     * WindowListen class to act upon window events
     */      
    class WindowListen implements WindowListener
    {
        /**
         * process window events
         * @param e window event when closed
         */ 
        @Override
        public void windowClosed(WindowEvent e) 
        {
            view.closeInnerFrame();
        }
         
        @Override
        public void windowClosing(WindowEvent e) {}
        @Override
        
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
    }// end of WindowListen class
}