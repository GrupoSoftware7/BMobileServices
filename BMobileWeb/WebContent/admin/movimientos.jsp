<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Vector" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimientos del usuario</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" type="text/css" href="/bmobile/css/tabla.css" media="all" />
	<link rel="stylesheet" type="text/css" href="/bmobile/css/estilos.css" media="all" />
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp"%>
<%@include file="/WEB-INF/include/resultados.jsp" %>
<%  
	Vector<String> nomTip = (Vector)request.getAttribute("nomTip");
	Vector<Float> total = (Vector)request.getAttribute("total");
	
	float sumT = 0;
	
	for(int i=0;i<total.size();i++){
		sumT+=total.get(i);
	}
	
%>
<div class="contenedor">    
          <h2>Movimientos del usuario</h2>
          <div class="tabla">
			<table >
				<tr> 
					<td>
						Tipo de movimiento
					</td>
					<td >
						Total
					</td>
					<td >
						Porcentaje
					</td>
				</tr>
				<%for(int i=0;i<total.size();i++){ %>
				<tr>
					<td >
						<%=nomTip.get(i) %>
					</td>
					<td>
						<%=total.get(i) %>
					</td>
					<td>
						<%=(total.get(i)*100/(sumT))+"%" %>
					</td>
				</tr>
				<%} %>
			</table>
		</div>
</div>
</body>
</html>