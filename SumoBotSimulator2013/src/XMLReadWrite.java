/*
 * A class that reads and writes XML files to the specifications of the program.  To clarify, the class is only good
 * for making or reading XML files that have the same layout as the robot.xml file found in SumobotSimulator2013/
 * SumobotSimulator2013/files/robot.xml
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult; 

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

public class XMLReadWrite {

	 /*
	  * This is a method that creates the xml file that will hold all the data inputed by the user
	  * 
	  * Precondition: The variables specified exist.
	  * Postcondition:  An xml file is created with all the variables stored.  The name of the file 
	  * 				is specified when calling the method.
	  */
	 public static void write(File name) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException, DOMException{ 
		 
		 try{
			 // Creates an instance of document builder so that an xml file can be made
			 DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();	
			 DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		 
			 // Makes the root element robot
			 Document doc = documentBuilder.newDocument();
			 Element rootElement = doc.createElement("robot");
			 doc.appendChild(rootElement);
			 
			 // The next blocks of code are all children to the root element "robot".
			 Element PSvoltage = doc.createElement("PSvoltage");
			 PSvoltage.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.PSVolt)));
			 rootElement.appendChild(PSvoltage);
			 
			 Element PScurrent = doc.createElement("PScurrent");
			 PScurrent.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.PSAmp)));
			 rootElement.appendChild(PScurrent);
		 
			 Element Mvoltage = doc.createElement("Mvoltage");
			 Mvoltage.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.MVolt)));
			 rootElement.appendChild(Mvoltage);
			 
			 Element Mcurrent = doc.createElement("Mcurrent");
			 Mcurrent.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.MAmp)));
			 rootElement.appendChild(Mcurrent);
		 
			 Element Mspeed = doc.createElement("Mspeed");
			 Mspeed.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.MSpeed)));
			 rootElement.appendChild(Mspeed);
		 
			 Element Mtorque = doc.createElement("Mtorque");
			 Mtorque.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.MTorque)));
			 rootElement.appendChild(Mtorque);
			 
			 Element Mdrive_shaft = doc.createElement("Mdrive_shaft");
			 Mdrive_shaft.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.MDriveShaft)));
			 rootElement.appendChild(Mdrive_shaft);
		 
			 Element Wilength = doc.createElement("Wilength");
			 Wilength.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.WiLen)));
			 rootElement.appendChild(Wilength);
		 
			 Element Wiresistance = doc.createElement("Wiresistance");
			 Wiresistance.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.WiOhm)));
			 rootElement.appendChild(Wiresistance); 
			
			 Element Wiarea = doc.createElement("Wiarea");
			 Wiarea.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.WiArea)));
			 rootElement.appendChild(Wiarea); 
		 
			 Element Wiresistivity = doc.createElement("Wiresistivity");
			 Wiresistivity.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.WiRes)));
			 rootElement.appendChild(Wiresistivity);
		 
			 Element Wicicuit_type = doc.createElement("Wicicuit_type");
			 Wicicuit_type.appendChild(doc.createTextNode(SumoBotSimulator2013.WiCircuit));
			 rootElement.appendChild(Wicicuit_type);
			 
			 Element Wediameter = doc.createElement("Wediameter");
			 Wediameter .appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.WhDiam)));
			 rootElement.appendChild(Wediameter);
		 
			 Element Wecoefficient = doc.createElement("Wecoefficient");
			 Wecoefficient.appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.WhMu)));
			 rootElement.appendChild(Wecoefficient);
		 
			 Element weight = doc.createElement("weight");
			 weight .appendChild(doc.createTextNode(Double.toString(SumoBotSimulator2013.RWeight)));
			 rootElement.appendChild(weight);
		 
			 // Creates an instance of TransformerFactory so that a "real" XML file can be created
			 TransformerFactory transformerFactory = TransformerFactory.newInstance();
			 Transformer transformer = transformerFactory.newTransformer();
			 DOMSource source = new DOMSource(doc);
			 StreamResult result = new StreamResult(name);
			 
			 // Exports the XML 
			 // To clarify:  this is when the XML file is made on the local hard drive.  
			 transformer.transform(source, result);
		 
			 // So it does not goof up
		 }catch(ParserConfigurationException pce){
			  pce.printStackTrace();
		 }catch(TransformerException tfe){
		   tfe.printStackTrace();
		 }catch(NullPointerException e){
			 e.printStackTrace();
		 }
		 
		// To test
		System.out.println("saved");
	 }
	 
	 /*
	  * This method is to read the xml file that will hold all the data saved by the user.
	  * 
	  * Precondition:  There is a unparsed xml file that has the format specified for the SumoBot Simulator 2013
	  * 			   application.
	  * Postcondition:  The specified xml file has been parsed and the values have been stored in the appropriate 
	  * 				variables as specified above.
	  */
	 public static void read(File doc) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException, DOMException{
		 
		 //File file = new File(doc);																// Determines which file to parse
		 DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();	// Creates the parser object
		 DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		 Document document = documentBuilder.parse(doc);										// Parses the file
		
		 // Gets the info and makes it an double
		 SumoBotSimulator2013.PSVolt = Double.parseDouble(document.getElementsByTagName("PSvoltage").item(0).getTextContent());	
		 SumoBotSimulator2013.PSAmp = Double.parseDouble(document.getElementsByTagName("PScurrent").item(0).getTextContent());
		 SumoBotSimulator2013.MVolt = Double.parseDouble(document.getElementsByTagName("Mvoltage").item(0).getTextContent());
		 SumoBotSimulator2013.MAmp = Double.parseDouble(document.getElementsByTagName("Mcurrent").item(0).getTextContent());
		 SumoBotSimulator2013.MSpeed = Double.parseDouble(document.getElementsByTagName("Mspeed").item(0).getTextContent());
		 SumoBotSimulator2013.MTorque = Double.parseDouble(document.getElementsByTagName("Mtorque").item(0).getTextContent());
		 SumoBotSimulator2013.MDriveShaft = Double.parseDouble(document.getElementsByTagName("Mdrive_shaft").item(0).getTextContent());
		 SumoBotSimulator2013.WiLen = Double.parseDouble(document.getElementsByTagName("Wilength").item(0).getTextContent());
		 SumoBotSimulator2013.WiOhm = Double.parseDouble(document.getElementsByTagName("Wiresistance").item(0).getTextContent());
		 SumoBotSimulator2013.WiArea = Double.parseDouble(document.getElementsByTagName("Wiarea").item(0).getTextContent());
		 SumoBotSimulator2013.WiRes = Double.parseDouble(document.getElementsByTagName("Wiresistivity").item(0).getTextContent());
		 SumoBotSimulator2013.WiCircuit = document.getElementsByTagName("Wicicuit_type").item(0).getTextContent();
		 SumoBotSimulator2013.WhDiam = Double.parseDouble(document.getElementsByTagName("Wediameter").item(0).getTextContent());
		 SumoBotSimulator2013.WhMu = Double.parseDouble(document.getElementsByTagName("Wecoefficient").item(0).getTextContent());
		 SumoBotSimulator2013.RWeight = Double.parseDouble(document.getElementsByTagName("weight").item(0).getTextContent());	 
		 
		 // To test
		 System.out.println("loaded");
	 }
	 

}
