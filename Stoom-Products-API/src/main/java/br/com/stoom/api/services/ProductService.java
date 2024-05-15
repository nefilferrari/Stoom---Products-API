package br.com.stoom.api.services;


import br.com.stoom.api.models.ProductEntity;
import br.com.stoom.api.repositories.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Log4j2
@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public ResponseEntity<ProductEntity> postProductInformation(ProductEntity product) {
        log.info("Posting product to the database (ID: {})", product.getId());
        Optional<ProductEntity> optionalProduct = repository.findByProductId(product.getId());

        if (optionalProduct.isPresent()) {
            log.error("Product is already in the database! (ID: {})", product.getId());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        repository.save(product);
        log.info("Product was successfully inserted in the database! (ID: {})", product.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<ProductEntity> getProductById(Long id) {
        log.info("Getting product from the database (ID: {})", id);
        Optional<ProductEntity> optionalProduct = repository.findByProductId(id);

        return optionalProduct.map(product -> new ResponseEntity<>(optionalProduct.get(), HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<ProductEntity>> getProductByCategory(String category) {
        log.info("Getting product from the database (Category: {})", category);
        Optional<List<ProductEntity>> optionalProduct = repository.findByCategory(category);

        return optionalProduct.map(product -> new ResponseEntity<>(optionalProduct.get(), HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<ProductEntity>> getProductByBrandName(String brandName) {
        log.info("Getting product from the database (BrandName: {})", brandName);
        Optional<List<ProductEntity>> optionalProduct = repository.findByBrandName(brandName);

        return optionalProduct.map(product -> new ResponseEntity<>(optionalProduct.get(), HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<ProductEntity> deleteProductInformation(Long id) {
        log.info("Deleting product from the database (ID: {})", id);
        Optional<ProductEntity> optionalProduct = repository.findByProductId(id);

        if (optionalProduct.isEmpty()) {
            log.error("Product NOT FOUND! (ID: {})", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        log.info("Product was successfully deleted from the database! (ID: {})", id);
        return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
    }

    public ResponseEntity<ProductEntity> updateProductInformation(ProductEntity product) {
        log.info("Updating product (ID: {})", product.getId());
        Optional<ProductEntity> optionalProduct = repository.findByProductId(product.getId());

        if (optionalProduct.isEmpty()) {
            log.error("Product DOES NOT EXIST in the database! (ID: {})", product.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.save(product);
        log.info("Product was successfully updated! (ID: {})", product.getId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Transactional
    public ResponseEntity<ProductEntity> inactivateByBrandName(String brandName, String flag) {
        log.info("Inactivating products (Brand: {})",brandName);
        Optional<List<ProductEntity>> optionalBrandList = repository.findByBrandName(brandName);

        if (optionalBrandList.isEmpty()) {
            log.error("Product brand name NOT FOUND! (Brand name: {})", brandName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            repository.inactivateByBrandName(brandName,flag);

        } catch (Exception e) {
            log.info("Inactivation failed: {}", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Transactional
    public ResponseEntity<ProductEntity> inactivateByIdList(List<Long> idList, String flag) {
        log.info("Inactivating products (ID's: {})",idList);
        Optional<List<ProductEntity>> optionalList = repository.findAllByProductsId(idList);

        if (optionalList.isEmpty()) {
            log.error("Product ID'S NOT FOUND! (ID's: {})", idList);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            repository.inactivateByIdList(idList, flag);

        } catch (Exception e) {
            log.info("Inactivation failed: {}", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Transactional
    public ResponseEntity<ProductEntity> inactivateByCategory(String category, String flag) {
        log.info("Inactivating products (Category: {})",category);
        Optional<List<ProductEntity>> optionalList = repository.findByCategory(category);

        if (optionalList.isEmpty()) {
            log.error("Product category NOT FOUND! (Category: {})", category);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            repository.inactivateByCategory(category, flag);

        } catch (Exception e) {
            log.info("Inactivation failed: {}", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
