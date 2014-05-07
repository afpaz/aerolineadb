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
		String servidor = IP_INTERNA;
		String puerto = "1522";
		String sid = "ESTUD";
		String usr = "";
		String pass = "";
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
	
	public ResultSet consultarClases() throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "SELECT * FROM CLASES";
		return con.prepareStatement( sql ).executeQuery( );
	}
	
	public boolean actualizarClase(String nombreV, String nombre, String descripcion, int multiplicador) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "UPDATE CLASES SET nombre = '" + nombre + "', descripcion = '" + descripcion + "', multiplicador = '" + multiplicador + "' WHERE nombre = '" + nombreV +"'" ;
		Statement statement = con.createStatement( );
		return statement.execute( sql );		
	}
	
	public boolean crearClase(String nombre, String descripcion, int multiplicador) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "INSERT INTO CLASES VALUES( '" + nombre + "', '" + descripcion + "', '" + multiplicador + "')";
		Statement statement = con.createStatement( );
		return statement.execute( sql );		
	}
	
	public boolean eliminarClase(String nombre) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "DELETE FROM CLASES c WHERE c.nombre = '" + nombre + "'";
		Statement statement = con.createStatement( );
		return statement.execute( sql );		
	}

	public ResultSet consultarClase( String nombre ) throws ClassNotFoundException, SQLException
	{
		Connection con = getConection();
		String sql = "SELECT * FROM CLASES c WHERE c.nombre = '" + nombre + "'";
		return con.prepareStatement( sql ).executeQuery( );
	}

	public boolean crearDestino(String id, double latitud, double longitud, String descripcion) throws SQLException, ClassNotFoundException
	{		
		Connection con = getConection();
		String sql = "INSERT INTO LUGARES VALUES( '" + id + "', " + latitud + ", " + longitud + ", '"+ descripcion + "')";
		Statement statement = con.createStatement( );
		return statement.execute( sql );	
	}

	public boolean actualizarDestino(String id, double latitud, double longitud, String descripcion) throws ClassNotFoundException, SQLException 
	{
		int latitudA = (int) latitud;
		int longitudA = (int) longitud;
		
		
		Connection conect = getConection();
		String sql = "UPDATE LUGARES SET latitud = '" + latitudA + "', longitud = '" + longitudA + "', descripcion = '" + descripcion + "' WHERE id = '" + id + "'";
		Statement statement = conect.createStatement();
		return statement.execute(sql);
	}

	public boolean eliminarDestino(String id) throws SQLException, ClassNotFoundException 
	{
		Connection conect = getConection();
		String sql = "DELETE FROM LUGARES WHERE id = '" + id + "'" ;
		Statement statement = conect.createStatement();
		return statement.execute(sql);
	}
	
	public ResultSet consultarDestinos() throws ClassNotFoundException, SQLException
	{
		Connection conect = getConection();
		String sql = "SELECT * FROM LUGARES";
		return conect.prepareStatement(sql).executeQuery();
	}
	
}