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

import java.util.Calendar;


public class Compras extends HttpServlet {
    
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
                     
                    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bmobilebd","root","root");
        			
        			Statement stmt = con.createStatement();
        			
        			Calendar fecha = java.util.Calendar.getInstance();
        			
        			ResultSet rs0= stmt.executeQuery("select max(CodTrans)+1 as codigo from Transaccion;");
        			
        			if(rs0.next()){
        				
        				String num=rs0.getString("codigo");
        				
        				while(num.length()<8){
        					num="0"+num;
        				}
        				
	        			int rs=stmt.executeUpdate("INSERT INTO Transaccion (CodTrans, monTrans, fecTrans, CodTipTrans)" +
								" VALUES ('"+num+"','"+request.getParameter("monto")+"', '"+fecha.get(java.util.Calendar.YEAR)+"-"+
								+ fecha.get(java.util.Calendar.MONTH) +"-"+ fecha.get(java.util.Calendar.DATE) +"', '"+request.getParameter("tipoT")+"');");
							
	        			if(rs==1){
	
	        				int rs2=stmt.executeUpdate("INSERT INTO Transaccionxcuenta (CodTrans, NumCel, CodTipMov, des)" +
	    						" VALUES ('"+num+"','"+request.getParameter("NumCel")+"', '"+request.getParameter("tipo")+"', '"+request.getParameter("celular")+"');");
	        				
	        				if(rs2==1){
	        					
		                        request.setAttribute("resultados", "Compra Satisfactoria");
		                        
	        				}else{
	        					
	        					stmt.executeUpdate("DELETE FROM Transaccion ORDER BY CodTrans desc LIMIT 1");
	        					request.setAttribute("resultados", "Error en el registro");
	        					
	        				}
		                } else {
		                        request.setAttribute("resultados", "Error en el registro");	                        
		                }
        		    }
            } catch (Exception ex) {
                request.setAttribute("resultados", "Error en el formulario");
            }
        } else {
            request.setAttribute("resultados", "Ocurrio un error en el registro");
        }
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }

    protected boolean validateForm(HttpServletRequest request) {
        if (request.getParameterMap().size() >= 5 && request.getParameter("monto") != null && request.getParameter("tipo") != null
        		&& request.getParameter("NumCel") != null && request.getParameter("celular") != null
        		&& request.getParameter("submit") != null) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String getServletInfo (){
        return "Servlet para el registro de compra de los usuarios (clientes)";
    }
}
