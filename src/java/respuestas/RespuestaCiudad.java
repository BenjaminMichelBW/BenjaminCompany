/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respuestas;

import java.util.List;
import objetos.Ciudad;

/**
 *
 * @author Benjamin Michel 
 * 2021-04-23
 */
public class RespuestaCiudad {

    private Respuesta respuesta;
    private List<Ciudad> listaCiudad;
    private Ciudad ciudad;
    
    
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
//</editor-fold>
    
}
