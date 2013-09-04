package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Vector;


public class Movimientos extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(404);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
        			
        			String codUser = request.getParameter("codUser");
        			String opcion = request.getParameter("ver");
        			
        			if(opcion.equalsIgnoreCase("todo")){        				
        				
	        			ResultSet rs0= stmt.executeQuery("select TM.nomTip, sum(T.monTrans) as total from TransaccionXCuenta TC"+
						" inner join Transaccion T on TC.CodTrans=T.CodTrans inner join TipoMovimiento TM on TC.CodTipMov=TM.CodTipMov "+
						" group by TC.CodTipMov, TC.NumCel having TC.NumCel='"+codUser+"';");
	        			
	        			Vector<String> nomTip = new Vector<>();
	        			Vector<Float> total = new Vector<>();
	        			
	        			while(rs0.next()){
	        				
	        				nomTip.add(rs0.getString("nomTip"));
	        				total.add(rs0.getFloat("total"));
	        			
	        			}

	        			request.setAttribute("nomTip", nomTip);	
	        			request.setAttribute("total", total);
	        			stmt.close();
        		    }
            } catch (Exception ex) {
            	System.out.print(ex.getMessage());
                request.setAttribute("resultados", "Error en el formulario");
            }
        } else {
            request.setAttribute("resultados", "Ocurrio un error en el registro");
        }
        RequestDispatcher rd = 
				getServletContext().getRequestDispatcher("/admin/movimientos.jsp");
		rd.forward(request, response);			
    }

    protected boolean validateForm(HttpServletRequest request) {
        if (request.getParameterMap().size() >= 2 && request.getParameter("codUser") != null && request.getParameter("ver") != null) {
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
