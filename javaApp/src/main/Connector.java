package main;

import java.io.IOException;
//import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Connector {
	
	URL url;
	
	public Connector() 
	{
		this.url = null;
	}
	
	public Connector(String text) throws IOException
	{
		this.url = new URL(text);
	}
	
	public URL getUrl() 
	{
		return this.url;
	}
	
	public void setUrl (String text) throws IOException
	{
		//"http://localhost/Skyetek/datalog.php?Tag="+tag //String tag="000000000000610121045315";
		this.url = new URL(text);
	}
	
	public void load() throws IOException
	{
		//Retrieving the contents of the specified page
		Scanner sc = new Scanner(this.url.openStream());
	    //Instantiating the StringBuffer class to hold the result
	    StringBuffer sb = new StringBuffer();
	    while(sc.hasNext()) {
	         sb.append(sc.next());
	         //System.out.println(sc.next());
	    }
	    //Retrieving the String from the String Buffer object
	    String result = sb.toString();
	    System.out.println(result);
	    //Removing the HTML tags
	    result = result.replaceAll("<[^>]*>", "");
	    System.out.println("Contents of the web page: "+result);
	}
}