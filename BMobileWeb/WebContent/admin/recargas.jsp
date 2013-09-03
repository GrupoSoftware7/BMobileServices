<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Recargas en BMobile</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="../css/formulario.css" media="all" />
    <link rel="stylesheet" type="text/css" href="../css/registro.css" media="all" />
    <link rel="stylesheet" type="text/css" href="../css/estilos.css" media="all" />
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp"%>
<div class="container">    
      <div  class="form">
      		<h2>Recargas</h2>  
    		<form id="contactform" method="post" action="Recargas">   			          
	            <p class="contact"><label for="phone">Numero de Celular a Recargar</label></p> 
	            <input id="phone" name="celular" placeholder="Escriba Numero de celular" required="" type="text">
    			 
    			<p class="contact"><label for="monto">Monto (soles)</label></p> 
    			<input id="email" name="correo" placeholder="Escriba Monto en soles" required="" type="email"> 
    			 
				<br>
				<br>
	            <input class="buttom" name="submit" id="submit" tabindex="5" value="Recargar!" type="submit"> 	 
   			</form> 
		</div>      
</div>

</body>
</html>
