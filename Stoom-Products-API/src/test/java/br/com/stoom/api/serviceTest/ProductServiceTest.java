package br.com.stoom.api.serviceTest;


import br.com.stoom.api.mocks.ProductEntityMock;
import br.com.stoom.api.repositories.ProductRepository;
import br.com.stoom.api.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService service;

    @Mock
    ProductRepository repository;


    @Test
    public void whenPostingCorrectProductInformation_DoesNotThrowExceptions_ReturnCreated() {
        assertDoesNotThrow(() -> service.postProductInformation(ProductEntityMock.getMock()));
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), service.postProductInformation(ProductEntityMock.getMock()));
    }

    @Test
    public void whenProductAlreadyExist_DoesNotPost_ReturnConflict() {
        when(repository.findByProductId(999L)).thenReturn(Optional.of(ProductEntityMock.getMock()));
        assertEquals(new ResponseEntity<>(HttpStatus.CONFLICT), service.postProductInformation(ProductEntityMock.getMock()));
    }

    @Test
    public void whenGettingProductById_DoesNotThrowExceptions_ReturnAccepted() {
        when(repository.findByProductId(999L)).thenReturn(Optional.of(ProductEntityMock.getMock()));
        assertDoesNotThrow(() -> service.getProductById(ProductEntityMock.getMock().getId()));
    }

    @Test
    public void whenGettingProductByIdDoesNotExist_DoesNotThrowExceptions_ReturnNotFound() {
        when(repository.findByProductId(999L)).thenReturn(Optional.empty());
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                service.getProductById(ProductEntityMock.getMock().getId()));
    }

    @Test
    public void whenGettingProductByCategory_DoesNotThrowExceptions_ReturnAccepted() {
        when(repository.findByCategory("cereal")).thenReturn(Optional.of(List.of(ProductEntityMock.getMock())));
        assertDoesNotThrow(() -> service.getProductByCategory(ProductEntityMock.getMock().getCategory()));
    }

    @Test
    public void whenGettingProductByCategory_DoesNotExist_DoesNotThrowExceptions_ReturnNotFound() {
        when(repository.findByCategory("cereal")).thenReturn(Optional.empty());
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                service.getProductByCategory(ProductEntityMock.getMock().getCategory()));
    }

    @Test
    public void whenGettingProductByBrandName_DoesNotThrowExceptions_ReturnAccepted() {
        when(repository.findByBrandName("nestle")).thenReturn(Optional.of(List.of(ProductEntityMock.getMock())));
        assertDoesNotThrow(() -> service.getProductByBrandName(ProductEntityMock.getMock().getBrandName()));
    }

    @Test
    public void whenGettingProductByBrandName_DoesNotExist_DoesNotThrowExceptions_ReturnNotFound() {
        when(repository.findByBrandName("nestle")).thenReturn(Optional.empty());
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                service.getProductByBrandName(ProductEntityMock.getMock().getBrandName()));
    }


    @Test
    public void whenDeletingProduct_DoesNotThrowExceptions_ReturnAccepted() {
        when(repository.findByProductId(999L)).thenReturn(Optional.of(ProductEntityMock.getMock()));
        assertDoesNotThrow(() -> service.deleteProductInformation(ProductEntityMock.getMock().getId()));
    }

    @Test
    public void whenDeletingProductDoesNotExist_DoesNotThrowExceptions_ReturnNotFound() {
        when(repository.findByProductId(999L)).thenReturn(Optional.empty());
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                service.deleteProductInformation(ProductEntityMock.getMock().getId()));
    }

    @Test
    public void whenUpdatingProduct_DoesNotThrowExceptions_ReturnAccepted() {
        when(repository.findByProductId(999L)).thenReturn(Optional.of(ProductEntityMock.getMock()));
        assertDoesNotThrow(() -> service.updateProductInformation(ProductEntityMock.getMock()));
    }

    @Test
    public void whenUpdatingProductDoesNotExist_DoesNotThrowExceptions_ReturnNotFound() {
        when(repository.findByProductId(999L)).thenReturn(Optional.empty());
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                service.updateProductInformation(ProductEntityMock.getMock()));
    }

    @Test
    public void whenInactivatingProductByBrandName_DoesNotThrowExceptions_ReturnAccepted() {
        when(repository.findByBrandName("nestle")).thenReturn(Optional.of(List.of(ProductEntityMock.getMock())));
        assertDoesNotThrow(() -> service.inactivateByBrandName(ProductEntityMock.getMock().getBrandName(),"true"));
    }

    @Test
    public void whenInactivatingProductByBrandNameDoesNotExist_DoesNotThrowExceptions_ReturnNotFound() {
        when(repository.findByBrandName("nestle")).thenReturn(Optional.empty());
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                service.inactivateByBrandName(ProductEntityMock.getMock().getBrandName(),"true"));
    }

    @Test
    public void whenInactivatingProductByCategory_DoesNotThrowExceptions_ReturnAccepted() {
        when(repository.findByCategory("cereal")).thenReturn(Optional.of(List.of(ProductEntityMock.getMock())));
        assertDoesNotThrow(() -> service.inactivateByCategory(ProductEntityMock.getMock().getCategory(),"true"));
    }

    @Test
    public void whenInactivatingProductByCategoryDoesNotExist_DoesNotThrowExceptions_ReturnNotFound() {
        when(repository.findByCategory("cereal")).thenReturn(Optional.empty());
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                service.inactivateByCategory(ProductEntityMock.getMock().getCategory(),"true"));
    }

    @Test
    public void whenInactivatingProductByIdList_DoesNotThrowExceptions_ReturnAccepted() {
        when(repository.findAllByProductsId(new ArrayList<>(Arrays.asList(1L,2L,3L,4L,5L)))).thenReturn(Optional.of(List.of(ProductEntityMock.getMock())));
        assertDoesNotThrow(() -> service.inactivateByIdList(new ArrayList<>(Arrays.asList(1L,2L,3L,4L,5L)),"true"));
    }

    @Test
    public void whenInactivatingProductByIdListDoesNotExist_DoesNotThrowExceptions_ReturnNotFound() {
        when(repository.findAllByProductsId(new ArrayList<>(Arrays.asList(1L,2L,3L,4L,5L)))).thenReturn(Optional.empty());
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                service.inactivateByIdList(new ArrayList<>(Arrays.asList(1L,2L,3L,4L,5L)),"true"));
    }


}
