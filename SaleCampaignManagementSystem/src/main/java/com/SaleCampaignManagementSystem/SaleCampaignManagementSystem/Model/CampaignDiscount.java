package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

//@Data
@Entity
@Table(name = "campaign_discount")
public class CampaignDiscount {
    @Id
    @Column(name = "discount_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long discountId;
    @Column(name = "discount")
    private int discount;
    @ManyToOne
    @JoinColumn(name = "fk_campaign_id")
    @JsonBackReference
    private Campaign campaignId;
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    @JsonBackReference
    private Product fkProductId;

    public CampaignDiscount(long discountId) {
        this.discountId = discountId;
    }

    public CampaignDiscount() {
    }

    public CampaignDiscount(long discountId, int discount, Campaign campaignId, Product fkProductId) {
        this.discountId = discountId;
        this.discount = discount;
        this.campaignId = campaignId;
        this.fkProductId = fkProductId;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Campaign getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Campaign campaignId) {
        this.campaignId = campaignId;
    }

    public Product getFkProductId() {
        return fkProductId;
    }

    public void setFkProductId(Product fkProductId) {
        this.fkProductId = fkProductId;
    }
}
