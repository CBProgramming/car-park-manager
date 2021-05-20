/*
 * CarParkView class, creates and changes the GUI accordingly
 * @author Christopher Burrell
 */
package car.park.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * initialise class variables
 */
public class CarParkView extends JFrame
{
    protected JFrame innerFrame = new JFrame();
    private final JPanel GUIPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel carParkPanel = new JPanel();
    private JPanel fieldsPanel;     
    private JPanel okAndCancel;
    private JPanel defaultPanel;
    
    private final Dimension GUIDim = new Dimension (810,810);
    private final Dimension buttonPanelDim = new Dimension (160,800);
    private final Dimension carParkPanelDim = new Dimension (640,800);
    private final Dimension lorryPanelDim = new Dimension (154,395);
    private final Dimension carPanelDim = new Dimension (154,127);
    
    private final GridLayout buttonLayout = new GridLayout(8,1);
    
    protected final JLabel[] vehicleLabel = new JLabel[16]; 
    protected ArrayList<JComponent> dataFields;
    private ArrayList <JComponent> headingLabels;
    private String[] headings;
    
    protected final JButton addCarButton = new JButton ("Add car");
    protected final JButton addLorryButton = new JButton ("Add lorry");
    protected final JButton addCoachButton = new JButton ("Add coach");
    protected final JButton clearAllButton = new JButton ("Clear all");
    protected final JButton saveButton = new JButton ("Save");
    protected final JButton loadButton = new JButton ("Load");
    protected final JButton currentTotalButton = new JButton ("Current total");
    protected final JButton dailyTotalButton = new JButton ("Total for day");
    
    protected final JButton okAddCar = new JButton("OK");
    protected final JButton okAddLorry = new JButton("OK"); 
    protected final JButton okAddCoach = new JButton("OK"); 
    protected final JButton okClearAll = new JButton("OK"); 
    protected final JButton okSave = new JButton("OK");
    protected final JButton okLoad = new JButton("OK");    
    protected final JButton okGeneric = new JButton("OK");
    protected final JButton okRemove = new JButton("OK"); 
    protected final JButton cancel = new JButton("Cancel");
    
    private final String[] carLengths = {"Under six metres","Six metres and over"};
    private final String[] lorryWeights = {"Under 20 tonnes","20 - 35 tonnes","Over 35 tonnes"};
    private final String[] coachNumbers = {"1-20","21+"};  
    private final String[] removeReason = {"Added in error","Vehicle has left"};
         
    private final ImageIcon shortCarImg = new ImageIcon("car.png");
    private final ImageIcon longCarImg = new ImageIcon("campervan.png");
    private final ImageIcon coachImg = new ImageIcon("coach.png");
    private final ImageIcon lorryImg = new ImageIcon("lorry.png");
    
    private final DecimalFormat money = new DecimalFormat("0.00");
   
    protected int currentLabel;
    protected boolean mainEnabled;
    
    private final CarParkModel model;

    /**
     * CarParkGUI default constructor for initial GUI interface
     * @param m CarParkModel object
     */   
    public CarParkView(CarParkModel m)
    {
        model = m;
        
        GUIPanel.setPreferredSize(GUIDim);
        buttonPanel.setPreferredSize(buttonPanelDim);
        carParkPanel.setPreferredSize(carParkPanelDim);
        carParkPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        buttonPanel.setLayout(buttonLayout);
        addMainButtons();
        initialiseLabels();
        mainEnabled = true;
        GUIPanel.add(buttonPanel);
        GUIPanel.add(carParkPanel);
        this.setContentPane(GUIPanel);
        this.pack();       
        this.setTitle("Car Park Management Suite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * adds buttons to main GUI panel
     */ 
    public final void addMainButtons()
    {
        buttonPanel.add(addCarButton);
        buttonPanel.add(addLorryButton);
        buttonPanel.add(addCoachButton);
        buttonPanel.add(clearAllButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(currentTotalButton);
        buttonPanel.add(dailyTotalButton);        
    }
    
    /**
     * adds action listeners to all buttons
     * @param aListener action listener to add to buttons
     */      
    public void addActionListeners(ActionListener aListener)
    {
        addCarButton.addActionListener(aListener);
        addLorryButton.addActionListener(aListener);
        addCoachButton.addActionListener(aListener);
        clearAllButton.addActionListener(aListener);
        saveButton.addActionListener(aListener);
        loadButton.addActionListener(aListener);
        currentTotalButton.addActionListener(aListener);
        dailyTotalButton.addActionListener(aListener);
        okAddCar.addActionListener(aListener);
        okAddLorry.addActionListener(aListener);
        okAddCoach.addActionListener(aListener);
        okClearAll.addActionListener(aListener);
        okSave.addActionListener(aListener);
        okLoad.addActionListener(aListener);
        okGeneric.addActionListener(aListener); 
        okRemove.addActionListener(aListener);
        cancel.addActionListener(aListener);         
    }
    
    /**
     * adds mouse listener to vehicle space labels
     * @param mListener mouse listener to add to labels
     */
    public void addMouseListeners(MouseListener mListener)
    {
        for (JLabel label : vehicleLabel) 
        {
            label.addMouseListener(mListener);
        }           
    }
    
    /**
     * adds window listener to inneFrame
     * @param wListener window listener to add to innerFrame
     */
    public void addWindowListeners(WindowListener wListener)
    {
        innerFrame.addWindowListener(wListener);        
    }
    
    /**
     * initialises vehicle labels on main GUI
     */ 
    public final void initialiseLabels()
    {
        for (int i = 0;i<vehicleLabel.length;i++)
        {
            vehicleLabel[i] = new JLabel();
            vehicleLabel[i].setBorder(BorderFactory.createLineBorder(Color.RED));
            if (i<4)
            {
                vehicleLabel[i].setPreferredSize(lorryPanelDim);                
            }
            else
            {
                vehicleLabel[i].setPreferredSize(carPanelDim);
            }
            carParkPanel.add(vehicleLabel[i]);           
        }        
    }

    /**
     * create an inner window based on main GUI button click
     * @param windowType description of window type
     */        
    public void innerWindow(String windowType)
    {
        dataFields = setDataFields(windowType);
        headings = getHeadings(windowType);
        generateHeadingLabels();
        editFieldsPanel();        
        okCancelPanel(getOkButton(windowType));
        generateInnerPanel();
        generateInnerFrame(windowType + " Window");      
    }
    
    /**
     * obtain the required JComponents to create inner window
     * @param windowType description of window type
     * @return ArrayList containing the required JComponents
     */
    public ArrayList<JComponent> setDataFields (String windowType)
    {
        switch (windowType) 
        {
            case "Car":
                return new ArrayList<>(Arrays.asList(new JTextField(),new JTextField(),new JComboBox(carLengths), new JCheckBox()));
            case "Lorry":
                return new ArrayList<>(Arrays.asList(new JTextField(),new JComboBox(lorryWeights)));
            case "Coach":
                return new ArrayList<>(Arrays.asList(new JTextField(),new JComboBox(coachNumbers),new JCheckBox()));
            case "Save":
            case "Load":
                return new ArrayList<>(Arrays.asList(new JTextField()));
            case "Clear All":
                return null;
            case "Daily Total":
                return new ArrayList<>(Arrays.asList(new JLabel("£" + money.format(model.getDailyTotal()))));
            case "Current Total":
                return new ArrayList<>(Arrays.asList(new JLabel("£" + money.format(model.getCurrentTotal())))); 
            case "Remove":
                return new ArrayList<>(Arrays.asList(new JComboBox(removeReason)));
            case "View Car":
            case "View Lorry":
            case "View Coach":
                return getDataLabels();
            default:
                System.out.println("Switch case not found in setDataFields method in CarParkView class.  Add switch case to resolve");
                return null;
        }   
    }
    
    /**
     * populate JComponent ArrayList dataFields with labels containing vehicles data (used for viewing vehicle details in innerFrame)
     * @return dataFields to be used as an argument where needed
     */    
    public ArrayList<JComponent> getDataLabels()
    {
        String [] vDetails = model.getCarPark()[currentLabel].dataToStringArray();
        dataFields = new ArrayList<>();
        for (String vDetail : vDetails) 
        {
            dataFields.add(new JLabel(vDetail));
        }
        return dataFields;
    }
    
    /**
     * gets headings for a specific inner window type
     * @param windowType the type of window for which headings are required
     * @return String array containing required headings
     */
    public String[] getHeadings(String windowType)
    {
        switch (windowType) 
        {
            case "Car":
            case "View Car":
                return new String [] {"Registration","Hours","Car size","Disabled","Charge"};
            case "Lorry":
            case "View Lorry":
                return new String [] {"Registration","Weight","Charge"}; 
            case "Coach":
            case "View Coach":
                return new String [] {"Registration","Number of passengers","Tourist operator","Charge"}; 
            case "Save":
            case "Load":
                return new String [] {"Please enter file name"}; 
            case "Clear All":
                return new String [] {"Are you sure you want to clear all?  All vehicles will be removed."};  
            case "Daily Total":
                return new String [] {"Daily Total"};
            case "Current Total":
                return new String [] {"Current Total"};
            case "Remove":
                return new String [] {"Reason for removing vehicle"};
            default:
                System.out.println("Switch case not found in setUpFramVars method in CarParkView class.  Add switch case to resolve");
                return null;
        }
    }
    
    /**
     * adds all String variables in headings array to JLabels and add the labels to ArrayList headingLabels
     */
    private void generateHeadingLabels()
    {
        headingLabels = new ArrayList<>();
        for (String heading : headings) 
        {
            headingLabels.add(new JLabel(heading + "   ", SwingConstants.RIGHT));
        }         
    }

    /**
     * Dynamically create a panel of required JComponents for the inner window
     */   
    public void editFieldsPanel()
    {        
        fieldsPanel = new JPanel();        
        if (dataFields == null)
        {
            fieldsPanel.add(headingLabels.get(0));
        }
        else
        {
            GridLayout layout = new GridLayout(dataFields.size(),2);        
            fieldsPanel.setLayout(layout);
            for (int i = 0; i < dataFields.size(); i++)
            {
                fieldsPanel.add(headingLabels.get(i));
                fieldsPanel.add(dataFields.get(i)); 
            }
        }
    }

    /**
     * initialise panel for 'ok' and 'cancel' buttons for inner window
     * @param ok the specific OK button required for the given inner window
     */      
    public void okCancelPanel(JButton ok)
    {
        okAndCancel = new JPanel();
        okAndCancel.add(ok,BorderLayout.CENTER);
        okAndCancel.add(cancel,BorderLayout.CENTER);
    }
    
    /**
     * returns button relative to String description
     * @param type a description of the window type
     * @return the relevant button
     */      
    public JButton getOkButton(String type)
    {
        switch (type) 
        {
            case "Car":
                return okAddCar;
            case "Lorry":
                return okAddLorry;
            case "Coach":
                return okAddCoach;
            case "Clear All":
                return okClearAll;
            case "Save":
                return okSave;
            case "Load":
                return okLoad;
            case "Remove":
                return okRemove;              
            default:
                return okGeneric;
        }        
    }
    
    /**
     * create a JPanel containing a JPanel of data fields and a panel with confirmation button/s
     */     
    public void generateInnerPanel ()
    {
        fieldsPanel.setBorder(new EmptyBorder(0,0,20,0));
        BorderLayout layout = new BorderLayout();
        defaultPanel = new JPanel();
        defaultPanel.setLayout(layout);
        defaultPanel.add(fieldsPanel,BorderLayout.NORTH);
        defaultPanel.add(okAndCancel,BorderLayout.SOUTH);  
        defaultPanel.setBorder(new EmptyBorder(20,20,20,20));
    }    
    
    /**
     * Reinitialises innerFrame and adds defaultPanel to it
     * @param winName the JFrame title
     */   
    public void generateInnerFrame(String winName)
    {
        mainEnabled = false;
        innerFrame.setTitle(winName);
        innerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        innerFrame.add(defaultPanel);
        innerFrame.setResizable(false);
        innerFrame.setVisible(true); 
        innerFrame.setAlwaysOnTop(true);
        innerFrame.pack();
        innerFrame.setLocationRelativeTo(null);
    }

    /**
     * populates dataFields array with values of a given vehicle, allowing editing
     * @param index position of vehicle in car park array
     */      
    public void populateFields(int index)
    {
        model.setFoundSpace(index);
        String [] vDetails = model.getCarPark()[index].dataToStringArray();
        for (int i = 0; i < dataFields.size(); i++)
        {
            if (dataFields.get(i).getClass().getSimpleName().equals("JTextField"))
            {
                ((JTextField)dataFields.get(i)).setText(vDetails[i]);
            }
            else if (dataFields.get(i).getClass().getSimpleName().equals("JComboBox"))
            {
                ((JComboBox)dataFields.get(i)).setSelectedItem(vDetails[i]);
            }
            else if (dataFields.get(i).getClass().getSimpleName().equals("JCheckBox")
                    && vDetails[i].equals("Yes"))
            {
                ((JCheckBox)dataFields.get(i)).setSelected(true);
            }
        }
    }
    
    /**
     * processes error codes returned from CarParkManager class and displays relevant JDialog
     * @param errorCode the error code
     */    
    public void responseHandler(int errorCode)
    {        
        switch (errorCode) 
        {
            case 100: // vehicle success (add/edit)
                closeInnerFrame();
                vehicleSuccessHandler();
                break;
            case 101: // invalid registration
                JOptionPane.showMessageDialog(innerFrame,"Vehicle not added. Invalid registration", "Invalid registration", JOptionPane.ERROR_MESSAGE);
                break;
            case 102: // invalid hours
                JOptionPane.showMessageDialog(innerFrame,"Vehicle not added. Invalid hours.  Hours must be an integer between 1 and 24","Invalid hours", JOptionPane.ERROR_MESSAGE);
                break;
            case 103: // invalid weight
                JOptionPane.showMessageDialog(innerFrame,"Vehicle not added. Maximum weight exceeded. Maximum weight is 35 tonnes","Too heavy", JOptionPane.ERROR_MESSAGE);
                break;
            case 200: // save success               
                closeInnerFrame(); 
                JOptionPane.showMessageDialog(this,"File saved successfully","Save complete", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 201: // save fail
                JOptionPane.showMessageDialog(innerFrame,"Save failed","Save failed", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 300: // load success
                closeInnerFrame();                 
                JOptionPane.showMessageDialog(this,"File loaded successfully","Load complete", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 301: // load fail
                JOptionPane.showMessageDialog(innerFrame,"File not found, load failed","Load failed", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 400: // empty space
                JOptionPane.showMessageDialog(null,"There is no vehicle parked here", "Space free", JOptionPane.ERROR_MESSAGE);
                break;
            case 401: // no space left
                JOptionPane.showMessageDialog(null,"Cannot add vehicle. All spaces are taken", "Error! Full car park", JOptionPane.ERROR_MESSAGE);
                break;            
            default:
                break;
        }
    }
   
    /**
     * displays information on amounts due from adding and editing vehicles
     */     
    public void vehicleSuccessHandler()
    {
        double originalCharge = model.getOriginalCharge();
        double newCharge = model.getCarPark()[model.getFoundSpace()].getCharge();  
        String info;
        if (originalCharge == -1)
        {
            info = "Vehicle added successfully. £" + money.format(newCharge) + " due to pay.";
        }
        else if (originalCharge == newCharge)
        {
            info = "Vehicle edited successfully. Charge unaffected";            
        }
        else if (originalCharge > newCharge)
        {
            info = "Vehicle edited successfully. Refund of £" + money.format(originalCharge - newCharge) + " due.";             
        }
        else //if (originalCharge < newCharge)
        {
            info = "Vehicle edited successfully. Additional amount of £" + money.format(newCharge - originalCharge) + " due to pay";             
        }
        JOptionPane.showMessageDialog(this,info,"Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * dispose of and remove components from innerFrame, re-enable the main GUI window and draw vehicles
     */     
    public void closeInnerFrame()
    {
        innerFrame.dispose();
        innerFrame.getContentPane().removeAll();
        mainEnabled = true;
        drawVehicles();

    }
    
    /**
     * sets an icon to GUI labels to represent the vehicle parked in that space
     */    
    public void drawVehicles()
    {
        for (int i = 0; i < model.getCarPark().length; i++)
        {
            if (model.getCarPark()[i] != null)
            {
                if (i < model.getNumLargeVehicles())
                {
                    drawLargeVehicle(i);
                }
                else
                {
                    drawSmallVehicle(i);
                }
            }
            else
            {
                vehicleLabel[i].setIcon(null);
            }
        }
    }
    
    /**
     * set label icon to a coach or lorry
     * @param index the index of the label to be set
     */
    public void drawLargeVehicle(int index)
    {
        if (model.getCarPark()[index].getClass().getSimpleName().equals("Lorry"))
        {
            vehicleLabel[index].setIcon(lorryImg);
        }
        else // if (model.getCarPark()[index].getClass().getSimpleName().equals("Coach"))
        {
            vehicleLabel[index].setIcon(coachImg);
        }        
    }
    
    /**
     * set label icon to a short or long car
     * @param index the index of the label to be set
     */
    public void drawSmallVehicle(int index)
    {
        if (((Car)model.getCarPark()[index]).getOverSix() == true)
        {
            vehicleLabel[index].setIcon(longCarImg); 
        }
        else
        {
            vehicleLabel[index].setIcon(shortCarImg);                          
        }        
    }
}