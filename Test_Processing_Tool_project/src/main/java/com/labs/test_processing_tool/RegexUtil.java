package com.labs.test_processing_tool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static String findMatches(String pattern, String text){
        StringBuilder results = new StringBuilder();
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);

        while (matcher.find()){
            results.append("Match: ").append(matcher.group()).append(" at position ").append(matcher.start()).append("\n");
        }
        return results.length() > 0 ? results.toString() : "No matches found";
    }

    public static String replaceText(String pattern, String text, String replacement){
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);
        return matcher.replaceAll(replacement);
    }

    // for finding match
     public static boolean isMatch(String pattern, String text)
     {
         if(pattern == null || text == null){
             return false;
         }
         return Pattern.matches(pattern, text);
     }

    public static void main(String[] args){
        String retunString = findMatches("xbx","texbx");

        String replace = replaceText("[^abc]", "Are you back", "cba");
        System.out.println(replace);
    }


}
