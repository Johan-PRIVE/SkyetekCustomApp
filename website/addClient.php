<html>
	
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="images/favicon.ico">
		<title>AddClient</title>
		
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
		<h1>Add client</h1>
		<?php
		
		echo '
			<form action="returnAddClient.php" method="post">
				<label id="1">Name client:</label>
				<input type="text" id="name" name="name" required>	
				</br>
				<label id="2">Table</label>
				<input type="text" id="table" name="table" required>	
				</br>
				<input type="submit" id="ButtonID1" name="valider" value="valider">
			</form>
			
			';
		
		?>
		
		<a class="class2" href="index.php">return</a>
	</body>
</html>
		