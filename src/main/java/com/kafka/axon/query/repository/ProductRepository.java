package com.kafka.axon.query.repository;

import com.kafka.axon.query.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
