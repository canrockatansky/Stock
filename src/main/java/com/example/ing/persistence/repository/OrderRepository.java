package com.example.ing.persistence.repository;

import com.example.ing.persistence.model.OrderEntity;
import com.example.ing.persistence.model.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomerId(Long customerId);
    List<OrderEntity> findByDateIsBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("select o.stock from OrderEntity o where o.customer = :customerId and o.status = 'MATCHED'")
    List<StockEntity> customStockSearchByCustomerId(Long customerId);
}
