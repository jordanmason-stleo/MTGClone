import java.util.Scanner;

public class Player {
    protected Deck deck;
    protected String name;
    protected int lifeTotal;
    protected int manaTotal;
    protected int armorTotal;

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(Deck deck, String name) {
        this.deck = deck;
        this.name = name;
        this.lifeTotal = 20;
        this.manaTotal = 0;
        this.armorTotal = 0;
    }

    public boolean playCard(Card card, int currentMana) {
        if (currentMana < card.getManaCost())
            return false;
        currentMana -= card.getManaCost();
        if (currentMana > manaTotal)
            manaTotal = currentMana;
        return true;

    }

    public void turn(Player target, Scanner in) {
        int currentMana = manaTotal;
        int cardUse = 0;
        System.out.println("\n\n\tIt is " + name + "'s turn.");
        deck.drawCard();
        System.out.println("Your health and armor is " + lifeTotal + " and " + armorTotal
                + ". Your opponent's health and armor is " + target.lifeTotal + " and " + target.armorTotal + ".\n");

        do {
            deck.showHand();
            System.out.println("You have " + currentMana + " mana.\n");
            System.out.print("Play your cards. 0 to skip: ");
            cardUse = Integer.parseInt(in.nextLine());

            if (cardUse > 0 && cardUse <= deck.getHand().size()) {
                Card card = deck.getHand().get(cardUse - 1);
                if (playCard(card, currentMana)) {
                    deck.getHand().remove(cardUse - 1);
                    currentMana -= card.manaCost;
                    armorTotal += card.toughness;
                    int damage = card.power;
                    int armor = target.armorTotal;
                    if (target.armorTotal > 0) {
                        target.setArmorTotal(target.getArmorTotal() - damage);
                        damage -= armor;
                        if (target.armorTotal < 0) 
                            target.setArmorTotal(0);
                    }
                    if (damage > 0)
                        target.setLifeTotal(target.getLifeTotal() - damage);

                    if (card.power > 0) {
                        System.out.println("Your opponent's health and armor is " + target.lifeTotal + " and " + target.armorTotal + ".");
                    }
                } else {
                    System.out.println("You do not have enough mana to play that card.");
                }
            } else if (cardUse!=0) {
                System.out.println("Invalid selection.");
            }
        } while (cardUse != 0);

        while (deck.getHand().size() > 7) {
            deck.showHand();
            System.out.print("Discard a card: ");
            cardUse = Integer.parseInt(in.nextLine());

            if (cardUse > 0 && cardUse <= deck.getHand().size())
                deck.getHand().remove(cardUse - 1);
            else
                System.out.println("You've selected an invalid card.");
        }
    }

    @Override
    public String toString() {
        return "Player [armorTotal=" + armorTotal + ", deck=" + deck + ", lifeTotal=" + lifeTotal + ", manaTotal="
                + manaTotal + ", name=" + name + "]";
    }

    public int getLifeTotal() {
        return lifeTotal;
    }

    public void setLifeTotal(int lifeTotal) {
        this.lifeTotal = lifeTotal;
    }

    public int getManaTotal() {
        return manaTotal;
    }

    public void setManaTotal(int manaTotal) {
        this.manaTotal = manaTotal;
    }

    public int getArmorTotal() {
        return armorTotal;
    }

    public void setArmorTotal(int armorTotal) {
        this.armorTotal = armorTotal;
    }

}