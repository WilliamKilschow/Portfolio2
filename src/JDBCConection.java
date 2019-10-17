import java.sql.*;

class JDBCConnection {

    public Connection connect(String url)
            throws SQLException{
        /*Kalder en connection metode som returnerer et connection objekt.
         Dette objekt skaber en connection til vores URL*/
        return DriverManager.getConnection(url);
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
            //Vi laver et preparedstatement kaldet feedback, som kører vores selectpreparedstatement på vores JDBC objekt.
            PreparedStatement Feedback = retriever.selectpreparedstatement(conn);
            //Resultatet er vores preparedstatement som henter vores eksekverede query
            ResultSet pres = Feedback.executeQuery();
            //Vores JDBC objekt præsenterer den indsamlede data
            retriever.PresentRoute(pres);

        /*Hvis der opstår en fejl med forbindelsen til
         databasen bliver stacktrace printet, så fejlen kan findes*/
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
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

    public PreparedStatement selectpreparedstatement(Connection conn)
            throws SQLException {
        String query = "select Departures.DepTime As DepTime, Departures.TrainID As TrainID from Departures" +
                " Where Departures.StationID = 2 ";
        PreparedStatement selectpstmt = null;
        selectpstmt = conn.prepareStatement(query);
        return selectpstmt;
    }

   /* public ResultSet statement(Connection conn)
        throws SQLException {
        String query = "select Departures.DepTime As DepTime, Departures.TrainID As TrainID from Departures" +
                "Where Departures.StationID = 2 ";
        Statement stmt = null;
        ResultSet res = null;
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);
        return res;
    }
    */

    public void PresentRoute(ResultSet res)
        throws SQLException{
        if(res==null)
            System.out.println("No records");
        while(res !=null & res.next()) {
            String foundRoute = res.getString("TrainID");
            String foundDepartureTime = res.getString("DepTime");
            System.out.println(foundRoute + " " + foundDepartureTime);
        }
        }
    }


