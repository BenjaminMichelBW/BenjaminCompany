
package objetos;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Benjamin Michel 2021-04-23
 */
@ManagedBean
@ViewScoped
public class Cliente implements Serializable {

    private int idCliente;
    private String numCliente;
    private String nombreCliente;
    private int idDistribuidor;
    private String nombreDistribuidor;
    private int idCiudad;
    private String descripcionCiudad;
    private String telContacto;
    private String rfc;
    private String calle;
    private String numExt;
    private String colonia;
    private String cp;
    private Boolean estado;
    private int idUsuarioModifica;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets">
    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the numCliente
     */
    public String getNumCliente() {
        return numCliente;
    }

    /**
     * @param numCliente the numCliente to set
     */
    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

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
     * @return the nombreDistribuidor
     */
    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    /**
     * @param nombreDistribuidor the nombreDistribuidor to set
     */
    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

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
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the numExt
     */
    public String getNumExt() {
        return numExt;
    }

    /**
     * @param numExt the numExt to set
     */
    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * @return the estado
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Boolean estado) {
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
