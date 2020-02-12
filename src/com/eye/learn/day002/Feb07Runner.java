package com.eye.learn.day002;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Feb07Runner {

    public static void main(String args[]) {

        while(true) {
            System.out.println("Enter your choice :");
            System.out.println("1)Palindrome String \t 2)Are anagram Strings\t " +
                    "3)Are parentheses balanced \t 0)Terminate");

            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            switch (n) {

                case 0:
                    System.out.println("Terminating");
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("Enter a String");
                    String s1 = sc.next();
                    System.out.println(isPalindrome(s1));
                    break;

                case 2:
                    System.out.println("Enter two String");
                    String s21 = sc.next();
                    String s22 = sc.next();
                    System.out.println(AreAnagrams(s21, s22));
                    break;

                case 3:
                    System.out.println("Enter a String");
                    String s3 = sc.next();
                    System.out.println(isBalancedParentheses(s3));
                    break;

                default:
                    System.out.println("Incorrect entry. Try Again");
                    break;
            }
        }
    }

    public static boolean isPalindrome(String s) {
        int low = 0;
        int high = s.length() - 1;

        while(low < high) {
            if(s.charAt(low) != s.charAt(high))
                return false;
            low++;
            high--;
        }

        return true;
    }

    public static boolean AreAnagrams(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s1.length(); i++) {
            Character c = s1.charAt(i);
            if(map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for(int i = 0; i < s2.length(); i++) {
            Character c = s2.charAt(i);

            if(map.containsKey(c) && map.get(c) > 0) {
                map.put(c, map.get(c) - 1);
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean isBalancedParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        int i = 0;

        while(i < s.length()) {
            Character currentChar = s.charAt(i);
            if(currentChar == '(' || currentChar == '{' || currentChar == '[') {
                stack.push(currentChar);
            } else if(currentChar == ')') {
                if(stack.isEmpty()) return false;
                Character poppedChar = stack.pop();
                if(poppedChar != '(') return false;
            } else if(currentChar == '}') {
                if(stack.isEmpty()) return false;
                Character poppedChar = stack.pop();
                if(poppedChar != '{') return false;
            } else if(currentChar == ']') {
                if(stack.isEmpty()) return false;
                Character poppedChar = stack.pop();
                if(poppedChar != '[') return false;
            }

            i++;
        }

        if(stack.isEmpty())
            return true;

        return false;
    }

}
