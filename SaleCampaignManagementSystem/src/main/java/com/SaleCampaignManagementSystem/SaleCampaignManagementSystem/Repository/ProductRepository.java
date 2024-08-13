package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Repository;


import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
