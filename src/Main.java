import java.sql.*;

public class Main {

    //Crear queries y guardarlas en constantes para usarlas luego.
    private static final String SQL_CREATE_TABLE = "DROP TABLE IF EXISTS ANIMALES; CREATE TABLE ANIMALES "
            + "("
            + " ID INT PRIMARY KEY,"
            + " NOMBRE varchar(100) NOT NULL, "
            + " TIPO varchar(100) NOT NULL "
            + " )";

    private static final String SQL_INSERT1 =  "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (1, 'Pancho', 'Perro')";
    private static final String SQL_INSERT2 =  "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (2, 'Pillo', 'Gato')";
    private static final String SQL_INSERT3 =  "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (3, 'Cepillo', 'Elefante')";
    private static final String SQL_INSERT4 =  "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (4, 'Pepe', 'Perro')";
    private static final String SQL_INSERT5 =  "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (5, 'Rolo', 'Caballo')";

    private static final String SQL_DELETE =  "DELETE FROM ANIMALES WHERE ID=2";



    public static void main(String[] args) throws SQLException {

        Connection connection = null;

        try{
            connection = getConnection(); //Abrir conexión
            Statement statementCreateTable = connection.createStatement();
            statementCreateTable.execute(SQL_CREATE_TABLE);

            Statement statementInsert1 = connection.createStatement();
            statementCreateTable.execute(SQL_INSERT1);

            Statement statementInsert2 = connection.createStatement();
            statementCreateTable.execute(SQL_INSERT2);

            Statement statementInsert3 = connection.createStatement();
            statementCreateTable.execute(SQL_INSERT3);

            Statement statementInsert4 = connection.createStatement();
            statementCreateTable.execute(SQL_INSERT4);

            Statement statementInsert5 = connection.createStatement();
            statementCreateTable.execute(SQL_INSERT5);

            verListadoAnimales(connection);

        } catch (Exception e){
            System.out.println("ERROR in TABLE CREATION");

        }finally {
            connection.close(); //Cerrar conexión;
            System.out.println("Finally");
        }

    }

    // Especificar el driver y abrir la conexión con base de datos H2
    public static Connection getConnection(){
        DriverManager driverManager = null;
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
            connection = driverManager.getConnection("jdbc:h2:~/h2-learningH2", "root", "");
        }catch (Exception e){
            System.out.println("ERROR");
        }
    return connection;
    }

    //Buscar en base de datos
    private static void verListadoAnimales(Connection connection) throws SQLException {
        String statementSelect = "SELECT * FROM ANIMALES";

        Statement statementSelectAnimales = connection.createStatement();
        ResultSet resultSet = statementSelectAnimales.executeQuery(statementSelect);

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - "
                    + resultSet.getString(2) + " - " + resultSet.getString(3));
        }
    }
}