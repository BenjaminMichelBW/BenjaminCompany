
package objetos;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Benjamin Michel
 * 2021-04-23
 */
@ManagedBean
@ViewScoped
public class Ciudad implements Serializable {

    private int idCiudad;
    private String descripcionCiudad;
    
    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the idCiudad
     */
    public int getIdCiudad() {
        return idCiudad;
    }
    
    /**
     * @param idCiudad the idCiudad to set
     */
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    /**
     * @return the descripcionCiudad
     */
    public String getDescripcionCiudad() {
        return descripcionCiudad;
    }
    
    /**
     * @param descripcionCiudad the descripcionCiudad to set
     */
    public void setDescripcionCiudad(String descripcionCiudad) {
        this.descripcionCiudad = descripcionCiudad;
    }
//</editor-fold>
    
}
