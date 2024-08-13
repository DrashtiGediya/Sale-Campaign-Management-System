package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Controller;


import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.Campaign;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Service.CampaignService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("campaign")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;
    @PostMapping("saveCampaign")
    public void saveCampaign(@RequestBody Campaign campaign){
        campaignService.saveCampaign(campaign);
    }

    @PutMapping("deleteCampaign")
    public void deleteCampaign(@RequestParam Long id){
        campaignService.deleteCampaign(id);
    }

    @GetMapping("getAllCampaign")
    public List<Campaign> getAllCampaign(){
        return campaignService.getAllCampaign();
    }
}
