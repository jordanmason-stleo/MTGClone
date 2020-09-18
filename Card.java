public class Card{
    protected String cardName;
    protected int manaCost;
    protected int power;
    protected int toughness;
    protected String description;
    protected String image;
    protected String creatureType;


    
    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatureType() {
        return creatureType;
    }

    public void setCreatureType(String creatureType) {
        this.creatureType = creatureType;
    }

    @Override
    public String toString() {
        return "Card [cardName=" + cardName + ", creatureType=" + creatureType + ", description=" + description
                + ", image=" + image + ", manaCost=" + manaCost + ", power=" + power + ", toughness=" + toughness + "]";
    }

    public Card(String cardName, int manaCost, int power, int toughness, String description, String image,
            String creatureType) {
        this.cardName = cardName;
        this.manaCost = manaCost;
        this.power = power;
        this.toughness = toughness;
        this.description = description;
        this.image = image;
        this.creatureType = creatureType;
    }
    

}