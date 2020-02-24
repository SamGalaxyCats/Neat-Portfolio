public class Card
{
   public char suit;
   public int number;
   public boolean face;
   
   public Card(char suit, int number)
   {
      this.suit = suit;
      this.number = number;
      face = false;
   }
   
   public Card(char suit, int number, boolean face)
   {
      this.suit = suit;
      this.number = number;
      this.face = face;
   }
   
   public char getSuit()
   {
      return suit;
   }
   
   public int getNumber()
   {
      return number;
   }
   
   public boolean isFace()
   {
      return face;
   }
   
   public String display()
   {
      String printable = "";
      if(face)
      {
         if(number == 11)
            printable += 'J';
         else if(number == 12)
            printable += 'Q';
         else if(number == 13)
            printable += 'K';
      }
      else if(number == 1)
      {
         printable += 'A';
      }
      else
      {
         printable += number;
      }
      
      printable += suit;
      return printable; 
   }
}