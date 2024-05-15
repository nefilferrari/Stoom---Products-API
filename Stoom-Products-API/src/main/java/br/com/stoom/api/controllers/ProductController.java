package br.com.stoom.api.controllers;


import br.com.stoom.api.models.ProductEntity;
import br.com.stoom.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/product/post")
    private ResponseEntity<ProductEntity> post (@RequestBody ProductEntity product) {
        return service.postProductInformation(product);
    }

    @GetMapping("/product/getProductById/{id}")
    private ResponseEntity<ProductEntity> getById (@PathVariable Long id) {
        return service.getProductById(id);
    }

    @GetMapping("/product/getProductByBrandName/{brandName}")
    private ResponseEntity<List<ProductEntity>> getByBrandName (@PathVariable String brandName) {
        return service.getProductByBrandName(brandName);
    }

    @GetMapping("/product/getProductByCategory/{category}")
    private ResponseEntity<List<ProductEntity>> getByCategory (@PathVariable String category) {
        return service.getProductByCategory(category);
    }

    @PatchMapping("/product/updateById/{id}")
    private ResponseEntity<ProductEntity> patch (@PathVariable Long id, @RequestBody ProductEntity product) {
        return service.updateProductInformation(product);
    }

    @DeleteMapping("/product/deleteById/{id}")
    private ResponseEntity<ProductEntity> deleteProduct (@PathVariable Long id) {
        return service.deleteProductInformation(id);
    }

    @PatchMapping("/product/inactivateByBrandName/{brandName}")
    private ResponseEntity<ProductEntity> inactivateByBrandName (@PathVariable String brandName) {
        return service.inactivateByBrandName(brandName, "true");
    }

    @PatchMapping("/product/inactivateByCategory/{category}")
    private ResponseEntity<ProductEntity> inactivateByCategory(@PathVariable String category) {
        return service.inactivateByCategory(category, "true");
    }

    @PatchMapping("/product/inactivateByIDs/{idList}")
    private ResponseEntity<ProductEntity> inactivateByIdList (@PathVariable List<Long> idList) {
        return service.inactivateByIdList(idList, "true");
    }

    @PatchMapping("/product/reactivateByBrandName/{brandName}")
    private ResponseEntity<ProductEntity> reactivateByBrandName (@PathVariable String brandName) {
        return service.inactivateByBrandName(brandName, "false");
    }

    @PatchMapping("/product/reactivateByCategory/{category}")
    private ResponseEntity<ProductEntity> reactivateByCategory(@PathVariable String category) {
        return service.inactivateByCategory(category, "false");
    }

    @PatchMapping("/product/reactivateByIDs/{idList}")
    private ResponseEntity<ProductEntity> reactivateByIdList (@PathVariable List<Long> idList) {
        return service.inactivateByIdList(idList, "false");
    }

}
