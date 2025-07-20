package org.example.lambda;

@FunctionalInterface
interface ReverseString {
    String reverse(String str);
}

/*
class ReverseStringImpl implements ReverseString {

    public String reverse(String str) {

       // StringBuilder strbul = new StringBuilder(str);  // with StringBuilder
       // return strbul.reverse().toString();
        String result = "";
        for (int i = str.length()-1; i>=0; i--){
            result += str.charAt(i);
        }
        return result;
    }
} */

public class LambdaMain {

    public static void main(String[] args) {

        String inputString = "My name is Sachin ...";
        //  ReverseString revStr = new ReverseStringImpl();
        //System.out.println("Input String = " + inputString + " ***** Output String (Reverse) = " + revStr.reverse(inputString));

        // Lambda implementation
        ReverseString revStr = (String str) -> {
            String outputString = "";
            for (int i = str.length()-1; i >= 0; i--) {
                outputString += str.charAt(i);
            }
            return outputString;
        };

        System.out.println("Input String = " + inputString + " ***** Output String (Reverse) = " + revStr.reverse(inputString));

    }
}
