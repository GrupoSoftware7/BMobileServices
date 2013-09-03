<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Tarifas de BMobile</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" type="text/css" href="css/estilos.css" media="all" />
	<link rel="stylesheet" type="text/css" href="css/contacto.css" media="all" />
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp"%>
<div class="contenedor">    
          <h2>Si tiene alguna duda, consulta o sugerencia</h2>
          <form class="contact_form" action="contactenos" method="post">
		    <ul>
		        <li>
		            <label for="email">Correo:</label>
		            <input type="email" name="correo" placeholder="jricardo@ejemplo.com" required />
		        </li>
		        <li>
		            <label for="Mensaje">Comentario:</label>
		            <textarea name="Mensaje" cols="40" rows="6" required ></textarea>
		        </li>
		        <li>
		        	<button class="submit" type="submit">Enviar</button>
		        </li>
		    </ul>
		</form>
</div>

</body>
</html>
