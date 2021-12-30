package ConexaoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConection {
	
	private static String url = "jdbc:mysql://localhost/posjava?useTimezone=true&serverTimezone=UTC";
	private static String password = "CARlos799";
	private static String user = "root";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
				//pra eu decidir quando o crud serão gravadas efetivamente
				connection.setAutoCommit(false);
				System.out.println("conectado com sucesso");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
