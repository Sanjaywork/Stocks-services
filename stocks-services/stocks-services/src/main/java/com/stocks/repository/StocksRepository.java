package com.stocks.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.entity.StocksEntity;

@Repository
public interface StocksRepository extends JpaRepository<StocksEntity, String>
{
	List<StocksEntity> findByStock(String stock);
}
