<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Registrese en BMobile</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="css/formulario.css" media="all" />
    <link rel="stylesheet" type="text/css" href="css/registro.css" media="all" />
    <link rel="stylesheet" type="text/css" href="css/estilos.css" media="all" />
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp"%>
<div class="container">    
      <div  class="form">
      		<h2>Registro de cuenta</h2>	  
    		<form id="contactform" method="post" action="registro"> 
    			<p class="contact"><label for="name">Nombre</label></p> 
    			<input id="name" name="nombre" placeholder="Escriba su nombre" required="" tabindex="1" type="text"> 
    			
    			<p class="contact"><label for="name">Apellido Paterno</label></p> 
    			<input id="name" name="apellidoP" placeholder="Escriba su apellido paterno" required="" tabindex="1" type="text"> 
    			
    			<p class="contact"><label for="name">Apellido Materno</label></p> 
    			<input id="name" name="apellidoM" placeholder="Escriba su apellido materno" required="" tabindex="1" type="text"> 
    			          
	            <p class="contact"><label for="phone">Numero de Celular</label></p> 
	            <input id="phone" name="celular" placeholder="Escriba Numero de celular" required="" type="text">
    			 
    			<p class="contact"><label for="email">Correo</label></p> 
    			<input id="email" name="correo" placeholder="example@domain.com" required="" type="email"> 
    			 
                <p class="contact"><label for="password">Contraseña</label></p> 
    			<input type="password" id="password" name="clave" required=""> 
                <p class="contact"><label for="repassword">Confirme su contraseña</label></p> 
    			<input type="password" id="repassword" name="repetirClave" required=""> 
				<br>
	            <input class="buttom" name="submit" id="submit" tabindex="5" value="Registrarme!" type="submit"> 	 
   			</form> 
		</div>      
</div>

</body>
</html>
