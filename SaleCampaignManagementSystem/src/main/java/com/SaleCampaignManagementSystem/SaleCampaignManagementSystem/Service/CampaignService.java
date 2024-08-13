package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Service;

import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.Campaign;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.CampaignDiscount;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    public void saveCampaign(Campaign campaign){
        List<CampaignDiscount> lst = campaign.getCampaignDiscounts();
        for (CampaignDiscount campaignDiscount : lst) {
            campaignDiscount.setCampaignId(campaign);
        }

        LocalDate currDate = LocalDate.now();
        LocalDate startDate = campaign.getStartDate().toLocalDate();
        LocalDate endDate = campaign.getEndDate().toLocalDate();

        if (currDate.equals(startDate) || currDate.isBefore(endDate) && currDate.isAfter(startDate)){
            campaign.setStatus("Current Campaign");
        }
        else if(endDate.isBefore(currDate)) {
            campaign.setStatus("Past Campaign");
        }
        else if(startDate.isAfter(currDate)){
            campaign.setStatus("Upcoming Campaign");
        }
        campaignRepository.save(campaign);
    }

    public void deleteCampaign(Long id){
        campaignRepository.deleteById(id);
    }

    public List<Campaign> getAllCampaign(){

        return campaignRepository.findAll();
    }


}
