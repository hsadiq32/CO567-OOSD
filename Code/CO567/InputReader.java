import java.util.Scanner;
import java.util.regex.*;

/**
 * InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is returned.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @modified Haroon Sadiq
 * @version    0.1 (2016.02.29)
 */
public class InputReader
{
    private Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Read a line of text from standard input (the text terminal),
     * and return it as a String.
     *
     * @return  A String typed by the user.
     */
    public String getInput(String prompt)
    {
        System.out.print(prompt);         // print prompt
        String inputLine = reader.nextLine();
        return inputLine;
    }

    /**
     * Prompt the user to enter a string and Read a line of text
     * from standard input (the text terminal), and return a String.
     *
     * @return  A String typed by the user.
     */
    public String getString(String prompt)
    {
        String value = null;
        boolean isValid = false;

        while(isValid == false)
        {
            System.out.println(prompt);
            value = reader.nextLine();

            if(value.isBlank() || value.isEmpty())
            {
                System.out.println("|✘| Blank Input");
            }
            else
            {
                isValid = true;
            }
        }

        return value;
    }
    
    public String getStringEmail(String prompt)
    {
        String value = null;
        boolean isValid = false;

        while(isValid == false)
        {
            System.out.println(prompt);
            value = reader.nextLine();
            Pattern pattern = Pattern.compile("^[A-Za-z0-9._]{1,16}+@{1}+[a-z]{1,7}\\.[a-z]{1,3}$");
            Matcher match = pattern.matcher(value);
            if(value.isBlank() || value.isEmpty())
            {
                System.out.println("|✘| Blank Input");
            }
            else
            {
                if (match.find()) 
                {
                    isValid = true;
                } 
                else 
                {
                    System.out.println("|✘| Incorrect Email format");
                }
            }
        }

        return value;
    }


    /**
     * Prompt the user to enter an int
     * Read an int from standard input 
     * (the text terminal), and return it as an int.
     *
     * @return  A String typed by the user.
     */
    public int getInt(String prompt)
    {
        boolean isValid = false;
        int number = 0;

        while(isValid == false)
        {
            System.out.println(prompt);

            try
            {
                number = reader.nextInt();
                isValid = true;
            }
            catch(Exception e)
            {
                System.out.println("|✘| Invalid integer!\n");
            }

        }
        return number;
    }

    /**
     * Display a prompt and Get an integer value between min and max
     * @param prompt the message to the user to enter an int
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @return the integer number
     */
    public int getInt(String prompt, int min, int max)
    {
        boolean isValid = false;
        int number = 0;

        while(isValid == false)
        {
            number = getInt(prompt);
            if(number < min || number > max)
            {
                System.out.println("|✘| Number must be between " + min + " and " + max);
            }
            else
                isValid = true;
        }
        return number;
    }
    
        /**
     * Prompt the user to enter an int
     * Read an int from standard input 
     * (the text terminal), and return it as an int.
     *
     * @return  A String typed by the user.
     */
    public int getHour(String prompt)
    {
        boolean isValid = false;
        int number = 0;

        while(isValid == false)
        {
            System.out.println(prompt);

            try
            {
                number = reader.nextInt();
                if(number >= 0 && number <= 23)
                {
                    isValid = true;
                }
                else
                {
                    System.out.println("|✘| Range of hours is 0-23!\n");
                }
            }
            catch(Exception e)
            {
                System.out.println("|✘| Invalid integer!\n");
            }

        }
        return number;
    }
    
    public void printOptions(String[] options)
    {
        int i = 1;
        for (String option: options) 
        {           
            //Do your stuff here
            System.out.println(i + ". " + option); 
            i++;
        }
    }
}
