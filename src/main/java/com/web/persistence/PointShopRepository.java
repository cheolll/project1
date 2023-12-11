package com.web.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.PointShop;



public interface PointShopRepository extends JpaRepository<PointShop, Long> {

}