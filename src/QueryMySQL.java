
import java.sql.*;

public class QueryMySQL {

	// For MySQL -> jdbc:mysql://hostname:portNumber/databaseName
	private static final String DATABASE_URL = "-";
	private static final String USER_NAME = "";
	private static final String PASSWORD = "";

	public static void main(String args[]) {

//		String QUERY1_1 = "SELECT * FROM supplier"; 
//		String QUERY1_2 = "SELECT * FROM parts";
//		String QUERY1_3 = "SELECT * FROM supplies";

//		String updateQuery2 = "UPDATE supplier SET status = 45 WHERE city = 'London'";
//		String QUERY2 = "SELECT * FROM supplier WHERE city = 'London'";

//		String QUERY3 = "SELECT city, COUNT(*) AS number_of_suppliers "
//				+ "FROM supplier GROUP BY city ORDER BY number_of_suppliers DESC";

//		String QUERY4 = "SELECT name, weight FROM parts WHERE NOT (colour = 'Red' AND weight > 15.0)";

//		String QUERY5 = "SELECT s.supplierNum AS supplier_number, " + "sup.name AS supplier_name, "
//				+ "s.partNum AS part_number, " + "p.name AS part_name, " + "s.quantity AS quantity "
//				+ "FROM supplies s JOIN supplier sup ON s.supplierNum = sup.supplierNum "
//				+ "JOIN parts p ON s.partNum = p.partNum";

//		String QUERY6 = "SELECT sup.name AS supplier_name "
//				+ "FROM supplies s JOIN supplier sup ON s.supplierNum = sup.supplierNum "
//				+ "GROUP BY sup.name HAVING COUNT(s.supplierNum) > 1";

//		String QUERY7_1 = "DELETE FROM supplies WHERE supplierNum = 'S3'";
//		String QUERY7_2 = "DELETE FROM supplier WHERE supplierNum = 'S3'";
//		String QUERY7 = "SELECT * FROM supplier";

//		String QUERY8 = "SELECT p.partNum, p.name AS part_name, p.colour, p.weight, p.city "
//				+ "FROM parts p JOIN supplies s ON p.partNum = s.partNum "
//				+ "WHERE s.quantity < 200 OR s.quantity > 300";
		
		String QUERY9="SELECT p.name AS part_name, "
				+ "p.colour AS part_colour, "
				+ "sup.name AS supplier_name "
				+ "FROM parts p "
				+ "JOIN supplies s ON p.partNum = s.partNum "
				+ "JOIN supplier sup ON s.supplierNum = sup.supplierNum";

		// use try-with-resources to connect to and query the database
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
				Statement statement = connection.createStatement()) {
			System.out.println("Connected to MySQL server...\n");

//			statement.executeUpdate(updateQuery2);
//			statement.executeUpdate(QUERY7_1);
//			statement.executeUpdate(QUERY7_2);
			ResultSet result_set = statement.executeQuery(QUERY9);

			// get ResultSet's meta data
			ResultSetMetaData meta_data = result_set.getMetaData();
			int number_of_columns = meta_data.getColumnCount();

			// display the names of the columns in the result_set
			for (int i = 1; i <= number_of_columns; i++) {
				System.out.printf("%-8s\t", meta_data.getColumnName(i));
			}

			System.out.println(); // newline

			// display query results
			while (result_set.next()) {
				for (int i = 1; i <= number_of_columns; i++) {
					System.out.printf("%-8s\t", result_set.getObject(i));
				}

				System.out.println(); // newline
			}
		} // AutoCloseable objects' close methods are called now
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

}
