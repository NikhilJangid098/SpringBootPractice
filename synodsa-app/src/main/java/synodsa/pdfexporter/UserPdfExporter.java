package synodsa.pdfexporter;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import synodsa.entity.UserDetailsEntity;


public class UserPdfExporter {
    private List<UserDetailsEntity> listUsers;

    public UserPdfExporter(List<UserDetailsEntity> listUsers) {
        this.listUsers = listUsers;
    }


    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);

        Paragraph p = new Paragraph("",font);
        p.add(Chunk.NEWLINE);
        p.setAlignment(Paragraph.ALIGN_LEFT);
        for (UserDetailsEntity userDetailsEntity : listUsers) {
            p.add("Id : " + String.valueOf(userDetailsEntity.getId())); p.add(Chunk.NEWLINE);
            p.add("Full Name : " + userDetailsEntity.getFirstName() + " " + userDetailsEntity.getLastName()); p.add(Chunk.NEWLINE);
            p.add("Email : " + userDetailsEntity.getEmail()); p.add(Chunk.NEWLINE);
            p.add("Mobile Number : " + userDetailsEntity.getMobileNumber()); p.add(Chunk.NEWLINE);
            p.add("Aadhar Number : " + userDetailsEntity.getAadharCard()); p.add(Chunk.NEWLINE);
            p.add("City : " + userDetailsEntity.getCity()); p.add(Chunk.NEWLINE);
            p.add("State : " + userDetailsEntity.getState()); p.add(Chunk.NEWLINE);
            p.add("Zip Code : " + userDetailsEntity.getZipCode()); p.add(Chunk.NEWLINE);
            p.add("Type of Employee : " + userDetailsEntity.getTypeOfEmp()); p.add(Chunk.NEWLINE);
            p.add("Salary : " + userDetailsEntity.getSalary()); p.add(Chunk.NEWLINE);
            p.add("Required Loan : " + userDetailsEntity.getRequiredLoan()); p.add(Chunk.NEWLINE);

            p.add(Chunk.NEWLINE);
            p.add(Chunk.NEWLINE);
        }
        document.add(p);
        document.close();
    }
}