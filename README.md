# Sale-Campaign-Management-System
The Sale Campaign Management System handles a large product catalog and allows dynamic pricing adjustments based on active campaigns. Built with Java and Spring Boot, and utilizing MySQL for data storage, the system provides APIs for product retrieval, campaign management, and historical data querying.


# System Features
Paginated Product API
Efficiently retrieves a subset of products with their current prices. 

Product Information Retrieval
Query all products and their details, including current prices adjusted by any ongoing sales.

Pricing History
Track the historical pricing of each product.

Campaign Information
Retrieve details of all campaigns, including past, current, and upcoming ones.

Campaign Management
Automatically start and end campaigns using @Scheduled tasks in Spring Boot.

# API Usage
Paginated Product API: GET /products?page={page}&pageSize={size}

Create Sale Campaign: POST /campaigns with JSON payload

# Extra APIs
Get Campaign Details: To fetch details of a specific campaign.

Update Product Prices: To apply or remove discounts outside the campaign context.

# Technologies
Language & Framework: Java, Spring Boot

Database: MySQL
