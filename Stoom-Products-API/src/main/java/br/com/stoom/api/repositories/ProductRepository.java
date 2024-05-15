package br.com.stoom.api.repositories;


import br.com.stoom.api.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    @Query(value = "SELECT * FROM Products WHERE id = ?1 AND fg_inactive = false", nativeQuery = true)
    Optional<ProductEntity> findByProductId(Long id);

    @Query(value = "SELECT * FROM Products WHERE id in (?1) AND fg_inactive = false", nativeQuery = true)
    Optional<List<ProductEntity>> findAllByProductsId(List<Long> idList);

    @Query(value = "SELECT * FROM Products WHERE category = ?1 AND fg_inactive = false", nativeQuery = true)
    Optional<List<ProductEntity>> findByCategory(String category);

    @Query(value = "SELECT * FROM Products WHERE brand_name = ?1 AND fg_inactive = false", nativeQuery = true)
    Optional<List<ProductEntity>> findByBrandName(String brandName);

    @Modifying
    @Query(value = "UPDATE Products p SET p.fg_Inactive = ?2 WHERE brand_name = ?1", nativeQuery = true)
    void inactivateByBrandName(String brandName, String flag);

    @Modifying
    @Query(value = "UPDATE Products p SET p.fg_Inactive = ?2 WHERE category = ?1", nativeQuery = true)
    void inactivateByCategory(String category, String flag);

    @Modifying
    @Query(value = "UPDATE Products SET fg_Inactive = ?2 WHERE id IN (?1)", nativeQuery = true)
    void inactivateByIdList(List<Long> idList, String flag);
}
