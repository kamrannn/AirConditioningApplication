package org.app.AirConditioningApplication.Service;

import org.app.AirConditioningApplication.Model.Budget;
import org.app.AirConditioningApplication.Repository.BudgetRepo;
import org.app.AirConditioningApplication.Utilities.PdfTable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public ResponseEntity<Object> save(Budget budget) {
        try {
            budgetRepo.save(budget);
            return ResponseEntity.accepted().body(budget);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public ResponseEntity<Object> showAll() {
        try {
            List<Budget> budgetList = budgetRepo.findAll();
            if (!budgetList.isEmpty())
                return ResponseEntity.ok().body(budgetList);
            else
                return ResponseEntity.ok().body("There are no Budgets");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    public ResponseEntity<Object> getById(Long Id) {
        try {
            Optional<Budget> budget = budgetRepo.findById(Id);
            if (budget.isPresent())
                return ResponseEntity.ok().body(budget);
            else return ResponseEntity.ok().body("Invalid Id");
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }


    public ResponseEntity<Object> delete(Long Id) {
        try {
            Optional<Budget> budget = budgetRepo.findById(Id);
            if (budget.isPresent()) {
                budgetRepo.delete(budget.get());
                return ResponseEntity.ok().body("Deleted");
            } else return ResponseEntity.ok().body("Invalid ID");

        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

//    public void ExportToPDF(HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        List<Budget> budgetList = budgetRepo.findAll();
//        PDFExporter exporter = new PDFExporter(budgetList);
//        exporter.export(response);
//    }
    public void pdfcall(){
        PdfTable pdfTable = new PdfTable();
        pdfTable.pdfdownload();
    }
}
