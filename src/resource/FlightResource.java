package resource;

import model.Flight;
import model.SearchFlight;
import model.Ticket;
import service.FlightService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("flights")
public class FlightResource {

    FlightService flightService = new FlightService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getFlights() throws Exception {
        return flightService.getAllFlights();
    }

    @GET
    @Path("{flightId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight getFlight(@PathParam("flightId") int id, @Context UriInfo uriInfo) throws Exception {
        Flight newFlight = flightService.getFlight(id);
        String uri = uriInfo.getBaseUriBuilder()
                .path(FlightResource.class)
                .path(String.valueOf(newFlight.getId()))
                .build()
                .toString();
        newFlight.addLink(uri, "self");
        return newFlight;
    }

    @GET
    @Path("client/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTickets(@PathParam("clientId") int clientId) throws Exception {
        return flightService.getTickets(clientId);
    }

    @POST
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Flight> findFlights(SearchFlight searchElement) throws Exception{
        return flightService.findFlights(searchElement);
    }

    @POST
    @Path("client/{clientId}/buy/{flightId}")
    public Flight buyTicket(@PathParam("clientId") int clientId, @PathParam("flightId") int flightId) throws Exception {
        flightService.buyTicket(flightId, clientId);
        Flight flight = flightService.getFlight(flightId);
        flightService.setSoldTickets(flightId, flight.getSoldTickets()+1);
        return flight;
    }
}
