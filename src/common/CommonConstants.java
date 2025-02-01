package common;

public interface CommonConstants {
    
    String  LINE_BREAK = "•".repeat(100);
    StrFunc comma      = (s) -> s.replace(",", "")
                                 .replaceAll("\s+", "");
    
    static String formatStr(String str, StrFunc func)
    {
        return func.run(str);
    }
    
}
