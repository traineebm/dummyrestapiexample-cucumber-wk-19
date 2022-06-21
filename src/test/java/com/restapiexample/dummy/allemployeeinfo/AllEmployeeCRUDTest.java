package com.restapiexample.dummy.allemployeeinfo;

import com.restapiexample.dummy.testbase.TestBase;
import com.restapiexample.dummy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class AllEmployeeCRUDTest extends TestBase {
    static String employee_name = "Mona Singh" + TestUtils.getRandomValue();
    static String employee_salary = "50000";
    static String employee_age="30";
    static int employeeId;

    @Steps
    EmployeeSteps employeeSteps;

    @Title("This will create a new employee")
    @Test
    public void test001() {
        ValidatableResponse response=employeeSteps.createEmployee(employee_name,employee_salary,employee_age);
        response.log().all().statusCode(200);
    }

    @Title("Verify if the employee was added to the application employee_name = Mona Singh")
    @Test
    public void test002() {
        employee_name="Mona Singh";
        HashMap<String, Object> employeeMap = employeeSteps.getEmployeeInfoByEmployeeName(employee_name);
        Assert.assertThat(employeeMap, hasValue(employee_name));
        employeeId = (int) employeeMap.get("id");
        System.out.println(employeeId);
    }
    @Title("Update the employee information and verify the updated information for ID = 9658")
    @Test
    public void test003(){
        employee_name = "Jenette Caldwell";
        employee_salary = "80000";
        employee_age = "40";
        employeeId = 9658;
        employeeSteps.updateEmployee(employeeId,employee_name,employee_salary,employee_age).log().all().statusCode(200);
    }

    @Title("Delete the employee and verify if the employee is deleted! for ID = 9658")
    @Test
    public void test004(){
        employeeId = 9658;
        employeeSteps.deleteEmployee(employeeId).statusCode(200).log().status();
    }
}
