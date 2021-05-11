/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import controller.SAccesosJpaController;
import controller.SPerfilesJpaController;
import entidades.SAccesos;
import entidades.SPerfiles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import utils.TraeDatoSesion;

/**
 *
 * @author Benjamin Michel 2021-05-07
 */
@ManagedBean
@ApplicationScoped
public class PerfilesBean {

    private List<SPerfiles> listaPerfiles;
    private SPerfiles perfiles;
    private SPerfiles seleccionPerfiles;
    

    private DualListModel<SAccesos> plAccesos;
    private List<SAccesos> listaAccesosDisponibles;
    private List<SAccesos> listaAccesosActuales;
    
    public PerfilesBean() {
        cargarDatosPerfiles();
        plCargarPerfilesAccesos();
        perfiles = new SPerfiles();
        
    }

    public void plCargarPerfilesAccesos() {
        try {
            SAccesosJpaController modelo = new SAccesosJpaController();
            listaAccesosDisponibles = modelo.findSAccesosEntities();
            listaAccesosActuales = new ArrayList<SAccesos>();
            plAccesos = new DualListModel<SAccesos>(listaAccesosDisponibles, listaAccesosActuales);
            
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarDatosPerfiles() {
        try {
            SPerfilesJpaController modelo = new SPerfilesJpaController();
            listaPerfiles = modelo.findSPerfilesEntities();
            
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRowSelect(SelectEvent<SPerfiles> event) {
        try {
            perfiles = event.getObject();
            plCargarPerfilesAccesos();
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nuevoPerfil() {
        try {
            perfiles = new SPerfiles();
            plCargarPerfilesAccesos();
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPerfiles() {
        SPerfilesJpaController modelo = new SPerfilesJpaController();
        
        Date fechaActual = new Date();
        int usuarioSesion = TraeDatoSesion.traerIdUsuario();
        
        perfiles.setFechaAlta(fechaActual);
        perfiles.setFechaServidor(fechaActual);
        perfiles.setActivo(true);
        perfiles.setIdUsuarioModifica(usuarioSesion);

        try {
            if (perfiles.getIdPerfil() == null) {
                modelo.create(perfiles);
            } else {
                
                modelo.edit(perfiles);
            }
            nuevoPerfil();
            cargarDatosPerfiles();
            plCargarPerfilesAccesos();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", "Se guardó correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarPerfil() {
        SPerfilesJpaController modelo = new SPerfilesJpaController();
        try {
            modelo.destroy(perfiles.getIdPerfil());
            nuevoPerfil();
            cargarDatosPerfiles();
            plCargarPerfilesAccesos();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", "Se guardó correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the listaPerfiles
     */
    public List<SPerfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    /**
     * @param listaPerfiles the listaPerfiles to set
     */
    public void setListaPerfiles(List<SPerfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    /**
     * @return the perfiles
     */
    public SPerfiles getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(SPerfiles perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the seleccionPerfiles
     */
    public SPerfiles getSeleccionPerfiles() {
        return seleccionPerfiles;
    }

    /**
     * @param seleccionPerfiles the seleccionPerfiles to set
     */
    public void setSeleccionPerfiles(SPerfiles seleccionPerfiles) {
        this.seleccionPerfiles = seleccionPerfiles;
    }

    /**
     * @return the plAccesos
     */
    public DualListModel<SAccesos> getPlAccesos() {
        return plAccesos;
    }

    /**
     * @param plAccesos the plAccesos to set
     */
    public void setPlAccesos(DualListModel<SAccesos> plAccesos) {
        this.plAccesos = plAccesos;
    }
    
    /**
     * @return the listaAccesosDisponibles
     */
    public List<SAccesos> getListaAccesosDisponibles() {
        return listaAccesosDisponibles;
    }

    /**
     * @param listaAccesosDisponibles the listaAccesosDisponibles to set
     */
    public void setListaAccesosDisponibles(List<SAccesos> listaAccesosDisponibles) {
        this.listaAccesosDisponibles = listaAccesosDisponibles;
    }

    /**
     * @return the listaAccesosActuales
     */
    public List<SAccesos> getListaAccesosActuales() {
        return listaAccesosActuales;
    }

    /**
     * @param listaAccesosActuales the listaAccesosActuales to set
     */
    public void setListaAccesosActuales(List<SAccesos> listaAccesosActuales) {
        this.listaAccesosActuales = listaAccesosActuales;
    }
//</editor-fold>

    
}
