import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class AdministerInvoice {
    public AdministerInvoice(){}

    public static void createInvoice(Order order) {
        String name = order.getCustomerName();
        String item = String.valueOf(order.getItem().getItemName());
        String itemCount = String.valueOf(order.getItemCount());
        String price = String.valueOf(order.getTotalPrice());
        String status = order.getStatus();
        String orderMode = order.getMode();

        try {
            String path = ""; //<Desktop file path> + "SS Colecao Invoice.pdf"
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(pdfWriter);
            pdf.addNewPage();
            Document doc = new Document(pdf);
            doc.add(new Paragraph("SS Colecao Invoice"));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Name: " + name));
            doc.add(new Paragraph("Item: " + item));
            doc.add(new Paragraph("Item Count: " + itemCount));
            doc.add(new Paragraph("Price: $" + price));
            doc.add(new Paragraph("Status: " + status));
            doc.add(new Paragraph("Delivery Mode: " + orderMode));
            doc.close();
        }
        catch (Exception e) {
            System.out.println("File cannot be created");
        }
    }
}
