<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Inicie Sesión en BMobile</title>
	
	<!-- Stylesheets -->
	<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'>
	<link rel="stylesheet" href="css/estilos.css">
	<link rel="stylesheet" href="css/login.css">

	<!-- Optimize for mobile devices -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>  
</head>
<body>
	<%@include file="/WEB-INF/include/menu.jsp"%>
	<!-- MAIN CONTENT -->
	<div id="content">
		
		<form action="login" method="POST" id="login-form">
		<h2>Iniciar Sesión</h2>
			<fieldset>

				<p>
					<label for="login-username">N° Celular</label>
					<input type="text" id="login-username" class="round full-width-input" autofocus />
				</p>

				<p>
					<label for="login-password">Contraseña</label>
					<input type="password" id="login-password" class="round full-width-input" />
				</p>
				
				<p><a href="#">Recuperar su contraseña</a></p>
				
				<input type="submit" class="button round blue image-right ic-right-arrow" value="Ingresar" />

			</fieldset>

			<br/><div class="information-box round">Simplemente haga clic en el botón "Ingresar" para continuar.</div>

		</form>
		
	</div> <!-- end content -->

</body>
</html>