package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Flight implements Serializable {
    private int id;
    private String departureCity;
    private String arrivalCity;
    private String date;
    private String hour;
    private int soldTickets;
    private int allTickets;
    private List<Link> links = new ArrayList<Link>();

    public Flight(int id, String departureCity, String arrivalCity, String date, String hour, int soldTickets, int allTickets){
        this.id = id;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.date = date;
        this.hour = hour;
        this.soldTickets = soldTickets;
        this.allTickets = allTickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }

    public int getAllTickets() {
        return allTickets;
    }

    public void setAllTickets(int allTickets) {
        this.allTickets = allTickets;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        this.links.add(link);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", soldTickets=" + soldTickets +
                ", allTickets=" + allTickets +
                '}';
    }

}
