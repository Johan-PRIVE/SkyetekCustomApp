<html>
	
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="images/favicon.ico">
		<title>Table</title>
		
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


<?php

$connexion = new mysqli("localhost", "root", "", "rfid");
		if ($connexion->connect_errno) {
			printf("´Echec de la connexion : %s %s",
			$connexion->connect_errno, $connexion->connect_error);
			exit();
		}
		
		
		
		$Myquery="UPDATE fonctions SET Dothat = 'add'";
		//echo $Myquery;
		$result = $connexion->query($Myquery);
		if(!$result)
		{
			echo "error";
		}
		else
		{
			echo "<h1> present your tag </h1>";
		}
	
		$Myquery="Select * FROM fonctions";
		//echo $Myquery;
		$result = $connexion->query($Myquery);
		if($result)
		{
			$dataList = $result->fetch_assoc();
			echo "fonction en cours : ".$dataList['Dothat']."</br>";
		}
		
	
?>

<a class="class2" href="index.php">return</a>

</body>
</html>
		