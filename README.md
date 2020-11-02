# micronaut-injection
Micronaut injection sample project

The StatusController representing the "/_status" endpoint collects all the defined components in the application, add them in the sorted order with their respective status, and overall status of the application.

The test StatusControllerTest creates a test Micronaut Application Context and try to override one of the components with a mocked instance.
