package flashcards;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the number of cards: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] terms = new String[n];
        String[] defs = new String[n];

        for (int i = 0; i < n; i++)
        {
            System.out.println("The term of card #" + (i+1) + ":");
            terms[i] = sc.nextLine();
            System.out.println("The definition of card #" + (i+1) + ":");
            defs[i] = sc.nextLine();
        }

        for (int i = 0; i < n; i++)
        {
            System.out.println("Print the definition of \"" + terms[i] + "\":");
            String inputTerm = sc.nextLine();

            if (inputTerm.equals(defs[i]))
            {
                System.out.println("Correct answer.");
            }
            else
            {
                System.out.println("Wrong answer. The correct one is \"" + defs[i] + "\"");
            }
        }
    }
}
