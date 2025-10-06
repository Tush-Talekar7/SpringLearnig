package com.example.vishwatej.repository;

import com.example.vishwatej.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findBySku(String sku);

    List<Product> findByVendor(String vendorName);

    @Query("select p from Product p where p.price >=:price")
    List<Product> findProdcutsGreaterPrice(Integer price);
}
