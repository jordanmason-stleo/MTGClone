import java.util.ArrayList; 
public class Game {
    protected Player player1;
    protected Player player2;

    protected ArrayList<Card> player1Field;
    protected ArrayList<Card> player2Field;


    
    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public ArrayList<Card> getPlayer1Field() {
        return player1Field;
    }

    public void setPlayer1Field(ArrayList<Card> player1Field) {
        this.player1Field = player1Field;
    }

    public ArrayList<Card> getPlayer2Field() {
        return player2Field;
    }

    public void setPlayer2Field(ArrayList<Card> player2Field) {
        this.player2Field = player2Field;
    }

    @Override
    public String toString() {
        return "Game [player1=" + player1 + ", player1Field=" + player1Field + ", player2=" + player2
                + ", player2Field=" + player2Field + "]";
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Field=new ArrayList<Card>();
        this.player2Field=new ArrayList<Card>(); 
    }

    public void Turn(Player player,Player target) {
        player.turn(target);
    }
    
}