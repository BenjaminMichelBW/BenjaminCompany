package entidades;

import entidades.CCiudad;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-17T08:52:00")
@StaticMetamodel(HActivacion.class)
public class HActivacion_ { 

    public static volatile SingularAttribute<HActivacion, Long> idActivacion;
    public static volatile SingularAttribute<HActivacion, Long> idUsuario;
    public static volatile SingularAttribute<HActivacion, String> distribuidor;
    public static volatile SingularAttribute<HActivacion, Date> fechaRespuesta;
    public static volatile SingularAttribute<HActivacion, CCiudad> idCiudad;
    public static volatile SingularAttribute<HActivacion, String> descripcionTipo;
    public static volatile SingularAttribute<HActivacion, String> cliente;
    public static volatile SingularAttribute<HActivacion, String> iccid;
    public static volatile SingularAttribute<HActivacion, Long> monto;
    public static volatile SingularAttribute<HActivacion, Long> idCliente;
    public static volatile SingularAttribute<HActivacion, String> ciudad;
    public static volatile SingularAttribute<HActivacion, Date> fechaPeticion;
    public static volatile SingularAttribute<HActivacion, String> imei;
    public static volatile SingularAttribute<HActivacion, Long> id;
    public static volatile SingularAttribute<HActivacion, String> telefono;
    public static volatile SingularAttribute<HActivacion, Long> idDistribuidor;
    public static volatile SingularAttribute<HActivacion, Long> idTipoTelefonia;
    public static volatile SingularAttribute<HActivacion, String> respuestaAplicacion;
    public static volatile SingularAttribute<HActivacion, Date> fechaServidor;

}