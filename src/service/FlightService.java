package service;

import db.MySQLAccess;
import model.Flight;
import model.SearchFlight;
import model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class FlightService {

    private final MySQLAccess dao = new MySQLAccess();

    public FlightService() {
    }

    public List<Flight> getAllFlights() throws Exception {
        return dao.getFlights();
    }

    public List<Flight> findFlights(SearchFlight searchParams) throws Exception {
        String departureCity = null;
        String arrivalCity = null;
        String date = null;

        if (searchParams != null) {
            departureCity = searchParams.getDepartureCity();
            arrivalCity = searchParams.getArrivalCity();
            date = searchParams.getDate();
        }


        List<Flight> flights = dao.getFlights();
        List<Flight> filteredFlights = new ArrayList<>(flights);
        if (departureCity != null) {
            for (Flight flight :
                    flights) {
                if (!flight.getDepartureCity().equals(departureCity))
                    filteredFlights.remove(flight);
            }
        }
        if (arrivalCity != null) {
            for (Flight flight :
                    flights) {
                if (!flight.getArrivalCity().equals(arrivalCity))
                    filteredFlights.remove(flight);
            }
        }
        if (date != null) {
            for (Flight flight :
                    flights) {
                if (!flight.getDate().equals(date))
                    filteredFlights.remove(flight);
            }
        }
        return filteredFlights;
    }

    public Flight getFlight(int id) throws Exception {
        return dao.getFlight(id);
    }

    public List<Ticket> getTickets(int id) throws Exception {
        return dao.getTickets(id);
    }

    public void buyTicket(int flightId, int userId) throws Exception {
        dao.buyTicket(flightId, userId);
    }

    public void setSoldTickets(int flightId, int amount) throws Exception {
        dao.setSoldTickets(flightId, amount);
    }
}
