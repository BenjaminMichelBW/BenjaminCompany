package objetos;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Benjamin Michel 
 * 2021-04-16
 */
@ManagedBean
@ViewScoped
public class Telefonia implements Serializable {

    private int idTelefonia;
    private String descripcion;
    private String clave;
    private boolean estado;

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the idTelefonia
     */
    public int getIdTelefonia() {
        return idTelefonia;
    }
    
    /**
     * @param idTelefonia the idTelefonia to set
     */
    public void setIdTelefonia(int idTelefonia) {
        this.idTelefonia = idTelefonia;
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
//</editor-fold>

}
