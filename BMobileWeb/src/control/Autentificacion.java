package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Cuenta;
import modelo.Usuario;

public class Autentificacion extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	 try {
 			Class.forName("com.mysql.jdbc.Driver");
 		}
         catch( Exception x ){
                 System.out.println( "No se cargaron los drivers" ); 
         }
        if (validateForm(request) == false) {
        	request.setAttribute("resultados", "Error iniciando sesion, El formulario enviado no es correcto");	
        } else if (request.getSession().getAttribute("intentosLogin") != null && (Integer) request.getSession().getAttribute("intentosLogin") >= 5) {
        	request.setAttribute("resultados", "Inicio de sesión bloqueado, espere 5 min para volver a intentarlo");	
            this.starTimer(request.getSession());
        } else {
            try {
            	Usuario usuario = null;
            	Cuenta cuenta = null;
                String celular = request.getParameter("celular");
                String clave = request.getParameter("clave");

                Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bmobilebd","root","root");
    			
    			Statement stmt = con.createStatement();
    			
    			ResultSet rs=stmt.executeQuery("Select * from Cuenta where NumCel='"+celular+"' and clave='"+clave+"'");
					
    			if(rs.next()){
    							
    				String CodUsuario = rs.getString("CodUsuario");
    				String saldo = rs.getString("saldo");
					
					cuenta = new Cuenta(celular, Float.parseFloat(saldo));
		
    				ResultSet rs2=stmt.executeQuery("Select * from Usuario where CodUsuario='"+CodUsuario+"'");
    				
    				if(rs2.next()){
    					
        				String nombre = rs2.getString("nom");
        				String apePat = rs2.getString("apePat");
        				String apeMat = rs2.getString("apeMat");
        				String correo = rs2.getString("correo");
        				
        				usuario = new Usuario(nombre, apePat, apeMat, correo);	
    					
    				}else{
    					stmt.executeUpdate("DELETE FROM Cuenta ORDER BY CodUsuario desc LIMIT 1");
    				}
    				
                    request.getSession().setAttribute("auth", true);
                    request.getSession().setAttribute("usuario", usuario);
                    request.getSession().setAttribute("cuenta", cuenta);

                    if (request.getSession().getAttribute("requestedPage") != null) {
                            String redirect = (String) request.getSession().getAttribute("requestedPage");
                            request.getSession().removeAttribute("requestedPage");
                            response.sendRedirect(redirect);
                    } else {
                    	request.setAttribute("resultados", "Sesion correcta");	
                    }
                    request.getSession().removeAttribute("intentosLogin");
                    request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
                    return;
                //Contraseña incorrecta
                } else {
                	request.setAttribute("resultados", "Disculpe, la combinacion de usuario y contraseña es incorrecta");	
                }
                this.incrementarIntentos(request.getSession());
                request.setAttribute("resultados", "Error inciando sesion");
                request.getRequestDispatcher("admin/index.jsp").forward(request, response);
            } catch (Exception ex) {
            	request.setAttribute("resultados", "Intrusión detectada");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

    protected boolean validateForm(HttpServletRequest request) {
        if (request.getParameterMap().size() >= 3 && request.getParameter("celular") != null && request.getParameter("clave") != null && request.getParameter("submit") != null) {
            return true;
        } else {
            return false;
        }
    }

    protected void starTimer(final HttpSession sesion) {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                sesion.invalidate();
            }
        };

        Timer timer = new Timer();
        //10 minutos ---> 600.000 milisegundos
        timer.schedule(timerTask, 600000);
    }

    protected void incrementarIntentos(HttpSession sesion) {
        if (sesion.getAttribute("intentosLogin") == null) {
            sesion.setAttribute("intentosLogin", 1);
        } else {
            sesion.setAttribute("intentosLogin", (Integer) sesion.getAttribute("intentosLogin") + 1);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para la autentificación de usuarios";
    }
}
