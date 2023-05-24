public class Card {
    private String suit;
    private String value;

    /**
     * Creates a Card object to hold the information for each of the 52 cards
     * @param suit can be: ♦ (diamond), ♥ (heart), ♣ (club), ♠ (spades)
     * @param value can be numerical values 2 - 10, or A(ce), J(ack), Q(ueen), K(ing)
     */
    public Card(String suit, String value){
        this.suit = suit;//
        this.value = value;
    }

    public String getSuit(){
        return suit;
    }

    public String getValue(){
        return value;
    }

    public int getNumbericalValue(){
        if (value.equals("A")){
            return 1;
        }
        if (value.equals("J")){
            return 11;
        }
        if (value.equals("Q")){
            return 12;
        }
        if (value.equals("K")){
            return 13;
        }
        return Integer.parseInt(value);
    }

   public String cardInfo(){
        return getValue() + getSuit();
   }

   public String getColor(){
        if (suit.equals("♦") || suit.equals("♥")){
            return "red";
        }
        return "black";
   }
}
