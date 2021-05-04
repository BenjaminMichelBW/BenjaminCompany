package modelos;

import dbutils.PoolDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import objetos.Ciudad;
import objetos.Cliente;
import objetos.Distribuidor;
import objetos.Telefonia;
import objetos.Usuario;
import respuestas.Respuesta;
import respuestas.RespuestaCiudad;
import respuestas.RespuestaCliente;
import respuestas.RespuestaDistribuidor;
import respuestas.RespuestaLogin;
import respuestas.RespuestaTelefonia;

/**
 *
 * @author Benjamin Michel 2021-04-16
 */
public class CatalogosModel {

    //Logica de Reglas de Negocio y llamadas a la BD.
    /**
     * Obtener datos de Telefonia para tabla
     *
     */
    public static RespuestaTelefonia getListaTelefonia(String clave, String descripcion) {
        RespuestaTelefonia resFinal = new RespuestaTelefonia();
        Respuesta respuesta = new Respuesta();
        ArrayList<Telefonia> listaTelefonia = new ArrayList<Telefonia>();
        Connection con = null;
        String query = "";
        try {

            con = PoolDB.getConnection("activar");
            boolean hayRegistros = false;

            query = "select ID_TELEFONIA, DESCRIPCION, CLAVE, ACTIVO from C_TELEFONIA";

            if (!clave.trim().equals("") && !descripcion.trim().equals("")) {
                query += " WHERE CLAVE LIKE '%" + clave + "%' AND DESCRIPCION LIKE '%" + descripcion + "%'";
            } else if (!clave.trim().equals("")) {
                query += " WHERE CLAVE LIKE '%" + clave + "%'";
            } else if (!descripcion.trim().equals("")) {
                query += " WHERE DESCRIPCION LIKE '%" + descripcion + "%'";
            }

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hayRegistros = true;
                Telefonia telefonia = new Telefonia();

                telefonia.setIdTelefonia(rs.getInt("ID_TELEFONIA"));
                telefonia.setDescripcion(rs.getString("DESCRIPCION"));
                telefonia.setClave(rs.getString("CLAVE"));
                telefonia.setEstado(rs.getBoolean("ACTIVO"));

                listaTelefonia.add(telefonia);
            }
            rs.close();
            ps.close();

            if (hayRegistros) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Consutla Exitosa.");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("No existen registros.");
            }

        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        resFinal.setRespuesta(respuesta);
        resFinal.setListaTelefonia(listaTelefonia);

        return resFinal;
    }

    /**
     * Guardar nuvo registro en telefonia
     *
     * @param telefonia
     * @return
     */
    public static Respuesta insertTelefonia(Telefonia telefonia) {
        Respuesta respuesta = new Respuesta();

        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("insert into C_TELEFONIA values(?,?,GETDATE(),?)");
            ps.setString(1, telefonia.getDescripcion());
            ps.setString(2, telefonia.getClave());
            ps.setBoolean(3, telefonia.isEstado());

            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Insercion Exitoso");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al insertar");
            }

            ps.close();

        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return respuesta;
    }

    /**
     * Actualiza un registro en telefonia
     *
     * @param telefonia
     * @return
     */
    public static Respuesta updateTelefonia(Telefonia telefonia) {
        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("update C_TELEFONIA set DESCRIPCION = ?, CLAVE = ?, ACTIVO = ? where ID_TELEFONIA = ?");
            ps.setString(1, telefonia.getDescripcion());
            ps.setString(2, telefonia.getClave());
            ps.setBoolean(3, telefonia.isEstado());
            ps.setInt(4, telefonia.getIdTelefonia());

            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Actualizacion Exitosa");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al actualizar");
            }

            ps.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return respuesta;
    }

    /**
     * elimina un registro en telefonia
     *
     * @param telefonia
     * @return
     */
    public static Respuesta deleteTelefonia(Telefonia telefonia) {
        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("delete from C_TELEFONIA where ID_TELEFONIA = ?");
            ps.setInt(1, telefonia.getIdTelefonia());

            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Eliminacion Exitosa");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al eliminar");
            }

            ps.close();

        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return respuesta;
    }

    /**
     * Trae datos para latabla de distribuidor
     *
     * @return
     */
    public static RespuestaDistribuidor getListaDistribuidor(String clave, String nombre) {
        RespuestaDistribuidor resFinal = new RespuestaDistribuidor();
        Respuesta respuesta = new Respuesta();
        ArrayList<Distribuidor> listaDistribuidor = new ArrayList<Distribuidor>();
        Connection con = null;
        String query = "";

        try {
            con = PoolDB.getConnection("activar");
            boolean hayRegistros = false;

            query = "select ID_DISTRIBUIDOR, CLAVE_DISTRIBUIDOR, NOMBRE, ACTIVO from C_DISTRIBUIDOR";

            if (!clave.trim().equals("") && !nombre.trim().equals("")) {
                query += " WHERE CLAVE_DISTRIBUIDOR LIKE '%" + clave + "%' AND NOMBRE LIKE '%" + nombre + "%'";
            } else if (!clave.trim().equals("")) {
                query += " WHERE CLAVE_DISTRIBUIDOR LIKE '%" + clave + "%'";
            } else if (!nombre.trim().equals("")) {
                query += " WHERE NOMBRE LIKE '%" + nombre + "%'";
            }

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hayRegistros = true;
                Distribuidor distribuidor = new Distribuidor();

                distribuidor.setIdDistribuidor(rs.getInt("ID_DISTRIBUIDOR"));
                distribuidor.setClave(rs.getString("CLAVE_DISTRIBUIDOR"));
                distribuidor.setNombre(rs.getString("NOMBRE"));
                distribuidor.setEstado(rs.getBoolean("ACTIVO"));

                listaDistribuidor.add(distribuidor);
            }
            rs.close();
            ps.close();
            if (hayRegistros) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Consutla Exitosa.");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("No existen registros.");
            }

        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        resFinal.setRespuesta(respuesta);
        resFinal.setListaDistribuidores(listaDistribuidor);

        return resFinal;
    }

    /**
     * Inserta un registro en telefonia
     *
     * @param distribuidor
     * @return
     */
    public static Respuesta insertDistribuidor(Distribuidor distribuidor) {
        Respuesta respuesta = new Respuesta();

        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("insert into C_DISTRIBUIDOR values (?,?,?,GETDATE(),NULL,GETDATE(),?)");
            ps.setString(1, distribuidor.getClave());
            ps.setString(2, distribuidor.getNombre());
            ps.setBoolean(3, distribuidor.isEstado());
            ps.setInt(4, distribuidor.getIdUsuarioModifica());

            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Insercion Exitoso");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al insertar");
            }
            ps.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return respuesta;
    }

    /**
     * Actualiza un registro en telefonia
     *
     * @param distribuidor
     * @return
     */
    public static Respuesta updateDistribuidor(Distribuidor distribuidor) {
        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("update C_DISTRIBUIDOR set CLAVE_DISTRIBUIDOR = ?, NOMBRE = ?, ACTIVO = ?, ID_USUARIO_MODIFICA = ? where ID_DISTRIBUIDOR = ?");
            ps.setString(1, distribuidor.getClave());
            ps.setString(2, distribuidor.getNombre());
            ps.setBoolean(3, distribuidor.isEstado());
            ps.setInt(4, distribuidor.getIdUsuarioModifica());
            ps.setInt(5, distribuidor.getIdDistribuidor());

            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Actualizacion Exitosa");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al actualizar");
            }

            ps.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return respuesta;
    }

    /**
     * Elimina un registro en telefonia
     *
     * @param distribuidor
     * @return
     */
    public static Respuesta deleteDistribuidor(Distribuidor distribuidor) {
        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("delete from C_DISTRIBUIDOR where ID_DISTRIBUIDOR = ?");
            ps.setInt(1, distribuidor.getIdDistribuidor());
            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Eliminacion Exitosa");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al eliminar");
            }

            ps.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return respuesta;
    }

    /**
     * Revisa si el usuario que se pone en el login existe.
     *
     * @param usuario
     * @return
     */
    public static RespuestaLogin loginUsuario(Usuario usuario) {
        RespuestaLogin resFinal = new RespuestaLogin();
        Respuesta respuesta = new Respuesta();
        Connection con = null;
        Usuario usuarioConsulta = new Usuario();

        try {
            con = PoolDB.getConnection("activar");
            boolean hayRegistro = false;
            PreparedStatement ps = con.prepareStatement("select ID_USUARIO,USUARIO,NOMBRE_USUARIO,PASSWORD,isnull(ULTIMA_SESION, GETDATE()) as ULTIMA_SESION from S_USUARIOS where USUARIO = ?;");
            ps.setString(1, usuario.getUsuario());
            ResultSet rs = ps.executeQuery();
            PreparedStatement ps1 = null;

            if (rs.next()) {
                hayRegistro = true;

                usuarioConsulta.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuarioConsulta.setUsuario(rs.getString("USUARIO"));
                usuarioConsulta.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                usuarioConsulta.setPassword(rs.getString("PASSWORD").toLowerCase());
                usuarioConsulta.setUltimaSesion(rs.getString("ULTIMA_SESION"));

            }
            if (usuarioConsulta.getPassword().equals(usuario.getPassword())) {
                ps1 = con.prepareStatement("update S_USUARIOS set ULTIMA_SESION = GETDATE() where ID_USUARIO = ?");
                ps1.setInt(1, usuarioConsulta.getIdUsuario());
            }

            rs.close();
            ps.close();

            if (hayRegistro && ps1.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Consulta Exitosa y actualizacion de fecha exitosa.");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("No existen registros.");
            }
            ps1.close();

        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        resFinal.setRespuesta(respuesta);
        resFinal.setUsuario(usuarioConsulta);
        return resFinal;
    }

    /**
     * Obtener datos de clientes
     *
     * @return
     */
    public static RespuestaCliente getListaCliente(String nombreCliente, String nombreCiudad) {
        RespuestaCliente resFinal = new RespuestaCliente();
        Respuesta respuesta = new Respuesta();
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
        Connection con = null;
        String query = "";
        try {

            con = PoolDB.getConnection("activar");
            boolean hayRegistros = false;

            query = "SELECT CCL.ID_CLIENTE,\n"
                    + "       CCL.NUM_CLIENTE,\n"
                    + "       CCL.NOMBRE_CLIENTE,\n"
                    + "       CCL.ID_DISTRIBUIDOR,\n"
                    + "       CDI.NOMBRE AS NOMBRE_DISTRIBUIDOR,\n"
                    + "       CCL.ID_CIUDAD,\n"
                    + "       CCI.DESCRIPCION AS DESCRIPCION_CIUDAD,\n"
                    + "       CCL.TEL_CONTACTO,\n"
                    + "       CCL.RFC,\n"
                    + "       CCL.CALLE,\n"
                    + "       CCL.NUM_EXT,\n"
                    + "       CCL.COLONIA,\n"
                    + "       CCL.CP,\n"
                    + "       CCL.ACTIVO\n"
                    + "FROM C_CLIENTES CCL,\n"
                    + "     C_DISTRIBUIDOR CDI,\n"
                    + "     C_CIUDAD CCI\n"
                    + "WHERE CCL.ID_CIUDAD = CCI.ID_CIUDAD\n"
                    + "      AND CCL.ID_DISTRIBUIDOR = CDI.ID_DISTRIBUIDOR";

            if (!nombreCliente.trim().equals("") && !nombreCiudad.trim().equals("")) {
                query += "\n AND CCI.DESCRIPCION LIKE '%" + nombreCiudad + "%'\n"
                        + "  AND CCL.NOMBRE_CLIENTE LIKE '%" + nombreCliente + "%'";
            } else if (!nombreCliente.trim().equals("")) {
                query += "\n  AND CCL.NOMBRE_CLIENTE LIKE '%" + nombreCliente + "%'";
            } else if (!nombreCiudad.trim().equals("")) {
                query += "\n AND CCI.DESCRIPCION LIKE '%" + nombreCiudad + "%'";
            }

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hayRegistros = true;
                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setNumCliente(rs.getString("NUM_CLIENTE"));
                cliente.setNombreCliente(rs.getString("NOMBRE_CLIENTE"));
                cliente.setIdDistribuidor(rs.getInt("ID_DISTRIBUIDOR"));
                cliente.setNombreDistribuidor(rs.getString("NOMBRE_DISTRIBUIDOR"));
                cliente.setIdCiudad(rs.getInt("ID_CIUDAD"));
                cliente.setDescripcionCiudad(rs.getString("DESCRIPCION_CIUDAD"));
                cliente.setTelContacto(rs.getString("TEL_CONTACTO"));
                cliente.setRfc(rs.getString("RFC"));
                cliente.setCalle(rs.getString("CALLE"));
                cliente.setNumExt(rs.getString("NUM_EXT"));
                cliente.setColonia(rs.getString("COLONIA"));
                cliente.setCp(rs.getString("CP"));
                cliente.setEstado(rs.getBoolean("ACTIVO"));

                listaCliente.add(cliente);
            }
            rs.close();
            ps.close();

            if (hayRegistros) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Consutla Exitosa.");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("No existen registros.");
            }
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        resFinal.setRespuesta(respuesta);
        resFinal.setListaCliente(listaCliente);

        return resFinal;
    }

    /**
     * Obtener lista de ciudad para el Select One Menu
     *
     * @return
     */
    public static RespuestaCiudad getSomListaCiudad() {
        RespuestaCiudad resFinal = new RespuestaCiudad();
        Respuesta respuesta = new Respuesta();
        ArrayList<Ciudad> listaCiudad = new ArrayList<Ciudad>();

        Connection con = null;
        try {

            con = PoolDB.getConnection("activar");
            boolean hayRegistros = false;
            PreparedStatement ps = con.prepareStatement("select ID_CIUDAD, DESCRIPCION from C_CIUDAD where ACTIVO = 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hayRegistros = true;
                Ciudad ciudad = new Ciudad();

                ciudad.setIdCiudad(rs.getInt("ID_CIUDAD"));
                ciudad.setDescripcionCiudad(rs.getString("DESCRIPCION"));

                listaCiudad.add(ciudad);
            }
            rs.close();
            ps.close();

            if (hayRegistros) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Consutla Exitosa.");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("No existen registros.");
            }
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        resFinal.setRespuesta(respuesta);
        resFinal.setListaCiudad(listaCiudad);

        return resFinal;
    }

    public static RespuestaDistribuidor getSomListaDistribuidor() {
        RespuestaDistribuidor resFinal = new RespuestaDistribuidor();
        Respuesta respuesta = new Respuesta();
        ArrayList<Distribuidor> listaDistribuidor = new ArrayList<Distribuidor>();

        Connection con = null;
        try {
            con = PoolDB.getConnection("activar");
            boolean hayRegistros = false;
            PreparedStatement ps = con.prepareStatement("SELECT ID_DISTRIBUIDOR, NOMBRE  FROM dbo.C_DISTRIBUIDOR WHERE ACTIVO = 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hayRegistros = true;
                Distribuidor distribuidor = new Distribuidor();

                distribuidor.setIdDistribuidor(rs.getInt("ID_DISTRIBUIDOR"));
                distribuidor.setNombre(rs.getString("NOMBRE"));
                listaDistribuidor.add(distribuidor);
            }
            rs.close();
            ps.close();

            if (hayRegistros) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Consutla Exitosa.");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("No existen registros.");
            }
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        resFinal.setRespuesta(respuesta);
        resFinal.setListaDistribuidores(listaDistribuidor);

        return resFinal;
    }

    /**
     * Inserta un cliente nuevo
     *
     * @param cliente
     * @return
     */
    public static Respuesta insertCliente(Cliente cliente) {
        Respuesta respuesta = new Respuesta();

        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("insert into C_CLIENTES \n"
                    + "values( ?,\n"
                    + "?,\n"
                    + "?,--idDistribuidor\n"
                    + "?,--idCiudad\n"
                    + "?,\n"
                    + "?',\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,--estado\n"
                    + "GETDATE(),--fechaAlta\n"
                    + "null,--fechaBaja\n"
                    + "GETDATE(),--fechaServidor\n"
                    + "?)--usuarioModifica");

            ps.setString(1, cliente.getNumCliente());
            ps.setString(2, cliente.getNombreCliente());
            ps.setInt(3, cliente.getIdDistribuidor());
            ps.setInt(4, cliente.getIdCiudad());
            ps.setString(5, cliente.getTelContacto());
            ps.setString(6, cliente.getRfc());
            ps.setString(7, cliente.getCalle());
            ps.setString(8, cliente.getNumExt());
            ps.setString(9, cliente.getColonia());
            ps.setString(10, cliente.getCp());
            ps.setBoolean(11, cliente.getEstado());
            ps.setInt(12, cliente.getIdUsuarioModifica());

            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Insercion Exitoso");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al insertar");
            }
            ps.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return respuesta;
    }

    /**
     * Actualiza un cliente existente
     *
     * @param cliente
     * @return
     */
    public static Respuesta updateCliente(Cliente cliente) {
        Respuesta respuesta = new Respuesta();

        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("UPDATE dbo.C_CLIENTES SET \n"
                    + "NUM_CLIENTE = ?,\n"
                    + "NOMBRE_CLIENTE = ?,\n"
                    + "ID_DISTRIBUIDOR = ?,\n"
                    + "ID_CIUDAD = ?,\n"
                    + "TEL_CONTACTO = ?,\n"
                    + "RFC = ?,\n"
                    + "CALLE = ?,\n"
                    + "NUM_EXT = ?,\n"
                    + "COLONIA = ?,\n"
                    + "CP = ?,\n"
                    + "ACTIVO = ?,\n"
                    + "ID_USUARIO_MODIFICA = ?\n"
                    + "WHERE ID_CLIENTE = ?");

            ps.setString(1, cliente.getNumCliente());
            ps.setString(2, cliente.getNombreCliente());
            ps.setInt(3, cliente.getIdDistribuidor());
            ps.setInt(4, cliente.getIdCiudad());
            ps.setString(5, cliente.getTelContacto());
            ps.setString(6, cliente.getRfc());
            ps.setString(7, cliente.getCalle());
            ps.setString(8, cliente.getNumExt());
            ps.setString(9, cliente.getColonia());
            ps.setString(10, cliente.getCp());
            ps.setBoolean(11, cliente.getEstado());
            ps.setInt(12, cliente.getIdUsuarioModifica());
            ps.setInt(13, cliente.getIdCliente());

            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Actualizacion Exitosa");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al actualizar");
            }

            ps.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return respuesta;
    }

    /**
     * Elimina un cliente
     *
     * @param cliente
     * @return
     */
    public static Respuesta deleteCliente(Cliente cliente) {
        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {
            con = PoolDB.getConnection("activar");
            PreparedStatement ps = con.prepareStatement("delete from dbo.C_CLIENTES where ID_CLIENTE = ?");
            ps.setInt(1, cliente.getIdCliente());
            if (ps.executeUpdate() == 1) {
                respuesta.setIdRespuesta(0);
                respuesta.setMensaje("Eliminacion Exitosa");
            } else {
                respuesta.setIdRespuesta(1);
                respuesta.setMensaje("Error al eliminar");
            }
            ps.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setMensaje("Error de Base de datos.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-2);
            respuesta.setMensaje("Error al intentar realizar la conexion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-3);
            respuesta.setMensaje("Error en la aplicacion.");
            Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                respuesta.setIdRespuesta(-4);
                respuesta.setMensaje("Error en la conexion.");
                Logger.getLogger(CatalogosModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return respuesta;
    }

}
