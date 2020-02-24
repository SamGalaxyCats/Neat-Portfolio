import java.util.*;
import java.lang.Math;
public class PlayingCardDeck
{
   char spades = 's';
   char diamonds = 'd';
   char clubs = 'c';
   char hearts = 'h';
   int handSize = 7;
   int randy;
   int pDrawnMatches = 0;
   int cDrawnMatches = 0;
   int pFail = 0;
   int cFail = 0;
   boolean fishing = false;
   public ArrayList<Card> deck = new ArrayList<Card>(52);
   public ArrayList<Card> shuffler = new ArrayList<Card>(52);
   public ArrayList<Card> playerHand = new ArrayList<Card>(7);
   public ArrayList<Card> cpuHand = new ArrayList<Card>(7);
   public ArrayList<Card> yourPairs = new ArrayList<Card>();
   public ArrayList<Card> cpuPairs = new ArrayList<Card>();
   Scanner in = new Scanner(System.in);
   //Make a deck of cards.
   public PlayingCardDeck()
   {
      makeOneSuit(spades);
      makeOneSuit(diamonds);
      makeOneSuit(clubs);
      makeOneSuit(hearts);
      shuffle();
      shuffle();
      draw(7, playerHand, yourPairs, "You");
      draw(7, cpuHand, cpuPairs, "The CPU");
   }
      
   public void makeOneSuit (char suitSymbol)
   {
      for(int i = 1; i <= 10; i++)
      {
         deck.add(new Card(suitSymbol, i));
      }
      for(int i = 11; i <= 13; i++)
      {
         deck.add(new Card(suitSymbol, i, true));
      }
   }
   
   //gameplay
   public void pause ()
   {
      String trash = "";
      System.out.println("Type anything to continue.");
      do
      {
         trash = in.nextLine();
      } while (trash.equals(""));
      System.out.println("");
   }
   
   public void removePairs (ArrayList<Card> hand, ArrayList<Card> pairs, String name)
   {
      int matches = 0;
      int matchesMade = 0;
      boolean foundAll = false;
      while(!foundAll)
      {
         for(int i = hand.size() - 1; i > 0; i--)
         {
            for(int j = i - 1; j >= 0; j--)
            {
               if(hand.get(i).getNumber() == hand.get(j).getNumber())
               {
                  matchesMade++;
                  pairs.add(hand.get(i));
                  pairs.add(hand.get(j));
                  hand.remove(i);
                  hand.remove(j);
                  i--;
                  break;
               }
            }
         }
         matches += matchesMade;
         if(matchesMade == 0)
            foundAll = true;
         matchesMade = 0;
      }
      if(matches > 0)
      {
         System.out.println(name + " made " + matches + " new match(es)!");
         if(fishing && (hand == playerHand))
            pDrawnMatches++;
         else if(fishing && (hand == cpuHand))
            cDrawnMatches++;
      }
      
   }
   
   public void cpuTurn()
   {
      System.out.println("\nCPU's turn!");
      boolean fished = false;
      while (!fished && !cpuHand.isEmpty())
      {
         randy = (int)(Math.random() * cpuHand.size());
         int askFor = cpuHand.get(randy).getNumber();
         System.out.println("\"Got any " + askFor + "'s?\"");
         if(isInHand(askFor, playerHand))
         {
            System.out.println("You do!");
            takeCard(findCard(askFor, playerHand), cpuHand, playerHand, cpuPairs, "The CPU");
         }
         else
         {
            fishing = true;
            System.out.println("Go fish!");
            draw(1, cpuHand, cpuPairs, "The CPU");
            fishing = false;
            fished = true;
         }
      }
      pause();
   }
   
   public boolean playerTurn()
   {
      int num = -1;
      String trash = "";
      boolean fished = false;
      boolean legalNum = false;
      if(playerHand.isEmpty())
         return true;
      do
      {
         showPairs();
         displayHand();
         System.out.println("What card (numbers only) will you ask for?");
         if(in.hasNextInt()) //checks whether the user entered a number
         {
            num = in.nextInt();
            if(isInHand(num, playerHand)) //checks the hand for that number
            {
               legalNum = true; //you may proceed if you have the number.
            }
            else
            {
               System.out.println("You don't have that number.");
               in.nextLine();
            }
         }
         else
         {
            System.out.println("A = 1, J = 11, Q = 12, and K = 13, just in case you forgot.");
            trash = in.nextLine();
         }
      } while (!legalNum);
      
      System.out.println("You ask, \"Got any " + num + "'s?\"");
      if(isInHand(num, cpuHand))
      {
         System.out.println("They do!");
         takeCard(findCard(num, cpuHand), playerHand, cpuHand, yourPairs, "You");
      }
      else
      {
         fishing = true;
         System.out.println("Go fish!");
         draw(1, playerHand, yourPairs, "You");
         displayHand();
         fished = false;
         fished = true;
      }
      return fished;
   }
      
   //card motion
   public void shuffle ()
   {
      for(int i = deck.size() - 1; i >= 0; i--)
      {
         randy = (int)(Math.random() * i);
         shuffler.add(deck.get(randy));
         deck.remove(randy);
      }
      for(int i = shuffler.size() - 1; i >= 0; i--)
      {
         randy = (int)(Math.random() * i);
         deck.add(shuffler.get(randy));
         shuffler.remove(randy);
      }
   }
   
   public int findCard(int number, ArrayList<Card> hand)
   {
      int index = -1;
      if (isInHand(number, hand))
      {
         for(int i = hand.size() - 1; i >= 0; i--)
         {
            if(hand.get(i).getNumber() == number)
            {
               index = i;
            }
         }
      }
      return index;
   }
   
   public boolean isInHand(int number, ArrayList<Card> hand)
   {
      boolean hasCard = false;
      for(int i = hand.size() - 1; i >= 0; i--) //originally didn't check the whole hand. Whoops.
      {
         if(hand.get(i).getNumber() == number)
         {
            hasCard = true;
         }
      }
      return hasCard;
   }
   
   public void draw(int drawTo, ArrayList<Card> hand, ArrayList<Card> pairs, String name)
   {
      for(int i = 0; i < drawTo; i++)
      {
         hand.add(deck.get(0));
         deck.remove(0);
      }
      removePairs(hand, pairs, name);
   }
   
   public void takeCard(int index, ArrayList<Card> taker, ArrayList<Card> giver, ArrayList<Card> takerPairs, String name)
   {
      taker.add(giver.get(index));
      giver.remove(index);
      removePairs(taker, takerPairs, name);
   }
   
   public boolean isGameOver ()
   {
      if(playerHand.size() == 0)
         return true;
      else if(cpuHand.size() == 0)
         return true;
      else
         return false;
   }
   
   
      
   //display
   public void displayHand ()
   {
      for(int i = 0; i < playerHand.size(); i++)
      {
         System.out.print(playerHand.get(i).display() + ", ");
      }
      System.out.println("");

   }
   
   public void printDeck ()
   {
      for(int i = 0; i < deck.size(); i++)
      {
         System.out.print(deck.get(i).display());
      }
      System.out.println("\nDeck size: " + deck.size() + "\n");
   }
   
   public void showPairs ()
   {
      System.out.print("Your matches: ");
      for(int i = 0; i < yourPairs.size(); i += 2)
      {
         System.out.print("(" + yourPairs.get(i).display() + ", " + yourPairs.get(i + 1).display() + "), ");
      }
      System.out.println("");
      
      System.out.print("CPU's matches: ");
      for(int i = 0; i < cpuPairs.size(); i += 2)
      {
         System.out.print("(" + cpuPairs.get(i).display() + ", " + cpuPairs.get(i + 1).display() + "), ");
      }
      System.out.println("");
   }
   
   //finalization
   public void postGame ()
   {
      int pScore = yourPairs.size() / 2;
      int cScore = cpuPairs.size() / 2;
      String theEmptyHand = "";
      if(playerHand.isEmpty())
      {
         theEmptyHand = "\nYou";
      }
      else if(cpuHand.isEmpty())
      {
         theEmptyHand = "\nThe computer";
      }
      System.out.println(theEmptyHand + " ran out of cards! The game is over.");
      
      System.out.println("Final scores:\nThe CPU made " + cScore + " match(es).");
      System.out.println("You made " + pScore + " match(es).");
      if(pScore == 0)
      {
         System.out.println("===Achievement: Robbed Blind===");
      }
      else if(cScore == 0)
         System.out.println("===Achievement: Competitive Fisher===");
      else if(pScore <= (cScore - 5))
         System.out.println("+++Achievement: Demolished+++");
      else if(cScore <= (pScore - 5))
         System.out.println("+++Achievement: Pro Fish+++");
      else if(cScore == (pScore - 1))
         System.out.println(">>>Achievement: Intense Win>>>");
      else if(pScore == (cScore - 1))
         System.out.println("<<<Achievement: Oh no!<<<");
         
      if((pScore + cScore) <= 7)
         System.out.println("---Achievement: Lightning Round---");
      else if((cScore + pScore) <= 9)
         System.out.println("---Achievement: Speedrun---");
      else if((cScore + pScore) >= 25)
         System.out.println("~~~Achievement: Marathon~~~");
         
      if((pDrawnMatches >= (pScore / 2)) &&  (cDrawnMatches >= (cScore / 2)))
         System.out.println("***Achievement: Lucky catch!***");
      else if(pDrawnMatches >= ((3*pScore) / 4))
         System.out.println("***Achievement: Best friends with the draw pile***");
      else if(cDrawnMatches >= ((3*cScore) / 4))
         System.out.println("***Achievement: The CPU can't guess but it sure can draw!***");
         
         
      if(pScore > cScore)
         System.out.println("You win! Congrats!");
      else if(pScore < cScore)
         System.out.println("You lost! Good game!");   
      else if(pScore == cScore)
         System.out.println("You tied! Everyone wins!");
      else
         System.out.println("How did you get this message? If you're seeing this, that means something is really broken.");
   }
   
   //cheats
   public void checkCPUHand ()
   {
      for(int i = 0; i < cpuHand.size(); i++)
      {
         System.out.print(cpuHand.get(i).display() + ", ");
      }
      System.out.println("");
   }
   
}