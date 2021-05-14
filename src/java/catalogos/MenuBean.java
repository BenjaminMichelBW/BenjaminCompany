package catalogos;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Benjamin Michel 
 * @since 2021-04-29
 */
@ManagedBean
@ApplicationScoped
public class MenuBean implements Serializable {

    private MenuModel modo;
    private String opcion;

    public MenuBean() {
       seleccionMenu(1);
    }

    /**
     * Asigna el valor para mostrar los catalogos
     *
     * @param seleccion
     */
    public void seleccionMenu(int seleccion) {
        switch (seleccion) {
            case 1:
                opcion = "/index.xhtml";
                break;
            case 2:
                opcion = "/catalogos/catalogoCliente.xhtml";
                break;
            case 3:
                opcion = "/catalogos/catalogoDistribuidor.xhtml";
                break;
            case 4:
                opcion = "/catalogos/catalogoTelefonia.xhtml";
                break;
            case 5:
                opcion = "/catalogos/catalogoAccesos.xhtml";
                break;
            case 6:
                opcion = "/catalogos/catalogoPerfiles.xhtml";
                break;
            case 7:
                opcion = "/reporte/reporte.xhtml";
                break;
            default:
                opcion = "/index.xhtml";
                break;
        }
    }

//<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the opcion
     */
    public String getOpcion() {
        return opcion;
    }

    /**
     * @param opcion the opcion to set
     */
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    /**
     * @return the modo
     */
    public MenuModel getModo() {
        return modo;
    }

    /**
     * @param modo the modo to set
     */
    public void setModo(MenuModel modo) {
        this.modo = modo;
    }
//</editor-fold>

}
