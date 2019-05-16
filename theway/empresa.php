<?PHP
$hostname_localhost = "localhost";
$database_localhost = "id6029382_theway";
$username_localhost = "id6029382_root";
$password_localhost = "123456789";

$json = array(); 


//Verificar que esten seteados
 if(isset($_GET["id"]) ){
	 $id = $_GET["id"];
	
	 
	 $conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	 
	 $consulta = "SELECT * FROM empresa";	 

	 $resultado = mysqli_query($conexion,$consulta);
		 
	if ($registro = mysqli_fetch_array($resultado)){
			 $json['empresa'][] = $registro;
			 //echo json_encode($json);
	}else{
		 $result["id"]=0;
		 $json['empresa'][] = $result;
	}

  	mysqli_close($conexion);
	echo json_encode($json); 	 

	}else{
		 $result["id"]=0;
		 $json['empresa'][] = $result;
		  echo json_encode($json); 	  
	  
  }

?>