package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.util.List;

//@Data
@Entity
@Table(name = "tbl_Product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    @Column(name = "product_tile")
    private String productTitle;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "mrp")
    private double mrp;
    @Column(name = "current_price")
    private double currentPrice;
    @Column(name = "discount")
    private double discount;
    @Column(name = "inventory_count")
    private int inventoryCount;
    @OneToMany(mappedBy = "fkProductId",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<PricingHistory> pricingHistoryList;

    @OneToMany(mappedBy = "fkProductId",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CampaignDiscount> campaignDiscounts;

    public long getProductId() {
        return productId;
    }

    public Product() {
    }

    public Product(long productId, String productTitle, String productDescription, double mrp, double currentPrice, double discount, int inventoryCount, List<PricingHistory> pricingHistoryList, List<CampaignDiscount> campaignDiscounts) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.mrp = mrp;
        this.currentPrice = currentPrice;
        this.discount = discount;
        this.inventoryCount = inventoryCount;
        this.pricingHistoryList = pricingHistoryList;
        this.campaignDiscounts = campaignDiscounts;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public List<PricingHistory> getPricingHistoryList() {
        return pricingHistoryList;
    }

    public void setPricingHistoryList(List<PricingHistory> pricingHistoryList) {
        this.pricingHistoryList = pricingHistoryList;
    }

    public List<CampaignDiscount> getCampaignDiscounts() {
        return campaignDiscounts;
    }

    public void setCampaignDiscounts(List<CampaignDiscount> campaignDiscounts) {
        this.campaignDiscounts = campaignDiscounts;
    }

    public Product(long productId) {
        this.productId = productId;
    }
}
