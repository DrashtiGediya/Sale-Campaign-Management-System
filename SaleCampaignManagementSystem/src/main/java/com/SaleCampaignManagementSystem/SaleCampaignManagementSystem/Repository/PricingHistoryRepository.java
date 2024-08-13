package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Repository;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.PricingHistory;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface PricingHistoryRepository extends JpaRepository<PricingHistory,Long> {
    @Query(value = "SELECT * FROM tbl_pricing_history WHERE fk_product_id=?1 AND date=?2 ",nativeQuery = true)
    public PricingHistory getOldPrice(@Param("fk_product_id") long productId, @Param("date") Date oldDate);
    
}
