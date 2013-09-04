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
			<a href="tarifas.jsp">TARIFAS</a>
		</li>
		<li>
			<a href="contactenos.jsp">CONTÁCTENOS</a>		
		</li>
		<% if (auth != null && auth == true){
	   	actualUser = (Usuario)session.getAttribute("usuario");
	   	actualCuenta = (Cuenta)session.getAttribute("cuenta");
	   	%>
	   	<li>
			<a href="admin/user.jsp"><%=actualUser.getNombre() %></a>
			<ul>
				<li>
					<a href="admin/compras.jsp">Compras</a>
				</li>
				<li>
					<a href="admin/recargas.jsp">Recargas</a>				
				</li>
				<li>
					<a href="admin/movimientos">Movimientos Realizados</a>
					<ul>
						<li>
							<a href="admin/movAlimentos">Alimento</a>				
						</li>
						<li>
							<a href="admin/movVestimenta">Vestimenta</a>				
						</li>
						<li>
							<a href="admin/movEscolar">Escolar</a>				
						</li>
						<li>
							<a href="admin/movOcio">Ocio</a>
						</li>
					</ul>				
				</li>
				<li>
					<a href="logout">Cerrar Sesión</a>
				</li>
			</ul>
		</li>
		<%}else{%>
		<li>
			<a href="registro.jsp">REGÍSTRESE</a>
		</li>
		<li><a href="login.jsp">INICIAR SESIÓN</a></li>
		<%} %>
	</ul>
</nav>
