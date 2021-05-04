package catalogos;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import objetos.Usuario;
import respuestas.RespuestaLogin;
import utils.HexDigest;
import utils.TraeDatoSesion;

/**
 *
 * @author Benjamin Michel 2021-04-22
 */
@ManagedBean
@SessionScoped

public class LoginBean implements Serializable {

    private Usuario usuario;
    private String inputUsuario;
    private String inputPassword;
    private String nombreUsuario;

    /**
     * Inicio de sesión
     *
     */
    public void irIndex() {

        try {
            String passwordEncriptada = HexDigest.hexDigest(inputPassword);
            Usuario usuarioBD = new Usuario();
            usuarioBD.setUsuario(inputUsuario);
            usuarioBD.setPassword(passwordEncriptada);

            RespuestaLogin res = modelos.CatalogosModel.loginUsuario(usuarioBD);
            usuario = res.getUsuario();

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idUsuario", usuario.getIdUsuario());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario.getUsuario());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreUsuario", usuario.getNombreUsuario());

            setNombreUsuario(TraeDatoSesion.traerNombreUsuario());

            if (res.getRespuesta()
                    .getIdRespuesta() == 0) {
                ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
                String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
                ctx.redirect(ctxPath + "/faces/plantilla/plantilla.xhtml");
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Datos no validos", "Favor de revisar que el usuario y la contraseña sean correctos");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cierra la sesion limpiando las variables de sesion
     *
     */
    public void cerrarSession() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            // Usar el contexto de JSF para invalidar la sesión,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            ((HttpSession) ctx.getSession(false)).invalidate();

            // Redirección de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallará.
            // Sin embargo, como ya está fuera del ciclo de vida 
            // de JSF se debe usar la ruta completa 
            ctx.redirect(ctxPath + "/faces/login/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DistribuidorBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * en caso de que el sistema cierre la sesion en automatico este regresa al
     * login
     *
     */
    public void redirigirLogin() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {

            ctx.redirect(ctxPath + "/faces/login/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DistribuidorBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String contextPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

//<editor-fold defaultstate="collapsed" desc="Gets y Sets">
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

    /**
     * @return the inputUsuario
     */
    public String getInputUsuario() {
        return inputUsuario;
    }

    /**
     * @param inputUsuario the inputUsuario to set
     */
    public void setInputUsuario(String inputUsuario) {
        this.inputUsuario = inputUsuario;
    }

    /**
     * @return the inputPassword
     */
    public String getInputPassword() {
        return inputPassword;
    }

    /**
     * @param inputPassword the inputPassword to set
     */
    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

//</editor-fold>

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
