package util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class StepDefUtil {

    @NotNull
    public static List<Integer> getCommaSeparatedIntegerList(String args)
    {
        return Arrays.stream(args.split(","))
                     .map(String::trim)
                     .map(Integer::valueOf)
                     .collect(Collectors.toList());
    }

    public static List<String> getCommaSeparatedStringList(String args)
    {
        return Arrays.stream(args.split(","))
                     .map(String::trim)
                     .collect(Collectors.toList());
    }

}
