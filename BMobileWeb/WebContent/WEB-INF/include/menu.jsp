<%@page import="modelo.Usuario"%>
<%@page import="modelo.Cuenta"%>
<% Boolean auth = (Boolean) session.getAttribute("auth");
   Usuario actualUser = null;
   Cuenta actualCuenta = null;
%>
<header>
	<img src="/bmobile/imagenes/bmobile.jpg" />
</header> 
<nav id="menu-wrap">  
	<ul id="menu">
		<li>
			<a href="/bmobile">INICIO</a>
			<ul>
				<li>
					<a href="">Nosotros</a>				
				</li>
				<li>
					<a href="">BMobile Android</a>				
				</li>
				<li>
					<a href="">BMobile Web</a>				
				</li>
				<li>
					<a href="">Beneficios</a>
				</li>
				<li>
					<a href="">Terminos y Condiciones</a>
				</li>
			</ul>
		</li>
		<li>
			<a href="/bmobile/tarifas.jsp">TARIFAS</a>
		</li>
		<li>
			<a href="/bmobile/contactenos.jsp">CONTÁCTENOS</a>		
		</li>
		<% if (auth != null && auth == true){
	   	actualUser = (Usuario)session.getAttribute("usuario");
	   	actualCuenta = (Cuenta)session.getAttribute("cuenta");
	   	%>
	   	<li>
			<a href="/bmobile/admin/index.jsp"><%=actualUser.getNombre() %></a>
			<ul>
				<li>
					<a href="/bmobile/admin/compras.jsp">Compras</a>
				</li>
				<li>
					<a href="/bmobile/admin/recargas.jsp">Recargas</a>				
				</li>
				<li>
					<a href="/bmobile/admin/movimientos?codUser=<%=actualCuenta.getCelular() %>&ver=todo">Movimientos Realizados</a>
					<ul>
						<li>
							<a href="/bmobile/admin/movimientos?codUser=<%=actualCuenta.getCelular() %>&ver=alim">Comida</a>				
						</li>
						<li>
							<a href="/bmobile/admin/movimientos?codUser=<%=actualCuenta.getCelular() %>&ver=vest">Vestimenta</a>				
						</li>
						<li>
							<a href="/bmobile/admin/movimientos?codUser=<%=actualCuenta.getCelular() %>&ver=esco">Escolar</a>				
						</li>
						<li>
							<a href="/bmobile/admin/movimientos?codUser=<%=actualCuenta.getCelular() %>&ver=ocio">Ocio</a>
						</li>
					</ul>				
				</li>
				<li>
					<a href="/bmobile/logout">Cerrar Sesión</a>
				</li>
			</ul>
		</li>
		<%}else{%>
		<li>
			<a href="/bmobile/registro.jsp">REGÍSTRESE</a>
		</li>
		<li><a href="/bmobile/login.jsp">INICIAR SESIÓN</a></li>
		<%} %>
	</ul>
</nav>