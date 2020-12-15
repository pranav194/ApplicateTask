package com.example.applicate.catalog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepo extends CrudRepository<Catalog, String> {
    List<Catalog> findBySupplierId(Long supplierId);
    //List<Catalog> findBySupplierIdAndSkuNameContaining(Long supplierId,String query);

    List<Catalog> findBySupplierNameAndSkuNameContaining(String supplier_name, String skuName);
}
