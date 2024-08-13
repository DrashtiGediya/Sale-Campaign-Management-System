package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

//@Data
@Entity
@Table(name = "tbl_pricing_history")
public class PricingHistory {
    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long historyId;
    @Column(name = "old_price")
    private double oldPrice;
    @Column(name = "date")
    private Date date;
    @Column(name = "discount")
    private double discount;
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    @JsonManagedReference
    private Product fkProductId;

    public PricingHistory(long historyId, double oldPrice, Date date, double discount, Product fkProductId) {
        this.historyId = historyId;
        this.oldPrice = oldPrice;
        this.date = date;
        this.discount = discount;
        this.fkProductId = fkProductId;
    }

    public PricingHistory() {
    }

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Product getFkProductId() {
        return fkProductId;
    }

    public void setFkProductId(Product fkProductId) {
        this.fkProductId = fkProductId;
    }
}
