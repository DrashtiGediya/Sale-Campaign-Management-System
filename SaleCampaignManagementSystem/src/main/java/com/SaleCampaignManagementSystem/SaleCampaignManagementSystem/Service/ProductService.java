package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Service;


import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.Campaign;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.CampaignDiscount;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.PricingHistory;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.Product;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Repository.CampaignRepository;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Repository.PricingHistoryRepository;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private PricingHistoryRepository pricingHistoryRepository;
    public void saveProducts(List<Product> products){

        productRepository.saveAll(products);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getAllProductsWithSorting(String field){
        // return productRepository.findAll(Sort.by(field));
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, (field)));
    }

    public Page<Product> getAllProductWithPagination(int offset,int pageSize){
        Page<Product> products =  productRepository.findAll(PageRequest.of(offset,pageSize));
        return products;
    }

    public Page<Product> getAllProductWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Product> products =  productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return products;
    }

    @Scheduled(cron = "0 23 13 * * *")
    public void startCampaign() {
        System.out.println(new java.util.Date());
//        List<Campaign> campaigns = campaignRepository.findAll();
//        List<Campaign> activeCampaigns=new ArrayList<>();
//        for(Campaign campaign : campaigns){
//            if(campaign.getStartDate() == campaignRepository.getAllActiveCampaign()){
//                activeCampaigns.add(campaign);
//            }
//        }
        System.out.println("Campaign Start");
        List<Campaign> activeCampaigns = campaignRepository.getAllActiveCampaign();

        for (Campaign currCampaign : activeCampaigns) {
            currCampaign.setStatus("Current Campaign");
            List<CampaignDiscount> campaignDiscounts = currCampaign.getCampaignDiscounts();
            for (CampaignDiscount campaignDiscount : campaignDiscounts) {
                double discount = campaignDiscount.getDiscount();
                Product product = productRepository.findById(campaignDiscount.getFkProductId().getProductId()).orElseThrow();

                PricingHistory pricingHistory = new PricingHistory();
                pricingHistory.setOldPrice(campaignDiscount.getFkProductId().getCurrentPrice());
                pricingHistory.setDate(currCampaign.getStartDate());
                pricingHistory.setDiscount(product.getDiscount());
                pricingHistory.setFkProductId(campaignDiscount.getFkProductId());

                product.setDiscount(product.getDiscount()+campaignDiscount.getDiscount());
                double currentPrice = product.getCurrentPrice();
                double discountAmount = currentPrice * (discount / 100);
                product.setCurrentPrice(currentPrice - discountAmount);

                pricingHistoryRepository.save(pricingHistory);
                campaignRepository.save(currCampaign);
                productRepository.save(product);

            }
        }
        System.out.println(new java.util.Date());
    }

    @Scheduled(cron = "59 59 23 5 9 *")
    public void endCampaign(){
        System.out.println("Campaign Ended");
        List<Campaign> endCampaigns = campaignRepository.getAllEndCampaign();

        for(Campaign currCampaign : endCampaigns){
            currCampaign.setStatus("Past Campaign");
            List<CampaignDiscount> campaignDiscounts = currCampaign.getCampaignDiscounts();

            for(CampaignDiscount campaignDiscount : campaignDiscounts){
                double discount = campaignDiscount.getDiscount();
                Product product = productRepository.findById(campaignDiscount.getFkProductId().getProductId()).orElseThrow();

                PricingHistory oldInfo = pricingHistoryRepository.getOldPrice(campaignDiscount.getFkProductId().getProductId(),currCampaign.getStartDate());

                PricingHistory pricingHistory = new PricingHistory();
                pricingHistory.setOldPrice(campaignDiscount.getFkProductId().getCurrentPrice());
                pricingHistory.setDate(currCampaign.getEndDate());
                pricingHistory.setDiscount(product.getDiscount());
                pricingHistory.setFkProductId(campaignDiscount.getFkProductId());


                product.setDiscount(oldInfo.getDiscount());
//                double currentPrice = oldInfo.getOldPrice();
//                double discountPrice = currentPrice * (discount/100);
                product.setCurrentPrice(oldInfo.getOldPrice());

                pricingHistoryRepository.save(pricingHistory);
                campaignRepository.save(currCampaign);
                productRepository.save(product);
            }
        }
    }
}
