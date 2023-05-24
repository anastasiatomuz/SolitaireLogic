import java.util.ArrayList;

public class SolitaireGame {
    private TableauStack[] tableau;
    private Foundation clubFoundation;
    private Foundation spadeFoundation;
    private Foundation heartFoundation;
    private Foundation diamondFoundation;
    private ArrayList<Card> stock;
    private ArrayList<Card> waste;
    private Card topWasteCard;


    public void play(){
        startUp();
        printGame();
    }

    private void startUp(){
        diamondFoundation = new Foundation("diamond");
        heartFoundation = new Foundation("heart");
        clubFoundation = new Foundation("club");
        spadeFoundation = new Foundation("spade");
        //generate a full deck of cards
        ArrayList<Card> orderedDeck = new ArrayList<>();
        for (int i = 1; i <= 4; i ++){
            String suit;
            if (i == 1){
                suit = "D";
            } else if (i == 2){
                suit = "H";
            } else if (i == 3){
                suit = "C";
            } else {
                suit = "S";
            }
            String value;

            for (int j = 1; j <= 13; j ++){
                if (j == 1){
                    value = "A"; //Ace
                } else if (j == 11){
                    value = "J";
                } else if (j == 12){
                    value = "Q";
                } else if (j == 13){
                    value = "K";
                } else {
                    value = "" + j;
                }

                orderedDeck.add(new Card(suit, value, true));
            }
        }

        for (Card card : orderedDeck){
            System.out.println(card.cardInfo());
        }

        //shuffle deck
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        while (orderedDeck.size() > 0){
            int randInd = (int) (Math.random() * orderedDeck.size());
            shuffledDeck.add(orderedDeck.remove(randInd));
        }



        //set up the tableau stacks
        tableau = new TableauStack[7];
        for (int i = 1; i <= 7; i ++){
            ArrayList<Card> stackToAdd = new ArrayList<>();
            for (int j = 0; j < i; j ++){
                Card cardToMove = shuffledDeck.remove(shuffledDeck.size() - 1);
                if (j < i - 1){
                    //makes the cards that aren't the last one in tableau stack are not visible
                    cardToMove.flip();
                }
                stackToAdd.add(cardToMove);
            }
            tableau[i - 1] = new TableauStack(stackToAdd);
        }
    }

    private void printGame(){
        System.out.print("Fountains:\n");
        System.out.print("Diamond: ");
        System.out.println(displayStack(diamondFoundation.getFoundationStack()));
        System.out.print("Heart: ");
        System.out.println(displayStack(heartFoundation.getFoundationStack()));
        System.out.print("Club: ");
        System.out.println(displayStack(clubFoundation.getFoundationStack()));
        System.out.print("Spade:  ");
        System.out.println(displayStack(spadeFoundation.getFoundationStack()));

        System.out.print("\nWaste: ");
        if (topWasteCard != null){
            System.out.println(topWasteCard.cardInfo());
        }

        System.out.println("\nTableau: ");
        for (int i = 0; i < tableau.length; i ++){
            System.out.println(displayStack(tableau[i].getStack()));
        }

    }

    public String displayStack(ArrayList<Card> stackToDisplay){
        if (stackToDisplay.size() == 0){
           return "currently empty";
        }

        String toDisplay = "";
        for (Card card : stackToDisplay){
            if (card.isVisible()){
                toDisplay += card.cardInfo() + " ";
            } else {
                toDisplay += "XX  ";
            }
        }

        return toDisplay;
    }

}
