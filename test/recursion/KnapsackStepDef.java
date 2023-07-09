package recursion;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KnapsackStepDef {

    List<Integer> args;
    int target;
    List<Integer> result;

    @Given("a list containing {string}")
    public void aListContainingNumbersAndATarget(String args)
    {
        this.args = getIntegerList(args);
    }

    @NotNull
    private static List<Integer> getIntegerList(String args)
    {
        return Arrays.stream(args.split(","))
                     .map(String::trim)
                     .map(Integer::valueOf)
                     .collect(Collectors.toList());
    }

    @And("the target {int}")
    public void theTargetTarget(int target)
    {
        this.target = target;
    }

    @When("I execute the recursive algorithm")
    public void iExecuteTheRecursiveAlgorithm()
    {
        result = Knapsack.knapsack(args, target);
    }


    @Then("I should have {string} as my result")
    public void iShouldHaveAsMyResult(String expected)
    {
        Assert.assertFalse("Results set is empty", result.isEmpty());
        List<Integer> expectedList = getIntegerList(expected);
        Assert.assertTrue(String.format("Expected %s but got %s", expectedList, result),
                          CollectionUtils.isEqualCollection(result,
                                                            expectedList));
    }

    @Then("I should have no result")
    public void iShouldHaveNoResult()
    {
        Assert.assertTrue("Results set is not empty:\t" + result, result.isEmpty());
    }

}
