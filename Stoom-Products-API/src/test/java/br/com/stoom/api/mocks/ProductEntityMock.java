package br.com.stoom.api.mocks;

import br.com.stoom.api.models.ProductEntity;

import java.time.LocalDate;

public class ProductEntityMock {

    public static ProductEntity getMock() {
        ProductEntity entity = new ProductEntity();
        entity.setId(999L);
        entity.setPrice(3.99F);
        entity.setName("Snowflakes");
        entity.setCategory("cereal");
        entity.setDescription("Corn flour");
        entity.setBrandID(99L);
        entity.setFgInactive(false);
        entity.setBrandName("nestle");
        entity.setRegistrationDate(LocalDate.now());
        entity.setExpirationDate(LocalDate.of(2030,01,01));
        return entity;
    }
}
