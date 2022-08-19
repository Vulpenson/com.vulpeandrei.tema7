## Vulpe Andrei - Tema 7 - 12.08.2022
-------------------------------------------------------------------------------
#### FIRST STAGE: 

The goal of this homework is to implement a small API for handling 
products in a store. In the app you can store products, each one of them having 
a name, a type (ELB, ELG, ELC) and a stock. Also, each product has a deleted 
field for doing a soft deletion (not deleting the product from the database but 
turning this field true, so we can still get the deleted products from the 
database).

#### SECOND STAGE: 

The goal of this homework is to implement unit test for each 
controller and service. 
-------------------------------------------------------------------------------
#### FIRST STAGE:

Requirements:
• Add a new product (name, id, initial stock)
• Delete a product (soft deletion)
• Get all products 
• Get all products (even the deleted products)
• Update the stock (supplementing or decreasing the stock)
• Increment the stock by 1 
• Decrement the stock by 1 

#### SECOND STAGE:

Requirements:
• Unit test for each controller (at least one method)
• Unit test for each service (at least one method)
-------------------------------------------------------------------------------
#### Implementation:
#### The project contains the packages (FIRST STAGE):
- Application
- Controllers
- DTO
- Exception
- Mapper
- Model
- Repository
- Service

#### In the SECOND STAGE there has been added Test Classes in the test subfolder:
- ProductControllerTest
- ProductServiceTest
-------------------------------------------------------------------------------
