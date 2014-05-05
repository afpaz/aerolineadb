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
	
	private static Connection getConection() throws ClassNotFoundException, SQLException
	{
		Connection connection = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String servidor = "172.16.0.103";
		String puerto = "1522";
		String sid = "ESTUD";
		String usr = "P09551_1_6";
		String pass = "RtL2ZhnY";			
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
}