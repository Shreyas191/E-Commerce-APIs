# E-Commerce-APIs

What's implemented in the code :
1. APIs created to add coupons with type Cart-Wise, Product-Wise and BxGy
2. Implemented using Entity classes, created DTO to handle each and every request.
3. Created Services to handle the logic.
4. Implemented two controller classes one to add coupons and other to retrieve coupons.
5. Added unit test cases.
6. MySQL database is used to save and retrieve data.

What else can be done :
1. Delete mapping and put mapping is not handled in the code, which can be easily implemented.
2. Coupons expiry dates not added which can be added in future.
3. Nested static classes are used in the DTO classes to handle the request and response payload which can be implemented by creating seperate classes and creating object inside the required class instead of creating inner class, which will provide more flexibility to the code.
4. Response entities DTOS needs to be created so that appropriate response along with the HTTP status can be sent.
5. Need to implement authentication and authorization using Spring Security and JWT authentication, so that only authorized users or admins with role specific access can add coupons.
6. Redis cache can be implemented so that frequent calls to the database are reduced significantly.
7. Can add coupons based on the type of products eg(Clothing, Electronics, Footware. Groceries).
8. Referral coupons can be added based on invites to the app.
9. Can implement coupon analytics to understand which coupon is being used the most.
10. Needs to be documented using Open API specifications (Swagger).

Limitations :
1. Frequent calls to the database are made which can reduce the performance and may not be scalable.
2. Discount types are too limited, need to implement more discount types.
3. Errors are needed to be handled more carefully.

How to overcome limitations:
1. Microservices architecture can be implemented so that it can be more scalable.
2. Can implement RabbitMQ or apache kafka message queues to update the information about the coupons to all the microservices.
