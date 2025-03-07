package com.example.demo.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextChangeUtil {
    public static void main(String[] args) {
        String text="{\"waterMarks\":[],\"navigationMode\":\"1\",\"appSysIconCode\":\"1729475897594ppt背景.PNG\"}";
        String patternString = "\"navigationMode\":\"(.*?)\""; // 这里的.*?表示非贪婪匹配任意字符
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        String replacedString;
        if (matcher.find()) {
            replacedString = matcher.replaceAll("\"navigationMode\":\"0\"");
        } else {
            replacedString = text;
        }
        System.out.println(replacedString);
    }

    public static String textChange(String text,String source,String target){
        return text.replaceAll(source,target);
    }
}
