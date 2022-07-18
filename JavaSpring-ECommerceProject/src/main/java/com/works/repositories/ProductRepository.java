package com.works.repositories;

import com.works.entities.Product;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("Select p from Product p where p.cid = ?1")
    List<Product> allProCatID(int cid);

    @Query("select p from Product p where p.price > ?1")
    List<Product> findByPriceGreaterThan(double price, Pageable pageable);

    @Query("select p from Product p order by p.price DESC")
    List<Product> findByOrderByPriceDesc(Pageable pageable);

    @Query("select p from Product p order by p.price")
    List<Product> findByOrderByPriceAsc(Pageable pageable);



}
