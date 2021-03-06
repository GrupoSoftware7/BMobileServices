package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cuenta;
import modelo.Usuario;


public class Registro extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(404);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		}
        catch( Exception x ){
                System.out.println( "No se cargaron los drivers" ); 
        }
        if (validateForm(request) == true) {
            try {

                if (request.getParameter("clave").equals(request.getParameter("repetirClave"))) {
                	
                    Usuario user = new Usuario(request.getParameter("nombre"), request.getParameter("apellidoP"),request.getParameter("apellidoM") ,request.getParameter("correo"));
                    Cuenta cuenta = new Cuenta(request.getParameter("celular"), 0);
                     
                    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bmobilebd","root","root");
        			
        			Statement stmt = con.createStatement();
        			
        			ResultSet rs0= stmt.executeQuery("select max(CodUsuario)+1 as codigo from Usuario;");
        			
        			if(rs0.next()){
        				
        				String num=rs0.getString("codigo");
        				
        				while(num.length()<4){
        					num="0"+num;
        				}
        				
        				int rs1=stmt.executeUpdate("INSERT INTO Usuario (CodUsuario, nom, apePat, apeMat, correo)" +
    							" VALUES ('"+num+"','"+user.getNombre()+"', '"+user.getApellidoP()+"', '"+user.getApellidoM()+"', '"+user.getCorreo()+"');");
        			
        				if(rs1==1){

            				int rs2=stmt.executeUpdate("INSERT INTO Cuenta (NumCel, clave, saldo, CodUsuario)" +
        						" VALUES ('"+cuenta.getCelular()+"', '"+request.getParameter("clave")+"', '"+cuenta.getSaldo()+"','"+num+"' );");
            				
            				if(rs2==1){
            					
    	                        request.setAttribute("resultados", "Usuario registrado");
    	                        
            				}else{
            					
            					stmt.executeUpdate("DELETE FROM Usuario ORDER BY CodUsuario desc LIMIT 1");
            					request.setAttribute("resultados", "Error en el registro");
            					
            				}
    	                } else {
    	                        request.setAttribute("resultados", "Error en el registro");	                        
    	               }
        			
        			}
 
        			
                } else {
                    request.setAttribute("resultados", "Datos incorrectos");
                }

            } catch (Exception ex) {
                request.setAttribute("resultados", "Error en el formulario");
            }
        } else {
            request.setAttribute("resultados", "Ocurrio un error en el registro");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected boolean validateForm(HttpServletRequest request) {
        if (request.getParameterMap().size() >= 8 && request.getParameter("nombre") != null && request.getParameter("apellidoP") != null
        		&& request.getParameter("apellidoM") != null && request.getParameter("celular") != null
        		&& request.getParameter("correo") != null && request.getParameter("clave") != null 
        		&& request.getParameter("repetirClave") != null && request.getParameter("submit") != null) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String getServletInfo (){
        return "Servlet para el registro de usuarios (clientes)";
    }
}
