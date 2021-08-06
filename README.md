# joi-energy
1. Entity
2. Repository
3. DTO
4  Factory - to create price plan base on Energy Supplier Type
5. Service to process business logic
6. RESTful API use HTTP method with plural
7. Functional programming(Stream API)
    - immutable
    - no state
    - no side-effect
    - optional with handling of orElse() (Monad)
    - concise
8. Error handling


1. MVP - sending usage data through, convert to a cost and show lowest tariffs
        - readings/read/:smartMeterId
        - modify to filter min reading and return
2. Add on peak and off peak pricing and messaging
    - modify PricePlan to have a state peekOn when calling get price
    - modify PricePlanService to have a method to get PeakPrice
        - if not present -> return as is
        - if present -> return peekPrice: 9xx.00
v3 - Add on Gas and Dual fuel tariffs
       - Modify the PricePlan service to have a method
       - createPricePlan
       - getAllPricePlan
       - Modify PricePlanController with new endpoint