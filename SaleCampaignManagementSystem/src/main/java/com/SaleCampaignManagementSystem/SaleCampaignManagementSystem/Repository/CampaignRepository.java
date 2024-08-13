package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Repository;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {

//    @Query(value = "SELECT * FROM tbl_campaign WHERE start_date =:currentDate",nativeQuery = true)
//    public List<Campaign> getAllActiveCampaign(@Param("currentDate")String currentDate);

    @Query(value = "SELECT * FROM tbl_campaign WHERE start_date = current_date()",nativeQuery = true)
    public List<Campaign> getAllActiveCampaign();

    @Query(value = "SELECT * FROM tbl_campaign WHERE end_date = current_date()",nativeQuery = true)
    public List<Campaign> getAllEndCampaign();
}
