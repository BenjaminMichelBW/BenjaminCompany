package catalogos;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import objetos.Ciudad;
import objetos.Cliente;
import objetos.Distribuidor;
import org.primefaces.event.SelectEvent;
import respuestas.Respuesta;
import respuestas.RespuestaCiudad;
import respuestas.RespuestaCliente;
import respuestas.RespuestaDistribuidor;
import utils.TraeDatoSesion;

/**
 *
 * @author Benjamin Michel
 */
@ManagedBean
@ApplicationScoped
public class ClienteBean implements Serializable {

    private Cliente cliente;
    private Cliente seleccionCliente;
    private List<Cliente> listaCliente;
    private Distribuidor distribuidor;
    private List<Distribuidor> listaDistribuidor;
    private Ciudad ciudad;
    private List<Ciudad> listaCiudad;

    private int inputIdCliente;
    private String inputNumCliente;
    private String inputNombreCliente;
    private int inputIdDistribuidor;
    private int inputIdCiudad;
    private String inputTelContacto;
    private String inputRfc;
    private String inputCalle;
    private String inputNumExt;
    private String inputColonia;
    private String inputCp;
    private Boolean inputEstado;
    
    private String burcarNombreCliente;
    private String buscarCiudadCliente;

    public ClienteBean() {
        CargarSomCiudad();
        CargarSomDistribuidor();
    }

    /**
     * Carga los datos del selectOneMenu de distribuidor
     *
     */
    public void CargarSomDistribuidor() {
        try {
            RespuestaDistribuidor res = modelos.CatalogosModel.getSomListaDistribuidor();
            if (res.getRespuesta().getIdRespuesta() == 0) {

                listaDistribuidor = res.getListaDistribuidores();

            } else {
                System.out.println(res.getRespuesta().getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga los datos del selectOneMenu de ciudad
     *
     */
    public void CargarSomCiudad() {
        try {
            RespuestaCiudad res = modelos.CatalogosModel.getSomListaCiudad();
            if (res.getRespuesta().getIdRespuesta() == 0) {

                listaCiudad = res.getListaCiudad();

            } else {
                System.out.println(res.getRespuesta().getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga datos de la tabla clientes
     *
     */
    public void cargarDatosCliente() {
        try {
            RespuestaCliente res = modelos.CatalogosModel.getListaCliente(burcarNombreCliente, buscarCiudadCliente);
            if (res.getRespuesta().getIdRespuesta() == 0 || res.getRespuesta().getIdRespuesta() == 1) {
                listaCliente = res.getListaCliente();
            } else {
                System.out.println(res.getRespuesta().getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga los controles del panel con la seleccion de la tabla
     *
     * @param event
     */
    public void onRowSelect(SelectEvent<Cliente> event) {
        try {
            inputIdCliente = event.getObject().getIdCliente();
            inputNumCliente = event.getObject().getNumCliente();
            inputNombreCliente = event.getObject().getNombreCliente();
            inputIdDistribuidor = event.getObject().getIdDistribuidor();
            inputIdCiudad = event.getObject().getIdCiudad();
            inputTelContacto = event.getObject().getTelContacto();
            inputRfc = event.getObject().getRfc();
            inputCalle = event.getObject().getCalle();
            inputNumExt = event.getObject().getNumExt();
            inputColonia = event.getObject().getColonia();
            inputCp = event.getObject().getCp();
            inputEstado = event.getObject().getEstado();
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpia los controles del panel
     *
     */
    public void nuevoCliente() {
        try {
            inputIdCliente = 0;
            inputNumCliente = "";
            inputNombreCliente = "";
            inputIdDistribuidor = listaDistribuidor.get(0).getIdDistribuidor();
            inputIdCiudad = listaCiudad.get(0).getIdCiudad();
            inputTelContacto = "";
            inputRfc = "";
            inputCalle = "";
            inputNumExt = "";
            inputColonia = "";
            inputCp = "";
            inputEstado = true;
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Guarda o actualiza a un cliente
     * 
     */
    public void guardarCliente() {
        try {
            Cliente clienteInputs = new Cliente();
            clienteInputs.setIdCliente(inputIdCliente);
            clienteInputs.setNumCliente(inputNumCliente);
            clienteInputs.setNombreCliente(inputNombreCliente);
            clienteInputs.setIdDistribuidor(inputIdDistribuidor);
            clienteInputs.setIdCiudad(inputIdCiudad);
            clienteInputs.setTelContacto(inputTelContacto);
            clienteInputs.setRfc(inputRfc);
            clienteInputs.setCalle(inputCalle);
            clienteInputs.setNumExt(inputNumExt);
            clienteInputs.setColonia(inputColonia);
            clienteInputs.setCp(inputCp);
            clienteInputs.setEstado(inputEstado);
            clienteInputs.setIdUsuarioModifica(TraeDatoSesion.traerIdUsuario());

            Respuesta res;

            if (inputIdCliente == 0) {
                res = modelos.CatalogosModel.insertCliente(clienteInputs);
            } else {
                res = modelos.CatalogosModel.updateCliente(clienteInputs);
            }

            if (res.getIdRespuesta() != 0) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", res.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(res.getMensaje());

            } else {
                cargarDatosCliente();
                nuevoCliente();
                FacesMessage msg = new FacesMessage("Guardar", "Se guardó correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Elimina un cliente
     * 
     */
    public void eliminarCliente() {
        try{
            Cliente clienteInputs = new Cliente();
            clienteInputs.setIdCliente(inputIdCliente);
            Respuesta res = modelos.CatalogosModel.deleteCliente(clienteInputs);
            if (res.getIdRespuesta() != 0) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Guardar", res.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println(res.getMensaje());

            } else {
                cargarDatosCliente();
                nuevoCliente();
                FacesMessage msg = new FacesMessage("Guardar", "Se guardó correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the seleccionCliente
     */
    public Cliente getSeleccionCliente() {
        return seleccionCliente;
    }

    /**
     * @param seleccionCliente the seleccionCliente to set
     */
    public void setSeleccionCliente(Cliente seleccionCliente) {
        this.seleccionCliente = seleccionCliente;
    }

    /**
     * @return the listaCliente
     */
    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    /**
     * @param listaCliente the listaCliente to set
     */
    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

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
     * @return the ciudad
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the listaCiudad
     */
    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    /**
     * @param listaCiudad the listaCiudad to set
     */
    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    /**
     * @return the inputIdCliente
     */
    public int getInputIdCliente() {
        return inputIdCliente;
    }

    /**
     * @param inputIdCliente the inputIdCliente to set
     */
    public void setInputIdCliente(int inputIdCliente) {
        this.inputIdCliente = inputIdCliente;
    }

    /**
     * @return the inputNumCliente
     */
    public String getInputNumCliente() {
        return inputNumCliente;
    }

    /**
     * @param inputNumCliente the inputNumCliente to set
     */
    public void setInputNumCliente(String inputNumCliente) {
        this.inputNumCliente = inputNumCliente;
    }

    /**
     * @return the inputNombreCliente
     */
    public String getInputNombreCliente() {
        return inputNombreCliente;
    }

    /**
     * @param inputNombreCliente the inputNombreCliente to set
     */
    public void setInputNombreCliente(String inputNombreCliente) {
        this.inputNombreCliente = inputNombreCliente;
    }

    /**
     * @return the inputIdDistribuidor
     */
    public int getInputIdDistribuidor() {
        return inputIdDistribuidor;
    }

    /**
     * @param inputIdDistribuidor the inputIdDistribuidor to set
     */
    public void setInputIdDistribuidor(int inputIdDistribuidor) {
        this.inputIdDistribuidor = inputIdDistribuidor;
    }

    /**
     * @return the inputIdCiudad
     */
    public int getInputIdCiudad() {
        return inputIdCiudad;
    }

    /**
     * @param inputIdCiudad the inputIdCiudad to set
     */
    public void setInputIdCiudad(int inputIdCiudad) {
        this.inputIdCiudad = inputIdCiudad;
    }

    /**
     * @return the inputTelContacto
     */
    public String getInputTelContacto() {
        return inputTelContacto;
    }

    /**
     * @param inputTelContacto the inputTelContacto to set
     */
    public void setInputTelContacto(String inputTelContacto) {
        this.inputTelContacto = inputTelContacto;
    }

    /**
     * @return the inputRfc
     */
    public String getInputRfc() {
        return inputRfc;
    }

    /**
     * @param inputRfc the inputRfc to set
     */
    public void setInputRfc(String inputRfc) {
        this.inputRfc = inputRfc;
    }

    /**
     * @return the inputCalle
     */
    public String getInputCalle() {
        return inputCalle;
    }

    /**
     * @param inputCalle the inputCalle to set
     */
    public void setInputCalle(String inputCalle) {
        this.inputCalle = inputCalle;
    }

    /**
     * @return the inputNumExt
     */
    public String getInputNumExt() {
        return inputNumExt;
    }

    /**
     * @param inputNumExt the inputNumExt to set
     */
    public void setInputNumExt(String inputNumExt) {
        this.inputNumExt = inputNumExt;
    }

    /**
     * @return the inputColonia
     */
    public String getInputColonia() {
        return inputColonia;
    }

    /**
     * @param inputColonia the inputColonia to set
     */
    public void setInputColonia(String inputColonia) {
        this.inputColonia = inputColonia;
    }

    /**
     * @return the inputCp
     */
    public String getInputCp() {
        return inputCp;
    }

    /**
     * @param inputCp the inputCp to set
     */
    public void setInputCp(String inputCp) {
        this.inputCp = inputCp;
    }

    /**
     * @return the inputEstado
     */
    public Boolean getInputEstado() {
        return inputEstado;
    }

    /**
     * @param inputEstado the inputEstado to set
     */
    public void setInputEstado(Boolean inputEstado) {
        this.inputEstado = inputEstado;
    }
    
        /**
     * @return the burcarNombreCliente
     */
    public String getBurcarNombreCliente() {
        return burcarNombreCliente;
    }

    /**
     * @param burcarNombreCliente the burcarNombreCliente to set
     */
    public void setBurcarNombreCliente(String burcarNombreCliente) {
        this.burcarNombreCliente = burcarNombreCliente;
    }

    /**
     * @return the buscarCiudadCliente
     */
    public String getBuscarCiudadCliente() {
        return buscarCiudadCliente;
    }

    /**
     * @param buscarCiudadCliente the buscarCiudadCliente to set
     */
    public void setBuscarCiudadCliente(String buscarCiudadCliente) {
        this.buscarCiudadCliente = buscarCiudadCliente;
    }

//</editor-fold>
}
