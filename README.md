# Distributed-System-for-IT-Product-Store-using-Java-RMI-and-Sockets


This project is a distributed system designed to support an online store for IT products. The system follows a client-server-server architecture, where the client interacts with two servers: the User Management Server and the Inventory Management Server. The application facilitates user authentication, product search, and product purchases.

## Design Decisions and Assumptions

### Technologies
- **Java RMI**: Used for Remote Method Invocation (RMI), enabling communication between applications running on different virtual machines.
- **Swing API**: Used for creating the graphical user interface (GUI) of the application, providing a user-friendly and interactive experience.

### Architecture
- **Client-Server-Server Model**: The client interacts with two servers. The User Management Server handles user-related functions, while the Inventory Management Server handles product-related functions. This separated approach ensures clear responsibility allocation and improves the scalability and maintainability of the system.

### User Authentication
- **Simple Authentication Algorithms**: User credentials are verified against a database on the User Management Server, ensuring security and preventing unauthorized access.

### Data Storage
- **Hash Maps**: Used for storing user and product information, providing quick access and efficient data management.

## Communication Protocol


### User Authentication
- **authenticate(username, password)**: The client sends authentication requests to the User Management Server, which responds with a boolean indicating the success or failure of the authentication.

### Product Search
- **searchProducts(criteria)**: The client sends product search requests with specific criteria to the User Management Server, which returns a list of products matching the criteria.

### Product Purchase
- **purchaseProduct(username, productCode, quantity)**: The client sends product purchase requests to the User Management Server, which responds with a boolean indicating the success or failure of the purchase.

### Inventory Management
- The User Management Server communicates with the Inventory Management Server to manage products using methods such as:
  - **getAvailableProducts()**
  - **purchaseProduct(productCode, quantity)**
- These methods include the necessary parameters, and the Inventory Management Server responds with the relevant data or purchase outcomes.

## Client Application Execution

### Failed Connection Scenario
- When the user enters incorrect credentials or there are network issues, an error message is displayed, informing the user of the connection failure.

  
<div align="center">
    <img src="https://github.com/user-attachments/assets/7ecaa33e-9539-47d8-bc25-a828fca21d86" alt="image" width="600">
</div>

### Successful Connection Scenario for Regular User
- After successful login, the user can choose the options "Search Product" and "Purchase Product."
  - **Search Product**: The user enters search criteria, and the application displays the matching products.

<div align="center">
    <img src="https://github.com/user-attachments/assets/d309f8b4-c0a5-4581-8b58-6924de5c2bbe" alt="image" width="600">
</div>


<div align="center">
    <img src="https://github.com/user-attachments/assets/0f321081-6bbc-4f2c-a7bf-1e7b937e3089" alt="image" width="600">
</div>

  - **Purchase Product**: The user enters the product code and quantity, confirming the purchase. Success or failure messages are displayed accordingly.

<div align="center">
    <img src="https://github.com/user-attachments/assets/417050b4-3f58-47d0-b7ea-efe6c7b77f1a" alt="image" width="600">
</div>

<div align="center">
    <img src="https://github.com/user-attachments/assets/189b6362-a4e8-4ecd-84d2-36cfedc2c711" alt="image" width="600">
</div>


## System Functionality

### User Management Server
- Manages user authentication.
- Communicates with the Inventory Management Server for product-related operations.
- Supports two user categories: store customers and administrators.

### Inventory Management Server
- Manages product inventory.
- Provides product information based on search criteria.
- Handles requests for adding or removing products from administrators.

## Deployment and Execution of the Application

1. **Start the Servers**: Start both the User Management Server and the Inventory Management Server.
2. **Run Client Application**: Run the client application, which interacts with the servers via Java RMI.
