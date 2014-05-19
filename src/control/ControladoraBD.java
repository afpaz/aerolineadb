package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.Destino;
import modelo.ReporteVentas;
import oracle.jdbc.OracleTypes;
import vista.PanelPasabordo;

public class ControladoraBD
{

	public final static String IP_EXTERNA = "200.3.193.24";
	public final static String IP_INTERNA = "172.16.0.103";

	private static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Connection connection = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String servidor = IP_INTERNA;
		String puerto = "1522";
		String sid = "ESTUD";
		String usr = "P09551_1_2";
		String pass = "kirUjsrZ";
		String cadenaConeccion = "jdbc:oracle:thin:@" + servidor + ":" + puerto + ":" + sid;
		connection = DriverManager.getConnection(cadenaConeccion, usr, pass);
		return connection;
	}

	public void anularVenta(String idVenta) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call ANULAR_VENTA(?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, idVenta);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}
	
	static void cambiarEdadAtodos(int edad) throws Exception
	{
		Connection connection = getConnection();
		String procedure = "{ call pn_setAgeToAll(?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setInt(1, edad);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public ReporteVentas reporteVendedor(String usuario) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call REPORTE_VENDEDOR(?,?,?,?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.registerOutParameter(2, java.sql.Types.NUMERIC);
		pr_almacenado.registerOutParameter(3, java.sql.Types.NUMERIC);
		pr_almacenado.registerOutParameter(4, OracleTypes.CURSOR);
		pr_almacenado.execute();

		double valor = pr_almacenado.getDouble(2);
		int cantidad = pr_almacenado.getInt(3);
		ResultSet rs = (ResultSet) pr_almacenado.getObject(4);
		ArrayList<String> lista = new ArrayList<String>();
		int i = 0;
		while (rs.next())
		{
			if(i <= 9)
			{
				lista.add(rs.getString(1) + " - " + rs.getInt(2) + " Tiquete(s)");
			}
			i++;
		}

		ReporteVentas reporte = new ReporteVentas(valor, cantidad, lista);

		pr_almacenado.close();
		connection.close();

		return reporte;

	}

	public ResultSet consultarVuelosEntreFechas(Date inicio, Date fin) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String query = "SELECT vu.FECHA AS Fecha, vu.VUELO_ID AS Vuelo, l1.NOMBRE_LUGAR AS Origen, l2.NOMBRE_LUGAR AS Destino FROM VUELOS vu, LUGARES l1, LUGARES l2 WHERE vu.FECHA >= ? AND vu.FECHA <= ? AND vu.ORIGEN = l1.NOMBRE_LUGAR AND vu.DESTINO = l2.NOMBRE_LUGAR";
		PreparedStatement stat = con.prepareStatement(query);
		stat.setDate(1, new java.sql.Date(inicio.getTime()));
		stat.setDate(2, new java.sql.Date(fin.getTime()));
		ResultSet rs = stat.executeQuery();

		return rs;
	}

	public static String[] consultarViajes(String origen, String destino, String clase) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();

		String function = "{? = call FN_GET_VIAJES(?,?,?)}";
		String dato = "";
		String[] arreglo;
		CallableStatement cs = null;

		try
		{
			cs = con.prepareCall(function);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, origen);
			cs.setString(3, destino);
			cs.setString(4, clase);
			cs.execute();
			dato = cs.getString(1);
		}

		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error al recuperar la funci�n desde SQL DEVELOPER\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally
		{
			cs.close();
			con.close();
		}
		if (dato == null)
		{
			arreglo = new String[1];
			arreglo[0] = "No hay Viajes!!!";
		} else
			arreglo = dato.split(",");
		return arreglo;
	}

	public void generarTablaRankingDinero() throws SQLException, ClassNotFoundException
	{
		Connection connection = getConnection();
		String procedure = "{ call ranking_dinero }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void generarTablaRankingTiquetes() throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call ranking_cantidad }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void actualizarClase(String usuario, String nombreV, String nombre, String descripcion, String multiplicador) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call editar_clases(?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, nombre);
		pr_almacenado.setString(3, descripcion);
		pr_almacenado.setString(4, multiplicador + "");
		pr_almacenado.setString(5, nombreV);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void actualizarDestino(String usuario, String id, double latitud, double longitud, String descripcion, String nombreV) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call editar_lugar(?, ?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, id);
		pr_almacenado.setString(3, descripcion);
		pr_almacenado.setDouble(4, latitud);
		pr_almacenado.setDouble(5, longitud);
		pr_almacenado.setString(6, nombreV);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void actualizarDescuento(String usuario, String id_v, String id, int[] fechaInf, int[] fechaSup, int ocupacionInf, int ocupacionSup, int descuento) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call editar_descuento(?, ?, ?, ?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, id);
		java.sql.Date d1 = new java.sql.Date(fechaInf[2], fechaInf[1], fechaInf[0]);
		pr_almacenado.setDate(3, d1);
		java.sql.Date d2 = new java.sql.Date(fechaSup[2], fechaSup[1], fechaSup[0]);
		pr_almacenado.setDate(4, d2);
		pr_almacenado.setInt(5, ocupacionInf);
		pr_almacenado.setInt(6, ocupacionSup);
		pr_almacenado.setInt(7, descuento);
		pr_almacenado.setString(8, id_v);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void actualizarTarifa(String usuario, String nombre, int valor, double inferior, double superior) throws SQLException, ClassNotFoundException
	{
		Connection connection = getConnection();
		String procedure = "{ call editar_tarifa(?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, nombre);
		pr_almacenado.setInt(3, valor);
		pr_almacenado.setDouble(4, inferior);
		pr_almacenado.setDouble(5, superior);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}
	

	public void agregarVenta(Date fecha, String comprador, String vendedor) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call agregar_venta(?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
	
		java.sql.Date d1 = new java.sql.Date(fecha.getTime());
		pr_almacenado.setDate(1, d1);
		pr_almacenado.setString(2, vendedor);
		pr_almacenado.setString(3, comprador);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void crearClase(String usuario, String nombre, String descripcion, int multiplicador) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call crear_clases(?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, nombre);
		pr_almacenado.setString(3, descripcion);
		pr_almacenado.setString(4, multiplicador + "");
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public boolean crearRuta(String fecha, String viajeID, String tarifaID) throws ClassNotFoundException, SQLException
	{
		String formatDate = "TO_DATE('" + fecha + "','YYYY/MM/DD')";
		Connection con = getConnection();
		String sql = "INSERT INTO Rutas VALUES(" + "seq_ruta.nextval" + "," + formatDate + ",'" + viajeID + "','" + tarifaID + "')";
		Statement statement = con.createStatement();
		return statement.execute(sql);
	}

	public void crearDestino(String usuario, String id, double latitud, double longitud, String descripcion) throws SQLException, ClassNotFoundException
	{
		Connection connection = getConnection();
		String procedure = "{ call crear_lugar(?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, id);
		pr_almacenado.setString(3, descripcion);
		pr_almacenado.setDouble(4, latitud);
		pr_almacenado.setDouble(5, longitud);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void crearDescuento(String usuario, String id, int[] fechaInf, int[] fechaSup, int ocupacionInf, int ocupacionSup, int descuento) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call crear_descuento(?, ?, ?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, id);
		java.sql.Date d1 = new java.sql.Date(fechaInf[2], fechaInf[1], fechaInf[0]);
		pr_almacenado.setDate(3, d1);
		java.sql.Date d2 = new java.sql.Date(fechaSup[2], fechaSup[1], fechaSup[0]);
		pr_almacenado.setDate(4, d2);
		pr_almacenado.setInt(5, ocupacionInf);
		pr_almacenado.setInt(6, ocupacionSup);
		pr_almacenado.setInt(7, descuento);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void crearTarifa(String usuario, String nombre, int valor, double inferior, double superior) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call crear_tarifa(?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, nombre);
		pr_almacenado.setInt(3, valor);
		pr_almacenado.setDouble(4, inferior);
		pr_almacenado.setDouble(5, superior);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void eliminarTarifa(String usuario, String nombre) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call eliminar_tarifa(?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, nombre);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void eliminarDestino(String usuario, String id) throws SQLException, ClassNotFoundException
	{
		Connection connection = getConnection();
		String procedure = "{ call eliminar_lugar(?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, id);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void eliminarClase(String usuario, String nombre) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call eliminar_clases(?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, nombre);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void eliminarDescuento(String usuario, String id) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call eliminar_descuento(?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, usuario);
		pr_almacenado.setString(2, id);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public boolean eliminarRuta(int id) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "DELETE FROM RUTAS r WHERE r.ruta_id = '" + id + "'";
		Statement statement = con.createStatement();
		return statement.execute(sql);
	}

	public ResultSet consultarTarifas() throws ClassNotFoundException, SQLException
	{
		Connection conect = getConnection();
		String sql = "SELECT * FROM TARIFAS";
		return conect.prepareStatement(sql).executeQuery();
	}
	
	public ResultSet consultarVentas() throws ClassNotFoundException, SQLException
	{
		Connection conect = getConnection();
		String sql = "SELECT * FROM VENTAS";
		return conect.prepareStatement(sql).executeQuery();
	}

	public ResultSet consultarDescuentos() throws ClassNotFoundException, SQLException
	{
		Connection conect = getConnection();
		String sql = "SELECT * FROM DESCUENTOS";
		return conect.prepareStatement(sql).executeQuery();
	}

	public ResultSet consultarDestinos() throws ClassNotFoundException, SQLException
	{
		Connection conect = getConnection();
		String sql = "SELECT * FROM LUGARES";
		return conect.prepareStatement(sql).executeQuery();
	}

	public ResultSet consultarClases() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "SELECT * FROM CLASES";
		return con.prepareStatement(sql).executeQuery();
	}

	public ResultSet consultarClase(String nombre) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "SELECT * FROM CLASES c WHERE c.nombre = '" + nombre + "'";
		return con.prepareStatement(sql).executeQuery();
	}

	/**
	 * Permite consultar todos los viajes que hay en la bd. Se necesitar para
	 * crear una ruta.
	 * 
	 * @return Lista de viajes que hay en la bd
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet consultarViajes() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "SELECT * FROM Viajes";
		return con.prepareStatement(sql).executeQuery();
	}

	/**
	 * Permite consultar todos los pasabordos de un determinado vuelo
	 * @param idVuelo Id del vuelo para generar los pasabordos
	 * @return ResultSet Lista de todos los pasabordos asociados al vuelo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet consultarPasabordos(String idVuelo) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "SELECT ti.tiquete_id,vu.FECHA,vu.ORIGEN,vu.DESTINO,vu.HORA,ve.COMPRADOR "
				+ "FROM VUELOS vu, RUTA_VUELO rv, RUTAS ru, VIAJES vi, TIQUETES ti, VENTAS ve "
				+ "WHERE vu.VUELO_ID ='" +idVuelo+"' AND rv.VUELO_ID = vu.VUELO_ID "
				+ "AND ru.RUTA_ID = rv.RUTA_ID "
				+ "AND vi.VIAJE_ID = ru.VIAJE_ID "
				+ "AND ti.VIAJE_ID = vi.VIAJE_ID "
				+ "AND ve.VENTA_ID = ti.VENTA_ID";
		
		return con.prepareStatement(sql).executeQuery();
	}

	public String consultarCompactadoTablaRank() throws SQLException, ClassNotFoundException
	{
		Connection connection = getConnection();
		String funcion = "{ ? = call compactar_tabla_rank }";
		CallableStatement statement = connection.prepareCall(funcion);
		statement.registerOutParameter(1, java.sql.Types.VARCHAR);
		statement.execute();
		return statement.getString(1);
	}

	public String consultarCompactadoTablaRank2() throws SQLException, ClassNotFoundException
	{
		Connection connection = getConnection();
		String funcion = "{ ? = call compactar_tabla_rank2 }";
		CallableStatement statement = connection.prepareCall(funcion);
		statement.registerOutParameter(1, java.sql.Types.VARCHAR);
		statement.execute();
		return statement.getString(1);
	}

	public double consultarDineroTotalRecaudado() throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String funcion = "{ ? = call calcular_total_dinero }";
		CallableStatement statement = connection.prepareCall(funcion);
		statement.registerOutParameter(1, java.sql.Types.NUMERIC);
		statement.execute();
		return statement.getDouble(1);
	}

	public int consultarTotalTiquetes() throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String funcion = "{ ? = call calcular_total_viajes }";
		CallableStatement statement = connection.prepareCall(funcion);
		statement.registerOutParameter(1, java.sql.Types.INTEGER);
		statement.execute();
		return statement.getInt(1);
	}

	/**
	 * Permite consultar las rutas que hay en la bd. Necesario para eliminar
	 * rutas.
	 * 
	 * @return ResultSet con la lista de rutas que hay en la bd.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet consultarRutas() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "SELECT * FROM Rutas";
		return con.prepareStatement(sql).executeQuery();
	}

	/**
	 * Permite consultar los vuelos que hay en la bd. Necesario para generar
	 * pasabordos.
	 * 
	 * @return ResultSet con la lista de vuelos que hay en la bd.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet consultarVuelos() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "SELECT * FROM Vuelos";
		return con.prepareStatement(sql).executeQuery();
	}
	
	

	// ------------------------------------------------------------------------------------------------------------------------------------------
	public String generarVenta(String fecha, int id_comprador, int id_vendedor) throws ClassNotFoundException, SQLException
	{
		int id = 0;
		String idVenta = "";
		Connection connection = getConnection();
		String procedure = "{? = call REALIZAR_VENTA(?,?,?,?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString(1, fecha);
		pr_almacenado.setInt(2, id_comprador);
		pr_almacenado.setInt(3, id_vendedor);
		pr_almacenado.setInt(4, id);
		pr_almacenado.registerOutParameter(1, Types.VARCHAR);
		pr_almacenado.execute();
		idVenta = pr_almacenado.getString(1);
		pr_almacenado.close();
		connection.close();
		return idVenta;
	}

	public void generarTiquete(int valor, String id_venta, String id_viaje) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call GENERAR_TIQUETE(?,?,?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setInt(1, valor);
		pr_almacenado.setString(2, id_venta);
		pr_almacenado.setString(3, id_viaje);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public static String[] getLugares() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String function = "{? = call getLugares()}";
		String dato = "";
		String[] arreglo;
		CallableStatement cs = null;

		try
		{
			cs = con.prepareCall(function);
			// cs.setString(0, "");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			dato = cs.getString(1);
		}

		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error al recuperar la funci�n desde SQL DEVELOPER\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		finally
		{
			cs.close();
			con.close();
		}

		if (dato == null)
		{
			arreglo = new String[1];
			arreglo[0] = "No hay ciudades!!!";
		}

		else
			arreglo = dato.split(",");

		return arreglo;
	}

	public static String[] getClases() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String function = "{? = call getClases()}";
		String dato = "";
		String[] arreglo;
		CallableStatement cs = null;

		try
		{
			cs = con.prepareCall(function);
			// cs.setString(0, "");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			dato = cs.getString(1);
		}

		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error al recuperar la funci�n desde SQL DEVELOPER\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		finally
		{
			cs.close();
			con.close();
		}

		if (dato == null)
		{
			arreglo = new String[1];
			arreglo[0] = "No hay clases!!!";
		}

		else
			arreglo = dato.split(",");

		return arreglo;
	}

	public ResultSet consultarDemanda() throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call REPORTE_DEMANDA(?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.registerOutParameter(1, OracleTypes.CURSOR);
		pr_almacenado.execute();
        return (ResultSet) pr_almacenado.getObject(1);
	}
	
	public static String[] getUsuarios() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String function = "{? = call GET_USUARIOS()}";
		String dato = "";
		String[] arreglo;
		CallableStatement cs = null;

		try
		{
			cs = con.prepareCall(function);
			// cs.setString(0, "");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			dato = cs.getString(1);
		}

		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error al recuperar la funci�n desde SQL DEVELOPER\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		finally
		{
			cs.close();
			con.close();
		}

		if (dato == null)
		{
			arreglo = new String[1];
			arreglo[0] = "No hay usaurios registrados!!!";
		}

		else
			arreglo = dato.split(",");

		return arreglo;
	}


	public static void veder(String comprador, String vendedor) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		String procedure = "{ call VENDER(?,?) }";
		CallableStatement funcion = connection.prepareCall(procedure);
		funcion.setString(1, comprador);
		funcion.setString(2, comprador);
		funcion.execute();
		funcion.close();
		connection.close();
	}
	
	public ResultSet ConsultarDemandaMes(Date inicio, Date fin, Destino origen, Destino destino) throws ClassNotFoundException, SQLException  {
		// TODO Auto-generated method stub
		return null;
	}

	public static String[] generarPasabordos() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String function = "{? = call generarPasabordo(?, ?)}";
		String dato = "";
		String[] arreglo;
		CallableStatement cs = null;

		try
		{
			cs = con.prepareCall(function);
			cs.setString(2, PanelPasabordo.idUsuario);
			cs.setString(3, PanelPasabordo.idViaje);
			// cs.setString(2, "Fernando");
			// cs.setString(3, "V1");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			dato = cs.getString(1);
		}

		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error al recuperar la funci�n desde SQL DEVELOPER\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		finally
		{
			cs.close();
			con.close();
		}

		if (dato == null)
		{
			arreglo = new String[1];
			arreglo[0] = "No hay pasabordos disponibles!!!";
		}

		else
			arreglo = dato.split(";");

		return arreglo;
	}
	
	public static String[] getRutasDeViaje(String viajeId) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String function = "{? = call FN_GET_RUTAS_DE_VIAJE(?)}";
		String dato = "";
		String[] arreglo;
		CallableStatement cs = null;
		try{
			cs = con.prepareCall(function);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, viajeId);
			cs.execute();
			dato = cs.getString(1);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error al recuperar la funci�n desde SQL DEVELOPER\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}finally{
			cs.close();
			con.close();
		}
		if (dato == null){
			arreglo = new String[1];
			arreglo[0] = "No hay rutas registradas!!!";
		}else
			arreglo = dato.split(",");
		return arreglo;
	}
	
	public static String[] getVuelosDeRuta(String RutaId) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		String function = "{? = call FN_GET_VUELOS_DE_RUTA(?)}";
		String dato = "";
		String[] arreglo;
		CallableStatement cs = null;
		try{
			cs = con.prepareCall(function);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, RutaId);
			cs.execute();
			dato = cs.getString(1);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error al recuperar la funci�n desde SQL DEVELOPER\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}finally{
			cs.close();
			con.close();
		}
		if (dato == null){
			arreglo = new String[1];
			arreglo[0] = "No hay vuelos registrados!!!";
		}else
			arreglo = dato.split(",");
		return arreglo;
	}
	
	public int actualizarVenta (Date fecha, String vendedor, String comprador, String idVenta) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String query = "UPDATE VENTAS SET FECHA = ?, VENDEDOR = ?, COMPRADOR = ? WHERE VENTA_ID = ?";
		PreparedStatement stat = con.prepareStatement(query);
		stat.setDate(1, new java.sql.Date(fecha.getTime()));
		stat.setString(2, vendedor);
		stat.setString(3, comprador);
		stat.setString(4, idVenta);
		return stat.executeUpdate();
		
	}
}