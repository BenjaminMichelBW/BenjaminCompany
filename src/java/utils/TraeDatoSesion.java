package utils;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import objetos.Menu;

/**
 *
 * @author antuan.yanez
 */
public class TraeDatoSesion {

    public static int traerIdUsuario() {
        int usuario = 0;
        usuario = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idUsuario");
        return usuario;
    }

    public static String traerUsuario() {
        String usuario = "";
        usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return usuario;
    }

    public static String traerNombreUsuario() {
        String usuario = "";
        usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario");
        return usuario;
    }
    
    public static List<Menu> traerListaMenu(){
        List<Menu> listaMenu = new ArrayList<>();
        listaMenu = (List<Menu>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("listaMenu");
        return listaMenu;
    }

    public static Boolean verificarSession() {
        Boolean usuario = false;

        if (traerIdUsuario() == 0) {
            usuario = true;
        }

        return usuario;
    }

}