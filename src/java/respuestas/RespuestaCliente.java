package respuestas;

import java.util.List;
import objetos.Cliente;

/**
 *
 * @author Benjamin Michel
 * 2021-04-23
 */
public class RespuestaCliente {

    private Respuesta respuesta;
    private List<Cliente> listaCliente;
    private Cliente cliente;

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the respuesta
     */
    public Respuesta getRespuesta() {
        return respuesta;
    }
    
    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
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
//</editor-fold>

}
