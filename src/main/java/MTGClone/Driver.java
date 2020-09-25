package MTGClone;
import java.util.Scanner;
public class Driver {
   public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       System.out.print("Enter Player One's name: ");
       String player1Name = in.nextLine();
       System.out.print("Enter Player Two's name: ");
       String player2Name = in.nextLine();
       
       Deck deck=new Deck();
       Deck deck2=new Deck();
        Player player1= new Player(deck, player1Name);
        Player player2= new Player(deck2, player2Name);
        Game game= new Game(player1, player2);
        
        

        while(player1.lifeTotal>=0&&player2.lifeTotal>=0){
            if (game.player1.lifeTotal>0)
                game.Turn(game.player1,game.player2,in);
                System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            if (game.player2.lifeTotal>0)
                game.Turn(game.player2,game.player1,in);
                System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        }
        in.close();
   }
       
   
}