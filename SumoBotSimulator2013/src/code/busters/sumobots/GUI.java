package code.busters.sumobots;
/*
 * A class that makes the GUI and handles user interaction with the application
 * It's name is George U. Inhaussulvhat and he is beautiful! 
 */

import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.StateBasedGame;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

// Watch the tree of life
public class GUI {
	
	public static int MenuBarFileX = 4;
	public static int MenuBarFileY = 2;
	
	public static float AspectRatio = (MainSim.WinX / MainSim.WinY);
	public static double InfoPaneScale = 0.25;
	public static int InfoPaneWidth;
	
	// Assorted colours
	public static Color LightBlue = new Color(0xffaaccff);
	public static Color DarkBlue = new Color(0xff252830);
	public static Color White = new Color(0xffffffff);
	public static Color HighlightGray = new Color(0x40000000);
	public static Color HighlightBlue = new Color(0xff768DB0);
	
	// Assorted buttons
	public static Button fileButton, btnSelNew, btnSelOpen, btnSelSave, btnSelQuit;
	public static Button editButton, btnSelElec, btnSelMech, btnSelSim;
	public static Button helpButton, btnSelHelp, btnSelAbout;
	public static Button fancyMech, fancyElec, fancySim;
	
	private static Font defaultFont = new Font("Lucida Console", Font.PLAIN, 12);
	static TrueTypeFont defaultTTF = new TrueTypeFont(defaultFont, true);
	
	// Assorted text fields
	// For mechanical info
	static TextField txtWheelDiam;
	static TextField txtWheelMu;
	static TextField txtWeight;
	// For electrical info
	static TextField txtPSVolt;
	static TextField txtPSCurrent;
	static TextField txtMotorVolt;
	static TextField txtMotorCurrent;
	static TextField txtMotorSpeed;
	//static TextField txtMotorTorque;
	static TextField txtMotorShaft;
	static TextField txtWireLen;
	static TextField txtWireResistivity;
	static TextField txtWireArea;
	
	public static Image menu;
	public static Image menuscaled;
	
	public static Image mech;		// 
	public static Image elecPar;	// The images that will be displayed on each thingy 
	public static Image sim;		//
	
	public static Image tabElec;	//
	public static Image tabMech;	// The tab images that will be shown at the bottom of the page
	public static Image tabSim;		//
	
	public static Color MenuBarColorMain = Color.transparent;
	public static Color MenuBarColorSecond = LightBlue;
	
	public static void CloseMenu() throws SlickException {
		fileButton.setActivity(false);
		editButton.setActivity(false);
		helpButton.setActivity(false);
	}
	
	public static void InitGUI(GameContainer gc, StateBasedGame sg) throws SlickException {
		
		// Buttons for the menu bar
		fileButton = new Button(gc, sg, 0, 1, 40, 18, MenuBarColorMain, Color.black, "File");
		editButton = new Button(gc, sg, fileButton.getX() + fileButton.getWidth() + 2, 1, 40, 18, MenuBarColorMain, Color.black, "Edit");
		helpButton = new Button(gc, sg, editButton.getX() + editButton.getWidth() + 2, 1, 40, 18, MenuBarColorMain, Color.black, "Help");
		
		btnSelNew = new Button(gc, sg, fileButton.getX(), fileButton.getY()+fileButton.getHeight()+1, 40, 18, MenuBarColorSecond, Color.black, "New ");
		btnSelOpen = new Button(gc, sg, fileButton.getX(), btnSelNew.getY()+btnSelNew.getHeight(), 40, 18, MenuBarColorSecond, Color.black, "Open");
		btnSelSave = new Button(gc, sg, fileButton.getX(), btnSelOpen.getY()+btnSelOpen.getHeight(), 40, 18, MenuBarColorSecond, Color.black, "Save");
		btnSelQuit = new Button(gc, sg, fileButton.getX(), btnSelSave.getY()+btnSelSave.getHeight(), 40, 18, MenuBarColorSecond, Color.black, "Quit");
		
		btnSelElec = new Button(gc, sg, editButton.getX(), editButton.getY()+editButton.getHeight()+1, 40, 18, MenuBarColorSecond, Color.black, "Electrical");
		btnSelMech = new Button(gc, sg, editButton.getX(), btnSelElec.getY()+btnSelElec.getHeight(), 40, 18, MenuBarColorSecond, Color.black, "Mechanical");
		btnSelSim = new Button(gc, sg, editButton.getX(), btnSelMech.getY()+btnSelMech.getHeight(), 40, 18, MenuBarColorSecond, Color.black, "Smulation ");
		
		btnSelHelp = new Button(gc, sg, helpButton.getX(), helpButton.getY()+helpButton.getHeight()+1, 40, 18, MenuBarColorSecond, Color.black, "Help ");
		btnSelAbout = new Button(gc, sg, helpButton.getX(), btnSelHelp.getY()+btnSelHelp.getHeight(), 40, 18, MenuBarColorSecond, Color.black, "About");
	
		// Images that ar displayed for each window
		mech = new Image("res/mech.png");
		elecPar = new Image("res/parallel.gif");
		menu = new Image("res/menu.png");
		
		menuscaled = menu.getScaledCopy(MainSim.WinX, 20);
		 
		// Text fields for mechanical components
		// Text field for wheel diameter
		txtWheelDiam = new TextField(gc, defaultTTF, InfoPaneWidth + 590, 365, 55, 20);
		txtWheelDiam.setBackgroundColor(new Color(0xfff4f4f4));
		txtWheelDiam.setBorderColor(Color.black);
		txtWheelDiam.setTextColor(Color.black);
		txtWheelDiam.setMaxLength(6);
		txtWheelDiam.setAcceptingInput(false);
		// Text field for coefficient of friction
		txtWheelMu = new TextField(gc, defaultTTF, InfoPaneWidth + 660, 220, 55, 20);
		txtWheelMu.setBackgroundColor(new Color(0xfff4f4f4));
		txtWheelMu.setBorderColor(Color.black);
		txtWheelMu.setTextColor(Color.black);
		txtWheelMu.setMaxLength(6);
		txtWheelMu.setAcceptingInput(false);
		// Text field for the weight of the robot
		txtWeight = new TextField(gc, defaultTTF, InfoPaneWidth + 340, 340, 55, 20);
		txtWeight.setBackgroundColor(new Color(0xfff4f4f4));
		txtWeight.setBorderColor(Color.black);
		txtWeight.setTextColor(Color.black);
		txtWeight.setMaxLength(6);
		txtWeight.setAcceptingInput(false);
		
		// Text fields for electrical components
		// For the powers supply voltage
		txtPSVolt = new TextField(gc, defaultTTF, InfoPaneWidth + 327, 125, 55, 20);
		txtPSVolt.setBackgroundColor(new Color(0xfff4f4f4));
		txtPSVolt.setBorderColor(Color.black);
		txtPSVolt.setTextColor(Color.black);
		txtPSVolt.setMaxLength(6);
		txtPSVolt.setAcceptingInput(false);
		// For the power supply current
		txtPSCurrent = new TextField(gc, defaultTTF, InfoPaneWidth + 513, 125, 55, 20);
		txtPSCurrent.setBackgroundColor(new Color(0xfff4f4f4));
		txtPSCurrent.setBorderColor(Color.black);
		txtPSCurrent.setTextColor(Color.black);
		txtPSCurrent.setMaxLength(6);
		txtPSCurrent.setAcceptingInput(false);
		// For the motor voltage
		txtMotorVolt = new TextField(gc, defaultTTF, InfoPaneWidth + 286, 221, 55, 20);
		txtMotorVolt.setBackgroundColor(new Color(0xfff4f4f4));
		txtMotorVolt.setBorderColor(Color.black);
		txtMotorVolt.setTextColor(Color.black);
		txtMotorVolt.setMaxLength(6);
		txtMotorVolt.setAcceptingInput(false);
		// For the motor current
		txtMotorCurrent = new TextField(gc, defaultTTF, InfoPaneWidth + 286, 280, 55, 20);
		txtMotorCurrent.setBackgroundColor(new Color(0xfff4f4f4));
		txtMotorCurrent.setBorderColor(Color.black);
		txtMotorCurrent.setTextColor(Color.black);
		txtMotorCurrent.setMaxLength(6);
		txtMotorCurrent.setAcceptingInput(false);
		// For the motor speed
		txtMotorSpeed = new TextField(gc, defaultTTF, InfoPaneWidth + 286, 338, 55, 20);
		txtMotorSpeed.setBackgroundColor(new Color(0xfff4f4f4));
		txtMotorSpeed.setBorderColor(Color.black);
		txtMotorSpeed.setTextColor(Color.black);
		txtMotorSpeed.setMaxLength(6);
		txtMotorSpeed.setAcceptingInput(false);
		// For the motor shaft
		txtMotorShaft = new TextField(gc, defaultTTF, InfoPaneWidth + 286, 410, 55, 20);
		txtMotorShaft.setBackgroundColor(new Color(0xfff4f4f4));
		txtMotorShaft.setBorderColor(Color.black);
		txtMotorShaft.setTextColor(Color.black);
		txtMotorShaft.setMaxLength(6);
		txtMotorShaft.setAcceptingInput(false);
		// For the wire length
		txtWireLen = new TextField(gc, defaultTTF, InfoPaneWidth + 637, 175, 55, 20);
		txtWireLen.setBackgroundColor(new Color(0xfff4f4f4));
		txtWireLen.setBorderColor(Color.black);
		txtWireLen.setTextColor(Color.black);
		txtWireLen.setMaxLength(6);
		txtWireLen.setAcceptingInput(false);
		// For the wire resistivity
		txtWireResistivity = new TextField(gc, defaultTTF, InfoPaneWidth + 637, 231, 55, 20);
		txtWireResistivity.setBackgroundColor(new Color(0xfff4f4f4));
		txtWireResistivity.setBorderColor(Color.black);
		txtWireResistivity.setTextColor(Color.black);
		txtWireResistivity.setMaxLength(6);
		txtWireResistivity.setAcceptingInput(false);
		// For the wire cross sectional area
		txtWireArea = new TextField(gc, defaultTTF, InfoPaneWidth + 637, 290, 55, 20);
		txtWireArea.setBackgroundColor(new Color(0xfff4f4f4));
		txtWireArea.setBorderColor(Color.black);
		txtWireArea.setTextColor(Color.black);
		txtWireArea.setMaxLength(6);
		txtWireArea.setAcceptingInput(false);
				
		if (AspectRatio > 1.34) {
			InfoPaneScale = 0.15;
		} else {
			InfoPaneScale = 0.25;
		}
		InfoPaneWidth = (int)(MainSim.WinX * InfoPaneScale);
		
		// Images for the tabs at the bottom of the screen
		tabElec = new Image("res/TabElectric.png");
		tabMech = new Image("res/TabMech.png");
		tabSim = new Image("res/TabSim.png");
		// Actual tabs
		fancyMech = new Button(gc, sg, 200,572, tabMech.getWidth(),tabMech.getHeight(), tabMech);
		fancyElec = new Button(gc, sg, 400, 572, tabElec.getWidth(), tabElec.getHeight(), tabElec);
		fancySim = new Button(gc, sg, 600, 572, tabSim.getWidth(), tabSim.getHeight(), tabSim);
	}
	
	public static void UpdateInfo(Graphics g) throws SlickException {
		g.setFont(defaultTTF);
		g.setColor(White);
		
		//Motor
		g.drawString("Motor:", 3, 47);
		g.drawString("Voltage: " + Double.toString(BuildState.roboMotorOne.getVoltage()), 12, 59);
		g.drawString("Current: " + Double.toString(BuildState.roboMotorOne.getCurrent()), 12, 71);
		g.drawString("Speed: " + Double.toString(BuildState.roboMotorOne.getSpeed()), 12, 83);
		g.drawString("Torque: " + Double.toString(BuildState.roboMotorOne.getTorque()), 12, 95);
		g.drawString("Shaft: " + Double.toString(BuildState.roboMotorOne.getShaft()), 12, 107);
		
		//Power Supply
		g.drawString("Power Supply:", 3, 131);
		g.drawString("Voltage: " + Double.toString(BuildState.roboPS.getVoltage()), 12, 143);
		g.drawString("Current: " + Double.toString(BuildState.roboPS.getCurrent()), 12, 155);

		//Wheel
		g.drawString("Wheel:", 3, 179);
		g.drawString("Diameter: " + Double.toString(BuildState.roboWheel.getDiam()), 12, 191);
		g.drawString("Friction Coefficient: " + Double.toString(BuildState.roboWheel.getMu()), 12, 203);

		//Wire
		g.drawString("Wire:", 3, 227);
		g.drawString("Length: " + Double.toString(BuildState.roboWire.getLen()), 12, 239);
		g.drawString("Ohm: " + Double.toString(BuildState.roboWire.getOhm()), 12, 251);
		g.drawString("Resistivity: " + Double.toString(BuildState.roboWire.getResistivity()), 12, 263);
		g.drawString("Area: " + Double.toString(BuildState.roboWire.getArea()), 12, 275);
		g.drawString("Circuit Type: " + BuildState.roboWire.getCircuitType(), 12, 287);
		
		// NEW
		g.drawString("Robot:", 3, 308);
		g.drawString("Weight: " + Double.toString(BuildState.RWeight), 12, 320);
	
	}
	
	// This is some very long method with a ton of if statements to make the GUI look pretty and more fun to use.
	// ooooooo look, the file just button changed colour.
	public static void UpdateGUI(GameContainer gc, StateBasedGame sg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		// Checks if the mouse is over some buttons.  If it is then the background colour changes to light gray
		if (fileButton.mouseOverArea(gc) == true){
			fileButton.changeColour(HighlightGray);
		}else{
			fileButton.changeColour(MenuBarColorMain);
		}
		if (editButton.mouseOverArea(gc) == true){
			editButton.changeColour(HighlightGray);
		}else{
			editButton.changeColour(MenuBarColorMain);
		}
		if (helpButton.mouseOverArea(gc) == true){
			helpButton.changeColour(HighlightGray);
		}else{
			helpButton.changeColour(MenuBarColorMain);
		}
		
		// When the button is clicked then is becomes active.  The other buttons become inactive.
		if (fileButton.buttonClicked(gc) == true){
			CloseMenu();
			fileButton.setActivity(true);
			editButton.setActivity(false);
			helpButton.setActivity(false);
		}else if(editButton.buttonClicked(gc) == true){
			CloseMenu();
			editButton.setActivity(true);
			fileButton.setActivity(false);
			helpButton.setActivity(false);
		}else if(helpButton.buttonClicked(gc) == true){
			CloseMenu();
			helpButton.setActivity(true);
			editButton.setActivity(false);
			fileButton.setActivity(false);
		}else if(fancyMech.buttonClicked(gc) == true){
			CloseMenu();
			btnSelMech.setActivity(true);
			btnSelElec.setActivity(false);
			btnSelSim.setActivity(false);
		}else if(fancyElec.buttonClicked(gc) == true){
			CloseMenu();
			btnSelElec.setActivity(true);
			btnSelSim.setActivity(false);
			btnSelMech.setActivity(false);
		}else if(fancySim.buttonClicked(gc) == true){
			CloseMenu();
			btnSelSim.setActivity(true);
			btnSelElec.setActivity(false);
			btnSelMech.setActivity(false);
		
		}
		
		// When the file button is active; checks if the mouse is over the buttons that pop up underneath file.  
		// If it is then the background colour changes to light gray.
		// Also, allows for interaction with the new, save, open and quit buttons.
		if (fileButton.isActive() == true){
			// Makes the buttons change colour
			if (btnSelNew.mouseOverArea(gc) == true){
				btnSelNew.changeColour(HighlightBlue);
			}else{
				btnSelNew.changeColour(MenuBarColorSecond);
			}
			if (btnSelSave.mouseOverArea(gc) == true){
				btnSelSave.changeColour(HighlightBlue);
			}else{
				btnSelSave.changeColour(MenuBarColorSecond);
			}
			if (btnSelOpen.mouseOverArea(gc) == true){
				btnSelOpen.changeColour(HighlightBlue);
			}else{
				btnSelOpen.changeColour(MenuBarColorSecond);
			}
			if (btnSelQuit.mouseOverArea(gc) == true){
				btnSelQuit.changeColour(HighlightBlue);
			}else{
				btnSelQuit.changeColour(MenuBarColorSecond);
			}
			
			// Adds function to the buttons
			// Reads robot.xml file to re-initialize all the variables
			if (btnSelNew.buttonClicked(gc) == true){
				CloseMenu();
				txtWheelMu.setText("");
				txtWheelDiam.setText("");
				txtWeight.setText("");
				try {
					File robot = new File("files/robot.xml");
					XMLReadWrite.read(robot);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (DOMException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
			}
			// Creates a new file with all the modified data
			if (btnSelSave.buttonClicked(gc) == true){
				CloseMenu();
				JFrame guiFrame = new JFrame();						// Uses swing's fileChooser class to make
				JFileChooser fileDialog = new JFileChooser();		//    a very pretty dialogue that allows the user 
				FileNameExtensionFilter xmlfilter = new 
						FileNameExtensionFilter("xml files (*.xml)", "xml");
				fileDialog.setFileFilter(xmlfilter);
				int save = fileDialog.showSaveDialog(guiFrame);		//    to save their progress in style
				if (save == JFileChooser.APPROVE_OPTION){			// Makes sure the input is good
					File saveAs = fileDialog.getSelectedFile();
					try {
						XMLReadWrite.write(saveAs);					// Writes the XML file with the inputed file name and 
					} catch (FileNotFoundException e) {				//  in the chosen directory
						e.printStackTrace();
					} catch (DOMException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					}
				} 
				
			}
			if (btnSelOpen.buttonClicked(gc) == true){
				CloseMenu();
				JFrame guiFrame = new JFrame();						// Uses the fileChooser class to open up projects
				JFileChooser fileDialog = new JFileChooser();		//    in style ;)
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
			                "xml files (*.xml)", "xml");
				fileDialog.setFileFilter(xmlfilter);
				int open = fileDialog.showOpenDialog(guiFrame);		
				if (open == JFileChooser.APPROVE_OPTION){			// Makes sure the file is good
				  File file = fileDialog.getSelectedFile();
				    try {
						XMLReadWrite.read(file);					// Reads the file contents
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (DOMException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					}
				}
			}
			// Exits the program
			if (btnSelQuit.buttonClicked(gc) == true){
				CloseMenu();
				System.exit(0);
			}
		}
		
		// When the edit button is active; checks if the mouse is over the buttons that pop up underneath edit.  
		// If it is then the background colour changes to light gray.
		// Also allows for interactions with the electrical, mechanical and simulation buttons.
		if (editButton.isActive() == true){
			// Makes the buttons change colour
			if (btnSelElec.mouseOverArea(gc) == true){
				btnSelElec.changeColour(HighlightBlue);
			}else{
				btnSelElec.changeColour(MenuBarColorSecond);
			}
			if (btnSelMech.mouseOverArea(gc) == true){
				btnSelMech.changeColour(HighlightBlue);
			}else{
				btnSelMech.changeColour(MenuBarColorSecond);
			}
			if (btnSelSim.mouseOverArea(gc) == true){
				btnSelSim.changeColour(HighlightBlue);
			}else{
				btnSelSim.changeColour(MenuBarColorSecond);
			}
			
			// Adds function to the buttons
			if (btnSelElec.buttonClicked(gc) == true){
				CloseMenu();
				btnSelElec.setActivity(true);
				btnSelMech.setActivity(false);
				btnSelSim.setActivity(false);
			}else if(btnSelMech.buttonClicked(gc) == true){
				CloseMenu();
				btnSelMech.setActivity(true);
				btnSelElec.setActivity(false);
				btnSelSim.setActivity(false);
			}else if(btnSelSim.buttonClicked(gc) == true){
				CloseMenu();
				btnSelMech.setActivity(false);
				btnSelElec.setActivity(false);
				btnSelSim.setActivity(true);
			}
		}
		
		// When the help button is active; checks if the mouse is over the buttons that pop up underneath help.  
		// If it is then the background colour changes to light gray.
		// Also allows for interaction with the about and help buttons.
		if (helpButton.isActive() == true){
			// Makes the buttons change colour
			if (btnSelAbout.mouseOverArea(gc) == true){
				btnSelAbout.changeColour(HighlightBlue);
			}else{
				btnSelAbout.changeColour(MenuBarColorSecond);
			}
			if (btnSelHelp.mouseOverArea(gc) == true){
				btnSelHelp.changeColour(HighlightBlue);
			}else{
				btnSelHelp.changeColour(MenuBarColorSecond);
			}
			
			// Adds functionality to the buttons
			// Displays the about file using the default text editor
			if (btnSelAbout.buttonClicked(gc) == true){
				CloseMenu();
				File about = new File("res/about.txt");
				try {
					Desktop.getDesktop().open(about);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IllegalArgumentException e){
					e.printStackTrace();
				}
			}
			// Displays the help file using the default text editor
			if (btnSelHelp.buttonClicked(gc) == true){
				CloseMenu();
				File help = new File("res/help.txt");
				try {
					Desktop.getDesktop().open(help);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IllegalArgumentException e){
					e.printStackTrace();
				}
			}
	
		}
		
		// Gets the info from the mechanical text boxes
		if (btnSelMech.isActive() == true){
			try{
				// Make sure the text is not null
				if (txtWheelDiam.getText() != ""){
					BuildState.roboWheel.setDiam(Double.parseDouble(txtWheelDiam.getText()));
				}
				if (txtWheelMu.getText() != ""){
					BuildState.roboWheel.setMu(Double.parseDouble(txtWheelMu.getText()));
				}
				if (txtWeight.getText() != ""){
					BuildState.RWeight = (Double.parseDouble(txtWeight.getText()));
				}
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
						
		}
		
		// Gets the info from the electrical text boxes
		if (btnSelElec.isActive() == true){
			try{
				// Make sure the text is not null
				if (txtPSVolt.getText() != ""){
					BuildState.roboPS.setVoltage(Double.parseDouble(txtPSVolt.getText()));
				}
				if (txtPSCurrent.getText() != ""){
					BuildState.roboPS.setCurrent(Double.parseDouble(txtPSCurrent.getText()));
				}
				if (txtMotorVolt.getText() != ""){
					BuildState.roboMotorOne.setVoltage(Double.parseDouble(txtMotorVolt.getText()));
				}
				if (txtMotorCurrent.getText() != ""){
					BuildState.roboMotorOne.setCurrent(Double.parseDouble(txtMotorCurrent.getText()));
				}
				if (txtMotorShaft.getText() != ""){
					BuildState.roboMotorOne.setShaft(Double.parseDouble(txtMotorShaft.getText()));
				}
				if (txtMotorSpeed.getText() != ""){
					BuildState.roboMotorOne.setSpeed(Double.parseDouble(txtMotorSpeed.getText()));
				}
				if (txtWireLen.getText() != ""){
					BuildState.roboWire.setLen(Double.parseDouble(txtWireLen.getText()));
				}
				if (txtWireResistivity.getText() != ""){
					BuildState.roboWire.setResistivity(Double.parseDouble(txtWireResistivity.getText()));
				}
				if (txtWireArea.getText() != ""){
					BuildState.roboWire.setArea(Double.parseDouble(txtWireArea.getText()));
				}
				
				if (txtMotorVolt.getText() != "" && txtMotorCurrent.getText() != "" && txtMotorSpeed.getText() != ""){
					BuildState.roboMotorOne.setTorque();
				}
				// Sets the wire resistance based on inputed info
				if (txtWireArea.getText() != "" && txtWireResistivity.getText() != "" && txtWireLen.getText() != ""){
					BuildState.roboWire.setOhm();
				}
			}catch(NumberFormatException e){
				e.printStackTrace();	
			}
						
		}
		
		// When something other than the menu strip buttons are clicked the menu strip buttons are set to inactive.
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) || input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
			fileButton.setActivity(false);
			editButton.setActivity(false);
			helpButton.setActivity(false);
		
		}
	}
		
	public static void RenderGUI(GameContainer gc, StateBasedGame sg, Graphics g, String element) throws SlickException {
				
		if (element == "MainWindow"){ 
			if (btnSelElec.isActive() == true) {
				txtPSVolt.render(gc, g);
				txtPSCurrent.render(gc, g);
				txtMotorVolt.render(gc, g);
				txtMotorCurrent.render(gc, g);
				txtMotorShaft.render(gc, g);
				txtMotorSpeed.render(gc, g);
				txtWireLen.render(gc, g);
				txtWireResistivity.render(gc, g);
				txtWireArea.render(gc, g);
				txtPSVolt.setAcceptingInput(true);
				txtPSCurrent.setAcceptingInput(true);
				txtMotorVolt.setAcceptingInput(true);
				txtMotorCurrent.setAcceptingInput(true);
				txtMotorShaft.setAcceptingInput(true);
				txtMotorSpeed.setAcceptingInput(true);
				txtWireLen.setAcceptingInput(true);
				txtWireResistivity.setAcceptingInput(true);
				txtWireArea.setAcceptingInput(true);
				elecPar.draw(InfoPaneWidth + 10, 100);
			} else if (btnSelMech.isActive() == true) {
				txtWheelDiam.render(gc, g);
				txtWheelMu.render(gc, g);
				txtWeight.render(gc, g);
				txtWheelDiam.setAcceptingInput(true);
				txtWheelMu.setAcceptingInput(true);
				txtWeight.setAcceptingInput(true);
				mech.draw(InfoPaneWidth + 10, 100);
			} else if (btnSelSim.isActive() == true) {
				// TODO: add stuff
			}
			fancyMech.renderImg();
			fancyElec.renderImg();
			fancySim.renderImg();
		}
		
		// Draws the menu bar
		if (element == "MenuBar") {
			 g.setColor(MenuBarColorSecond);
			 g.fillRect(0, 0, MainSim.WinX, 20);
			 menuscaled.draw(0,0);
			 fileButton.render(gc, g);
			 editButton.render(gc, g);
			 helpButton.render(gc, g);
			 // Draws the options in file if file is active
			 if (fileButton.isActive() == true){
				 btnSelNew.render(gc, g);
				 btnSelOpen.render(gc, g);
				 btnSelSave.render(gc, g);
				 btnSelQuit.render(gc, g);
			 }
			// Draws the options in edit if edit is active
			 if (editButton.isActive() == true){
				 btnSelElec.render(gc, g);
				 btnSelMech.render(gc, g);
				 btnSelSim.render(gc, g);
			 }
			// Draws the options in help if help is active
			 if (helpButton.isActive() == true){
				 btnSelHelp.render(gc, g);
				 btnSelAbout.render(gc, g);
			 }
			 
		} else if (element == "InfoPane") {
			g.setColor(DarkBlue);
			g.fillRect(0, 20, InfoPaneWidth, MainSim.WinY);
			g.setFont(defaultTTF);
			g.setColor(White);
			g.drawString("Info Pane", 3, 23);
		}
	
	}

}