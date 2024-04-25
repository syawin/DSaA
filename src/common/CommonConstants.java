package common;

public interface CommonConstants {
    
    String         LINE_BREAK = "•".repeat(100);
    StringFunction comma      = (s) -> s.replace(",", "")
                                        .replaceAll("\s+", "");
    
    static String formatStr(String str, StringFunction func)
    {
        return func.run(str);
    }
    
}
