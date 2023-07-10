package recursion;

import stack.StackI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Knapsack {

    public static List<Integer> knapsack(List<Integer> list, int target)
    {
        StackI stackI = new StackI(list.size());
        List<Integer> ans = Collections.emptyList();
        if (knapRecur(list, target, stackI)) ans = stackI.popAll();
        return Collections.unmodifiableList(ans);
    }

    public static boolean knapRecur(List<Integer> arr, int target, StackI ans)
    {
        if (arr == null || arr.isEmpty()) {
            return false;
        }
        // while has next, get elem & compare to target
        for (int i = 0; i < arr.size(); i++) {
            int elem = arr.get(i);
            if (elem == target) {
                ans.push(elem);
                return true;
            }
            else if (elem < target) {
                /*
                2023-07-08 — For some reason the "3,4,5" target 10 scenario isn't working. Getting an off-by-one type
                error where sublist is duplicating elements already picked. Yet changing the code below to do a proper
                sublist by increasing index causes the happy path to fail.
                2023-07-09 — FIXED; All I had to do was change sublist call from 1 to i + 1 (hence off-by-one).
                */
                if (knapRecur(arr.subList(i + 1, arr.size()), target - elem, ans.push(elem))) {
                    return true;
                }
                else {
                    ans.pop();
                }
            }
        }
        return false;
    }

    private static class KnapsackDemo {

        public static void main(String[] args)
        {
            System.out.println(knapsack(Arrays.asList(8, 4, 2, 1), 5));
            System.out.println(knapsack(Arrays.asList(1, 2, 8, 4), 5));
            System.out.println(knapsack(Arrays.asList(12, 7, 11, 8, 9, 5), 20));
            System.out.println(knapsack(Arrays.asList(3), 5));
        }

    }

}
