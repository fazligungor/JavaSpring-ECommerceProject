package com.works.repositories;

import com.works.entities.JoinProCat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinCategoryToProductRepository extends JpaRepository<JoinProCat, Integer> {

}
