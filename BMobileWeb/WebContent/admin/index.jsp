<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio de usuario</title>
<link rel="stylesheet" type="text/css" href="css/estilos.css" media="all" />
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp"%>
<div class="contenedor">
<p>Bienvenido </p><label><%=actualUser.getNombre() %></label>
<p>Saldo </p><label><%=actualCuenta.getSaldo() %></label>
</div>
</body>
</html>