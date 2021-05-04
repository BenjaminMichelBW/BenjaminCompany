package catalogos;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import objetos.Telefonia;
import org.primefaces.event.SelectEvent;
import respuestas.Respuesta;
import respuestas.RespuestaTelefonia;


/**
 *
 * @author Benjamin Michel
 * @version 2021-04-16
 */
@ManagedBean
@ApplicationScoped

public class TelefoniaBean implements Serializable {

    private Telefonia telefonia;
    private List<Telefonia> listaTelefonia;
    private Telefonia seleccionTelefonia;
    private int idIntPutTelefonia;
    private String intPutDescripcion;
    private String intPutClave;
    private Boolean intPutEstado;
    private String buscarClave;
    private String buscarDescripcion;
    
    
    

    public TelefoniaBean() {
        
    }
    

    /**
     * Carga los datos de Telefonia
     *
     */
    public void cargarDatosTelefonia() {

        try {
            RespuestaTelefonia res = modelos.CatalogosModel.getListaTelefonia(buscarClave, buscarDescripcion);

            if (res.getRespuesta().getIdRespuesta() == 0 || res.getRespuesta().getIdRespuesta() == 1) {
                listaTelefonia = res.getListaTelefonia();
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Cargar tabla", res.getRespuesta().getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(res.getRespuesta().getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Evento para seleccionar de la tabla telefonia
     *
     * @param event
     */
    public void onRowSelect(SelectEvent<Telefonia> event) {
        try {
            idIntPutTelefonia = event.getObject().getIdTelefonia();
            intPutDescripcion = event.getObject().getDescripcion();
            intPutClave = event.getObject().getClave();
            intPutEstado = event.getObject().isEstado();
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpia los controles del panel de telefonia
     *
     */
    public void nuevoTelefonia() {
        try {
            idIntPutTelefonia = 0;
            intPutDescripcion = "";
            intPutClave = "";
            intPutEstado = true;
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserta o actualiza en la tabla telefonia
     *
     */
    public void guardarTelefonia() {
        try {
            Telefonia telefoniaIntputs = new Telefonia();

            telefoniaIntputs.setIdTelefonia(idIntPutTelefonia);
            telefoniaIntputs.setDescripcion(intPutDescripcion);
            telefoniaIntputs.setClave(intPutClave);
            telefoniaIntputs.setEstado(intPutEstado);

            Respuesta res;

            if (idIntPutTelefonia == 0) {

                res = modelos.CatalogosModel.insertTelefonia(telefoniaIntputs);

            } else {
                res = modelos.CatalogosModel.updateTelefonia(telefoniaIntputs);

            }

            if (res.getIdRespuesta() != 0) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", res.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(res.getMensaje());

            } else {
                cargarDatosTelefonia();
                nuevoTelefonia();
                FacesMessage msg = new FacesMessage("Guardar", "Se guardó correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Elimina un registro en telefonia
     *
     */
    public void eliminarTelefonia() {
        try {
            Telefonia telefoniaIntputs = new Telefonia();
            telefoniaIntputs.setIdTelefonia(idIntPutTelefonia);
            Respuesta res = modelos.CatalogosModel.deleteTelefonia(telefoniaIntputs);
            if (res.getIdRespuesta() != 0) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminar", res.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(res.getMensaje());

            } else {
                cargarDatosTelefonia();
                nuevoTelefonia();
                FacesMessage msg = new FacesMessage("Eliminar", "Se eliminó correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the seleccionTelefonia
     */
    public Telefonia getSeleccionTelefonia() {
        return seleccionTelefonia;
    }

    /**
     * @param seleccionTelefonia the seleccionTelefonia to set
     */
    public void setSeleccionTelefonia(Telefonia seleccionTelefonia) {
        this.seleccionTelefonia = seleccionTelefonia;
    }

    /**
     * @return the telefonia
     */
    public Telefonia getTelefonia() {
        return telefonia;
    }

    /**
     * @param telefonia the telefonia to set
     */
    public void setTelefonia(Telefonia telefonia) {
        this.telefonia = telefonia;
    }

    /**
     * @return the listaTelefonia
     */
    public List<Telefonia> getListaTelefonia() {
        return listaTelefonia;
    }

    /**
     * @param listaTelefonia the listaTelefonia to set
     */
    public void setListaTelefonia(List<Telefonia> listaTelefonia) {
        this.listaTelefonia = listaTelefonia;
    }

    /**
     * @return the idIntPutTelefonia
     */
    public int getIdIntPutTelefonia() {
        return idIntPutTelefonia;
    }

    /**
     * @param idIntPutTelefonia the idIntPutTelefonia to set
     */
    public void setIdIntPutTelefonia(int idIntPutTelefonia) {
        this.idIntPutTelefonia = idIntPutTelefonia;
    }

    /**
     * @return the intPutDescripcion
     */
    public String getIntPutDescripcion() {
        return intPutDescripcion;
    }

    /**
     * @param intPutDescripcion the intPutDescripcion to set
     */
    public void setIntPutDescripcion(String intPutDescripcion) {
        this.intPutDescripcion = intPutDescripcion;
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

    /**
     * @return the buscarDescripcion
     */
    public String getBuscarDescripcion() {
        return buscarDescripcion;
    }

    /**
     * @param buscarDescripcion the buscarDescripcion to set
     */
    public void setBuscarDescripcion(String buscarDescripcion) {
        this.buscarDescripcion = buscarDescripcion;
    }
//</editor-fold>
}
