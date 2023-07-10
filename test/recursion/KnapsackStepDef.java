package recursion;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import util.StepDefUtil;

import java.util.List;

public class KnapsackStepDef {

    List<Integer> args, result;
    int target;

    @Given("a list containing {string}")
    public void aListContainingNumbersAndATarget(String args)
    {
        this.args = StepDefUtil.getCommaSeparatedIntegerList(args);
    }

    @And("the target {int}")
    public void theTargetTarget(int target)
    {
        this.target = target;
    }

    @When("I execute the recursive knapsack algorithm")
    public void iExecuteTheRecursiveAlgorithm()
    {
        result = Knapsack.knapsack(args, target);
    }


    @Then("I should have {string} as my result")
    public void iShouldHaveAsMyResult(String expected)
    {
        Assert.assertFalse("Results set is empty", result.isEmpty());
        List<Integer> expectedList = StepDefUtil.getCommaSeparatedIntegerList(expected);
        Assert.assertTrue(String.format("Expected %s but got %s", expectedList, result),
                          CollectionUtils.isEqualCollection(result, expectedList));
    }

    @Then("I should have no result")
    public void iShouldHaveNoResult()
    {
        Assert.assertTrue("Results set is not empty:\t" + result, result.isEmpty());
    }

}
