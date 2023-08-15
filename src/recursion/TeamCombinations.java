package recursion;

import java.util.Arrays;
import java.util.List;

public class TeamCombinations {

    public static void listAllCombinations(List<String> args, int size)
    {
        doCombo(args, size, "");
    }

    public static void doCombo(List<String> args, int target, String ans)
    {
        /* take args, pull first item off string list
        if string empty, consider it bad input & return back up call stack
        tail-end recursive, pop the answer stack straight to output at the end

        2023-08-07 getting index out of bounds... something to do w/ static method?
        tried w/ char array & String impl, non-static. didn't seem to work.
        2023-08-15 Figured out the solution was two-fold: first was moving empty args array check to AFTER the base
        case; second was not copying the ans arg which was causing problems due to mutation. It seems when doing
        recursions with Java I need to be mindful of the fact that Java is pass-by-reference-value.*/
        if (target < 1) {
            System.out.println(ans);
        }
        else if (args.isEmpty()) {
            return;
        }
        else {
            List<String> slice = args.subList(1, args.size());
            String copy = ans;
            doCombo(slice, target - 1, copy + args.get(0));
            doCombo(slice, target, copy);
        }
    }

    private static class Demo {

        public static void main(String[] args)
        {
            listAllCombinations(Arrays.asList("a", "b", "c", "d", "e"), 3);
        }

    }

}
