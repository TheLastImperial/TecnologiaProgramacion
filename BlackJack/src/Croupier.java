import java.util.ArrayList;

public class Croupier extends Person {
    private Deck deck;
    public  Croupier(){
        super("Croupier");
        deck = new Deck(false);
    }
    public void distribute(Person player){
        player.getHand().setCard(deck.nextCard());
    }
    public void firstHand(ArrayList<Player> players){
        for (int i=0; i<2; i++){
            for (int j=0; j<players.size(); j++){
                Player aux = players.get(j);
                distribute(aux);
            }
            distribute(this);
        }
        Card lastCard = getHand().getCard(1);
        lastCard.setVisible(false);
    }
    public void playCroupier(){
        Card lastCard = getHand().getCard(1);
        lastCard.setVisible(true);
        if(!getHand().isBlackJack()){
            while(true){
                ArrayList<Integer> points = getHand().getPoints();
                if(points.size() == 1){
                    int point = points.get(0);
                }
            }
        }
    }
}
