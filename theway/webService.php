<?PHP
$hostname_localhost = "localhost";
$database_localhost = "id6029382_theway";
$username_localhost = "id6029382_root";
$password_localhost = "123456789";

$json = array(); 


//Verificar que esten seteados
	 $id = $_GET["id"];
	
	 
	 $conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	 
	 $consulta = "SELECT * FROM edificio WHERE empresaid='{$id}'";	 

	 $resultado = mysqli_query($conexion,$consulta);

	 while ($registro=mysqli_fetch_array($resultado)) {
	 	$result["nombre"]=$registro['nombre'];
	 	$result["descripcion"]=$registro['descripcion'];
	 	$result["descripcion_larga"]=$registro['descripcion_larga'];
	 	$result["imagen"]=base64_encode($registro['imagen']);
	 	$json['edificio'][]=$result;
	 }
	 mysqli_close($conexion);
	 echo json_encode($json);

	 /*foreach ($resultado as $row) {
	 	$json[]=$row;
	 }
	 echo json_encode($json);*/
		 
	/*if ($registro = mysqli_fetch_array($resultado)){
			 $json['edificio'][] = $registro;
			 
	}else{
		 $result["id no inserto"]=0;
		 $json['edificio'][] = $result;
	}

  	mysqli_close($conexion);
	echo json_encode($json); 	 

	}else{
		 $result["id no encontrado"]=0;
		 $json['edificio'][] = $result;
		  echo json_encode($json); 	*/  

?>