package modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;

    public Usuario (){
    }

    public Usuario(String nombre, String apellidoP, String apellidoM, String correo) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;

    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellidoP() {
        return apellidoP;
    }
    
    public String getApellidoM() {
        return apellidoM;
    }

    public String getCorreo() {
        return correo;
    }
}
