
package catalogos;

import controller.SAccesosJpaController;
import entidades.SAccesos;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Benjamin Michel
 * 2021-05-07
 */
@ManagedBean
@ApplicationScoped
public class AccesosBean {

    private List<SAccesos> listaAccesos;
    private SAccesos accesos;
    SAccesos seleccionAccesos;

    public AccesosBean() {
        cargarDatosAccesos();
        accesos = new SAccesos();
    }

    /**
     * Carga datos a la tabla accesos
     *
     */
    public void cargarDatosAccesos() {
        try {
            SAccesosJpaController sAccesosJpa = new SAccesosJpaController();
            listaAccesos = sAccesosJpa.findSAccesosEntities();
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Evento para seleccionar de la tabla accesos
     *
     * @param event
     */
    public void onRowSelect(SelectEvent<SAccesos> event) {
        try {
            accesos = event.getObject();

        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpia los controles del panel de accesos
     *
     */
    public void nuevoAcceso() {
        try {
            accesos = new SAccesos();
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Guarda un nuevo acceso o actualiza
     * 
     */
    public void guardarAccesos() {
        SAccesosJpaController sAccesosJpa = new SAccesosJpaController();
        
        Date fechaActual = new Date();
        accesos.setActivo(true);
        accesos.setFechaServidor(fechaActual);

        try {
            if (accesos.getIdAcceso() == null) {
                sAccesosJpa.create(accesos);

            } else {
                
                sAccesosJpa.edit(accesos);
            }
            nuevoAcceso();
            cargarDatosAccesos();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", "Se guardó correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", "Se produjo un error");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
    /**
     * elimina un acceso
     * 
     */
    public void eliminarAcceso(){
        SAccesosJpaController sAccesosJpa = new SAccesosJpaController();
        try{
            sAccesosJpa.destroy(accesos.getIdAcceso());
            nuevoAcceso();
            cargarDatosAccesos();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminar", "Se eliminó correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminar", "Se produjo un error");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the listaAccesos
     */
    public List<SAccesos> getListaAccesos() {
        return listaAccesos;
    }

    /**
     * @param listaAccesos the listaAccesos to set
     */
    public void setListaAccesos(List<SAccesos> listaAccesos) {
        this.listaAccesos = listaAccesos;
    }

    /**
     * @return the accesos
     */
    public SAccesos getAccesos() {
        return accesos;
    }

    /**
     * @param accesos the accesos to set
     */
    public void setAccesos(SAccesos accesos) {
        this.accesos = accesos;
    }

    /**
     * @return the seleccionAccesos
     */
    public SAccesos getSeleccionAccesos() {
        return seleccionAccesos;
    }

    /**
     * @param seleccionAccesos the seleccionAccesos to set
     */
    public void setSeleccionAccesos(SAccesos seleccionAccesos) {
        this.seleccionAccesos = seleccionAccesos;
    }

    
//</editor-fold>

}
