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
        if (validateForm(request) == false) {
        	out.print("Error iniciando sesion, El formulario enviado no es correcto");
        } else if (request.getSession().getAttribute("intentosLogin") != null && (Integer) request.getSession().getAttribute("intentosLogin") >= 5) {
        	out.print("Inicio de sesión bloqueado, espere 5 min para volver a intentarlo");
            this.starTimer(request.getSession());
        } else {
            try {
            	Usuario usuario = null;
            	Cuenta cuenta = null;
                String celular = request.getParameter("celular");
                String clave = request.getParameter("clave");

                try {
        			Class.forName("com.mysql.jdbc.Driver");
        		}
                catch( Exception x ){
                        System.out.println( "No se cargaron los drivers" ); 
                }
                Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bmobilebd","admin_billetera","a299029");
    			
    			Statement stmt = con.createStatement();
    			ResultSet rs=stmt.executeQuery("Select * from Cuenta where NumCel='"+celular+"' and clave='"+clave+"'");
					
    			if(rs.next()){
    				ResultSet rs2=stmt.executeQuery("Select * from Usuario where CodUsuario='"+rs.getString("CodUsuario")+"'");
    				if(rs2.next()){
    					usuario = new Usuario(rs.getString("nom"), rs.getString("apePat"), rs.getString("apeMat"), rs.getString("correo"));
    					cuenta = new Cuenta(rs2.getString("NumCel"), Double.parseDouble(rs2.getString("saldo")));
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
                        out.print("CORRECTO");
                    }
                    request.getSession().removeAttribute("intentosLogin");
                    return;
                //Contraseña incorrecta
                } else {
                    	out.print("Disculpe, la combinacion de usuario y contraseña es incorrecta");
                }
                this.incrementarIntentos(request.getSession());
                out.println("Error inciando sesion");
            } catch (Exception ex) {
            	out.print("Intrusión detectada");
            }
        }
    }

    protected boolean validateForm(HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        if (param.size() == 2 && param.containsKey("celular") && param.containsKey("clave")) {
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
