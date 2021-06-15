<?php
		//$username = $_GET['username'];
		//$password = $_GET['password'];
		//$BDD = $_GET['BDD'];
		//http://localhost/Skyetek/datalog.php?username=Skyetek+&password=Skyetek+&BDD=rfid+&Tag=101A
		//print "$username , $password wants to connect to $BDD </br>";
		
		$connexion = new mysqli("localhost", "root", "", "rfid");
		if ($connexion->connect_errno) {
			printf("´Echec de la connexion : %s %s",
			$connexion->connect_errno, $connexion->connect_error);
			exit();
		}
		
		
		$Tag = $_GET['Tag'];
		print "Tag = $Tag </br>";//http://localhost/Skyetek/datalog.php?Tag=101A
		
		$Myquery="Select * FROM fonctions";
		//echo $Myquery;
		$result = $connexion->query($Myquery);
		if($result)
		{
			$dataList = $result->fetch_assoc();
			echo "fonction en cours : ".$dataList['Dothat'];
		}
		
		if($dataList['Dothat'] == "add")
		{
			
			//selon la colonne Dothat de functions
			$Myquery="INSERT INTO rfid (Tag)values('$Tag')";
			//echo $Myquery;
			$result = $connexion->query($Myquery);
			if(!$result)
			{
				echo "error";
			}
		}
		else if ($dataList['Dothat'] == "fill")
		{
			$Myquery="UPDATE consommation , rfid SET Etat = '1' WHERE consommation.TagRfid = rfid.IdRfid AND rfid.Tag = '$Tag'";
			//echo $Myquery;
			$result = $connexion->query($Myquery);
			if(!$result)
			{
				echo "error";
			}
			else
			{
				echo "</br>verre rempli";
			}
		}
		else if ($dataList['Dothat'] == "delete")
		{
			$Myquery="DELETE c FROM consommation c INNER JOIN rfid r ON TagRfid=IdRfid WHERE Tag = '$Tag'";
			echo $Myquery;
			$result = $connexion->query($Myquery);
			if(!$result)
			{
				echo "error";
			}
			else
			{
				echo "</br>verre supprimé";
			}
		}
		else
		{
			//$Tag
			$Myquery="UPDATE consommation , rfid SET Etat = '0' WHERE consommation.TagRfid = rfid.IdRfid AND rfid.Tag = '$Tag'";
			//echo $Myquery;
			$result = $connexion->query($Myquery);
			if(!$result)
			{
				echo "error";
			}
			else
			{
				echo "</br>verre vide";
			}
			
		}
		
		
		
		
		$connexion->close();
 
/*
!!!CODE JAVA!!!



package main;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Connector {
   public static void main(String args[]) throws IOException {
      //Instantiating the URL class
	  String tag="000000000000610121045315";
      URL url = new URL("http://localhost/Skyetek/datalog.php?Tag="+tag);
      //Retrieving the contents of the specified page
      Scanner sc = new Scanner(url.openStream());
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
*/
 
 ?>
 
 
 
 