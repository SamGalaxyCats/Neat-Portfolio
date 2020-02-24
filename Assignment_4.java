import java.util.Scanner;
class Assignment_4 
{
   public static void main(String[] args) 
   {
      Scanner in = new Scanner(System.in);
      System.out.println("Type the message to be shortened");
      String entry = in.nextLine();
      String wthtVwls = "";
      char current;
      char prev = ' ';
      String result = "";
      int vowelCount = 0;
      int unpeatCount = 0;
      do
      {
         if(entry.length() < 10)
         {
            System.out.println("\nThis doesn't need shortening!");
            break;
         } 
         
         entry = entry.toLowerCase();
         for(int i = 0; i <= (entry.length()-1); i++)
         {
            current = entry.charAt(i);
            if(((current != 'a') && (current != 'e') && (current != 'i') && (current != 'o') && (current != 'u')) || (prev == ' '))
            {
               wthtVwls += current;
            }
            else
            {
               vowelCount++;
            }
            prev = current;
         }
         
         for(int i = 0; i < (wthtVwls.length()); i++)
         {
            current = wthtVwls.charAt(i);
            if(current != prev)
            {
               result += current;
            }
            else
            {
               unpeatCount++;
            }
            prev = current;
         }
         
         System.out.println("Shortened message: " + result);
         System.out.println("Repeated letters removed: " + unpeatCount);
         System.out.println("Vowels removed: " + vowelCount);
         System.out.println("Total characters saved: " + (vowelCount + unpeatCount));
      }while(1==0);
   }
}