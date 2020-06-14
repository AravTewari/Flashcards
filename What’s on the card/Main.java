package flashcards;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        String term = sc.nextLine();
        String def = sc.nextLine();
        String ans = sc.nextLine();

        System.out.println("Your answer is " + (ans.equals(def) ? "right!" : "wrong..."));
    }
}
