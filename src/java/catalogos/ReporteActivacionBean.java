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
import objetos.ActivacionReporte;

/**
 *
 * @author Benjamin Michel
 * @since 2021-05-14
 */
@ManagedBean
@ApplicationScoped
public class ReporteActivacionBean {

    private ActivacionReporte activacionRep;
    private List<HActivacion> listaRepActivacion;

    public ReporteActivacionBean() {
        activacionRep = new ActivacionReporte();
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
            if (activacionRep.getFechaInicio().before(activacionRep.getFechaFin()) || activacionRep.getFechaInicio().equals(activacionRep.getFechaFin())) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(activacionRep.getFechaFin());
                calendar.add(Calendar.HOUR, 23);
                calendar.add(Calendar.MINUTE, 59);
                activacionRep.setFechaFin(calendar.getTime());
                
                
                HActivacionJpaController hActivacionJpa = new HActivacionJpaController();
                listaRepActivacion = hActivacionJpa.traerListaRagoFechaPeticion(activacionRep);
                
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
    
        /**
     * @return the activacionRep
     */
    public ActivacionReporte getActivacionRep() {
        return activacionRep;
    }

    /**
     * @param activacionRep the activacionRep to set
     */
    public void setActivacionRep(ActivacionReporte activacionRep) {
        this.activacionRep = activacionRep;
    }
//</editor-fold>

}
