package com.example.applicate.supplier;

import com.example.applicate.catalog.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepo supplierRepo;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public Supplier addSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> list = new ArrayList<>();
        supplierRepo.findAll().forEach(list::add);
        return list;
    }


}
