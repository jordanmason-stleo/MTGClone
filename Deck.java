import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    protected ArrayList<Card> topdeck;
    protected ArrayList<Card> discard;
    protected ArrayList<Card> hand;

    public Card drawCard(){
        if (topdeck.size()<=0)
            return null;
        Card card=topdeck.get(0);
        topdeck.remove(0);
        hand.add(card);
        return card;
        
    }
    public Deck() {
        SQLDriver d = new SQLDriver();
        d.setupTable();
        topdeck = new ArrayList<Card>();
        hand = new ArrayList<Card>();
        discard = new ArrayList<Card>();
        for (int i = 0; i < 60; i++){
            topdeck.add(d.getRandomCard());
        }
        Shuffle();
        for (int i = 0; i < 7; i++){
            drawCard();
        }
    }
    
    private void Shuffle() {
        Collections.shuffle(topdeck);

    }

    public ArrayList<Card> getTopdeck() {
        return topdeck;
    }

    public void setTopdeck(ArrayList<Card> topdeck) {
        this.topdeck = topdeck;
    }

    public ArrayList<Card> getDiscard() {
        return discard;
    }

    public void setDiscard(ArrayList<Card> discard) {
        this.discard = discard;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Deck [discard=" + discard + ", hand=" + hand + ", topdeck=" + topdeck + "]";
    }

    public void showHand() {
        int maxName = 0;
        int maxType = 0;
        for (Card c : hand) {
            if (c.cardName.length() > maxName) {
                maxName = c.cardName.length();
            }
            if (c.creatureType.length() > maxType) {
                maxType = c.creatureType.length();
            }
        }
        for (int i = 0; i < hand.size(); i++) {
            Card c=hand.get(i);
            System.out.print((i+1) + " " + c.cardName);
            for (int ii = c.cardName.length(); ii < maxName; ii++) {
                System.out.print(" ");
            }
            System.out.print(" (" + c.creatureType + ")");
            for (int ii = c.creatureType.length(); ii < maxType; ii++) {
                System.out.print(" ");
            }
            System.out.print("\t" +(c.manaCost>=0? c.manaCost :0) + "M\t" + c.power + "P\t" + c.toughness + "T\n");
        }
        System.out.println("");
            
    }
}

