package modelo;

import java.io.Serializable;

public class Cuenta implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String celular;
    private double saldo;

    public Cuenta (){
    }

    public Cuenta(String celular, double saldo) {
        this.celular = celular;
        this.saldo = saldo;
    }

    
    public String getCelular() {
        return celular;
    }
    
    public double getSaldo() {
        return saldo;
    }
}
