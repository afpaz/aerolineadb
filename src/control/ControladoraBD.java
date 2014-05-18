package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ControladoraBD {
	
	public final static String IP_EXTERNA = "200.3.193.24";
	public final static String IP_INTERNA = "172.16.0.103";
	
	private static Connection getConection() throws ClassNotFoundException, SQLException
	{
		Connection connection = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String servidor = IP_EXTERNA;
		String puerto = "1522";
		String sid = "ESTUD";
		String usr = "P09551_1_2";
		String pass = "kirUjsrZ";
		String cadenaConeccion = "jdbc:oracle:thin:@" + servidor + ":" + puerto + ":" + sid;
		connection = DriverManager.getConnection(cadenaConeccion,usr,pass);
        return connection;
	}

	static void cambiarEdadAtodos(int edad) throws Exception
	{
		Connection connection = getConection();
		String procedure = "{ call pn_setAgeToAll(?) }";
		CallableStatement pr_almacenado = connection.prepareCall(procedure);
		pr_almacenado.setInt(1, edad);
		pr_almacenado.execute();
		pr_almacenado.close();
		connection.close();
	}
	
	static ResultSet consultarVuelosEntreFechas (Date inicio, Date fin) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String query = "SELECT vu.fecha, vu.vuelo_id, lu1.descripcion, lu2.descripcion, vi.Sentido, vu.cupoMax FROM Lugar lu1, Lugar lu2, Viajes vi," +
				" Vuelo vu WHERE vu.fecha >= ? AND vu.fecha <= ? AND vu.viaje_id = vi.viaje_id AND lu1.lugar_id = vi.origen AND lu2.lugar_id = vi.destino;";
		PreparedStatement stat = con.prepareStatement(query);  
		stat.setDate(1, (java.sql.Date) inicio);
		stat.setDate(2, (java.sql.Date) fin);
        ResultSet rs = stat.executeQuery();
        
		return rs;
	}
	
	public boolean actualizarClase(String nombreV, String nombre, String descripcion, String multiplicador) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "UPDATE CLASES SET clase_id = '" + nombre + "', descripcion = '" + descripcion + "', multiplicador = '" + multiplicador + "' WHERE clase_id = '" + nombreV +"'" ;
		Statement statement = con.createStatement( );
		return statement.execute( sql );		
	}
	
	public boolean actualizarDestino(String id, double latitud, double longitud, String descripcion) throws ClassNotFoundException, SQLException 
	{		
		Connection conect = getConection();
		String sql = "UPDATE LUGARES SET latitud = " + latitud + ", longitud = " + longitud + ", descripcion = '" + descripcion + "' WHERE nombre_lugar = '" + id+ "'";
		Statement statement = conect.createStatement();
		return statement.execute(sql);
	}

	public void actualizarDescuento(String id_v, String id, String fechaInf, String fechaSup, int ocupacionInf, int ocupacionSup, int descuento ) throws ClassNotFoundException, SQLException 
	{
		Connection con = getConection();		
		String sql = "UPDATE DESCUENTOS SET DESCUENTO_ID = '" + id + "', porcentaje_desc = " + descuento + ", liminfdias = " +
				"TO_DATE('" + fechaInf + "'," + "'DD/MM/YYYY'), limsupdias = TO_DATE('" + fechaSup + "'," + "'DD/MM/YYYY'), " +
						"liminfcupos = " + ocupacionInf + ", limsupcupos = " + ocupacionSup + " WHERE DESCUENTO_ID = '" + id_v + "'";
		Statement statement = con.createStatement( );
		statement.execute( sql );
	}

	public void actualizarTarifa(String id, int valor, double inferior, double superior) throws SQLException, ClassNotFoundException 
	{
		Connection conect = getConection();
		String sql= "UPDATE TARIFAS SET vlr_km =" + valor + ", limInfKM = " + inferior + ", limSupKM = " + superior + " where tarifa_id = '" + id +"'";
		Statement statement = conect.createStatement();
		statement.execute(sql);
	}

	public boolean crearClase(String nombre, String descripcion, int multiplicador) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "INSERT INTO CLASES VALUES( '" + nombre + "', '" + descripcion + "', '" + multiplicador + "')";
		Statement statement = con.createStatement( );
		return statement.execute( sql );
	}
	
	public boolean crearDestino(String id, double latitud, double longitud, String descripcion) throws SQLException, ClassNotFoundException
	{		
		Connection con = getConection();
		String sql = "INSERT INTO LUGARES VALUES( '" + id + "', '"+ descripcion + "', " + latitud + ", " + longitud +")";
		Statement statement = con.createStatement( );
		return statement.execute( sql );	
	}

	public void crearDescuento(String id, String fechaInf, String fechaSup, int ocupacionInf, int ocupacionSup, int descuento) throws ClassNotFoundException, SQLException 
	{
		Connection con = getConection();
		String sql = "INSERT INTO DESCUENTOS VALUES( '" + id + "'," + "'" + descuento + "', TO_DATE('" + fechaInf + "'," + "'DD/MM/YYYY'), TO_DATE('" + fechaSup + "'," + "'DD/MM/YYYY')," +  ocupacionInf + ", '"+ ocupacionSup + "')";
		Statement statement = con.createStatement( );
		statement.execute( sql );
	}

	public void crearTarifa(String id, int valor, double inferior, double superior) throws ClassNotFoundException, SQLException 
	{
		Connection conect = getConection();
		String sql = "INSERT INTO TARIFAS VALUES('"+ id + "', " + valor + ", " + inferior + ", " + superior + ")";
		Statement statement = conect.createStatement();
		statement.execute(sql);
	}

	public void eliminarTarifa(String id) throws ClassNotFoundException, SQLException 
	{
		Connection conect = getConection();
		String sql= "DELETE FROM TARIFAS where tarifa_id = '" + id + "'";
		Statement statement = conect.createStatement();
		statement.execute(sql);		
	}

	public boolean eliminarDestino(String id) throws SQLException, ClassNotFoundException 
	{
		Connection conect = getConection();
		String sql = "DELETE FROM LUGARES WHERE nombre_lugar = '" + id + "'" ;
		Statement statement = conect.createStatement();
		return statement.execute(sql);
	}

	public boolean eliminarClase(String nombre) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "DELETE FROM CLASES c WHERE c.nombre = '" + nombre + "'";
		Statement statement = con.createStatement( );
		return statement.execute( sql );		
	}

	public boolean eliminarDescuento(String id) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "DELETE FROM DESCUENTOS d WHERE d.descuento_id = '" + id + "'";
		Statement statement = con.createStatement( );
		return statement.execute( sql );
	}
	
	public ResultSet consultarTarifas() throws ClassNotFoundException, SQLException 
	{
		Connection conect = getConection();
		String sql = "SELECT * FROM TARIFAS";
		return conect.prepareStatement(sql).executeQuery();
	}

	public ResultSet consultarDescuentos( ) throws ClassNotFoundException, SQLException
	{
		Connection conect = getConection();
		String sql = "SELECT * FROM DESCUENTOS";
		return conect.prepareStatement(sql).executeQuery();
	}

	public ResultSet consultarDestinos() throws ClassNotFoundException, SQLException
	{
		Connection conect = getConection();
		String sql = "SELECT * FROM LUGARES";
		return conect.prepareStatement(sql).executeQuery();
	}

	public ResultSet consultarClases() throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "SELECT * FROM CLASES";
		return con.prepareStatement( sql ).executeQuery( );
	}

	public ResultSet consultarClase( String nombre ) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "SELECT * FROM CLASES c WHERE c.nombre = '" + nombre + "'";
		return con.prepareStatement( sql ).executeQuery( );
	}	
	
	
}