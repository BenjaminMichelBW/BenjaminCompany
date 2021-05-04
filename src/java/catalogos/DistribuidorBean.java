package catalogos;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import objetos.Distribuidor;
import org.primefaces.event.SelectEvent;
import respuestas.Respuesta;
import respuestas.RespuestaDistribuidor;
import utils.TraeDatoSesion;

/**
 *
 * @author Benjamin Michel 2021-04-16
 */
@ManagedBean
@ApplicationScoped

public class DistribuidorBean implements Serializable {

    private Distribuidor distribuidor;
    private List<Distribuidor> listaDistribuidor;
    private Distribuidor seleccionDistribuidor;
    private Distribuidor ordenarDistribuidor;
    private int idIntPutDistribuidor;
    private String intPutClave;
    private String intPutNombre;
    private Boolean intPutEstado;
    private String buscarNombre;
    private String buscarClave;

    public DistribuidorBean() {
        
        
    }

    /**
     * Carga datos a la tabla Distribuidor
     *
     */
    public void cargarDatosDistribuidor() {
        try {
            RespuestaDistribuidor res = modelos.CatalogosModel.getListaDistribuidor(buscarClave, buscarNombre);

            if (res.getRespuesta().getIdRespuesta() == 0 || res.getRespuesta().getIdRespuesta() == 1) {
                listaDistribuidor = res.getListaDistribuidores();
            } else {
                System.out.println(res.getRespuesta().getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Evento para seleccionar de la tabla distribuidor
     *
     * @param event
     */
    public void onRowSelect(SelectEvent<Distribuidor> event) {
        try {
            idIntPutDistribuidor = event.getObject().getIdDistribuidor();
            intPutClave = event.getObject().getClave();
            intPutNombre = event.getObject().getNombre();
            intPutEstado = event.getObject().isEstado();
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpia los controles del panel de distribuidor
     *
     */
    public void nuevoDistribuidor() {
        try {
            idIntPutDistribuidor = 0;
            intPutClave = "";
            intPutNombre = "";
            intPutEstado = true;
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserta o actualiza en la tabla distribuidor
     *
     */
    public void guardarDistribuidor() {
        try {
            Distribuidor distribuidorIntputs = new Distribuidor();
            distribuidorIntputs.setIdDistribuidor(idIntPutDistribuidor);
            distribuidorIntputs.setClave(intPutClave);
            distribuidorIntputs.setNombre(intPutNombre);
            distribuidorIntputs.setEstado(intPutEstado);
            distribuidorIntputs.setIdUsuarioModifica(TraeDatoSesion.traerIdUsuario());

            Respuesta res;

            if (idIntPutDistribuidor == 0) {
                res = modelos.CatalogosModel.insertDistribuidor(distribuidorIntputs);
            } else {
                res = modelos.CatalogosModel.updateDistribuidor(distribuidorIntputs);
            }

            if (res.getIdRespuesta() != 0) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", res.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(res.getMensaje());

            } else {
                cargarDatosDistribuidor();
                nuevoDistribuidor();
                FacesMessage msg = new FacesMessage("Guardar", "Se guardó correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Elimina un registro en distribuidor
     *
     */
    public void eliminarDistribuidor() {
        try {
            Distribuidor distribuidorIntputs = new Distribuidor();
            distribuidorIntputs.setIdDistribuidor(idIntPutDistribuidor);
            Respuesta res = modelos.CatalogosModel.deleteDistribuidor(distribuidorIntputs);
            if (res.getIdRespuesta() != 0) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminar", res.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(res.getMensaje());

            } else {
                cargarDatosDistribuidor();
                nuevoDistribuidor();
                FacesMessage msg = new FacesMessage("Eliminar", "Se eliminó correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the distribuidor
     */
    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    /**
     * @param distribuidor the distribuidor to set
     */
    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    /**
     * @return the listaDistribuidor
     */
    public List<Distribuidor> getListaDistribuidor() {
        return listaDistribuidor;
    }

    /**
     * @param listaDistribuidor the listaDistribuidor to set
     */
    public void setListaDistribuidor(List<Distribuidor> listaDistribuidor) {
        this.listaDistribuidor = listaDistribuidor;
    }

    /**
     * @return the seleccionDistribuidor
     */
    public Distribuidor getSeleccionDistribuidor() {
        return seleccionDistribuidor;
    }

    /**
     * @param seleccionDistribuidor the seleccionDistribuidor to set
     */
    public void setSeleccionDistribuidor(Distribuidor seleccionDistribuidor) {
        this.seleccionDistribuidor = seleccionDistribuidor;
    }

    /**
     * @return the ordenarDistribuidor
     */
    public Distribuidor getOrdenarDistribuidor() {
        return ordenarDistribuidor;
    }

    /**
     * @param ordenarDistribuidor the ordenarDistribuidor to set
     */
    public void setOrdenarDistribuidor(Distribuidor ordenarDistribuidor) {
        this.ordenarDistribuidor = ordenarDistribuidor;
    }

    /**
     * @return the idIntPutDistribuidor
     */
    public int getIdIntPutDistribuidor() {
        return idIntPutDistribuidor;
    }

    /**
     * @param idIntPutDistribuidor the idIntPutDistribuidor to set
     */
    public void setIdIntPutDistribuidor(int idIntPutDistribuidor) {
        this.idIntPutDistribuidor = idIntPutDistribuidor;
    }

    /**
     * @return the intPutClave
     */
    public String getIntPutClave() {
        return intPutClave;
    }

    /**
     * @param intPutClave the intPutClave to set
     */
    public void setIntPutClave(String intPutClave) {
        this.intPutClave = intPutClave;
    }

    /**
     * @return the intPutNombre
     */
    public String getIntPutNombre() {
        return intPutNombre;
    }

    /**
     * @param intPutNombre the intPutNombre to set
     */
    public void setIntPutNombre(String intPutNombre) {
        this.intPutNombre = intPutNombre;
    }

    /**
     * @return the intPutEstado
     */
    public Boolean getIntPutEstado() {
        return intPutEstado;
    }

    /**
     * @param intPutEstado the intPutEstado to set
     */
    public void setIntPutEstado(Boolean intPutEstado) {
        this.intPutEstado = intPutEstado;
    }

    /**
     * @return the buscarNombre
     */
    public String getBuscarNombre() {
        return buscarNombre;
    }

    /**
     * @param buscarNombre the buscarNombre to set
     */
    public void setBuscarNombre(String buscarNombre) {
        this.buscarNombre = buscarNombre;
    }

    /**
     * @return the buscarClave
     */
    public String getBuscarClave() {
        return buscarClave;
    }

    /**
     * @param buscarClave the buscarClave to set
     */
    public void setBuscarClave(String buscarClave) {
        this.buscarClave = buscarClave;
    }
//</editor-fold>
}
