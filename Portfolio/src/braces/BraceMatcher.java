package braces;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;
import java.util.Stack;

public class BraceMatcher {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();

        try {
            URL path = BraceMatcher.class.getResource("data.txt");
            if (path != null) {
                File inFile = new File(path.getFile());
                Scanner reader = new Scanner(inFile);

                while (reader.hasNext()) {
                    String line = reader.nextLine();
                    result.append(braceMatcher(line));
                }
            }
            System.out.println(result);
            //System.out.println("Test passed: " + isTestPassed(result.toString()));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static String braceMatcher(String line) {
        Stack<Character> charStack = new Stack<Character>();
        String result = "";
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            char temp;

            if (isOpenChar(c)) {
                charStack.push(c);
            } else if (isCloseChar(c)) {
                temp = charStack.peek();
                if (isValidPair(temp, c)) {
                    charStack.pop();
                } else {
                    result = "0 ";
                }
            }
            if (charStack.isEmpty()) {
                result = "1 ";
            } else {
                result = "0 ";
            }
        }
        return result;
    }

    private static boolean isOpenChar(char c) {
        return (c == '[' || c == '{' || c == '(' || c == '<');
    }

    private static boolean isCloseChar(char c) {
        return (c == ']' || c == '}' || c == ')' || c == '>');
    }

    private static boolean isValidPair(char open, char close) {
        return (open == '[' && close == ']') || (open == '{' && close == '}')
                || (open == '(' && close == ')') || (open == '<' && close == '>');
    }
    // private static boolean isTestPassed(String result) {
    //     String expectedResult = "1 0 1 1 0 0 0 1 ";
    //     //System.out.println(expectedResult);
    //     return result.equals(expectedResult);
    // }
}
