package recursion;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import util.StepDefUtil;

import java.util.List;

public class TeamCombinationsStepDef {

    private List<String> args, result;
    private int size;

    @Given("a selection set {string}")
    public void aSelectionSet(String input)
    {
        this.args = StepDefUtil.getCommaSeparatedStringList(input);
    }

    @And("a team size {int}")
    public void aTeamSize(int size)
    {
        this.size = size;
    }

    @When("I execute the team combo recursive algorithm")
    public void iExecuteTheTeamComboRecursiveAlgorithm()
    {
        result = TeamCombinations.listAllCombinations(args, size);
    }

    @Then("the result returned is {string}")
    public void theResultReturnedIs(String expected)
    {
        List<String> expectedList = StepDefUtil.getCommaSeparatedStringList(expected);
        Assert.assertTrue(String.format("Expected %s but got %s", expectedList, result),
                          CollectionUtils.isEqualCollection(result, expectedList));
    }

}
