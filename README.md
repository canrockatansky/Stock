# **Stock Order Application**
The application consist of API, Business Layer and Persistance parts. The JWT used for securing the API's. It also contains Swagger file to documentation. 

## Authorization
The application endpoint needs the roles to send requests. This authorization part consist of user creation and get token parts.

**Create User**: /auth/addNewUser (This endpoint creates user to authenticate. Then, you need to get token with your username and password.)

{
"username": "ca",
"password": "ca",
"email": "ca@ca.com",
"roles": "ROLE_USER"
}

**Get Token**: /auth/generateToken (This endpoint returns baerer token to authorize to system. It needs username and password parameters.)

{
"username": "ca",
"password": "ca"
}

## Customer API
The Customer API consist of create, update, delete, getById, withdrawMoney and depositMoney endpoints. For each request, authorization needed.

**Create**: /customer/create (Creates the customers.)

**Update**: /customer/update (Updates the customers.)

**Delete**: /customer/delete (Deletes the customers.)

**GetById**: /customer/{id} (Retrieves customer by Id.)

**Withdraw**: /customer/withdraw/{id}/{amount} (Withdraws money from customer's account.)

**Deposit**: /customer/deposit/{id}/{amount} (Deposit money from customer's account.)


## Stock API
The Stock API consist of create, update, delete and getStockByCustomerId endpoints. For each request, authorization needed.

**Create**: /stock/create (Creates the stock.)

**Update**: /stock/update (Updates the stock.)

**Delete**: /stock/delete (Deletes the stock.)

**getByCustomerId**: /getByCustomerId/{customerId} (Retrieves the stocks with customer Id.)


## Order API
The Order API consist of create, update, cancel, getByGustomerId, getByDate and forceMatched endpoints. For each request, authorization needed. For forceMatched endpoint, ROLE_ADMIN permission needed.

**Create**: /order/create (Creates the order.)

**Update**: /order/update (Updates the stock.)

**Cancel**: /order/cancel (Cancels the order. The record doesn't deletes permenantly, the status changes as CANCELLED.)

**getByGustomerId**: /getByGustomerId/{customerId} (Retrieves the orders by customer Id.)

**getByDate**: /getByDate/{startDate}/{endDate} (Retrieves the orders by date. It needs start and end date as search parameter.)

**forceMatched**: /forceMatched/{id} (Force order to be MATCHED. The pending orders can be matched with force, this endpoint needs ROLE_ADMIN permisson to match the request.)