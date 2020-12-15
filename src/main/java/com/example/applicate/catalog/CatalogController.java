package com.example.applicate.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("/{supplierid}")
    public ResponseEntity<Catalog> addCatalog(@PathVariable Long supplierid, @RequestBody Catalog catalog) {

        Catalog catalog1 = catalogService.addCatalog(supplierid, catalog);
        return ResponseEntity.created(URI.create("/catalog/" + catalog1.getSkuCode())).body(catalog1);
    }

    @GetMapping("{skucode}")
    public ResponseEntity<Catalog> getCatalogBySKUCode(@PathVariable String skucode) {
        Catalog catalog1 = catalogService.getCatalogBySKUCode(skucode);
        return ResponseEntity.ok(catalog1);
    }

    @GetMapping
    public ResponseEntity<List<Catalog>> getAllCatalogs() {
        List<Catalog> catalogs = catalogService.getAllCatalogs();
        return ResponseEntity.ok(catalogs);
    }

    @GetMapping("supplier/{supplierid}")
    public ResponseEntity<List<String>> getAllCatalog(@PathVariable Long supplierid) {
        List<String> catalogs = catalogService.getCatalogBySupplierId(supplierid);
        return ResponseEntity.ok(catalogs);
    }
    @GetMapping("search")
    public ResponseEntity<List<String>> getCatalogs(@RequestParam String suppliername, @RequestParam String query){
        List<String> catalogs = catalogService.getCatalogsByQuery(suppliername, query);
        return ResponseEntity.ok(catalogs);
    }
}
