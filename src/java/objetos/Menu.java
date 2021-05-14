/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.Serializable;

/**
 *
 * @author Benjamin Michel
 * 2021-05-14
 */
public class Menu implements Serializable {

    private int idMenu;
    private String nomAplicacion;
    private String icono;
    private String url;
    private int orden;
    
    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the idMenu
     */
    public int getIdMenu() {
        return idMenu;
    }
    
    /**
     * @param idMenu the idMenu to set
     */
    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }
    
    /**
     * @return the nomAplicacion
     */
    public String getNomAplicacion() {
        return nomAplicacion;
    }
    
    /**
     * @param nomAplicacion the nomAplicacion to set
     */
    public void setNomAplicacion(String nomAplicacion) {
        this.nomAplicacion = nomAplicacion;
    }
    
    /**
     * @return the icono
     */
    public String getIcono() {
        return icono;
    }
    
    /**
     * @param icono the icono to set
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }
    
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }
    
    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
//</editor-fold>
    
}
