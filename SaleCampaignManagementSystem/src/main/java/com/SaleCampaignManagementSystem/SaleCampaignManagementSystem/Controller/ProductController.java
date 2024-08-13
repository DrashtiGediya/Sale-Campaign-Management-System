package com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Controller;


import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Model.Product;
import com.SaleCampaignManagementSystem.SaleCampaignManagementSystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("saleCampaign")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("saveProduct")
    public void saveProduct(@RequestBody List<Product> products){
        productService.saveProducts(products);
    }

    @PutMapping("deleteProduct")
    public void deleteProduct(@RequestParam Long id){
        productService.deleteById(id);
    }

    @GetMapping("getAllProducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("getAllProductsWithSorting")
    public List<Product> getAllProductsWithSorting(@RequestParam String field){
        return productService.getAllProductsWithSorting(field);
    }

    @GetMapping("pagination")
    public Page<Product> getAllProductsWithPagination(@RequestParam int offset,@RequestParam int pageSize){
        return productService.getAllProductWithPagination(offset,pageSize);
    }

    @GetMapping("paginationWithSorting")
    public Page<Product> getAllProductsWithPaginationAndSorting(@RequestParam int offset,@RequestParam int pageSize,@RequestParam String field){
        return productService.getAllProductWithPaginationAndSorting(offset,pageSize,field);
    }
}
