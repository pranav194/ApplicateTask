package com.example.applicate;

import com.example.applicate.catalog.Catalog;
import com.example.applicate.catalog.CatalogRepo;
import com.example.applicate.supplier.Supplier;
import com.example.applicate.supplier.SupplierRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicateApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(SupplierRepo supplierRepo, CatalogRepo catalogRepo) throws Exception {
        return (String[] args) -> {
            Supplier supplier1 = new Supplier(1L, "supplierA");
            Supplier supplier2 = new Supplier(2L, "supplierB");
            supplierRepo.save(supplier1);
            supplierRepo.save(supplier2);
            Catalog catalog1 = new Catalog("1", "Haldiram Namkeen", "sample desc1", "brand1", "brand desc", supplier1);
            Catalog catalog2 = new Catalog("2", "Bingo 200 gm", "sample desc2", "brand2", "brand desc", supplier1);
            Catalog catalog3 = new Catalog("3", "Bingo 500 gm", "sample desc3", "brand3", "brand desc", supplier1);
            Catalog catalog4 = new Catalog("4", "Ashirwad Atta", "sample desc4", "brand4", "brand desc", supplier2);
            catalogRepo.save(catalog1);
            catalogRepo.save(catalog2);
            catalogRepo.save(catalog3);
            catalogRepo.save(catalog4);
        };
    }
}
