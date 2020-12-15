package com.example.applicate.catalog;

import com.example.applicate.supplier.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    private final CatalogRepo catalogRepo;
    private final SupplierRepo supplierRepo;

    @Autowired
    public CatalogService(CatalogRepo catalogRepo, SupplierRepo supplierRepo) {
        this.catalogRepo = catalogRepo;
        this.supplierRepo = supplierRepo;
    }

    public Catalog addCatalog(Long supplierid, Catalog catalog) {
        Optional<Catalog> catalogOptional = catalogRepo.findById(catalog.getSkuCode());
        if (catalogOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return supplierRepo.findById(supplierid).map(supplier -> {
            catalog.setSupplier(supplier);
            return catalogRepo.save(catalog);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public Catalog getCatalogBySKUCode(String skucode) {
        return catalogRepo.findById(skucode).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Catalog> getAllCatalogs() {
        List<Catalog> catalogs = new ArrayList<>();
        catalogRepo.findAll().forEach(catalogs::add);
        return catalogs;
    }

    public List<String> getCatalogBySupplierId(Long supplierid) {
        return catalogRepo.findBySupplierId(supplierid)
                .stream()
                .map(Catalog::getSkuName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> getCatalogsByQuery(String suppliername, String query) {
        return catalogRepo.findBySupplierNameAndSkuNameContaining(suppliername, query)
                .stream()
                .map(Catalog::getSkuName)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
