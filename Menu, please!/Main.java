package flashcards;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
 
public class Main 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
 
        Map<String, String> flashCards = new LinkedHashMap<>();
        boolean programOn = true;
 
        while (programOn) 
        {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String uChoice = scanner.nextLine();
 
            switch (uChoice) 
            {
                case "add":
                    addCard(scanner, flashCards);
                    break;
 
                case "remove":
                    System.out.println("The card:");
                    removeCard(flashCards, scanner.nextLine());
                    break;
 
                case "import":
                    System.out.println("File name:");
                    importCards(flashCards, scanner.nextLine());
                    break;
 
                case "export":
                    System.out.println("File name:");
                    export(flashCards, scanner.nextLine());
                    break;
 
                case "ask":
                    System.out.println("How many times to ask?");
                    ask(flashCards, Integer.parseInt(scanner.nextLine()), scanner);
                    break;
 
                case "exit":
                    System.out.println("Bye bye!");
                    programOn = false;
                    break;
 
                default:
                    System.out.println("Unknown action");
                    break;
            }
        }
    }
 
    public static void addCard(Scanner scanner, Map<String, String> flashCards) 
    {
 
            System.out.println("The card:");
            String term = scanner.nextLine();
 
            if (flashCards.containsKey(term))
            {
                System.out.println("The card " + "\"" + term + "\" already exists");
 
            } 
            else 
            {
                System.out.println("The definition of the card:");
                String definition = scanner.nextLine();
 
                if (flashCards.containsValue(definition)) 
                {
                    System.out.println("The definition "  + "\"" + definition + "\" already exists.");
                } 
                else 
                {
                    flashCards.put(term, definition);
                    System.out.println("The pair (" + "\"" + term + "\":" + "\"" + definition + "\") has been added." );
                }
            }
    }
 
    public static void removeCard(Map<String, String> flashCards, String cardToRemove) 
    {
        if (flashCards.containsKey(cardToRemove)) 
        {
            flashCards.remove(cardToRemove);
            System.out.println("The card has been removed.");
        } 
        else 
        {
            System.out.println("Can't remove " + "\"" + cardToRemove + "\": there is no such card");
        }
    }
 
    public static void importCards(Map<String, String> flashCards, String fileName)
    {
        File file = new File("./" + fileName);
        int count = 0;
 
        try (Scanner scanner = new Scanner(file)) 
        {
            while (scanner.hasNext()) 
            {
                String[] aLine = scanner.nextLine().split(":"); //0 - term, 1 - definition
                flashCards.put(aLine[0], aLine[1]);
                count++;
            }
            System.out.println(count + " cards have been loaded.");
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found.");
        }
    }
 
 
    public static void export(Map<String, String> flashCards, String fileName) 
    {
        File file = new File("./" + fileName);
        int count = 0;
 
        try (PrintWriter printWriter = new PrintWriter(file)) 
        {
            for (var entry:flashCards.entrySet()) 
            {
                printWriter.println(entry.getKey() + ":" + entry.getValue());
                count++;
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        System.out.println(count + " cards have been saved.");
    }
 
 
    public static void ask(Map<String, String> flashCards, int numOfTimes, Scanner scanner)
    {
 
        ArrayList<String> terms = new ArrayList<>(flashCards.keySet());
        Random random = new Random();
 
        for (int i = 0; i < numOfTimes ; i++)
        {
            String randomTerm = terms.get(random.nextInt(terms.size()));
            System.out.println("Print the definition of " + "\"" + randomTerm + "\"");
            String userAnswer = scanner.nextLine();
 
            if (Objects.equals(flashCards.get(randomTerm), userAnswer)) 
            {
                System.out.println("Correct answer.");
 
            } 
            else if (flashCards.containsValue(userAnswer)) 
            {
                flashCards.forEach((aTerm, aDefinition) -> 
                {
                    if (Objects.equals(userAnswer, aDefinition)) 
                    {
                        System.out.println("Wrong answer. The correct one is "  + "\"" + flashCards.get(randomTerm) + "\", you've just written the definition of " + "\"" + aTerm + "\" (ignoring case)");
                    }
                });
 
            } 
            else 
            {
                System.out.println("Wrong answer. The correct one is "  + "\"" + flashCards.get(randomTerm) + "\"");
            }
        }
    }
 
}
