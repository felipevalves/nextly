package part2;

import java.util.Scanner;

/**
 *  1 – A palindrome is a word that is symmetric: if we write it backward, the result word is the
 *  same. For example, “HANNAH” is a palindrome, but “GAGA” is not. Write a short program
 *  that determines whether a word is a palindrome.
 */
public class Exercise1 {

    public boolean isPalindrome(String word) {

        if (word == null)
            return false;

        StringBuilder sb = new StringBuilder();

        for (int i = word.length() - 1; i >= 0; i--)
            sb.append(word.charAt(i));

        return word.equals(sb.toString());
    }


    public static void main(String[] args) {

        Exercise1 e = new Exercise1();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a word:");

        String word = scanner.nextLine();

        if (e.isPalindrome(word))
            System.out.println("The word " + word + " is a palindrome");
        else
            System.out.println("The word " + word + " is not a palindrome");

        scanner.close();

    }
}
