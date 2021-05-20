
package objetos;

import java.util.Date;

/**
 *
 * @author Benjamin Michel
 * @since 2021-05-20
 */
public class ActivacionReporte {

    private Date fechaInicio;
    private Date fechaFin;
    private int lada;
    private String nombreDistribuidor;
    private String nombreCliente;
    
    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    
    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }
    
    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    /**
     * @return the lada
     */
    public int getLada() {
        return lada;
    }
    
    /**
     * @param lada the lada to set
     */
    public void setLada(int lada) {
        this.lada = lada;
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
//</editor-fold>
    
}
