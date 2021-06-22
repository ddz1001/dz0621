Tool Rental project
Author: Dante Zitello
2021


A simple project representing a tool rental service. 
Compiled using maven.

Summary:
    A simple project designed to mimmick a tool rental service similar to what might be
    used by a big box hardware store. The project structure was designed to mirror 
    the style used in Spring. With a few modifications, the provided domain objects, POJOs,
    repositories and services could be used in a Spring project, or any number of frameworks.

Demoing this Project:
    This project includes a main method which demonstrates a basic use case.
    Data is printed to the console.

    There are a number of unit tests which handle a variety of other use cases.
    Specifically, in test.com.ddz.toolrental.service.CheckoutService:
        Test_ChainsawWithDiscount()
        Test_JackhammerWithDiscount_July4th()
        Test_JackhammerWithNoDiscount_July4th()
        Test_JackhammerWithNoDiscount_LaborDay()
        Test_LadderWithDiscount()
        Test_JackhammerWithInvalidDiscount()

    These tests listed above demonstrate how data is manipulated, and what business
    rules are enforeced.

