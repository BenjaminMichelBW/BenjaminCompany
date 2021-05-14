
package objetos;

import java.io.Serializable;

/**
 *
 * @author Benjamin Michel
 * 2021-05-14
 */
public class ReporteCliente implements Serializable {

    private String numeroCliente;
    private String nomCliente;
    private String nomDistribuidor;
    private String nomCiudad;
    private String telContacto;
    private String nomUsuario;
    
    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the numeroCliente
     */
    public String getNumeroCliente() {
        return numeroCliente;
    }
    
    /**
     * @param numeroCliente the numeroCliente to set
     */
    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }
    
    /**
     * @return the nomCliente
     */
    public String getNomCliente() {
        return nomCliente;
    }
    
    /**
     * @param nomCliente the nomCliente to set
     */
    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }
    
    /**
     * @return the nomDistribuidor
     */
    public String getNomDistribuidor() {
        return nomDistribuidor;
    }
    
    /**
     * @param nomDistribuidor the nomDistribuidor to set
     */
    public void setNomDistribuidor(String nomDistribuidor) {
        this.nomDistribuidor = nomDistribuidor;
    }
    
    /**
     * @return the nomCiudad
     */
    public String getNomCiudad() {
        return nomCiudad;
    }
    
    /**
     * @param nomCiudad the nomCiudad to set
     */
    public void setNomCiudad(String nomCiudad) {
        this.nomCiudad = nomCiudad;
    }
    
    /**
     * @return the telContacto
     */
    public String getTelContacto() {
        return telContacto;
    }
    
    /**
     * @param telContacto the telContacto to set
     */
    public void setTelContacto(String telContacto) {
        this.telContacto = telContacto;
    }
    
    /**
     * @return the nomUsuario
     */
    public String getNomUsuario() {
        return nomUsuario;
    }
    
    /**
     * @param nomUsuario the nomUsuario to set
     */
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }
//</editor-fold>
    
}
