/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respuestas;

import objetos.Usuario;

/**
 *
 * @author Benjamin Michel 
 * 2021-04-22
 */
public class RespuestaLogin {
    
    private Respuesta respuesta;
    private Usuario usuario;

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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }
    
    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
//</editor-fold>
    
}
