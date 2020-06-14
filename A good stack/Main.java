package flashcards;

import java.util.*;

public class Main
{
    static Scanner sc = new Scanner(System.in);
    static Map<String, String> map = new LinkedHashMap<>();
    static int firstNumberOfCards;

    public static void main(String[] args)
    {
        start();
        cardInput();
        checkCards(map);

    }

    private static void start()
    {
        System.out.println("Enter the number of cards: ");
        firstNumberOfCards = sc.nextInt();
        sc.nextLine();
    }

    private static void cardInput()
    {
        for (int i = 0; i < firstNumberOfCards; i++)
        {
            System.out.println("The term of card #" + (i+1) + ":");

            String term = sc.nextLine();
            while (map.containsKey(term))
            {
                System.out.println("The card \"" + term + "\" already exists. Try again: ");
                term = sc.nextLine();
            }

            System.out.println("The definition of card #" + (i+1) + ":");

            String def = sc.nextLine();
            while (map.containsValue(def))
            {
                System.out.println("The definition \"" + def + "\" already exists. Try again: ");
                def = sc.nextLine();
            }
            map.put(term.toLowerCase(Locale.US), def.toLowerCase(Locale.US));
        }
    }

    private static void checkCards(Map<String, String> map)
    {
        for (String s : map.keySet())
        {
            System.out.println("Print the definition of \"" + s + "\": ");
            String ans = sc.nextLine().toLowerCase(Locale.US);

            if (ans.equals(map.get(s)))
            {
                System.out.println("Correct answer");
            }
            else
            {
                if(map.containsValue(ans))
                {
                    System.out.println("Wrong answer. The correct one is \"" + map.get(s) + "\""
                                        + ", you've just written the definition of \"" + getKey(ans) + "\".");
                }
                else
                {
                    System.out.println("Wrong answer. The correct one is \"" + map.get(s) + "\".");
                }
            }

        }
    }

    public static String getKey (String value)
    {
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            if (entry.getValue().equals(value))
            {
                return entry.getKey();
            }
        }
        return null;
    }



}
