import java.lang.Math;

public class ButtonMashGen
{
   public static void main(String[] args)
   {
      String result = "";
      char translate = 'a';
      int switcher;
      int ascii = 0;
      int rbool = (int)(Math.random() * 2);
      boolean symbol = false;
      boolean number = false;
      int num;
      int alphabet;
      int length = (int)(Math.random() * 6) + 10;
      int chance = 20; //odds that a character will be a non-letter.
      
      //97-122 inclusive for letters 
      //65-90 inclusive for uppercase
      //; = 59
      //' = 39
      //48-57 for numbers
      //64 total potential characters
      
      /*favlorites: aveprakahorrng
        myxpyiirxdm
        rceke2pl'ov0
        joljabogrhjn
        gl;ygixiivymss
        aurafegqjnqt;
        'fgcu'g'';xf
        ouuuknpekwhfmgkn
        gupsupydnoz
        g90yvxyy7olueq3n
        1xklxfzixdc
        2yrsgekjibyvhv
        lcawhfrhddoomzad
      */ 
      
      do 
      {
         switcher = (int)(Math.random() * chance) + 1; //5% chance to get a non-letter.
         rbool = (int)(Math.random() * 2);
      
         switch(switcher)
         {
            case 1: 
               symbol = false;
               number = true;
               break;
            case 2:
               symbol = true;
               number = false;
               break;
            default:
               symbol = false;
               number = false;
         } 
         
         if(!number && !symbol)
         {
            ascii = (int)(Math.random() * 26) + 97;
         }
         else if((!number && symbol) && (rbool == 0))
         {
            ascii = 39;
         }
         else if((!number && symbol) && (rbool == 1))
         {
            ascii = 59;
         }
         else if(number && !symbol)
         {
            ascii = (int)(Math.random() * 10) + 48;
         }
         
         translate = (char)ascii;
         result += translate;
         
      } while (result.length() <= length);
      System.out.println(result);
   }
}