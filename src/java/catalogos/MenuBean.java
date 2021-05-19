package catalogos;

import controller.SAplicacionesJpaController;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import objetos.Menu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import utils.TraeDatoSesion;

/**
 *
 * @author Benjamin Michel
 * @since 2021-04-29
 */
@ManagedBean
@ApplicationScoped
public class MenuBean implements Serializable {

    private MenuModel model;
    private List<Menu> lista;

    private String opcion;

    public MenuBean() {
        model = new DefaultMenuModel();
        opcion = "/index.xhtml";
        cargaMenuDinamico();

    }

    //<editor-fold defaultstate="collapsed" desc="Menu no dinamico">
    /**
     * Asigna el valor para mostrar los catalogos
     *
     * @param seleccion
     */
    /**public void seleccionMenu(int seleccion) {
     * switch (seleccion) {
     * case 1:
     * opcion = "/index.xhtml";
     * break;
     * case 2:
     * opcion = "/catalogo/catalogoCliente.xhtml";
     * break;
     * case 3:
     * opcion = "/catalogo/catalogoDistribuidor.xhtml";
     * break;
     * case 4:
     * opcion = "/catalogo/catalogoTelefonia.xhtml";
     * break;
     * case 5:
     * opcion = "/catalogo/catalogoAccesos.xhtml";
     * break;
     * case 6:
     * opcion = "/catalogo/catalogoPerfil.xhtml";
     * break;
     * case 7:
     * opcion = "/reporte/reporteClientes.xhtml";
     * break;
     * case 8:
     * opcion = "/reporte/reporteActivacion.xhtml";
     * break;
     * default:
     * opcion = "/index.xhtml";
     * break;
     * }
     * }*/
//</editor-fold>

    /**
     * Carga del menu dinamico
     * 
     */
    public void cargaMenuDinamico() {
        SAplicacionesJpaController sAplicacionesJpa = new SAplicacionesJpaController();
        
        lista = TraeDatoSesion.traerListaMenu();
        DefaultSubMenu firstSubmenu = new DefaultSubMenu();

        DefaultMenuItem itemIndex = DefaultMenuItem.builder()
                                .value("Inicio")
                                .icon("fa fa-home")
                                .update("panelCenter")
                                .style("font-size:19px;")
                                .command(String.format("#{menuBean.opcionMenuDinamico('%s')}", "/index.xhtml"))
                                .build();
        model.addElement(itemIndex);
        
        for (Menu m : lista) {
            if (m.getUrl().equals("#")) {
                firstSubmenu = DefaultSubMenu.builder()
                        .label(m.getNomAplicacion())
                        .icon(m.getIcono())
                        .style("font-size:19px;")
                        .build();
                
                String nombreMenu = m.getNomAplicacion();
                String nombreMenuConfirmacion = "";
                for (Menu i : lista) {
                    String submenu = i.getUrl();
                    if (i.getIdMenu() == 0){
                        nombreMenuConfirmacion = i.getNomAplicacion();
                    }

                    if (!submenu.equals("#") && nombreMenu.equals(nombreMenuConfirmacion) ) {
                        DefaultMenuItem item = DefaultMenuItem.builder()
                                .value(i.getNomAplicacion())
                                .icon(i.getIcono())
                                .update("panelCenter")
                                .style("font-size:19px;")
                                .command(String.format("#{menuBean.opcionMenuDinamico('%s')}", i.getUrl()))
                                .build();
                        firstSubmenu.getElements().add(item);

                    }

                }
                model.addElement(firstSubmenu);
            }
            
        }

    }
    
    public void opcionMenuDinamico(String url){
        opcion = url;
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
     * @return the model
     */
    public MenuModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(MenuModel model) {
        this.model = model;
    }
//</editor-fold>

}
