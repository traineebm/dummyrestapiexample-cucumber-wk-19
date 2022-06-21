@SANITY
Feature: Testing the CRUD operations on the Dummy rest api application

  Background: Given  I am on homepage of given application

  Scenario:Create a new employee & verify if the employee is added in records
    When I create a new employee by providing the information name salary and age
    Then I verify that the new employee is created

  Scenario:Updating the employee created and verify if it is updated with valid status code
    When I update the employee with name salary and age
    Then I verify that the employee information is updated

  Scenario:Deleting the employee details created and verify if it is deleted
    When I delete the created employee with id
    Then I verify that the employee is deleted and get the valid status code