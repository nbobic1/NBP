package ba.menit.nbp.services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class PdfExportService {

    public ByteArrayInputStream exportDoctorStats(List<Map<String, Object>> stats) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Doctor Statistics Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 5, 3, 3});

            // Header row
            Font headFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Stream.of("Doctor ID", "Name", "Total Patients", "Total Payments")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header, headFont));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBackgroundColor(Color.LIGHT_GRAY);
                        table.addCell(cell);
                    });

            // Totals
            int grandTotalPatients = 0;
            double grandTotalPayments = 0;

            // Data rows
            for (Map<String, Object> row : stats) {
                int patients = Integer.parseInt(row.get("TOTAL_PATIENTS").toString());
                double payments = Double.parseDouble(row.get("TOTAL_PAYMENTS").toString());

                grandTotalPatients += patients;
                grandTotalPayments += payments;

                table.addCell(row.get("DOCTOR_USER_ID").toString());
                table.addCell(row.get("DOCTOR_NAME").toString());
                table.addCell(String.valueOf(patients));
                table.addCell(String.format("%.2f", payments));
            }

            // Total row
            Font totalFont = new Font(Font.HELVETICA, 12, Font.BOLD);

            PdfPCell totalLabel = new PdfPCell(new Phrase("TOTAL", totalFont));
            totalLabel.setColspan(2);
            totalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalLabel);

            PdfPCell totalPatients = new PdfPCell(new Phrase(String.valueOf(grandTotalPatients), totalFont));
            totalPatients.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(totalPatients);

            PdfPCell totalPayments = new PdfPCell(new Phrase(String.format("%.2f", grandTotalPayments), totalFont));
            totalPayments.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(totalPayments);

            document.add(table);
            document.close();
        } catch (DocumentException ex) {
            throw new RuntimeException("Error generating PDF", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}