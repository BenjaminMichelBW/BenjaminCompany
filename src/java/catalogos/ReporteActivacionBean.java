package catalogos;

import controller.HActivacionJpaController;
import entidades.HActivacion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Benjamin Michel
 * @since 2021-05-14
 */
@ManagedBean
@ApplicationScoped
public class ReporteActivacionBean {

    private Date fechaInicio;
    private Date fechaFin;
    private List<HActivacion> listaRepActivacion;

    public ReporteActivacionBean() {
    }

    /**
     * Analiza si fecha inicio es menor que fecha fin.
     * Agrega a fecha fin 23 horas con 59 minutos.
     * Hace la llamada al controller de activacion enviando
     * fecha fin y fecha inicio para obtener un listado
     * entre el rango de fecha seleccionado
     * 
     */
    public void buscarActivaciones() {
        try {
            if (fechaInicio.before(fechaFin) || fechaInicio.equals(fechaFin)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaFin);
                calendar.add(Calendar.HOUR, 23);
                calendar.add(Calendar.MINUTE, 59);
                fechaFin = calendar.getTime();
                
                
                HActivacionJpaController hActivacionJpa = new HActivacionJpaController();
                listaRepActivacion = hActivacionJpa.traerListaRagoFechaPeticion(fechaInicio, fechaFin);
                
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Buscar", "Busqueda exitosa");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Buscar", "La fecha inicio no puede ser mayor que fecha fin");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Buscar", "Se produjo un error");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

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
     * @return the listaRepActivacion
     */
    public List<HActivacion> getListaRepActivacion() {
        return listaRepActivacion;
    }

    /**
     * @param listaRepActivacion the listaRepActivacion to set
     */
    public void setListaRepActivacion(List<HActivacion> listaRepActivacion) {
        this.listaRepActivacion = listaRepActivacion;
    }
//</editor-fold>

}
