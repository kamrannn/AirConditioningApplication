package org.app.AirConditioningApplication.Service;

import org.app.AirConditioningApplication.Model.Supplier;
import org.app.AirConditioningApplication.Repository.SupplierRepo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class SupplierService {
    private final SupplierRepo supplierRepo;

    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public ResponseEntity<Object> save(Supplier supplier) {
        try {
            supplierRepo.save(supplier);
            return ResponseEntity.accepted().body(supplier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public ResponseEntity<Object> showAll() {
        try {
            List<Supplier> supplierList = supplierRepo.findAll();
            if (!supplierList.isEmpty())
                return ResponseEntity.ok().body(supplierList);
            else
                return ResponseEntity.ok().body("There are no suppliers");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    public ResponseEntity<Object> getById(Long Id) {
        try {
            Optional<Supplier> supplier = supplierRepo.findById(Id);
            return ResponseEntity.ok().body(supplier);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }


    public ResponseEntity<Object> delete(Long Id) {
        try {
            Optional<Supplier> supplier = supplierRepo.findById(Id);
            supplierRepo.delete(supplier.get());
            return ResponseEntity.ok().body("Deleted");
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}