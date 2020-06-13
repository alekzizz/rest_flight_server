//package model;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//
//public class Utils {
//    public static DataHandler generatePDFFromTicket(Ticket ticket) {
//        Document document = new Document();
//        try
//        {
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
//            document.open();
//            document.add(new Paragraph("Potwierdzenie zakupu biletu."));
//            document.add(new Paragraph("Id klienta: " + ticket.getClientId()));
//            document.add(new Paragraph("Id lotu: " + ticket.getFlightId()));
//            document.add(new Paragraph("Cena: " + ticket.getPrice()));
//
//            document.close();
//            writer.close();
//            DataSource fds = new FileDataSource("HelloWorld.pdf");
//            return new DataHandler(fds);
//        } catch (DocumentException | FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
