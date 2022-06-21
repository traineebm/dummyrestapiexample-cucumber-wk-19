package com.restapiexample.dummy.cucumber;

import com.restapiexample.dummy.allemployeeinfo.EmployeeSteps;
import com.restapiexample.dummy.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class EmployeeStepDefs {
    static String employee_name = "Mona Singh" + TestUtils.getRandomValue();
    static String employee_salary = "50000";
    static String employee_age="30";
    static int employeeId;
    static ValidatableResponse response;

    @Steps
    EmployeeSteps employeeSteps;

    @When("^I create a new employee by providing the information name salary and age$")
    public void iCreateANewEmployeeByProvidingTheInformationNameSalaryAndAge() {
        response = employeeSteps.createEmployee(employee_name,employee_salary,employee_age);
        response.log().all().statusCode(200);
    }

    @Then("^I verify that the new employee is created$")
    public void iVerifyThatTheNewEmployeeIsCreated() {
        employee_name = "Mona Singh";
        HashMap<String, Object> employeeMap = employeeSteps.getEmployeeInfoByEmployeeName(employee_name);
        Assert.assertThat(employeeMap, hasValue(employee_name));
        employeeId = (int) employeeMap.get("id");
        System.out.println(employeeId);
    }

    @When("^I update the employee with name salary and age$")
    public void iUpdateTheEmployeeWithNameSalaryAndAge() {
        employee_name = "Jenette Caldwell";
        employee_salary = "80000";
        employee_age = "40";
        employeeId = 9658;
        response = employeeSteps.updateEmployee(employeeId,employee_name,employee_salary,employee_age);
    }

    @Then("^I verify that the employee information is updated$")
    public void iVerifyThatTheEmployeeInformationIsUpdated() {
        response.log().all().statusCode(200);
    }

    @When("^I delete the created employee with id$")
    public void iDeleteTheCreatedEmployeeWithId() {
        employeeId = 9658;
        response = employeeSteps.deleteEmployee(employeeId);
    }

    @Then("^I verify that the employee is deleted and get the valid status code$")
    public void iVerifyThatTheEmployeeIsDeletedAndGetTheValidStatusCode() {
        response.log().all().statusCode(200);
    }
}
