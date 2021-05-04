package objetos;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Benjamin Michel 
 * 20121-04-16
 */
@ManagedBean
@ViewScoped
public class Distribuidor implements Serializable {

    private int idDistribuidor;
    private String clave;
    private String nombre;
    private boolean estado;
    private int idUsuarioModifica;

    //<editor-fold defaultstate="collapsed" desc="Gets y sets">
    /**
     * @return the idDistribuidor
     */
    public int getIdDistribuidor() {
        return idDistribuidor;
    }
    
    /**
     * @param idDistribuidor the idDistribuidor to set
     */
    public void setIdDistribuidor(int idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }
    
    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }
    
    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }
    
    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
        /**
     * @return the idUsuarioModifica
     */
    public int getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    /**
     * @param idUsuarioModifica the idUsuarioModifica to set
     */
    public void setIdUsuarioModifica(int idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }
//</editor-fold>



}
