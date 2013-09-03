package control;

import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Contactenos extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validateParam(request) == true) {
            try {
                String correo = request.getParameter("correo");
                String comentario = request.getParameter("comentario");

                boolean mail = enviarMail(request, correo, comentario);
                    if (mail == false){
                        request.setAttribute("resultados", "Ocurrió un error");
                    }else{
                        request.setAttribute("resultados", "Su mensaje fue enviado");
                    }              
            } catch (Exception ex) {
                request.setAttribute("resultados", "Intrusión detectada");
            }finally {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("resultados", "Formulario no valido");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected boolean enviarMail(HttpServletRequest request, String correo, String comentario) {
        EnviarCorreo mailConfig = (EnviarCorreo) request.getServletContext().getAttribute("EmailSend");
        Session mailSession = mailConfig.startSession((Authenticator) request.getServletContext().getAttribute("autorizacionMail"));
        MimeMessage mensaje = mailConfig.newMail("Contáctenos", correo, comentario, mailSession);
        if (mensaje == null) {
            request.setAttribute("resultados", "Error enviando mensaje");
            return false;
        } else {
            boolean ok = mailConfig.sendEmail(mensaje, mailSession);

            if (ok == true) {
                request.setAttribute("resultados", "Mensaje enviado correctamente");
                return true;
            } else {
                request.setAttribute("resultados", "Error enviando mensaje");
                return false;
            }
        }
    }

    protected boolean validateParam(HttpServletRequest request) {
        if (request.getParameter("correo") != null && request.getParameter("comentario") != null && request.getParameter("enviar") != null ) {
            return true;
        }
        return false;
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
    public String getServletInfo (){
        return "Servlet para la restauraciÃ³n de contraseÃ±as perdidas";
    }
}
