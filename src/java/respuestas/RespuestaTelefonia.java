package respuestas;

import objetos.Telefonia;
import java.util.List;

/**
 *
 * @author Benjamin Michel 
 * 2021-04-16
 */
public class RespuestaTelefonia {

    private Respuesta respuesta;
    private List<Telefonia> listaTelefonia;
    private Telefonia telefonia;

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
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
//</editor-fold>

}
