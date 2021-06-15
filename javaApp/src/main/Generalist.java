package main;
import java.io.File;
import java.io.IOException;

//import main.Connector;
//import main.Skyetek;
//import java.lang.Integer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

//import javax.swing.*;

public class Generalist {
	//message to send in bytes to the Skyetek RFID base (the message says "show me everything you can read until i tell you to stop"
	static byte[] message= {0x0e, 0x0d, 0x30, 0x30, 0x30, 0x31, 0x30, 0x31, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x0d,  (byte) 0x98, 0x01, (byte) 0xe6, 0x02, (byte) 0x80, 0x01,  (byte)0xe6, 0x02, 0x00, 0x00, 0x00, 0x00, 0x2c, 0x7f, (byte) 0xa4, 0x00, (byte) 0x8d, 0x3d, 0x01, 0x10, 0x3a, 0x7f, (byte) 0xa4, 0x00, (byte) 0xbd, 0x3d, 0x01, 0x10,  (byte)0x98, 0x01,  (byte)0xe6, 0x02, 0x0e, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x52, 0x0e, 0x00, 0x2c, 0x7f, (byte) 0xa4, 0x00,  (byte)0x98};
	//create a SkyetekConnector to connect more easily to the Skyetek Base (and to separate each functions)
	static SkyetekConnector example = new SkyetekConnector();
	//URL of the website where the front end app is running.
	static String textUrl = "http://localhost/Skyetek/datalog.php";
	
	
	public static void main(String[] args) throws IOException, InterruptedException
	  {
		//sound file (to alert user that a tag has been detected)
		File sound = new File("//D:/MesDossiers/Documents/soundswav/extract.wav");//use the sound you want.wav
		
		//example de tag = String tag="000000000000610121045315";
		
		//create a Connector class to read the URLs we can target
		Connector Con = new Connector();
		
		// schedule this for the event dispatch thread (edt)
	    SwingUtilities.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	       //Jwindowsshow.displayJFrame();
	       //launch a visual window for the app
	       TextDemo.createAndShowGUI(); // quitting the window kill the app.
	            
	      }
	    });
		
		
	    Thread.sleep(500);//leave some time for the window to open
        //example.executeExample();
	    TextDemo.writeinframe("System discovery ...");
		example.initHidServices();//initialise parameters for skyetekConnector
		TextDemo.writeinframe("System discovery : ok");
    	//this.hidServices.shutdown();
		TextDemo.writeinframe("Connection to target ...");
    	example.selector();//select the HID device from the list of device that the computer might detect
    	TextDemo.writeinframe("Connected to target");

    	TextDemo.writeinframe("type help to get help");
    	//start reading
    	example.sendMessage(message);//say to skyetek base "read in loop"
    	//verify reading is ok
    	String text = example.specialReader();//read message from hid device (it might have said something on the UART)
    	System.out.println("Answer is : "+text);
    	if(text.indexOf("01C1") == 2) {//if the message contain 01C1 do:
    		//01C1 is a code from the Skyetek base. it says "Ok i m doing what you want".
    	}
    	else 
    	{
    		//re send message 
    		example.sendMessage(message);
    		//it might have answered 81C1 .. it says "Ok i m stopping, i won't read anymore"
    	}
    	
    	
    	while (true) {//main loop
    	text = example.specialReader();//1 read hid message (if there is one)
    	if(text!="") {//if we receive something (at least something other than a "" and a null string)
    		//System.out.println("Answer is : ["+text+"]");
    		text=text.replace("\r", "");//2 remove strange characters that can be written in the message
    		String[] arrOfStr = text.split("\n");//3 select only useful information
    		//System.out.println("response="+arrOfStr[1]);
    		if(arrOfStr[1]=="81C1"){//we said that it's not a good message !
        		//re send message 
        		example.sendMessage(message);//just read in a loop please
        	}
    		else 
    		{//we got a message that isn't an error code
    			String[] tag = arrOfStr[1].split("C");//we read everything that is after the C character (it's how Skyetek write the tags not me)
    			if(tag[1].length() == 1) {
    				example.sendMessage(message);//wait seriously? an error code? ok please read in loop
    			}
    			else {//ok now we are fine...
    				System.out.println("tag="+tag[1]);
    				System.out.println("length: "+tag[1].length());
    				TextDemo.writeinframe("tag : "+tag[1]+" detected");
    				//send Request
    				
    				Con.setUrl(textUrl+"?Tag="+tag[1]);//we set the correct URL
    				// !!! the URL is the response of a HTML GET form !!! the way it is written is in correlation to how the datalog.php script is written.
    				Con.load();// and now we connect to the URL and read it (not really useful to read it but at least we know that the website is acknowledging us)
    				TextDemo.writeinframe("Sent");
    				PlaySound(sound); //just alert the user that a tag has been written by emitting a sound
    			}
    		}
    	}	
    }
}
	
	
	
static void PlaySound(File Sound) //easy function to generate a sound
{
    try{
	      Clip clip = AudioSystem.getClip();
	      clip.open(AudioSystem.getAudioInputStream(Sound));
	      clip.start();

	      Thread.sleep(clip.getMicrosecondLength()/1000);
	    }catch(Exception e)
	    {

	    }
	}	
}
