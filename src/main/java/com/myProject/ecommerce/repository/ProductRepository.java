package com.myProject.ecommerce.repository;

import com.myProject.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
//public interface ProductRepository extends JpaRepository<Product, Long> { // Used for relational Db using H2 db
//}