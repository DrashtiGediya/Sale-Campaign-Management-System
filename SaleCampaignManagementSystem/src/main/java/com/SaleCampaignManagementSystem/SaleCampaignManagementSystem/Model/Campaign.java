package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

//@Data
@Entity
@Table(name = "tbl_campaign")
public class Campaign {
    @Id
    @Column(name = "campaign_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long campaignId;
    @Column(name = "campaign_title")
    private String campaignTitle;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "status")
    private String status;
    @JsonManagedReference
    @OneToMany(mappedBy = "campaignId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CampaignDiscount> campaignDiscounts;

    public Campaign(long campaignId) {
        this.campaignId = campaignId;
    }

    public Campaign() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Campaign(long campaignId, String campaignTitle, Date startDate, Date endDate, List<CampaignDiscount> campaignDiscounts,String status) {
        this.campaignId = campaignId;
        this.campaignTitle = campaignTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignDiscounts = campaignDiscounts;
        this.status = status;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<CampaignDiscount> getCampaignDiscounts() {
        return campaignDiscounts;
    }

    public void setCampaignDiscounts(List<CampaignDiscount> campaignDiscounts) {
        this.campaignDiscounts = campaignDiscounts;
    }
}
