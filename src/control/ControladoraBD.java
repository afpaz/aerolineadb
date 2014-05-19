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

public class ControladoraBD
{

	public final static String IP_EXTERNA = "200.3.193.24";
	public final static String IP_INTERNA = "172.16.0.103";

	private static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Connection connection = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String servidor = IP_EXTERNA;
		String puerto = "1522";
		String sid = "ESTUD";
		String usr = "P09551_1_2";
		String pass = "kirUjsrZ";
		String cadenaConeccion = "jdbc:oracle:thin:@" + servidor + ":" + puerto + ":" + sid;
		connection = DriverManager.getConnection(cadenaConeccion, usr, pass);
		return connection;
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
		while (rs.next())
		{
			lista.add(rs.getString(1) + " - " + rs.getInt(2) + " Tiquete(s)");
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

	public static String[] consultarViajes(String origen, String destino,String clase) throws ClassNotFoundException, SQLException
	{		
		Connection con = getConnection();
		
		String function = "{? = call FN_GET_VIAJES(?,?,?)}";
		String dato = "";
		String[] arreglo;
		CallableStatement cs = null;
		
		try{
			cs = con.prepareCall(function);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, origen);
			cs.setString(3, destino);
			cs.setString(4, clase);
			cs.execute();
			dato = cs.getString(1);
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error al recuperar la funci�n desde SQL DEVELOPER\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}finally{
			cs.close();
			con.close();
		}		
		if(dato == null){
			arreglo = new String[1];
			arreglo[0] = "No hay Viajes!!!";
		}else
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
		pr_almacenado.setString( 1, usuario );
		pr_almacenado.setString( 2, nombre );
		pr_almacenado.setString( 3, descripcion );
		pr_almacenado.setString( 4, multiplicador+"" );
		pr_almacenado.setString( 5, nombreV );
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public boolean actualizarDestino(String id, double latitud, double longitud, String descripcion) throws ClassNotFoundException, SQLException
	{
		Connection conect = getConnection();
		String sql = "UPDATE LUGARES SET latitud = " + latitud + ", longitud = " + longitud + ", descripcion = '" + descripcion + "' WHERE nombre_lugar = '" + id + "'";
		Statement statement = conect.createStatement();
		return statement.execute(sql);
	}

	public void actualizarDescuento(String usuario, String id_v, String id, int[] fechaInf, int[] fechaSup, int ocupacionInf, int ocupacionSup, int descuento) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call editar_descuento(?, ?, ?, ?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString( 1, usuario );
		pr_almacenado.setString( 2, id );
		java.sql.Date d1 = new java.sql.Date( fechaInf[2], fechaInf[1], fechaInf[0] );
		pr_almacenado.setDate( 3, d1 );
		java.sql.Date d2 = new java.sql.Date( fechaSup[2], fechaSup[1], fechaSup[0] );
		pr_almacenado.setDate( 4, d2 );
		pr_almacenado.setInt( 5, ocupacionInf );
		pr_almacenado.setInt( 6, ocupacionSup );
		pr_almacenado.setInt( 7, descuento );
		pr_almacenado.setString( 8, id_v );
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void actualizarTarifa(String id, int valor, double inferior, double superior) throws SQLException, ClassNotFoundException
	{
		Connection conect = getConnection();
		String sql = "UPDATE TARIFAS SET vlr_km =" + valor + ", limInfKM = " + inferior + ", limSupKM = " + superior + " where tarifa_id = '" + id + "'";
		Statement statement = conect.createStatement();
		statement.execute(sql);
	}

	public void crearClase(String usuario, String nombre, String descripcion, int multiplicador) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call crear_clases(?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString( 1, usuario );
		pr_almacenado.setString( 2, nombre );
		pr_almacenado.setString( 3, descripcion );
		pr_almacenado.setString( 4, multiplicador+"" );
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

	public boolean crearDestino(String id, double latitud, double longitud, String descripcion) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		String sql = "INSERT INTO LUGARES VALUES( '" + id + "', '" + descripcion + "', " + latitud + ", " + longitud + ")";
		Statement statement = con.createStatement();
		return statement.execute(sql);
	}

	public void crearDescuento(String usuario, String id, int[] fechaInf, int[] fechaSup, int ocupacionInf, int ocupacionSup, int descuento) throws ClassNotFoundException, SQLException
	{		
		Connection connection = getConnection();
		String procedure = "{ call crear_descuento(?, ?, ?, ?, ?, ?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString( 1, usuario );
		pr_almacenado.setString( 2, id );
		java.sql.Date d1 = new java.sql.Date( fechaInf[2], fechaInf[1], fechaInf[0] );
		pr_almacenado.setDate( 3, d1 );
		java.sql.Date d2 = new java.sql.Date( fechaSup[2], fechaSup[1], fechaSup[0] );
		pr_almacenado.setDate( 4, d2 );
		pr_almacenado.setInt( 5, ocupacionInf );
		pr_almacenado.setInt( 6, ocupacionSup );
		pr_almacenado.setInt( 7, descuento );
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void crearTarifa(String id, int valor, double inferior, double superior) throws ClassNotFoundException, SQLException
	{
		Connection conect = getConnection();
		String sql = "INSERT INTO TARIFAS VALUES('" + id + "', " + valor + ", " + inferior + ", " + superior + ")";
		Statement statement = conect.createStatement();
		statement.execute(sql);
	}

	public void eliminarTarifa(String id) throws ClassNotFoundException, SQLException
	{
		Connection conect = getConnection();
		String sql = "DELETE FROM TARIFAS where tarifa_id = '" + id + "'";
		Statement statement = conect.createStatement();
		statement.execute(sql);
	}

	public boolean eliminarDestino(String id) throws SQLException, ClassNotFoundException
	{
		Connection conect = getConnection();
		String sql = "DELETE FROM LUGARES WHERE nombre_lugar = '" + id + "'";
		Statement statement = conect.createStatement();
		return statement.execute(sql);
	}

	public void eliminarClase(String usuario, String nombre) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call eliminar_clases(?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString( 1, usuario );
		pr_almacenado.setString( 2, nombre );
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public void eliminarDescuento(String usuario, String id) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		String procedure = "{ call eliminar_descuento(?, ?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setString( 1, usuario );
		pr_almacenado.setString( 2, id );
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}

	public boolean eliminarRuta(String id) throws ClassNotFoundException, SQLException
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
	 * Permite consultar todos los viajes que hay en la bd. Se necesitar para crear una ruta.
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

	public ResultSet ConsultarDemanda(Date inicio, Date fin, Destino origen, Destino destino) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		String sql = "SELECT V.FECHA, SUM(OcupacionVuelo(V.VUELO_ID)) FROM VUELOS V GROUP BY V.FECHA;";
		/*if(inicio != null)
		{

			
			sql+= " WHERE V.FECHA >= TO_DATE('" + sdf.format(inicio) + "'," + "'DD/MM/YYYY')";
			if(fin != null)
			{
				sql+= " AND V.FECHA >= TO_DATE('" + sdf.format(fin) + "'," + "'DD/MM/YYYY')";
			}
			
			if(origen != null)
			{
				sql+= " AND V.ORIGEN = '"+ origen.getId() +"' ";
			}
			
			if(destino != null)
			{
				sql+= " AND V.ORIGEN = '"+ destino.getId() +"' ";
			}
		}else if(fin != null)
		{
			sql+= " WHERE V.FECHA >= TO_DATE('" + sdf.format(fin) + "'," + "'DD/MM/YYYY')";
			
			if(origen != null)
			{
				sql+= " AND V.ORIGEN = '"+ origen.getId() +"' ";
			}
			
			if(destino != null)
			{
				sql+= " AND V.ORIGEN = '"+ destino.getId() +"' ";
			}
		}else if(origen != null)
		{
			sql+= " WHERE V.ORIGEN = '"+ origen.getId() +"' ";
			
			if(destino != null)
			{
				sql+= " AND V.ORIGEN = '"+ destino.getId() +"' ";
			}
		}else if(destino != null)
		{
			sql+= " WHERE V.ORIGEN = '"+ destino.getId() +"' ";
		}
		*/
		//sql+= " GROUP BY V.FECHA;";
		return con.prepareStatement( sql ).executeQuery( );
	}

	public ResultSet ConsultarDemandaMes(Date inicio, Date fin, Destino origen, Destino destino) throws ClassNotFoundException, SQLException  {
		// TODO Auto-generated method stub
		return null;
	}

	public static String[ ] generarPasabordos( )
	{
		return new String[]{"METODO AUXILIAR PARA QUITAR ERROR EN CONTROLADORA DB"};
	}
}