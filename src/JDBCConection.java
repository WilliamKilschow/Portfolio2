import java.sql.*;
import java.util.Scanner;

class JDBCConnection {

    public Connection connect(String url)
            throws SQLException{
        /*Kalder en connection metode som returnerer et connection objekt.
         Dette objekt skaber en connection til vores URL*/
        return DriverManager.getConnection(url);
    }


    //Departures.TrainID As TrainID from Departures// Fra tidligere version
    public PreparedStatement selectpreparedstatement(Connection conn)
            throws SQLException {
        String query = "select Departures.DepTime As DepTime, Trains.Route as Route from Trains, Departures" +
                " INNER JOIN STATIONS  ON STATIONS.StationID = Departures.StationID"+
                " Where StationName = ? AND StationName = ?  ";
        PreparedStatement selectpstmt = null;
        selectpstmt = conn.prepareStatement(query);
        return selectpstmt;
    }

    public ResultSet plainstatement(String StationName,Connection conn)
        throws SQLException {
        String query = "select Departures.DepTime As DepTime, Trains.Route as Route from Trains, Departures" +
                " INNER JOIN STATIONS  ON STATIONS.StationID = Departures.StationID"+
                " Where StationName = '" + StationName + "'";
        Statement stmt = null;
        ResultSet res = null;
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);
        return res;
    }


    public void PresentRoute(ResultSet res)
        throws SQLException{
        if(res==null)
            System.out.println("No records");
        while(res !=null & res.next()) {
            String foundRoute = res.getString("Route");
            String foundDepartureTime = res.getString("DepTime");
            System.out.println(foundRoute + " " + foundDepartureTime);
        }
        }


    public static void main(String[] args) {
        JDBCConnection retriever = new JDBCConnection();
        //Connection er en del af SQLlib
        Connection conn = null;

        try {
            String url = "jdbc:sqlite:/Users/williamkilschowpetersen/Documents/5. Semester RUC/Software Development/databaser/TravelersFriend1.db";
            //Connection objektet, retriever, skaber adgang til databasen
            conn = retriever.connect(url);
            System.out.println("you have reached connection");
            System.out.println("At which station do you wish to depart?");
            Scanner scanner = new Scanner(System.in);
            String station1 = scanner.nextLine();
            System.out.println("Choose destination: ");
            String station2 = scanner.nextLine();
            //Vi laver et preparedstatement kaldet feedback, som kører vores selectpreparedstatement på vores JDBC objekt.
            PreparedStatement Feedback = retriever.selectpreparedstatement(conn);
            Feedback.setString(1, station1);
            Feedback.setString(2, station2);
            //Resultatet er vores preparedstatement som henter vores eksekverede query
            ResultSet pres = Feedback.executeQuery();
            //Vores JDBC objekt præsenterer den indsamlede data
            retriever.PresentRoute(pres);
            ResultSet res = retriever.plainstatement(station1, conn);
            retriever.PresentRoute(res);

        /*Hvis der opstår en fejl med forbindelsen til
         databasen bliver stacktrace printet, så fejlen kan findes*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Lukker vores connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
