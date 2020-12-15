package com.example.applicate.catalog;

import com.example.applicate.supplier.Supplier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "catalog")
public class Catalog {
    @Id
    private String skuCode;
    private String skuName;
    private String skuDescription;
    private String brandName;
    private String brandDescription;
    //    @Column()
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Supplier supplier;

    public Catalog() {
    }

    public Catalog(String skuCode, String skuName, String skuDescription, String brandName, String brandDescription, Supplier supplier) {
        this(skuCode, skuName, skuDescription, brandName, brandDescription);
        this.supplier = supplier;
    }

    public Catalog(String skuCode, String skuName, String skuDescription, String brandName, String brandDescription) {
        this.skuCode = skuCode;
        this.skuName = skuName;
        this.skuDescription = skuDescription;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuDescription() {
        return skuDescription;
    }

    public void setSkuDescription(String skuDescription) {
        this.skuDescription = skuDescription;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "skuCode='" + skuCode + '\'' +
                ", skuName='" + skuName + '\'' +
                ", skuDescription='" + skuDescription + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandDescription='" + brandDescription + '\'' +
                ", supplier=" + supplier +
                '}';
    }
}
