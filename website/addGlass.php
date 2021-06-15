<html>
	
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="images/favicon.ico">
		<title>AddGlass</title>
		
		<style>
		table, th, td {
			border: 1px solid black;
		}
		
		a.class1:link, a.class1:visited {
				background-color: #368ff4;
				color: white;
				padding: 14px 25px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
			}

			a.class1:hover, a.class1:active {
				background-color: blue;
			}

			a.class2:link, a.class2:visited {
				background-color: #d62709;
				color: white;
				padding: 14px 25px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
			}

			a.class2:hover, a.class2:active {
				background-color: red;
			}

			a.class3:link, a.class3:visited {
				background-color: #45993d;
				color: white;
				padding: 14px 25px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
			}

			a.class3:hover, a.class3:active {
				background-color: green;
			}

			a.class4:link, a.class4:visited {
				background-color: #9d9d9e;
				color: white;
				padding: 14px 25px;
				text-align: center;
				text-decoration: none;
				display: inline-block;
			}

			a.class4:hover, a.class4:active {
				background-color: grey;
			}
		</style>
	</head>
	<body >
	<h1>Add glass</h1>
	<?php
	
	$connexion = new mysqli("localhost", "Skyetek", "Skyetek", "rfid");
		if ($connexion->connect_errno) {
			printf("´Echec de la connexion : %s %s",
			$connexion->connect_errno, $connexion->connect_error);
			exit();
		}
	
	
	echo"
	<FORM action='returnAddGlass.php' method='post'>
		Client:
		<SELECT name='name' size='1'>";
	
	$Myquery="Select * FROM client";
	//echo $Myquery;
	$result = $connexion->query($Myquery);
	if(!$result) {
		//echo "la requête ne s’est pas executée</br>";
	} else {
		//echo "la requête s’est bien passée</br>";
		// on va chercher les r´esultats:
		$dataList = $result->fetch_assoc();
		
		
		while($dataList){
			
			echo"<OPTION Value=".$dataList['IdClient'];
			echo">".$dataList['NameClient'];
			//echo $dataList['LocaTable'];
			
			$dataList = $result->fetch_assoc();
		}
	}
	
	echo"
		</SELECT></br>
		Tag:
		<SELECT name='tag' size='1'>";
	$Myquery="Select * FROM rfid";
	//echo $Myquery;
	$result = $connexion->query($Myquery);
	if(!$result) {
		//echo "la requête ne s’est pas executée</br>";
	} else {
		//echo "la requête s’est bien passée</br>";
		// on va chercher les r´esultats:
		$dataList = $result->fetch_assoc();
		
		
		while($dataList){
			
			echo"<OPTION Value=".$dataList['IdRfid'];
			echo">".$dataList['Tag'];
			
			
			$dataList = $result->fetch_assoc();
		}
	}	
		
		
		
		
		echo"</SELECT></br>
		Boisson:
		<SELECT name='boisson' size='1'>
			<OPTION>Eau
			<OPTION>Coca
			<OPTION>Morito
			<OPTION>Vodka
			<OPTION>Rhum
		</SELECT></br>
		<input type='submit' id='ButtonID1' name='valider' value='valider'>
	</FORM>";
	
	


	?>
	<a class="class2" href="index.php">return</a>
	</body>
</html>
		