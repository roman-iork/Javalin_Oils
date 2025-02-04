import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void createTable() {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement()) {

            String sqlOil = """
                    CREATE TABLE IF NOT EXISTS oils (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        oil_name VARCHAR(100) NOT NULL UNIQUE,
                        is_calculated BOOLEAN DEFAULT FALSE
                        );
                    """;
            stmt.executeUpdate(sqlOil);
            System.out.println("Table oils created.");

            String sqlAcid = """
                    CREATE TABLE IF NOT EXISTS acids (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        acid_name VARCHAR(100) NOT NULL UNIQUE
                        );
                    """;
            stmt.executeUpdate(sqlAcid);
            System.out.println("Table acids created.");

            String sqlOilAcid = """
                    CREATE TABLE IF NOT EXISTS oils_acids (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        oil_id INT,
                        acid_id INT,
                        acid_amount DOUBLE,
                        FOREIGN KEY (oil_id) REFERENCES oils(id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE,
                        FOREIGN KEY (acid_id) REFERENCES acids(id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE,
                        UNIQUE (oil_id, acid_id)
                        );
                    """;
            stmt.executeUpdate(sqlOilAcid);
            System.out.println("Table sqlOilAcid created.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
