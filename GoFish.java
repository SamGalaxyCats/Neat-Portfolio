import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

//Running this file will start a game of go fish between the user and the computer.

public class GoFish
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      PlayGoFish game = new PlayGoFish();
      game.playGame();
   }
}
