package respuestas;

import java.util.ArrayList;
import objetos.Distribuidor;
import java.util.List;

/**
 *
 * @author Benjamin Michel
 * 2021-04-16
 */
public class RespuestaDistribuidor {

    private Respuesta respuesta;
    private List<Distribuidor> listaDistribuidores;
    private Distribuidor distribuidor;
    
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
     * @return the listaDistribuidores
     */
    public List<Distribuidor> getListaDistribuidores() {
        return listaDistribuidores;
    }
    
    /**
     * @param listaDistribuidores the listaDistribuidores to set
     */
    public void setListaDistribuidores(List<Distribuidor> listaDistribuidores) {
        this.listaDistribuidores = listaDistribuidores;
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
    
    public void setListaDistribuidor(ArrayList<Distribuidor> listaDistribuidor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>
    
    
    
}
