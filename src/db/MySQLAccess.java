package db;

import model.Flight;
import model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private void open() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/flight_server", "root", "root");
    }

    public List<Flight> getFlights() throws Exception {
        List<Flight> flights = new ArrayList<Flight>();
        open();
        statement = connect.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM Flights");
        while (resultSet.next())
            flights.add(new Flight(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7))
            );
        close();
        return flights;
    }

    public Flight getFlight(int id) throws Exception {
        Flight flight = null;
        open();
        statement = connect.createStatement();
        preparedStatement = connect.prepareStatement("SELECT * FROM Flights WHERE id=?");
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
            flight = new Flight(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7)
            );
        close();
        return flight;
    }

    public List<Ticket> getTickets(int userId) throws Exception {
        List<Ticket> tickets = new ArrayList();
        open();
        preparedStatement = connect.prepareStatement("SELECT * FROM Tickets WHERE id_user=?");
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
            tickets.add(new Ticket(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4)
                    )
            );
        close();
        return tickets;
    }

    public void buyTicket(int flightId, int userId) throws Exception {
        open();
        preparedStatement = connect.prepareStatement("INSERT INTO Tickets (id_flight, id_user, price) VALUES (?, ?, ?)");
        preparedStatement.setInt(1, flightId);
        preparedStatement.setInt(2, userId);
        preparedStatement.setInt(3, 50);
        preparedStatement.executeUpdate();
        close();
    }

    public Ticket getTicket(int ticketId) throws Exception {
        Ticket ticket = null;
        open();
        statement = connect.createStatement();
        preparedStatement = connect.prepareStatement("SELECT * FROM Tickets WHERE id=?");
        preparedStatement.setInt(1, ticketId);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
            ticket = new Ticket(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
        close();
        return ticket;
    }

    public void setSoldTickets(int flightId, int amount) throws Exception {
        open();
        preparedStatement = connect.prepareStatement("UPDATE Flights SET `bought_tickets_amount`=? WHERE id=?");
        preparedStatement.setInt(1, amount);
        preparedStatement.setInt(2, flightId);
        preparedStatement.executeUpdate();
        close();
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
