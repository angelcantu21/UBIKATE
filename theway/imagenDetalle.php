<?PHP
$hostname_localhost = "localhost";
$database_localhost = "id6029382_theway";
$username_localhost = "id6029382_root";
$password_localhost = "123456789";

$json = array(); 


//Verificar que esten seteados
	$nombre = $_GET["nombre"];
	
	 
	 $conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	 
	 $consulta = "SELECT latitud, longitud, imagen FROM edificio WHERE nombre='{$nombre}'";	 

	 $resultado = mysqli_query($conexion,$consulta);

	 while ($registro=mysqli_fetch_array($resultado)) {
	 	$result["latitud"]=$registro['latitud'];
	 	$result["longitud"]=$registro['longitud'];
	 	$result["imagen"]=base64_encode($registro['imagen']);
	 	$json['edificio'][]=$result;
	 }
	 mysqli_close($conexion);
	 echo json_encode($json); 

?>