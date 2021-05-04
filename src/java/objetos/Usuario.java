package objetos;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Benjamin michel
 * 2021-04-15
 */

@ManagedBean
@ViewScoped
public class Usuario implements Serializable {
    private int idUsuario;
    private String usuario;
    private String nombreUsuario;
    private String password;
    private String ultimaSesion;

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    
    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the ultimaSesion
     */
    public String getUltimaSesion() {
        return ultimaSesion;
    }
    
    /**
     * @param ultimaSesion the ultimaSesion to set
     */
    public void setUltimaSesion(String ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }
//</editor-fold>
    

}
