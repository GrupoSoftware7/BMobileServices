package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Calendar;


public class Recargas extends HttpServlet {
    
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
                     
                    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bmobilebd","admin_billetera","a299029");
        			
        			Statement stmt = con.createStatement();
        			
        			Calendar fecha = java.util.Calendar.getInstance();
        			
        			int rs=stmt.executeUpdate("INSERT INTO Transaccion (monTrans, fecTrans, CodTipTrans)" +
							" VALUES ('"+request.getParameter("monto")+"', '"+fecha.get(java.util.Calendar.DATE) + "/" + fecha.get(java.util.Calendar.MONTH) + "/" +
									  + fecha.get(java.util.Calendar.YEAR)+"', '0');");
						
        			if(rs==1){

        				int rs2=stmt.executeUpdate("INSERT INTO Transaccionxcuenta (NumCel, CodTipMov, des)" +
    						" VALUES ('"+request.getParameter("NumCel")+"', '2', '"+request.getParameter("celular")+"');");
        				
        				if(rs2==1){
        					
	                        request.setAttribute("resultados", "Recarga Satisfactoria");
	                        
        				}else{
        					
        					stmt.executeUpdate("DELETE FROM Transaccion ORDER BY CodTrans desc LIMIT 1");
        					request.setAttribute("resultados", "Error en el formulario");
        					
        				}
	                } else {
	                        request.setAttribute("resultados", "Error en el formulario");	                        
	               }

            } catch (Exception ex) {
                request.setAttribute("resultados", "Error en el formulario");
            }
        } else {
            request.setAttribute("resultados", "Ocurrio un error en el formulario");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected boolean validateForm(HttpServletRequest request) {
        if (request.getParameterMap().size() >= 4 && request.getParameter("monto") != null
        		&& request.getParameter("NumCel") != null && request.getParameter("celular") != null
        		&& request.getParameter("submit") != null) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String getServletInfo (){
        return "Servlet para el registro de Recarga de los usuarios (clientes)";
    }
}
