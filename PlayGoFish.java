import java.lang.Math;
import java.util.*;
public class PlayGoFish
{
   PlayingCardDeck cards = new PlayingCardDeck();
   Scanner in = new Scanner(System.in);
   int matchesFound;
   int pairs = 0;
   int cpuPairs = 0;
   
   
   //this is where the gameplay actually happens.
   public PlayGoFish ()
   {
      cards.shuffle();
      cards.shuffle();
   }
   
   public void playGame ()
   {
      boolean done = false;
      boolean gameOver = false;
      do
      {
         while (!done)
         {
            done = cards.playerTurn();
         }
         done = false;
         if(cards.isGameOver())
         {
            gameOver = true;
            break;
         }
         cards.cpuTurn();
         if(cards.isGameOver())
         {
            gameOver = true;
            break;
         } 
      }while(!gameOver);
      cards.postGame();
   }
   
   
}