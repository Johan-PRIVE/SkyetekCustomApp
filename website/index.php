<html>
	<meta http-equiv="refresh" content="2">
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="images/favicon.ico">
		<title>Bar Skyetek</title>
		
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
		
	<!--base = Skyetek rfid
	<form action="login.php" method="get">
		<p>username : <input type="text" name="username" /></p>
		<p>password : <input type="text" name="password" /></p>
		<p>BDD : <input type="text" name="BDD" /></p>
		<p><input type="submit" value="OK"></p>
	</form>
	-->
	
	<a class="class1" href="addClient.php">Add Client</a>
	<a class="class1" href="addTag.php">Add Tag</a>
	<a class="class3" href="addGlass.php">Add Glass</a>
	<a class="class3" href="remplirVerre.php">fill Glass</a>
	<!--<a class="class4" href="modifyDrink.php">Modify Drink</a>-->
	<a class="class2" href="removeDrink.php">Delete Drink</a>
	
	</br></br></br>
	
	
	<?php
	$connexion = new mysqli("localhost", "Skyetek", "Skyetek", "rfid");
		if ($connexion->connect_errno) {
			printf("´Echec de la connexion : %s %s",
			$connexion->connect_errno, $connexion->connect_error);
			exit();
		}
		
	$Myquery="UPDATE fonctions SET Dothat = 'select'";
		//echo $Myquery;
		$result = $connexion->query($Myquery);
		if(!$result)
		{
			echo "error";
		}
	
	

	$Myquery="Select * FROM client, consommation, rfid WHERE consommation.ClientTarget=client.IdClient AND consommation.TagRfid=rfid.IdRfid";
	//echo $Myquery;
	$result = $connexion->query($Myquery);
	
	
	echo '<table>';
	echo '<tr><td>ID Tag</td><td>Client</td><td>Boisson</td><td>table</td><td>etat</td></tr>';
	if(!$result) {
		echo "la requête ne s’est pas executée</br>";
	} else {
		//echo "la requête s’est bien passée</br>";
		// on va chercher les r´esultats:
		$dataList = $result->fetch_assoc();
		
		
		while($dataList){

			echo"<tr>";
			echo"<td>".$dataList['Tag']."</td>";
			echo"<td>".$dataList['NameClient']."</td>";
			echo"<td>".$dataList['Boisson']."</td>";
			echo"<td>".$dataList['LocaTable']."</td>";
			
			if($dataList['Etat']=="0")
			{echo"<td><p style='color:red;'>Vide</p></td>";}
			else
			{echo"<td><p style='color:green;'>OK</p></td>";}
			
			echo"</tr>";

			
			$dataList = $result->fetch_assoc();
		}
	}
	echo '</table>';
		
		
		
		$Myquery="Select * FROM fonctions";
		//echo $Myquery;
		$result = $connexion->query($Myquery);
		if($result)
		{
			$dataList = $result->fetch_assoc();
			echo "fonction en cours : ".$dataList['Dothat'];
		}
		
		$connexion->close();
	?>
</body>
</html>
		

<!--
page d'accueil
Main page. Montrer les verres enregistrés



//IdClient NameClient LocaTable
INSERT INTO client VALUES
(1,'test',1),
(2,'Aoutin',1),
(3,'Guillier',2),
(4,'Saint-Andre',3),
(5,'Huguet',4),
(6,'Boudet',3);

//IdRfid Tag
INSERT INTO rfid VALUES
(1,'000000000000610121045315'),
(2,'000000000000001');


//IdVerre	Boisson	TagRfid	ClientTarget	Etat
INSERT INTO consommation VALUES
(1,'Biere',1,1,1);

INSERT INTO consommation VALUES
(1,'Martini',2,1,0);

/*




-->