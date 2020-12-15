package com.example.applicate;

import com.example.applicate.catalog.Catalog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest
class ApplicateApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void initialCatalogsCheck() {
        given().when()
                .get("/catalog/1")
                .then().assertThat()
                .body("supplier.id", equalTo(1));
        given().when().get("/catalog/2").then().assertThat().body("supplier.id", equalTo(1));
        given().when().get("/catalog/3").then().assertThat().body("supplier.id", equalTo(1));
        given().when().get("/catalog/4").then().assertThat().body("supplier.id", equalTo(2));
    }

    @Test
    public void addNewCatalog() {
        given().body(new Catalog("4", "Ashirwad Atta", "sample desc1", "brand1", "brand desc"))
                .contentType("application/json")
                .when().post("/catalog/{supplierid}", 2)
                .then().assertThat().statusCode(400);

        given().body(new Catalog("10", "Ashirwad Atta", "sample desc1", "brand1", "brand desc"))
                .contentType("application/json")
                .when().post("/catalog/{supplierid}", 10)
                .then().assertThat().statusCode(404);
    }

    @Test
    public void getSupplier(){
        given().when().get("/supplier/1")
                .then().assertThat().body("id",equalTo(1));

        given().when().get("/supplier/5")
                .then().assertThat().statusCode(404);
    }

    @Test
    public void getCatalogSKUNameFromSupplierId(){
        given().when().get("/catalog/supplier/1")
                .then().assertThat().body("",hasItems("Haldiram Namkeen","Bingo 200 gm","Bingo 500 gm"));
        given().when().get("/catalog/supplier/2")
                .then().assertThat().body("",hasItems("Ashirwad Atta"));
    }
    @Test
    public void searchCatalogBySupplierNameAndSubstring(){
        given().when().param("suppliername","supplierA").param("query","ing")
                .get("/catalog/search")
                .then()
                .assertThat().body("",hasItems("Bingo 200 gm","Bingo 500 gm"));
    }
}
