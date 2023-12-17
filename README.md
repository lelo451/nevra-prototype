# Domain-Driven Design (DDD)
## Principles

What we’ll do here is divide our application into three layers: application (outside), domain (inside), and infrastructure (outside):
![](img\DDD-Layers.png)
Through the application layer, the user or any other program interacts with the application. This area should contain things like user interfaces, RESTful controllers, and JSON serialization libraries. It includes anything that exposes entry to our application, and orchestrates the execution of domain logic.

In the domain layer, we keep the code that touches and implements business logic. This is the core of our application. This layer should be isolated from both the application part and infrastructure part. In addition, it should also contain interfaces that define the API to communicate with external parts, like the database, which the domain interacts with.

Finally, the infrastructure layer is the part that contains anything that the application needs to work, such as database configuration or Spring configuration. It also implements infrastructure-dependent interfaces from the domain layer.

## Domain Layer
Because the domain layer is completely decoupled from the application and infrastructure layers, we can also test it independently.

## Application Layer

We’ll allow the user to communicate with our application via a RESTful API.

This controller adapts the outside RESTful interface to our domain.

## Infrastructure Layer

The infrastructure layer contains the logic needed to run the application.

## Benefits

The first advantage of this approach is that we separate work for each layer. We can focus on one layer without affecting others.

Furthermore, they’re naturally easier to understand because each of them focuses on its logic.

Another big advantage is that we’ve isolated the domain logic from everything else. The domain part only contains business logic, and can be easily moved to a different environment.

