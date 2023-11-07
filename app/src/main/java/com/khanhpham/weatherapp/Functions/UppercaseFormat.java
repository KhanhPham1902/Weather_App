package com.khanhpham.weatherapp.Functions;

public class UppercaseFormat {
    public static String getUppercase(String inputText){
        if (inputText == null) {
            return "";
        } else {
            String[] words = inputText.split(" ");
            StringBuilder text = new StringBuilder();
            for (String word : words) {
                if (!word.isEmpty()) {
                    String uppercaseText = word.substring(0, 1).toUpperCase() + word.substring(1);
                    text.append(uppercaseText).append(" ");
                }
            }
            return text.toString().trim();
        }
    }
}
