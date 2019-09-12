import java.util.ArrayList;

public class Croupier extends Person {
    private Deck deck;
    public  Croupier(){
        super("Croupier");
        deck = new Deck(false);
    }
    public void distribute(Person player){
        Card nextCard = deck.nextCard();
        player.getHand().setCard(nextCard);
    }

    public void firstHand(ArrayList<Player> players){
        deck.shuffle();
        for(int i = 0; i < 2; i++) {
            for(int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
                distribute(players.get(playerIndex));
            }
            distribute(this);
        }
        this.getHand().getCard(1).setVisible(false);
    }

    public void resetDeck(){
        deck = new Deck(false);
    }

    public void playCroupier() {
        Card lastCard = getHand().getCard(1);
        lastCard.setVisible(true);
        if (!getHand().isBlackJack()) {
            while (true) {
                System.out.println(" Mano: " + this.getHand().toString());
                System.out.println(" Puntos: " + this.getHand().getPoints());
                ArrayList<Integer> points = getHand().getPoints();
                // Solo tiene un valor en el arreglo. Porque no tiene un AS
                if (points.size() == 1) {
                    int point = points.get(0);
                    if (point < 17 && !(point > 21)) {
                        distribute(this);

                    } else {
                        break;
                    }
                } else {
                    points.removeIf(ele -> ele > 21);
                    if (points.isEmpty()) {
                        break;
                    }
                    points.removeIf(ele -> ele < 17);
                    if (!points.isEmpty()) {
                        break;
                    } else {
                        distribute(this);
                    }
                }
            }
        }
    }
}
