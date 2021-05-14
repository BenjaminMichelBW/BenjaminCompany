package catalogos;

import controller.CClientesJpaController;
import controller.SUsuariosJpaController;
import entidades.SUsuarios;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import objetos.ReporteCliente;

/**
 *
 * @author Benjamin Michel
 * @since 2021-05-14
 */
@ManagedBean
@ApplicationScoped
public class ReporteBean implements Serializable {

    private List<ReporteCliente> listaRepCliente;
    private List<SUsuarios> listaUsuarios;
    private SUsuarios usuario;

    public ReporteBean() {
        usuario = new SUsuarios();
        traerUsuarios();
    }

    /**
     * Trae el listado de usuarios para llenar el selectOneMenu de usuarios
     * 
     */
    public void traerUsuarios(){
        try{
            SUsuariosJpaController sUsuariosJpa = new SUsuariosJpaController();
            listaUsuarios = sUsuariosJpa.findSUsuariosEntities();
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Busca los clientes que el usuario seleccionado modifico
     * 
     */
    public void buscarClientes() {
        try {
            CClientesJpaController cClientesJpa = new CClientesJpaController();
            listaRepCliente = cClientesJpa.traerClientesRegistroUsuario(usuario.getIdUsuario());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Buscar", "Busqueda exitosa");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(DistribuidorBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Buscar", "Se produjo un error");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the listaRepCliente
     */
    public List<ReporteCliente> getListaRepCliente() {
        return listaRepCliente;
    }

    /**
     * @param listaRepCliente the listaRepCliente to set
     */
    public void setListaRepCliente(List<ReporteCliente> listaRepCliente) {
        this.listaRepCliente = listaRepCliente;
    }

    /**
     * @return the listaUsuarios
     */
    public List<SUsuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<SUsuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * @return the usuario
     */
    public SUsuarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(SUsuarios usuario) {
        this.usuario = usuario;
    }
//</editor-fold>

}
