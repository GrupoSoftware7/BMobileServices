<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio de usuario</title>
<link rel="stylesheet" type="text/css" href="/bmobile/css/estilos.css" media="all" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp"%>
<%@include file="/WEB-INF/include/resultados.jsp" %>
<div class="contenedor">
<p>Bienvenido <label><%=actualUser.getNombre() %></label></p>
<p>Saldo <label><%=actualCuenta.getSaldo() %></label></p>
</div>
</body>
</html>