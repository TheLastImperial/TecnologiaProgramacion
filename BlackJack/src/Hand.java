import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(){
       cards = new ArrayList<Card>();
    }
    public String toString(){
        String result = "";
        for(Card c:cards) {
            result += c + " | ";
        }
        return result;
    }
    public void setCard(Card card){
        cards.add(card);
    }
    public ArrayList<Integer> getPoints(){
        ArrayList<Integer> points = new ArrayList<Integer>();
        ArrayList<Integer> auxList = new ArrayList<Integer>();
        int tot = 0;
        for (Card c:cards){
            if(c.getValue().getCardValue() == CardValue.AS.getValue()){
                points.add(1);
                auxList.add(11);
            }
            else
                tot+= c.getValue().getCardValue();
        }
        if(points.isEmpty()){
            points.add(tot);
        }
        else{
            points.addAll(auxList);
            for (int i = 0; i< points.size();i++){
                int aux = points.get(i) + tot;
                points.set(i, aux);
            }
        }
        return points;
    }
    public Card getCard(int index){
        return cards.get(index);
    }

    public int maxPoint(){
        int points = 0;
        int asCounter = 0;
        for(Card card: cards) {
            if( card.getValue().getCardValue() == 1) {
                asCounter++;
            } else {
                points += card.getValue().getCardValue();
            }
        }
        for(int i = 1; i <= asCounter; i++) {
            if(points > 10)
                points++;
            else if (points < 11 && i != asCounter)
                points++;
            else
                points += 11;
        }
        return points;
    }

    public boolean isBlackJack(){
        return cards.size() == 2 &&
            (cards.get(0).getValue() == CardValue.AS || cards.get(0).getValue().getCardValue() == 10) &&
            (cards.get(1).getValue() == CardValue.AS || cards.get(1).getValue().getCardValue() == 10);
    }

    public void resetHand(){
        cards = new ArrayList<Card>();
    }
}
